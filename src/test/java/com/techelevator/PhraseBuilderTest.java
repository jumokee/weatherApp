package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.npgeek.model.PhraseBuilder;

public class PhraseBuilderTest {

	PhraseBuilder builder;

	@Before
	public void setup() {
		builder = new PhraseBuilder();
	}

	@Test
	public void snow_forecast_returns_correct_string() {
		List<String> newList = builder.generatePhraseList(50, 50, "snow");
		Assert.assertTrue(newList.contains("Pack snowshoes!"));

	}

	@Test
	public void rain_forecast_returns_correct_string() {
		List<String> newList = builder.generatePhraseList(50, 50, "rain");
		Assert.assertTrue(newList.contains("Pack rain gear and wear waterproof shoes!"));
	}

	@Test
	public void thunderstorms_forecast_returns_correct_string() {
		List<String> newList = builder.generatePhraseList(50, 50, "thunderstorms");
		Assert.assertTrue(newList.contains("Seek shelter and avoid hiking on exposed ridges!"));

	}

	@Test
	public void sunny_forecast_returns_correct_string() {
		List<String> newList = builder.generatePhraseList(50, 50, "sunny");
		Assert.assertTrue(newList.contains("Pack sunblock!"));
	}

	@Test
	public void high_of_76_returns_correct_string() {
		List<String> newList = builder.generatePhraseList(50, 76, "sunny");
		Assert.assertTrue(newList.contains("Bring an extra gallon of water!"));

	}

	@Test
	public void low_of_19_high_of_76_returns_correct_strings() {
		List<String> newList = builder.generatePhraseList(19, 76, "sunny");
		Assert.assertTrue(newList.contains("Bring an extra gallon of water!")
				&& newList.contains("Beware of exposure to frigid temperatures!")
				&& newList.contains("Wear breathable layers!"));
	}

}
