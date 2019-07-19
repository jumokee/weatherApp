package com.techelevator;

import java.util.List;

import javax.sql.DataSource;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.model.DailyForecast;
import com.techelevator.npgeek.model.DAO.JDBCDailyForecastDAO;



public class JDBCDailyForecastDAOTest extends DAOIntegrationTest {

	private DataSource dataSource = getDataSource();
	private JDBCDailyForecastDAO dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		dao = new JDBCDailyForecastDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Test
	public void getForecastsByCode_should_return_a_list_of_two_forecasts_for_park_code_AAAA() {
		addPark("AAAA");
		addNewForecast("AAAA",1,0,0,"sunny");
		addNewForecast("AAAA",2,0,0,"sunny");
		List<DailyForecast> forecastList= dao.getForecastsByCode("AAAA");
		
		Assert.assertEquals("expected the forecastList to be a size of two", 2, forecastList.size());
	}
	
	private void addNewForecast(String parkcode,int fivedayforecastvalue, int low, int high, String forecast) {
		String forecastSQL = "INSERT INTO weather VALUES (?,?,?,?,?)";
		jdbcTemplate.update(forecastSQL,parkcode,fivedayforecastvalue,low,high,forecast);
	}
	
	private void addPark(String parkcode) {
		String parkSQL = "insert into park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, "
				+ "annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) "
				+ "values (?, 'TestPark', 'Test', 0, 0, 0.0, 0, 'TestClimate', 0, 0, 'TestQuote', 'TestSource', "
				+ "'TestDescription', 0, 0);";
		jdbcTemplate.update(parkSQL,parkcode);
	}
	
}
