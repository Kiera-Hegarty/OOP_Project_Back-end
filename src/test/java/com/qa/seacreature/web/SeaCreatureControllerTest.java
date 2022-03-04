package com.qa.seacreature.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.seacreature.domain.SeaCreature;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:SeaCreature-schema.sql",
		"classpath:SeaCreature-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class SeaCreatureControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	//Create
	@Test
	void testcreate() throws Exception{
		SeaCreature testSeaCreature = new SeaCreature(null, "Seahorse", false, false, 3);
		String testSeaCraetureAsJSON=this.mapper.writeValueAsString(testSeaCreature);
		RequestBuilder req =post("/create").contentType(MediaType.APPLICATION_JSON).content(testSeaCraetureAsJSON);
		
		SeaCreature testCreatedSeaCreature = new SeaCreature(3, "Seahorse", false, false, 3);
		String testCreatedSeaCreatureAsJSON =  this.mapper.writeValueAsString(testCreatedSeaCreature);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedSeaCreatureAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		}
		
	//Read
	@Test
	void getAllTest() throws Exception{
		RequestBuilder req = get("/getAll");
		List<SeaCreature> testCreatures = List.of(new SeaCreature(1, "Seahorse", false, false, 3), new SeaCreature(2, "Orca", true, true, 60));
	String json = this.mapper.writeValueAsString(testCreatures);
	ResultMatcher checkStatus = status().isOk();
	ResultMatcher checkBody = content().json(json);
	
	this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getByIdTest() throws Exception{
		RequestBuilder req = get("/getById/1");
	String seaCreatureAsJson = this.mapper.writeValueAsString(new SeaCreature(1, "Seahorse", false, false, 3));
	ResultMatcher checkStatus = status().isOk();
	ResultMatcher checkBody = content().json(seaCreatureAsJson);
	
	this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getByNameTest() throws Exception{
		RequestBuilder req = get("/getByName/Seahorse");
		List<SeaCreature> testCreatures = List.of(new SeaCreature(1, "Seahorse", false, false, 3));
	String json = this.mapper.writeValueAsString(testCreatures);
	ResultMatcher checkStatus = status().isOk();
	ResultMatcher checkBody = content().json(json);
		
	this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getByLifeExp() throws Exception{
		RequestBuilder req = get("/getByLifeExp/3");
		List<SeaCreature> testCreatures = List.of(new SeaCreature(1, "Seahorse", false, false, 3));
	String json = this.mapper.writeValueAsString(testCreatures);
	ResultMatcher checkStatus = status().isOk();
	ResultMatcher checkBody = content().json(json);
		
	this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	//Replace
	@Test
	void testReplaced() throws Exception{
		SeaCreature testSeaCreature = new SeaCreature(1, "Seahorse", false, false, 3);
		String testSeaCreatureAsJSON = this.mapper.writeValueAsString(testSeaCreature);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testSeaCreatureAsJSON);
		
		SeaCreature testReplacedSeaCreature = new SeaCreature(1, "Seahorse", false, false, 3);
		String testReplacedSeaCreatureAsJSON = this.mapper.writeValueAsString(testReplacedSeaCreature);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testReplacedSeaCreatureAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	//Remove
	@Test
	void testRemove() throws Exception{
		RequestBuilder req = delete("/remove/1").contentType(MediaType.APPLICATION_JSON);
		ResultMatcher checkStatus = status().isNoContent();
		
		this.mvc.perform(req).andExpect(checkStatus);
	}
	}




























