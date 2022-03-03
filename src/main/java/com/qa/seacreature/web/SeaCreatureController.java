package com.qa.seacreature.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.seacreature.Service.SeaCreatureService;
import com.qa.seacreature.domain.SeaCreature;

@RestController
public class SeaCreatureController {
	
	private SeaCreatureService service;

	@Autowired
	public SeaCreatureController(SeaCreatureService service) {
		super();
		this.service = service;
	}
	
	//Create 201
	@PostMapping("/create")
	public ResponseEntity<SeaCreature> createSeaCreature(@RequestBody SeaCreature s){
		SeaCreature created = this.service.createSeaCreature(s);
		ResponseEntity<SeaCreature> response = new ResponseEntity<SeaCreature>(created, HttpStatus.CREATED);
		return response;
	}
	
	//Read 200 - ok
	@GetMapping("/getAll")
	public ResponseEntity<List<SeaCreature>> getAllCraetures(){
		return ResponseEntity.ok(this.service.getAllCreatures());
	}
	
	@GetMapping("/getById/{id}") //200 - ok
	public SeaCreature getSeaCreature(@PathVariable Integer id) {
		return this.service.getSeaCreature(id);
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<SeaCreature>> getSeaCreatureByName(@PathVariable String name){
		List<SeaCreature> found = this.service.getCreaturesByName(name);
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getByLifeExp/{lifeExpectancy}")
	public ResponseEntity<List<SeaCreature>> getSeaCreatureByLifeExpectancy(@PathVariable Integer lifeExpectancy){
		List<SeaCreature> found = this.service.getCreaturesByLifeExpectancy(lifeExpectancy);
		return ResponseEntity.ok(found);
	}
	
	//Replace 202 - accepted
	@PutMapping("/replace/{id}")
	public ResponseEntity<SeaCreature> replaceSeaCreature(@PathVariable Integer id, @RequestBody SeaCreature newSeaCreature){
		SeaCreature body = this.service.replaceSeaCreature(id,  newSeaCreature);
		ResponseEntity<SeaCreature> reponse = new ResponseEntity<SeaCreature>(body, HttpStatus.ACCEPTED);
		return reponse;
	}
	
	//Remove 204 - no content
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeSeaCreature(@PathVariable Integer id){
		this.service.removeSeaCreature(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}





















