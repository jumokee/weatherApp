package com.techelevator.npgeek.model.DAO;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.DailyForecast;

@Component
public class JDBCDailyForecastDAO implements DailyForecastDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCDailyForecastDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<DailyForecast> getForecastsByCode(String code) {

		String sql = "SELECT fivedayforecastvalue, low, high, forecast FROM weather "
				+ "WHERE parkcode = ? ORDER BY fivedayforecastvalue asc";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, code);

		List<DailyForecast> forecastList = new LinkedList<DailyForecast>();

		while (result.next()) {
			DailyForecast d = new DailyForecast();

			d.setDay(result.getInt("fivedayforecastvalue"));
			d.setLow(result.getInt("low"));
			d.setHigh(result.getInt("high"));
			d.setForecast(result.getString("forecast"));

			forecastList.add(d);
		}

		return forecastList;
	}

}
