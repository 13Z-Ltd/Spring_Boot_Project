package hu.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import hu.learnprogramming.App;
import hu.learnprogramming.model.entity.Interest;
import hu.learnprogramming.model.entity.Profile;
import hu.learnprogramming.model.entity.SiteUser;
import hu.learnprogramming.service.InterestService;
//import hu.learnprogramming.service.ProfileService;
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
public class ProfileTest {
	
	@Autowired
	private UserService userService;
	
	//@Autowired
	//private ProfileService profileService;
	
	@Autowired
	private InterestService interestService;
	
	private SiteUser[] users = {
			new SiteUser("bla@gmail.com", "bello", "abc", "bca"),
			new SiteUser("dada@gmail.com", "tello", "abc", "bca"),
			new SiteUser("megefe@gmail.com", "gennyo", "abc", "bca")
	};
	
	private String[][] interests = {
			{"music", "guitar_xxxxx", "plants"},
			{"music", "music", "philosopy_lklklk"},
			{"hockey", "football"}
	};
	
	@Test
	public void testInterests() {
		
		for(int i=0; i<users.length; i++) {
			SiteUser user = users[i];
			String[] interestArray = interests[i];
			
			String name = new Random().ints(10, 0, 10).mapToObj(Integer::toString).collect(Collectors.joining(""));
			user.setEmail(name + "@example.com");
			userService.register(user);
			
			HashSet<Interest> interestSet = new HashSet<>();
			
			for(String interestText: interestArray) {
				Interest interest = interestService.createIfNotExist(interestText);
				interestSet.add(interest);
				
				assertNotNull("Interesrt should not be null", interest);
				assertNotNull("Interest should have ID", interest.getId());
				assertEquals("Text should match", interestText, interest.getName());
			}
			
			Profile profile = new Profile(user);
			profile.setInterests(interestSet);
			//profileService.save(profile);
			
			//Profile retrievedProfile = profileService.getUserProfile(user);
			
			//assertEquals("Interest set should match", interestSet, retrievedProfile.getInterests());
		}
	}
}
