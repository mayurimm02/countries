package com.amazing.countries.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazing.countries.model.country.CapitalCity;

public interface CapitalCityRepository extends JpaRepository<CapitalCity, Integer> {
	public Optional<CapitalCity> findByCapital(String capital);
	
}
