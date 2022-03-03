package com.qa.seacreature.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SeaCreature {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, unique = true)
	private String name;
	private Boolean mammal;
	private Boolean dorsalFin;
	private Integer lifeExpectancy;
	
	public SeaCreature() {
		super();
	}

	public SeaCreature(Integer id, String name, Boolean mammal, Boolean dorsalFin, Integer lifeExpectancy) {
		super();
		this.id = id;
		this.name = name;
		this.mammal = mammal;
		this.dorsalFin = dorsalFin;
		this.lifeExpectancy = lifeExpectancy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getMammal() {
		return mammal;
	}

	public void setMammal(Boolean mammal) {
		this.mammal = mammal;
	}

	public Boolean getDorsalFin() {
		return dorsalFin;
	}

	public void setDorsalFin(Boolean dorsalFin) {
		this.dorsalFin = dorsalFin;
	}

	public Integer getLifeExpectancy() {
		return lifeExpectancy;
	}

	public void setLifeExpectancy(Integer lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	@Override
	public String toString() {
		return "SeaCreature [id=" + id + ", name=" + name + ", mammal=" + mammal + ", dorsalFin=" + dorsalFin
				+ ", lifeExpectancy=" + lifeExpectancy + "]";
	}

}
