package com.realdolmen.tdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StofController {
	
	@Autowired
	private StofRepository stofRepository;
	
	@RequestMapping(method = RequestMethod.POST, path="/stof/create", consumes = "application/json")
	public Stof createStof(@RequestBody /*@Valid*/ Stof stof){
		if (null != stof.getId()){
			throw new RuntimeException("New stof should not have an Id");
		}
		if (stof.getNummer() == "invalid"){
			throw new IllegalArgumentException("Invalid nummer");
		}
		stof = stofRepository.createStof(stof);
		return stof;
	}

	@RequestMapping(method = RequestMethod.GET, path= "/stof/{stofId}")
	public Stof getStof(@PathVariable Long stofId){
		Stof stof = stofRepository.getStof(stofId);
		return stof;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/stof/{stofId}")
	public Stof updateStof(@PathVariable Stof stof){
		if (stof.getNummer() == "invalid"){
			throw new IllegalArgumentException("Invalid nummer");
		}
		if (stofRepository.getStof(stof.getId()) == null){
			throw new IllegalArgumentException("Stof bestaat niet");
		}
		
		return stofRepository.updateStof(stof);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path="/stof/{stofId}")
	public boolean deleteStof(@PathVariable Long stofId){
		Stof stof = stofRepository.getStof(stofId);
		stofRepository.deleteStof(stof);
		return true;
	}
}
