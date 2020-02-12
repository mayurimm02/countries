package com.amazing.countries.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazing.countries.model.country.CapitalCity;

@Repository
public interface CapitalCityRepository extends JpaRepository<CapitalCity, Integer> {
	public Optional<CapitalCity> findByCapital(String capital);

	public Optional<CapitalCity> findByalpha2Code(String alpha2Code);

	public Optional<CapitalCity> findByalpha3Code(String alpha3Code);

}
