package br.com.faucille.dao;

import java.util.List;

import br.com.faucille.model.Feeder;

public class FeederRepository {
	
	private FeederDaoImpl feederDao;
	
	public FeederRepository() {
		this.feederDao = new FeederDaoImpl();
	}
	
	public List<Feeder> getFeeders(int start, int size) {
		return feederDao.search(start, size);
	}
	

	
}
