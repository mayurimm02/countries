package com.amazing.countries.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazing.countries.db.DataInsertionCheck;

public interface DataInsertionCheckRepository extends JpaRepository<DataInsertionCheck, Integer> {
	public Optional<DataInsertionCheck> findById(Integer id);

	public Optional<DataInsertionCheck> findTopByOrderByIdDesc();
}
