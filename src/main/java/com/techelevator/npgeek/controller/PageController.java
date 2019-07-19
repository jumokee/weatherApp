package com.techelevator.npgeek.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.model.DailyForecast;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.PhraseBuilder;
import com.techelevator.npgeek.model.StateList;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.DAO.DailyForecastDAO;
import com.techelevator.npgeek.model.DAO.ParkDAO;
import com.techelevator.npgeek.model.DAO.SurveyDAO;

@Controller
@SessionAttributes("tempType")
public class PageController {

	@Autowired
	private ParkDAO parkDAO;

	@Autowired
	private DailyForecastDAO forecastDAO;

	@Autowired
	private SurveyDAO surveyDAO;

	@RequestMapping("/")
	public String showIndex(ModelMap map) {
		map.addAttribute("parks", parkDAO.getAllParks());
		return "index";
	}

	@RequestMapping("/description")
	public String showParkDescription(HttpServletRequest request, ModelMap map) {
		String code = request.getParameter("parkCode");
		Park p = parkDAO.getParkByCode(code);
		map.addAttribute("park", p);
		List<DailyForecast> forecastList = forecastDAO.getForecastsByCode(p.getCode());
		PhraseBuilder builder = new PhraseBuilder();
		List<String> advisoryList = builder.generatePhraseList(forecastList.get(0).getLow(),
				forecastList.get(0).getHigh(), forecastList.get(0).getForecast());
		map.addAttribute("forecasts", forecastList);
		map.addAttribute("advisoryList", advisoryList);

		if (!map.containsAttribute("tempType")) {
			map.addAttribute("tempType", "F");
		}

		return "description";
	}

	@RequestMapping(path = "/description", method = RequestMethod.POST)
	public String setSessionTemperatureVariable(HttpServletRequest request, ModelMap map, RedirectAttributes attr) {

		if (map.containsAttribute("tempType") && map.get("tempType").equals("F")) {
			map.put("tempType", "C");
		} else {
			map.put("tempType", "F");
		}

		String code = request.getParameter("parkCode");

		return "redirect:/description?parkCode=" + code;
	}

	@RequestMapping(path = "/survey", method = RequestMethod.GET)
	public String showSurvey(ModelMap map) {

		if (!map.containsAttribute("survey")) {
			map.addAttribute("survey", new Survey());
		}

		StateList states = new StateList();
		String[] stateArr = states.getStateList();
		map.addAttribute("parks", parkDAO.getAllParks());
		map.addAttribute("states", stateArr);
		return "survey";
	}

	@RequestMapping(path = "/survey", method = RequestMethod.POST)
	public String doSurvey(@Valid @ModelAttribute("survey") Survey survey, BindingResult result, ModelMap map) {

		StateList states = new StateList();
		String[] stateArr = states.getStateList();
		map.addAttribute("parks", parkDAO.getAllParks());
		map.addAttribute("states", stateArr);

		if (result.hasErrors()) {
			return "/survey";
		}

		surveyDAO.save(survey);
		map.clear();

		return "redirect:/favoriteParks";
	}

	@RequestMapping(path = "/favoriteParks", method = RequestMethod.GET)
	public String showFavoriteParks(ModelMap map) {

		map.addAttribute("parks", parkDAO.getAllParks());
		map.addAttribute("surveys", surveyDAO.getAllSurveys());

		return "favoriteParks";
	}

}
