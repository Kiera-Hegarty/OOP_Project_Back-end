package com.qa.seacreature.Service;

import java.util.List;

public interface ServiceInterface<T> {
	
	//Create
	T create(T t);
	
	//Read
	List<T> getAll();
	 
	T getById(Integer id);
	
	//Replace
	T replace(Integer id, T t);
	
	//Remove
	void remove(Integer id);

}
