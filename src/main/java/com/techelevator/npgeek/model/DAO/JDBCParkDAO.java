package com.techelevator.npgeek.model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Park;

@Component
public class JDBCParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<Park>();
		String sql = "select parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded,"
				+ " annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies "
				+ "from  park;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		while (result.next()) {
			parks.add(mapToPark(result));
		}

		return parks;
	}

	@Override
	public Park getParkByCode(String code) {
		String sql = "select parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded,"
				+ " annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies "
				+ "from  park WHERE parkcode = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, code);

		result.next();
		Park p = mapToPark(result);

		return p;
	}

	private Park mapToPark(SqlRowSet result) {
		Park p = new Park();

		p.setCode(result.getString("parkcode"));
		p.setParkName(result.getString("parkname"));
		p.setState(result.getString("state"));
		p.setAcreage(result.getInt("acreage"));
		p.setElevation(result.getInt("elevationinfeet"));
		p.setMilesOfTrail(result.getDouble("milesoftrail"));
		p.setNumberOfCampsites(result.getInt("numberofcampsites"));
		p.setClimate(result.getString("climate"));
		p.setYearFounded(result.getInt("yearfounded"));
		p.setAnnualVisitorCount(result.getInt("annualvisitorcount"));
		p.setQuote(result.getString("inspirationalquote"));
		p.setQuoteSource(result.getString("inspirationalquotesource"));
		p.setDescription(result.getString("parkdescription"));
		p.setEntryFee(result.getInt("entryfee"));
		p.setNumberOfAnimalSpecies(result.getInt("numberofanimalspecies"));

		return p;
	}

}
