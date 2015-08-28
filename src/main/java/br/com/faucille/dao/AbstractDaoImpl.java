package br.com.faucille.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AbstractDaoImpl<T> {
	private static final String PERSISTENCE_UNIT_NAME = "faucille";
	private static EntityManagerFactory factory;
	protected EntityManager em;
	protected EntityTransaction tx;
	private final Class<T> aClass;

	public AbstractDaoImpl(Class<T> aClass) {
		this.aClass = aClass;
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		tx = em.getTransaction();
	}

	public void persist(T t) {
		tx.begin();
		em.persist(t);
		tx.commit();
	}

	public void update(T t) {
		tx.begin();
		em.merge(t);
		tx.commit();
	}

	public void remove(T t) {
		tx.begin();
		em.remove(t);
		tx.commit();
	}

	public T find(Serializable id) {
		return em.find(aClass, id);
	}

	public List<T> findAll() {
		String query = "select o from " + aClass.getName() + " o";
		Query q = em.createQuery(query, aClass);
		return q.getResultList();
	}
}