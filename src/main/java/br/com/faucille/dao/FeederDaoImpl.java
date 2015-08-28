package br.com.faucille.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.faucille.model.Feeder;

public class FeederDaoImpl extends AbstractDaoImpl<Feeder> {

	public FeederDaoImpl() {
		super(Feeder.class);
	}

	public List<Feeder> search(int start, int size) {
		String query = "select f from Feeder f";
		TypedQuery<Feeder> q = em.createNamedQuery(query, Feeder.class)
				.setMaxResults(size)
				.setFirstResult(start);

		return q.getResultList();
	}

}
