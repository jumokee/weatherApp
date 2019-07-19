package com.techelevator.npgeek.model.DAO;

import java.util.List;

import com.techelevator.npgeek.model.DailyForecast;

public interface DailyForecastDAO {

	List<DailyForecast> getForecastsByCode(String code);

}
