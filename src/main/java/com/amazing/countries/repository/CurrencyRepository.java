package com.amazing.countries.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazing.countries.model.country.Currencies;

@Repository
public interface CurrencyRepository extends JpaRepository<Currencies, Integer> {

	public List<Currencies> findByName(String fullCurrencyName);

	public List<Currencies> findByCode(String currencyCode);

	public List<Currencies> findBySymbol(String symbol);
}
