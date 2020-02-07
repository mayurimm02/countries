package com.amazing.countries.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amazing.countries.model.country.CapitalCity;
import com.amazing.countries.repository.CapitalCityRepository;
import com.amazing.countries.service.CountryService;

@RestController
@CrossOrigin
@RequestMapping("api/public")
public class CountryController {

	Logger logger = LoggerFactory.getLogger(CountryController.class);

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
	@Autowired
	private CountryService countryservice;

	@GetMapping(value = { "getByCapitalCity" })
	public Optional<CapitalCity> getByCapitalCity(@RequestParam(value = "capitalCityName") String capitalCityName) {
		System.out.println("**********************in getcapitalbycity*************************");
		CapitalCity capitalCity = new CapitalCity();
		Optional<CapitalCity> cities = null;
		logger.info("capital name " + capitalCityName);

		cities = capitalCityRepository.findByCapital(capitalCityName);

		return cities;

	}

	@GetMapping(value = { "admin/getAllCountries" })

	public List<CapitalCity> getAllCountries() {

		/*
		 * String url = "https://restcountries.eu/rest/v2/all"; CapitalCity[]
		 * allCountries = restTemplate.getForObject(url, CapitalCity[].class);
		 * System.out.println("length of all countires array " + allCountries.length);
		 */
		System.out.println(capitalCityRepository.findAll());
		List<CapitalCity> country = capitalCityRepository.findAll();

		return country;
	}

	@GetMapping(value = { "getByCountryCode" })
	public String getByCountryCode() {

		return "";
	}

}
