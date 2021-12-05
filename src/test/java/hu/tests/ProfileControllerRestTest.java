package hu.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import hu.learnprogramming.App;
import hu.learnprogramming.model.entity.Interest;
import hu.learnprogramming.model.entity.Profile;
import hu.learnprogramming.model.entity.SiteUser;
import hu.learnprogramming.service.InterestService;
import hu.learnprogramming.service.ProfileService;
import hu.learnprogramming.service.UserService;

/*
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
@Transactional
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
//@WebAppConfiguration
@Transactional
public class ProfileControllerRestTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private InterestService interestService; 
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	@WithMockUser(username="test@gmail.com")
	public void testSaveAndDeleteInterest() throws Exception {
		
		String interestText = "some interest_here";
		
		mockMvc.perform(post("/save-interest").param("name", interestText)).
		andExpect(status().isOk());
		
		Interest interest = interestService.get(interestText);
		
		assertNotNull("interest should exist", interest);
		assertEquals("Retrieved interest text should match", interestText, interest.getName());
				
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		SiteUser user = userService.get(email);		
		Profile profile = profileService.getUserProfile(user);
		
		assertTrue("Profile should contain interest", profile.getInterests().contains(new Interest(interestText)));
		
		mockMvc.perform(post("/delete-interest").param("name", interestText)).
		andExpect(status().isOk());
		
		profile = profileService.getUserProfile(user);
		
		assertFalse("Profile should not contain interest", profile.getInterests().contains(new Interest(interestText)));
	}
}
