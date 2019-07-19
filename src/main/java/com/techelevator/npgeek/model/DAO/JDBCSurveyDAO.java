package com.techelevator.npgeek.model.DAO;

import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Survey;

@Component
public class JDBCSurveyDAO implements SurveyDAO {

	JdbcTemplate jdbcTemplate;

	public JDBCSurveyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Map<String, Integer> getAllSurveys() {
		String sql = "SELECT parkcode, COUNT(*) as counter" + " FROM survey_result "
				+ "GROUP BY parkcode HAVING COUNT(*) > 1 " + "ORDER BY counter DESC, parkcode ASC;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		Map<String, Integer> surveyResults = new TreeMap<String, Integer>();

		while (result.next()) {
			surveyResults.put(result.getString("parkcode"), result.getInt("counter"));
		}
		return surveyResults;
	}

	@Override
	public void save(Survey survey) {

		String sql = " insert into survey_result VALUES (default,?, ?, ?, ?)";
		jdbcTemplate.update(sql, survey.getParkCode(), survey.getEmail(), survey.getState(), survey.getActivityLevel());

	}

}
