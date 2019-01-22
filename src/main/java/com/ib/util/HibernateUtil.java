package com.ib.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	/*
	 * Le ThreadLocal permet d'associer une instance d'un objet à UN thread unique
	 */
	private static final ThreadLocal<Session> sessionLT = new ThreadLocal<Session>();

	private static final SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Creation de la session factory via le service registry d'hibernate
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the ServiceRegistry from hibernate.cfg.xml
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml").build();

			// Create a metadata sources using the specified service registry.
			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

			return metadata.getSessionFactoryBuilder().build();
		} catch (Throwable ex) {

			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Permet de récupérer une session associée au thread courant ou de la créer si
	 * elle n'existe pas.
	 * 
	 * @return
	 */
	public static Session getCurrentSession() {
		Session session = sessionLT.get();
		if (session == null) {
			session = sessionFactory.openSession();
			sessionLT.set(session);
		}
		return session;
	}

	public static void closeCurrentSession() {
		Session session = sessionLT.get();
		if (session != null) {
			session.close();
			sessionLT.remove();
		}
	}
	
	public static Transaction beginTransaction() {
		return getCurrentSession().beginTransaction();
	}

}
