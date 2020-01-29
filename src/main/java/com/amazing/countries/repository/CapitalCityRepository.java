package com.amazing.countries.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazing.countries.model.capitalcity.CapitalCity;

public interface CapitalCityRepository extends JpaRepository<CapitalCity, Integer> {

}
