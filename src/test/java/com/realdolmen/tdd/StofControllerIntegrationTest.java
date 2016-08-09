package com.realdolmen.tdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TestPropertySource(locations = "classpath:test.properties")
public class StofControllerIntegrationTest {
	
	@Autowired
	StofController controller;
	
	@Autowired
	StofRepository repository;
	
	@Test
	public void testGetStof() {
		Stof stof = new Stof();
		stof.setNaam("Test3");
		stof.setNummer("nummer");
		repository.createStof(stof);
		
		Stof result = controller.getStof(stof.getId());
		
		Assert.assertEquals("Test3", result.getNaam());
		Assert.assertEquals("nummer", result.getNummer());
	}

}
