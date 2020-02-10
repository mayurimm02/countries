package com.amazing.countries.db;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amazing.countries.model.User;
import com.amazing.countries.model.country.CapitalCity;
import com.amazing.countries.repository.CapitalCityRepository;
import com.amazing.countries.repository.DataInsertionCheckRepository;
import com.amazing.countries.repository.UserRepository;
import com.amazing.countries.service.CountryService;

@Service
public class DbInit implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(DbInit.class);
	private Optional<DataInsertionCheck> check;
	private DataInsertionCheck insertionCheck;
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private CapitalCityRepository capitalCityRepository;
	private CountryService countryService;
	private RestTemplate restTemplate;
	@Autowired
	private DataInsertionCheck dbcheck;

	@Autowired
	private DataInsertionCheckRepository checkRepository;

	public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, CountryService countryService,
			RestTemplate restTemplate) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.restTemplate = restTemplate;
		this.countryService = countryService;

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("*********************isdatainserted from repo " + "*************************");
		check = checkRepository.findTopByOrderByIdDesc();

		if (check.get().getIsDataIntersted() == false) {
			String url = "https://restcountries.eu/rest/v2/all";
			CapitalCity[] allCountries = restTemplate.getForObject(url, CapitalCity[].class);
			this.countryService.CountryData(allCountries);
			// Delete all
			this.userRepository.deleteAll();

			// Crete users
			User dan = new User("dan", passwordEncoder.encode("dan123"), "USER", "");
			User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN",
					"ACCESS_getByCapitalCity,ACCESS_getAllCountries");
			// User manager = new
			// User("manager",passwordEncoder.encode("manager123"),"MANAGER","ACCESS_TEST1");

			List<User> users = Arrays.asList(dan, admin);

			// Save to db
			this.userRepository.saveAll(users);
//insertionCheck.setId(1);
			insertionCheck.setIsDataIntersted(true);
			insertionCheck.setInsertedOnDate(new Date());
			checkRepository.save(insertionCheck);
			logger.info(
					"******************************************************Data Inserted into the Database *************************************************************************");
		} else {
			logger.info(
					"******************************************************Data already exists into the Database *************************************************************************");

			// insertionCheck.setId(1);
			/*
			 * insertionCheck.setIsDataIntersted(false);
			 * insertionCheck.setInsertedOnDate(new Date());
			 * checkRepository.save(insertionCheck);
			 */
		}

	}
}
