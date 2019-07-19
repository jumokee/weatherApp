package com.techelevator.npgeek.model;

public class DailyForecast {

	private int day;
	private int low;
	private int high;
	private int lowC;
	private int highC;
	private String forecast;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public int getLowC() {
		lowC = (int) ((low - 32) / 1.8);
		return lowC;
	}

	public int getHighC() {
		highC = (int) ((high - 32) / 1.8);
		return highC;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

}
