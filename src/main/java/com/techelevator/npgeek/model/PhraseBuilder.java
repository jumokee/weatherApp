package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

public class PhraseBuilder {

	public List<String> generatePhraseList(int low, int high, String forecast) {
		List<String> phraseList = new ArrayList<String>();
		forecastAdvisory(phraseList, forecast);
		tempHighLowAdvisory(phraseList, high, low);
		return phraseList;
	}

	public void forecastAdvisory(List<String> phraseList, String forecast) {
		switch (forecast) {
		case "snow":
			phraseList.add("Pack snowshoes!");
			break;
		case "rain":
			phraseList.add("Pack rain gear and wear waterproof shoes!");
			break;
		case "thunderstorms":
			phraseList.add("Seek shelter and avoid hiking on exposed ridges!");
			break;
		case "sunny":
			phraseList.add("Pack sunblock!");
			break;
		}
	}

	public void tempHighLowAdvisory(List<String> phraseList, int high, int low) {
		if (high > 75) {
			phraseList.add("Bring an extra gallon of water!");
		}
		if (low < 20) {
			phraseList.add("Beware of exposure to frigid temperatures!");
		}
		if (Math.abs(high - low) > 20) {
			phraseList.add("Wear breathable layers!");
		}
	}

}
