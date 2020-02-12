package com.amazing.countries.db;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amazing.countries.model.User;
import com.amazing.countries.model.country.CapitalCity;
import com.amazing.countries.repository.UserRepository;
import com.amazing.countries.service.CountryService;

@Service
public class DbInit implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(DbInit.class);

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private CountryService countryService;
	private RestTemplate restTemplate;

	public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, CountryService countryService,
			RestTemplate restTemplate) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.restTemplate = restTemplate;
		this.countryService = countryService;

	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * calling the insertIntoDatabase() to check if data exists into DB ,If not then
		 * insert to DB.
		 */
		insertIntoDatabase();

		this.userRepository.deleteAll();
		// Crete users
		User dan = new User("dan", passwordEncoder.encode("dan123"), "USER", "");
		User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN",
				"ACCESS_getByCapitalCity,ACCESS_getAllCountries");

		List<User> users = Arrays.asList(dan, admin);

		// User Save to db
		this.userRepository.saveAll(users);
		// check = checkRepository.findTopByOrderByIdDesc();

	}

	/**
	 * Method for data insertion check
	 */
	public void insertIntoDatabase() {

		List<CapitalCity> addedCountriesToDB = null;
		List<CapitalCity> checkaddedCountries;
		String url = "https://restcountries.eu/rest/v2/all";
		CapitalCity[] allCountries = restTemplate.getForObject(url, CapitalCity[].class);
		addedCountriesToDB = this.countryService.CountryData(allCountries);
		// logger.info("***************** Status ************ " +
		// addedCountriesToDB.toString());
	}
}
