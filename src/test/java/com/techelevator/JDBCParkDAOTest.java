package com.techelevator;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.DAO.JDBCParkDAO;

public class JDBCParkDAOTest extends DAOIntegrationTest {

	private DataSource dataSource = getDataSource();
	private JDBCParkDAO dao;

	@Before
	public void setup() {
		dao = new JDBCParkDAO(dataSource);
	}

	@Test
	public void get_all_parks_gets_all_parks() {
		List<Park> listOne = dao.getAllParks();
		addPark("AAAA");
		addPark("BBBB");
		List<Park> listTwo = dao.getAllParks();

		Assert.assertEquals((listOne.size() + 2), listTwo.size());

	}

	@Test
	public void get_park_by_code_returns_proper_park() {
		addPark("AAAA");
		Park park = dao.getParkByCode("AAAA");

		Assert.assertEquals("AAAA", park.getCode());

	}

	private void addPark(String code) {
		String sql = "insert into park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, "
				+ "annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) "
				+ "values (?, 'TestPark', 'Test', 0, 0, 0.0, 0, 'TestClimate', 0, 0, 'TestQuote', 'TestSource', "
				+ "'TestDescription', 0, 0);";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, code);
	}

}
