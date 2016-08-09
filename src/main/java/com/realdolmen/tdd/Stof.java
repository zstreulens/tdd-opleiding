package com.realdolmen.tdd;

import javax.persistence.Entity;

@Entity
public class Stof {

	private Long id;
	
	private String naam;
	
	private String nummer;
	
	public Long getId() {
		return id;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public String getNummer() {
		return nummer;
	}
	
}
