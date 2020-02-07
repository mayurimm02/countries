package com.amazing.countries.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazing.countries.model.country.CapitalCity;
import com.amazing.countries.repository.CapitalCityRepository;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CapitalCityRepository capitalCityRepository;

	Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

	@Override
	public CapitalCity CountryData(CapitalCity[] allCountries) {

		int i = 0;
		CapitalCity countries = null;
		for (CapitalCity getCountry : allCountries) {
			countries = new CapitalCity();
			/*
			 * logger.info("element at " + getCountry.getName() + " first " +
			 * getCountry.getLanguages().get(0) + "\n" + "Translations " +
			 * getCountry.getTranslations().getFa() + "\n" + "reginal block " +
			 * getCountry.getRegionalBlocs().get(0));
			 */

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
			logger.info("**********************Saving the data into database ***********************");
			capitalCityRepository.save(countries);
			i++;
			logger.info("********************************************** " + "Inserted item " + i
					+ " *******************************************************************");
		}
		return countries;
	}

}
