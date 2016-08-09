package com.realdolmen.tdd;

import javax.persistence.EntityManager;

public class StofRepository {
	private EntityManager chemagEntityManager;
	
	public Stof createStof(Stof stof){
		chemagEntityManager.persist(stof);
		return stof;
	}
	
	public Stof getStof(Long stofId){
		return chemagEntityManager.find(Stof.class, stofId);
	}
	
	public Stof updateStof(Stof stof){
		chemagEntityManager.merge(stof);
		return stof;
	}
	
	public void deleteStof(Stof stof){
		chemagEntityManager.remove(stof);
	}

}
