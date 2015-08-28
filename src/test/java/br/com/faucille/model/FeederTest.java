package br.com.faucille.model;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.faucille.dao.FeederDaoImpl;
import static org.junit.Assert.*;
public class FeederTest {

	@Test
	public void shouldInsertRandomFeeder() {
		Feeder feeder = new Feeder();
		feeder.setLink("www.google.com");
		feeder.setLastUpdate(new Date());
		
		
		FeederDaoImpl dao = new FeederDaoImpl();
		dao.persist(feeder);
		List<Feeder> findAll = dao.findAll();
		
		assertTrue(
				"There should be www.google.com in database",
				findAll != null);
		assertTrue("There should be at least 1 record that we just added",
				!findAll.isEmpty());
	}
}
