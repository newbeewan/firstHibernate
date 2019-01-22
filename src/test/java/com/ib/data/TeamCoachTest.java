package com.ib.data;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.ib.util.HibernateUtil;

public class TeamCoachTest {

	@Test
	public void test() {
		Transaction transaction = HibernateUtil.beginTransaction();
		Team team = new Team();
		Coach coach = new Coach();
		team.setCoach(coach);
		team.setName("Les Winners");
		coach.setFirstname("Super");
		coach.setLastname("coach");
		Session session = HibernateUtil.getCurrentSession();
		session.save(team);
		transaction.commit();
		
		session.refresh(team);
		
		assertNotNull(team.getCoach().getTeam());
		
		
	}

}
