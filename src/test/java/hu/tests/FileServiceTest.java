package hu.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.lang.reflect.Method;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hu.learnprogramming.App;
import hu.learnprogramming.service.FileService;
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
public class FileServiceTest {
	
	@Autowired
	FileService fileService;
	
	@Value("${photo.upload.directory}")
	private String photoUploadDirectory;
	
	@Test
	public void testGetExtension() throws Exception {
		Method method = FileService.class.getDeclaredMethod("getFileExtension", String.class);
		method.setAccessible(true);
		
		assertEquals("should be png", "png", (String)method.invoke(fileService, "test.png"));
		assertEquals("should be doc", "doc", (String)method.invoke(fileService, "s.doc"));
		assertEquals("should be jpeg", "jpeg", (String)method.invoke(fileService, "file.jpeg"));
		assertNull("should be png", (String)method.invoke(fileService, "xyz"));
	}
	
	@Test
	public void testIsImageExtension() throws Exception {
		Method method = FileService.class.getDeclaredMethod("isImageExtension", String.class);
		method.setAccessible(true);
		
		assertTrue("should be valid png", (Boolean)method.invoke(fileService, "png"));
		assertTrue("should be valid png", (Boolean)method.invoke(fileService, "PNG"));
		assertTrue("should be valid png", (Boolean)method.invoke(fileService, "jpeg"));
		assertTrue("should be valid png", (Boolean)method.invoke(fileService, "GIF"));
		assertFalse("should be invalid png", (Boolean)method.invoke(fileService, "doc"));
		assertFalse("should be invalid png", (Boolean)method.invoke(fileService, "jpg3"));
		assertFalse("should be invalid png", (Boolean)method.invoke(fileService, "gi"));
	}
	
	@Test
	public void testCreateDirectory() throws Exception {
		Method method = FileService.class.getDeclaredMethod("makeSubdirectory", String.class, String.class);
		method.setAccessible(true);
		
		for (int i = 0; i < 10000; i++) {
			File created = (File)method.invoke(fileService, photoUploadDirectory, "photo");
			
			assertTrue("Directory should exist " + created.getAbsolutePath(), created.exists());
		}
	}
}
