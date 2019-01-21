package com.ib.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	/*
	 * Le ThreadLocal permet d'associer une instance d'un objet à UN thread unique
	 */
	private static final ThreadLocal<Session> sessionLT = new ThreadLocal<Session>();

	private static final SessionFactory sessionFactory;
	static {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.build();
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
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

}
