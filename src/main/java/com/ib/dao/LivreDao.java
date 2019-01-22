package com.ib.dao;

import java.util.List;

import com.ib.data.Livre;

public interface LivreDao {
	
	Livre getById(Integer id);
	
	void save(Livre livre);
	
	void update(Livre livre);
	
	void delete(Livre livre);
	
	List<Livre> findAll();

}
