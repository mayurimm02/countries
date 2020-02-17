package com.amazing.countries.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazing.countries.model.country.Languages;

@Repository
public interface LanguagesRepository extends JpaRepository<Languages, Integer> {

	public List<Languages> findByName(String languagename);
}
