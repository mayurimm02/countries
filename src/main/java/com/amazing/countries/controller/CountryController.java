package com.amazing.countries.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amazing.countries.model.ErrorCodes;
import com.amazing.countries.model.country.Countries;
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

	@GetMapping(value = { "getByCapitalCity/{capitalCityName}" }, produces = "application/json")
	public ResponseEntity<?> getByCapitalCity(@PathVariable String capitalCityName) {
		logger.info("**********************in getCapitalByCity*************************");
		Optional<Countries> cities = null;
		logger.info("capital name " + capitalCityName);

		try {

			if (countryservice.validateInputValue(capitalCityName)) {
				return ((BodyBuilder) ResponseEntity.badRequest())
						.body(new ErrorCodes(400, "Numeric values not allowed or capitalName should not be empty"));
			} else {
				cities = capitalCityRepository.findByCapital(capitalCityName);
			}

			return ResponseEntity.ok().body(cities);
		} catch (Exception e) {
			return ((BodyBuilder) ResponseEntity.notFound()).body(new ErrorCodes(404, "NOT FOUND"));

		}

	}

	@GetMapping(value = { "admin/getAllCountries" }, produces = "application/json")

	public ResponseEntity<?> getAllCountries() {
		logger.info("**********************in getAllCountries*************************");

		List<Countries> country;
		try {
			country = capitalCityRepository.findAll();

			return ResponseEntity.ok().body(country);
		} catch (Exception e) {
			return new ResponseEntity<String>("Soemthing went wrong", new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = { "getByCountryCode/{countryCode}" }, produces = "application/json")
	public ResponseEntity<?> getByCountryCode(@PathVariable String countryCode) {
		Optional<Countries> retrivedCountryCode = null;
		try {
			logger.info("**********************in getByCountryCode*************************");

			countryCode = countryCode.toUpperCase();
			if (countryservice.validateInputValue(countryCode)) {
				return ((BodyBuilder) ResponseEntity.badRequest())
						.body(new ErrorCodes(400, "Numeric country code not allowed or code should not be empty"));
			} else {
				if (countryCode.length() == 2) {
					retrivedCountryCode = capitalCityRepository.findByalpha2Code(countryCode);
				} else if (countryCode.length() == 3) {
					retrivedCountryCode = capitalCityRepository.findByalpha3Code(countryCode);
				} else {
					return ((BodyBuilder) ResponseEntity.notFound())
							.body(new ErrorCodes(404, "Country code must be either 2 or 3 alphabets only"));

				}

			}
			return ResponseEntity.ok().body(retrivedCountryCode);

		} catch (Exception e) {

			e.printStackTrace();
			/*
			 * return new ResponseEntity<String>("Something went wrong", new HttpHeaders(),
			 * HttpStatus.INTERNAL_SERVER_ERROR);
			 */
			return ((BodyBuilder) ResponseEntity.notFound()).body(new ErrorCodes(404, "NOT FOUND"));
		}

	}

}
