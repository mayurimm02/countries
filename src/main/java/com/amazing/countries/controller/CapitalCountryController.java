package com.amazing.countries.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amazing.countries.model.capitalcity.CapitalCity;
import com.amazing.countries.repository.CapitalCityRepository;

@RestController
public class CapitalCountryController {

	Logger logger = LoggerFactory.getLogger(CapitalCountryController.class);

	/*
	 * @GetMapping(value = { "/getByCapitalCity" }) public List<Object>
	 * getByCapitalCity(@PathParam(value = "capitalCityName") String
	 * capitalCityName) { System.out.println("capital name " + capitalCityName);
	 * String url = "https://restcountries.eu/rest/v2/capital/" + capitalCityName;
	 * Object[] objects = restTemplate.getForObject(url, Object[].class);
	 * 
	 * for (Object obj : objects) { int i = 0; System.out.println("object[" + i +
	 * "] " + objects[i]);
	 * 
	 * }
	 * 
	 * return Arrays.asList(objects);
	 * 
	 * }
	 */
	@Autowired
	private CapitalCityRepository capitalCityRepository;
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = { "/getByCapitalCity" })
	public Optional<CapitalCity> getByCapitalCity(@PathParam(value = "capitalCityName") String capitalCityName) {
		CapitalCity capitalCity = new CapitalCity();
		Optional<CapitalCity> cities = null;
		logger.info("capital name " + capitalCityName);

		String url = "https://restcountries.eu/rest/v2/capital/" + capitalCityName;

		CapitalCity[] CapitalCity = restTemplate.getForObject(url, CapitalCity[].class);

		for (CapitalCity InsertCity : CapitalCity) {

			logger.info("element at " + InsertCity);
			logger.info("element at " + InsertCity.getName() + " first " + InsertCity.getLanguages().get(0) + "\n"
					+ "Translations " + InsertCity.getTranslations().getFa() + "\n" + "reginal block "
					+ InsertCity.getRegionalBlocs().get(0));

			capitalCity.setName(InsertCity.getName());
			capitalCity.setTopLevelDomain(InsertCity.getTopLevelDomain());
			capitalCity.setAlpha2Code(InsertCity.getAlpha2Code());
			capitalCity.setAlpha3Code(InsertCity.getAlpha3Code());
			capitalCity.setCallingCodes(InsertCity.getCallingCodes());
			capitalCity.setCapital(InsertCity.getCapital());
			capitalCity.setAltSpellings(InsertCity.getAltSpellings());
			capitalCity.setRegion(InsertCity.getRegion());
			capitalCity.setSubregion(InsertCity.getSubregion());
			capitalCity.setPopulation(InsertCity.getPopulation());
			capitalCity.setLatlng(InsertCity.getLatlng());
			capitalCity.setDemonym(InsertCity.getDemonym());
			capitalCity.setArea(InsertCity.getArea());
			capitalCity.setGini(InsertCity.getGini());
			capitalCity.setTimezones(InsertCity.getTimezones());
			capitalCity.setBorders(InsertCity.getBorders());
			capitalCity.setNativeName(InsertCity.getNativeName());
			capitalCity.setNumericCode(InsertCity.getNumericCode());
			capitalCity.setCurrencies(InsertCity.getCurrencies());
			capitalCity.setLanguages(InsertCity.getLanguages());
			capitalCity.setTranslations(InsertCity.getTranslations());
			capitalCity.setFlag(InsertCity.getFlag());
			capitalCity.setRegionalBlocs(InsertCity.getRegionalBlocs());
			capitalCity.setCioc(InsertCity.getCioc());

			logger.info("**********************Saving the data into database ***********************");
			capitalCityRepository.save(capitalCity);

		}
		int id = 32;
		cities = capitalCityRepository.findById(id);
		// List<Object> cities = Arrays.asList(capitalCity);

		return cities;

	}

	@GetMapping(value = { "/getAllCountries" })
	public List<CapitalCity> getAllCountries() {
		int i = 0;
		String url = "";
		CapitalCity countries = new CapitalCity();
		CapitalCity[] allCountries = restTemplate.getForObject(url, CapitalCity[].class);
		System.out.println("length of all countires array " + allCountries.length);
		List<CapitalCity> country = null;
		for (CapitalCity getCountry : allCountries) {

			logger.info("***************************" + "element at " + i + " " + getCountry
					+ " *******************************");
			/*
			 * logger.info("element at " + getCountry.getName() + " first " +
			 * getCountry.getLanguages().get(0) + "\n" + "Translations " +
			 * getCountry.getTranslations().getFa() + "\n" + "reginal block " +
			 * getCountry.getRegionalBlocs().get(0));
			 */

			countries.setName(getCountry.getName());
			countries.setTopLevelDomain(getCountry.getTopLevelDomain());
			countries.setAlpha2Code(getCountry.getAlpha2Code());
			countries.setAlpha3Code(getCountry.getAlpha3Code());
			countries.setCallingCodes(getCountry.getCallingCodes());
			countries.setCapital(getCountry.getCapital());
			countries.setAltSpellings(getCountry.getAltSpellings());
			countries.setRegion(getCountry.getRegion());
			countries.setSubregion(getCountry.getSubregion());
			countries.setPopulation(getCountry.getPopulation());
			countries.setLatlng(getCountry.getLatlng());
			countries.setDemonym(getCountry.getDemonym());
			countries.setArea(getCountry.getArea());
			countries.setGini(getCountry.getGini());
			countries.setTimezones(getCountry.getTimezones());
			countries.setBorders(getCountry.getBorders());
			countries.setNativeName(getCountry.getNativeName());
			countries.setNumericCode(getCountry.getNumericCode());
			countries.setCurrencies(getCountry.getCurrencies());
			countries.setLanguages(getCountry.getLanguages());
			countries.setTranslations(getCountry.getTranslations());
			countries.setFlag(getCountry.getFlag());
			countries.setRegionalBlocs(getCountry.getRegionalBlocs());
			countries.setCioc(getCountry.getCioc());
			i++;
		}
		country = Arrays.asList(countries);
		return country;
	}
}
