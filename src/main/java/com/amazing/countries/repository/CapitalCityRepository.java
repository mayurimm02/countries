package com.amazing.countries.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazing.countries.model.country.Countries;

@Repository
public interface CapitalCityRepository extends JpaRepository<Countries, Integer> {

	public Optional<Countries> findByCapital(String capital);

	public Optional<Countries> findByalpha2Code(String alpha2Code);

	public Optional<Countries> findByalpha3Code(String alpha3Code);

	public List<Countries> findByRegion(String region);

	public Optional<Countries> findByName(String name);

}
