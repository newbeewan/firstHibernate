package com.ib.util;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ib.data.Client;

public class HibernateUtilTest {
	private final Logger logger = LoggerFactory.getLogger(HibernateUtilTest.class);

	@Test
	public void testGetCurrentSession() {
		Session session = HibernateUtil.getCurrentSession();
		assertNotNull(session);
	}

	@Test
	public void testCreateClient() {
		Client client = new Client();
		client.setNom("test");
		client.setPrenom("test");

		Session session = HibernateUtil.getCurrentSession();

		Transaction transaction = session.beginTransaction();
		logger.info("Enregistrement de l'objet");
		session.save(client);
		transaction.commit();

		assertNotNull(client.getId());
		logger.info("Client {}", client.getId());

	}

	@Test
	public void testFindAll() {
		Session session = HibernateUtil.getCurrentSession();
		List<Client> clients = session.createQuery("SELECT c From Client c", Client.class).getResultList();
		assertFalse(clients.isEmpty());
		clients.forEach(c -> logger.info("Client id {}", c.getId()));
	}

}
