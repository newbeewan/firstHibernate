package com.ib.dao.impl;

import static com.ib.util.HibernateUtil.getCurrentSession;

import java.util.List;

import org.hibernate.Transaction;

import com.ib.dao.LivreDao;
import com.ib.data.Livre;

/**
 * Implementation de la DAO Les transactions sont pilotées manuellement au sein
 * des methodes par manque d'outillage (sera vu avec Spring @Transactional)
 * 
 * @author mourad
 *
 */
public class LivreDaoImpl implements LivreDao {

	@Override
	public Livre getById(Integer id) {
		Transaction transaction = getCurrentSession().beginTransaction();
		Livre livre = getCurrentSession().get(Livre.class, id);
		transaction.commit();
		return livre;
	}

	@Override
	public void save(Livre livre) {
		Transaction transaction = getCurrentSession().beginTransaction();
		getCurrentSession().save(livre);
		transaction.commit();
	}

	@Override
	public void update(Livre livre) {
		Transaction transaction = getCurrentSession().getTransaction();
		getCurrentSession().merge(livre);
		transaction.commit();

	}

	@Override
	public void delete(Livre livre) {
		Transaction transaction = getCurrentSession().beginTransaction();
		getCurrentSession().delete(livre);
		transaction.commit();

	}

	@Override
	public List<Livre> findAll() {
		Transaction transaction = getCurrentSession().beginTransaction();
		List<Livre> livres = getCurrentSession().createQuery("From Livre", Livre.class).getResultList();
		transaction.commit();
		return livres;
	}

}
