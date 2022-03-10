package com.qa.seacreature.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.seacreature.domain.SeaCreature;

@Repository
public interface SeaCreatureRepo extends JpaRepository<SeaCreature, Integer>{
	
	List<SeaCreature> findByName(String name);
	List<SeaCreature> findByLifeExpectancy(Integer lifeExpectancy);
	
	

}
