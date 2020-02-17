package com.amazing.countries.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazing.countries.model.country.RegionalBlocs;

@Repository
public interface RegionalBlocAcronymRepository extends JpaRepository<RegionalBlocs, Integer> {
	public List<RegionalBlocs> findByAcronym(String acronym);
}
