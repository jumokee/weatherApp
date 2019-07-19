package com.techelevator;

import java.util.Map;

import javax.sql.DataSource;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.DAO.JDBCSurveyDAO;

public class JDBCSurveyDAOTest extends DAOIntegrationTest{
	
	private DataSource dataSource = getDataSource();
	private JDBCSurveyDAO dao;
	
	@Before
	public void setup() {
		dao = new JDBCSurveyDAO(dataSource);
	}
	
	@Test
	public void save_adds_a_new_servey_and_getAllSurveys_returns_the_new_servey_count() {
		addPark("AAAA");
		Survey s = new Survey();
		s.setParkCode("AAAA");
		s.setEmail("example@domain.com");
		s.setActivityLevel("active");
		s.setState("Ohio");
		
		dao.save(s);
		dao.save(s);
		Map<String, Integer> surveyMap = dao.getAllSurveys();
		int surveyCount = surveyMap.get("AAAA");
		
		Assert.assertEquals("expected survey count for park code AAAA to be 2", 2,surveyCount);
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
