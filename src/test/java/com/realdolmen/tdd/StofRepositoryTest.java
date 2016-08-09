package com.realdolmen.tdd;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TestPropertySource(locations="classpath:test.properties")
public class StofRepositoryTest {
	
	@Autowired
	StofRepository repository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Before
	public void cleanUp(){
		entityManager.createNativeQuery("DELETE FROM Stof").executeUpdate();
	}
	
	@Transactional
	@Test
	public void testCreateStof(){
		
		
		// 2. Create Stof instance and persist
		Stof stof = new Stof();
		stof.setNaam("Test");
		stof.setNummer("STOF/1/2/3");
		repository.createStof(stof);
		
		// 3. Check if new Stof is persisted
		Assert.assertNotEquals(null, stof.getId());
		
	}
	
	@Transactional
	@Test
	public void testUpdateStof(){
		// Create Stof
		entityManager.createNativeQuery("INSERT INTO Stof(naam, nummer) VALUES('TEST 2', 'TEST/1/2/3')").executeUpdate();
		Stof stof = (Stof) entityManager.createNativeQuery("SELECT * FROM Stof WHERE naam = 'TEST 2'", Stof.class).getSingleResult();
		
		// Change property and persist
		Assert.assertEquals("TEST 2", stof.getNaam());
		stof.setNaam("TEST 3");
		stof.setNummer("STOF/4/3/2");
		repository.updateStof(stof);
		
		// Load Stof again and check
		stof = (Stof) entityManager.createNativeQuery("SELECT * FROM Stof WHERE naam LIKE '%TEST%'", Stof.class).getSingleResult();
		Assert.assertNotEquals(null, stof.getId());
		Assert.assertEquals("TEST 3", stof.getNaam());
		Assert.assertEquals("STOF/4/3/2", stof.getNummer());
		assertStofCount(1);
	}
	
	private void assertStofCount(int count){
		Object result = entityManager.createNativeQuery("SELECT COUNT(1) FROM Stof").getSingleResult();
		Assert.assertEquals(new BigInteger("" + count), result);
	}

}
