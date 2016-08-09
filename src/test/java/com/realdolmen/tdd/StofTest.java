package com.realdolmen.tdd;

import static org.junit.Assert.*;

import org.junit.Test;

public class StofTest {
	
	@Test
	public void testIsValid(){
		//Valid stof
		Stof stof = new Stof();
		stof.setNummer("STOF/1/2/3");
		stof.setNaam("Naam");
		assertEquals(true, stof.isValid());
		
		//Invalid stof
		stof = new Stof();
		assertEquals(false, stof.isValid());
		
		//Invalid stof
		stof = new Stof();
		stof.setNaam("Test");
		stof.setNummer("invalid");
		assertEquals(false, stof.isValid());
	}

}
