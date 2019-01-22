package com.ib.data;

import static org.junit.Assert.*;

import org.hibernate.Transaction;

import static com.ib.util.HibernateUtil.*;

import org.junit.Test;

public class LoisirPersonne {

	@Test
	public void test() {
		Transaction transaction = beginTransaction();
		Personne personne = new Personne();
		personne.setNom("Doe");
		personne.setPrenom("John");
		
		Loisir loisir = new Loisir();
		loisir.setNom("Hockey sur glace");
		loisir.getPersonnes().add(personne);
		
		getCurrentSession().save(loisir);
		transaction.commit();
		
		getCurrentSession().refresh(loisir);
		
		assertFalse(loisir.getPersonnes().get(0).getLoisirs().isEmpty());
		
		
	}

}
