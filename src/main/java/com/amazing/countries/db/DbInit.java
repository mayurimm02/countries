package com.amazing.countries.db;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amazing.countries.model.User;
import com.amazing.countries.model.country.CapitalCity;
import com.amazing.countries.repository.CapitalCityRepository;
import com.amazing.countries.repository.UserRepository;
import com.amazing.countries.service.CountryService;

@Service
public class DbInit implements CommandLineRunner {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private CapitalCityRepository capitalCityRepository;
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
	}
}
