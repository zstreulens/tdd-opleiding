package com.realdolmen.tdd;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository(value = "stofRepository")
public class StofRepository {
	
	@PersistenceContext
	private EntityManager chemagEntityManager;
	
	@Transactional
	public Stof createStof(Stof stof){
		chemagEntityManager.persist(stof);
		return stof;
	}
	
	public Stof getStof(Long stofId){
		return chemagEntityManager.find(Stof.class, stofId);
	}
	
	@Transactional
	public Stof updateStof(Stof stof){
		chemagEntityManager.merge(stof);
		return stof;
	}
	
	@Transactional
	public void deleteStof(Stof stof){
		stof = chemagEntityManager.find(Stof.class, stof.getId());
		chemagEntityManager.remove(stof);
	}

}
