package com.realdolmen.tdd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Stof")
public class Stof {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naam;
	
	@Column
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
	
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

	public Object isValid() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
