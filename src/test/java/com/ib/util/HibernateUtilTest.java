package com.ib.util;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ib.data.Client;
import com.ib.data.Livre;

public class HibernateUtilTest {
	private final Logger logger = LoggerFactory.getLogger(HibernateUtilTest.class);

	@Test
	public void annotationSessionFactory() {
		ServiceRegistry standardRegistry =
		        new StandardServiceRegistryBuilder().configure("hibernate-annotation.cfg.xml").build();

		MetadataSources sources = new MetadataSources( standardRegistry )
		    .addAnnotatedClass( Livre.class );
		Metadata metadata = sources.getMetadataBuilder().build();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		List<Livre> livres = session.createQuery("From Livre", Livre.class).getResultList();
		assertNotNull(livres);
	}
	
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
