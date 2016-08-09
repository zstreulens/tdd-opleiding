package com.realdolmen.tdd;

import static org.junit.Assert.*;

import org.junit.Test;

public class StofNummerValidatorTest {
	
	@Test
	public void testValidateStofNummer(){
		StofNummerValidator validator = new StofNummerValidator();
		assertEquals(true, validator.validateStofNummer("STOF/1/2/3"));
		assertEquals(false, validator.validateStofNummer("STOF/2/3"));
		assertEquals(false, validator.validateStofNummer(null));
		assertEquals(true, validator.validateStofNummer("STOF/A/B/C"));
		assertEquals(false, validator.validateStofNummer("STOF/AA/B/C"));
		assertEquals(false, validator.validateStofNummer("STOF/Ã/B/C"));
		assertEquals(false, validator.validateStofNummer("STAF/A/B/C"));
	}

}
