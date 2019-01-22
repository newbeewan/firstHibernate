package com.ib.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ib.dao.impl.LivreDaoImpl;
import com.ib.data.Livre;

public class LivreDaoTest {
	private final Logger logger = LoggerFactory.getLogger(LivreDaoTest.class);

	LivreDao livreDao = new LivreDaoImpl();

	@Test
	public void testGetById() {
		Livre livre = livreDao.getById(1);
		assertNotNull(livre);
		logger.info("Livre chargé : {} {} {} {} ", 
				livre.getId(), livre.getIsbn(), livre.getTitre(), livre.getAuteur());
		;
	}

	@Test
	public void testSave() {
		Livre livre = new Livre();
		livre.setTitre("Critique de la raison pure");
		livre.setAuteur("Emmanuel Kant");
		livre.setIsbn(Long.toString(System.currentTimeMillis()));
		logger.info("création d'un nouveau livre");
		livreDao.save(livre);
		assertNotNull(livre.getId());
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

}
