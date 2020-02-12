package com.amazing.countries.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazing.countries.db.DataInsertionCheck;
import com.amazing.countries.model.country.CapitalCity;
import com.amazing.countries.repository.CapitalCityRepository;
import com.amazing.countries.repository.DataInsertionCheckRepository;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CapitalCityRepository capitalCityRepository;

	private Optional<CapitalCity> countryAlreadyExists;

	@Autowired
	private DataInsertionCheck insertionCheck;

	@Autowired
	private DataInsertionCheckRepository checkRepository;

	Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

	@Override
	public List<CapitalCity> CountryData(CapitalCity[] allCountries) {
		boolean isDataPresent = false;
		List<CapitalCity> countryData = new ArrayList<CapitalCity>();
		int i = 1;
		CapitalCity countries = null;
		for (CapitalCity getCountry : allCountries) {
			countries = new CapitalCity();

			countries.setName(getCountry.getName());
			countries.setTopLevelDomain(getCountry.getTopLevelDomain());
			countries.setAlpha2Code(getCountry.getAlpha2Code());
			countries.setAlpha3Code(getCountry.getAlpha3Code());
			countries.setCallingCodes(getCountry.getCallingCodes());
			countries.setCapital(getCountry.getCapital());
			countries.setAltSpellings(getCountry.getAltSpellings());
			countries.setRegion(getCountry.getRegion());
			countries.setSubregion(getCountry.getSubregion());
			countries.setPopulation(getCountry.getPopulation());
			countries.setLatlng(getCountry.getLatlng());
			countries.setDemonym(getCountry.getDemonym());
			countries.setArea(getCountry.getArea());
			countries.setGini(getCountry.getGini());
			countries.setTimezones(getCountry.getTimezones());
			countries.setBorders(getCountry.getBorders());
			countries.setNativeName(getCountry.getNativeName());
			countries.setNumericCode(getCountry.getNumericCode());
			countries.setCurrencies(getCountry.getCurrencies());
			countries.setLanguages(getCountry.getLanguages());
			countries.setTranslations(getCountry.getTranslations());
			countries.setFlag(getCountry.getFlag());
			countries.setRegionalBlocs(getCountry.getRegionalBlocs());
			countries.setCioc(getCountry.getCioc());

			countryData.add(countries);
			// System.out.println("alphacode at " + i + " " + getCountry.getAlpha2Code());

			try {
				countryAlreadyExists = capitalCityRepository.findByalpha3Code(getCountry.getAlpha3Code());
				// System.out.println("cityAlreadyExists at " + i + " "
				// +countryAlreadyExists.isPresent());
				if (countryAlreadyExists.isPresent()) {
					logger.info("*********************** Item at " + i + " alrady exists"
							+ " *******************************");
					/*
					 * insertionCheck.setInsertedOnDate(new Date());
					 * insertionCheck.setIsDataAlreadyPresent("Yes");
					 * insertionCheck.setIsDataIntersted("false");
					 */
					isDataPresent = true;

				} else {
					logger.info("**********************Saving the data into database ***********************");
					logger.info("*************************** Inserted item at " + i + " ***************************");
					capitalCityRepository.save(countries);
					isDataPresent = false;

				}
			} catch (Exception e) {
				System.out.println("Exception " + e.getStackTrace());
			}
			i++;

		}
		insertionCheck = new DataInsertionCheck();
		if (isDataPresent) {
			insertionCheck.setInsertedOnDate(new Date());
			insertionCheck.setIsDataAlreadyPresent("Yes");
			insertionCheck.setIsDataIntersted("false");
		} else {

			insertionCheck.setInsertedOnDate(new Date());
			insertionCheck.setIsDataAlreadyPresent("No");
			insertionCheck.setIsDataIntersted("true");
		}
		checkRepository.save(insertionCheck);

		return countryData;
	}

}
