package com.realdolmen.tdd;

import java.util.regex.Matcher;

public class StofNummerValidator {

	public Object validateStofNummer(String stofNummer) {
		if (stofNummer == null){
			return false;
		}
		try {
			Matcher m = PATTERN.matcher(stofNummer);
			return m.matches();
		} catch (Exception e){
			
		}
	}

}
