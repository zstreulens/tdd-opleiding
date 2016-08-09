package com.realdolmen.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


public class StofControllerUnitTest {
	
	@InjectMocks
	StofController controller = new StofController();
	
	@Mock
	StofRepository repository;
	
	private List<Stof> repoStoffen = new ArrayList<Stof>();
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		Mockito.when(repository.createStof(Mockito.any(Stof.class))).thenAnswer(new Answer<Stof>() {
			
			@Override
			public Stof answer(InvocationOnMock invocation) throws Throwable {
				Stof stof = invocation.getArgumentAt(0, Stof.class);
				stof.setId(new Random().nextLong());
				repoStoffen.add(stof);
				return stof;
			}
		});
		Mockito.when(repository.getStof(Mockito.anyLong())).then(new Answer<Stof>(){
			@Override
			public Stof answer(InvocationOnMock invocation) throws Throwable{
				Long stofId = invocation.getArgumentAt(0, Long.class);
				for (Stof stof : repoStoffen){
					if (stof.getId().equals(stofId)){
						return stof;
					}
				}				
				return null;
			}
		});
		
		Mockito.when(repository.updateStof(Mockito.any(Stof.class))).then(new Answer<Stof>(){
			@Override
			public Stof answer(InvocationOnMock invocation) throws Throwable {
				Stof update = invocation.getArgumentAt(0, Stof.class);
				Stof stof = repository.getStof(update.getId());
				if (null != stof){
					stof.setNaam(update.getNaam());
					stof.setNummer(update.getNummer());
				}
				return stof;
			}
		});
	}
	
	@Test
	public void testGetStof(){
		// 1. Create stof(...)
		Stof stof = new Stof();
		stof.setNaam("Test");
		stof.setNummer("nummer");
		repository.createStof(stof);
		
		// 2. Get stof via controller
		Stof result = controller.getStof(stof.getId());
		
		// 3. Assertions
		Assert.assertEquals("Test", result.getNaam());
		Assert.assertEquals("nummer", stof.getNummer());
	}
	
	@Test
	public void testCreateStof(){
		// 1. make sure no Stoffen are in repository
		
		// 2. Create Stof
		Stof stof = new Stof();
		stof.setNaam("Test2");
		stof.setNummer("nummerke");
		repository.createStof(stof);
		
		// 3. Check if Stof is in repository
		Stof result = repository.getStof(stof.getId());
		Assert.assertEquals("Test2", result.getNaam());
		Assert.assertEquals("nummerke", result.getNummer());
	}
	
	@Test
	public void testCreateInvalidStof(){
		Stof invalid = new Stof();
		invalid.setNummer("invalid");
		invalid.setNaam("foute stof");
		// When creating, controller should throw exception
		try {
			controller.createStof(invalid);
			Assert.fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e){
			Assert.assertEquals("Invalid nummer", e.getMessage());
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateInvalidNummer(){
		Stof stof1 = new Stof();
		stof1.setNaam("Test 1");
		repository.createStof(stof1);
		Stof stof2 = repository.getStof(stof1.getId());
		stof2.setNaam("Test 2");
		stof2.setNummer("invalid");
		controller.updateStof(stof2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateNonExistingStof(){
		Stof stof1 = new Stof();
		stof1.setNaam("Test 1");
		repository.createStof(stof1);
		Stof stof2 = new Stof();
		stof2.setNaam("Test 2");
		stof2.setId((long) 5464);
		stof2.setNummer("nummerke");
		controller.updateStof(stof2);
	}
	
	@Test
	public void testUpdateStof(){
		Stof stof1 = new Stof();
		stof1.setNaam("Test 1");
		repository.createStof(stof1);
		Stof stof2 = repository.getStof(stof1.getId());
		stof2.setNaam("Test 2");
		controller.updateStof(stof2);
		Stof stof3 = repository.getStof(stof1.getId());
		Assert.assertEquals("Test 2", stof3.getNaam());
		Stof stof4 = new Stof();
		stof4.setId(stof1.getId());
		stof4.setNaam("Stof 4");
		Stof stof5 = controller.updateStof(stof4);
		Assert.assertEquals("Stof 4", stof5.getNaam());
	}
	
	@Test
	public void testDeleteStof(){
		
	}

}
