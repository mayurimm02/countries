package com.amazing.countries.db;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazing.countries.model.User;
import com.amazing.countries.repository.UserRepository;

@Service
public class DbInit implements CommandLineRunner {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		// Delete all
		this.userRepository.deleteAll();

		// Crete users
		User dan = new User("dan", passwordEncoder.encode("dan123"), "USER", "");
		User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_getByCapitalCity,ACCESS_getAllCountries");
		// User manager = new
		// User("manager",passwordEncoder.encode("manager123"),"MANAGER","ACCESS_TEST1");

		List<User> users = Arrays.asList(dan, admin);

		// Save to db
		this.userRepository.saveAll(users);
	}
}
