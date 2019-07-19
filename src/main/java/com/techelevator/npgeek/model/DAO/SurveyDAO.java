package com.techelevator.npgeek.model.DAO;

import java.util.Map;

import com.techelevator.npgeek.model.Survey;

public interface SurveyDAO {

	Map<String, Integer> getAllSurveys();

	void save(Survey survey);

}
