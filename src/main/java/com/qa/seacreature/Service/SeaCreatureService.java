package com.qa.seacreature.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.seacreature.domain.SeaCreature;
import com.qa.seacreature.repo.SeaCreatureRepo;

@Service
public class SeaCreatureService implements ServiceInterface<SeaCreature>{
	
	private SeaCreatureRepo repo;
	
	@Autowired
	public SeaCreatureService(SeaCreatureRepo repo) {
		super();
		this.repo = repo;
	}
	
	//Create
	public SeaCreature create(SeaCreature s) {
		SeaCreature created = this.repo.save(s);
		return created;
	}
	
	//Read
	public List<SeaCreature> getAll(){
		return this.repo.findAll();
	}
	
	public SeaCreature getById(Integer id) {
		Optional<SeaCreature> found = this.repo.findById(id);
		return found.get();
	}
	
	public List<SeaCreature> getCreaturesByName(String name){
		List<SeaCreature> found = this.repo.findByName(name);
		return found;
	}
	
	public List<SeaCreature> getCreaturesByLifeExpectancy(Integer lifeExpetancy){
		List<SeaCreature> found = this.repo.findByLifeExpectancy(lifeExpetancy);
		return found;
	}
	
	//replace
	public SeaCreature replace(Integer id, SeaCreature newSeaCreature) {
		SeaCreature existing = this.repo.findById(id).get();
		existing.setName(newSeaCreature.getName());
		existing.setMammal(newSeaCreature.getMammal());
		existing.setDorsalFin(newSeaCreature.getDorsalFin());
		existing.setLifeExpectancy(newSeaCreature.getLifeExpectancy());
		SeaCreature replaced = this.repo.save(existing);
		return replaced;
	}
	
	//Remove
	public void remove(@PathVariable Integer id) {
		this.repo.deleteById(id);
	}

}



























