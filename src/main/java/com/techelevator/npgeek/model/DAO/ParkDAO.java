package com.techelevator.npgeek.model.DAO;

import java.util.List;

import com.techelevator.npgeek.model.Park;

public interface ParkDAO {

	List<Park> getAllParks();

	Park getParkByCode(String code);

}
