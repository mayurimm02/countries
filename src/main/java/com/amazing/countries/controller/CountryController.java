package com.amazing.countries.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amazing.countries.model.ErrorCodes;
import com.amazing.countries.model.country.Countries;
import com.amazing.countries.model.country.Currencies;
import com.amazing.countries.model.country.Languages;
import com.amazing.countries.model.country.RegionalBlocs;
import com.amazing.countries.repository.CapitalCityRepository;
import com.amazing.countries.repository.CurrencyRepository;
import com.amazing.countries.repository.LanguagesRepository;
import com.amazing.countries.repository.RegionalBlocAcronymRepository;
import com.amazing.countries.service.CountryService;

@RestController
@CrossOrigin
@RequestMapping("api/public")
public class CountryController {

	Logger logger = LoggerFactory.getLogger(CountryController.class);
	@Autowired
	private CapitalCityRepository capitalCityRepository;
	@Autowired
	private CurrencyRepository currencyRepository;
	@Autowired
	private LanguagesRepository languageRepository;
	@Autowired
	private RegionalBlocAcronymRepository regionalBlockAcronymRepo;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CountryService countryservice;

	@GetMapping(value = { "getByCapitalCity/{capitalCityName}" }, produces = "application/json")
	public ResponseEntity<?> getByCapitalCity(@PathVariable String capitalCityName) {
		logger.info("**********************in getCapitalByCity*************************");
		Optional<Countries> cities = null;
		logger.info("capital name " + capitalCityName);

		try {

			if (countryservice.validateInputValue(capitalCityName)) {
				return ((BodyBuilder) ResponseEntity.badRequest())
						.body(new ErrorCodes(400, "Numeric values not allowed or capitalName should not be empty"));
			} else {
				cities = capitalCityRepository.findByCapital(capitalCityName);
			}

			return ResponseEntity.ok().body(cities);
		} catch (Exception e) {
			return ((BodyBuilder) ResponseEntity.notFound()).body(new ErrorCodes(404, "NOT FOUND"));

		}

	}

	@GetMapping(value = { "admin/getAllCountries" }, produces = "application/json")

	public ResponseEntity<?> getAllCountries() {
		logger.info("**********************in getAllCountries*************************");

		List<Countries> country;
		try {
			country = capitalCityRepository.findAll();

			return ResponseEntity.ok().body(country);
		} catch (Exception e) {
			return new ResponseEntity<String>("Soemthing went wrong", new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = { "getByCountryCode/{countryCode}" }, produces = "application/json")
	public ResponseEntity<?> getByCountryCode(@PathVariable String countryCode) {
		Optional<Countries> retrivedCountryCode = null;
		try {
			logger.info("**********************in getByCountryCode*************************");

			countryCode = countryCode.toUpperCase();
			if (countryservice.validateInputValue(countryCode)) {
				return ((BodyBuilder) ResponseEntity.badRequest())
						.body(new ErrorCodes(400, "Numeric country code not allowed or code should not be empty"));
			} else {
				if (countryCode.length() == 2) {
					retrivedCountryCode = capitalCityRepository.findByalpha2Code(countryCode);
				} else if (countryCode.length() == 3) {
					retrivedCountryCode = capitalCityRepository.findByalpha3Code(countryCode);
				} else {
					return ((BodyBuilder) ResponseEntity.notFound())
							.body(new ErrorCodes(404, "Country code must be either 2 or 3 alphabets only"));

				}

			}
			return ResponseEntity.ok().body(retrivedCountryCode);

		} catch (Exception e) {

			e.printStackTrace();
			/*
			 * return new ResponseEntity<String>("Something went wrong", new HttpHeaders(),
			 * HttpStatus.INTERNAL_SERVER_ERROR);
			 */
			return ((BodyBuilder) ResponseEntity.notFound()).body(new ErrorCodes(404, "NOT FOUND"));
		}

	}

	@GetMapping(value = { "getCountriesByRegion/{regionName}" }, produces = "application/json")
	public ResponseEntity<?> getCountriesByRegion(@PathVariable String regionName) {
		List<Countries> countriesByRegion = new ArrayList<Countries>();
		try {
			logger.info("**********************in getCountriesByRegion*************************");
			if (countryservice.validateInputValue(regionName)) {
				return ((BodyBuilder) ResponseEntity.badRequest())
						.body(new ErrorCodes(400, "Numeric  region name not allowed "));
			} else {
				if (regionName.equalsIgnoreCase("america")) {
					regionName = regionName.concat("s");
				}

				countriesByRegion = capitalCityRepository.findByRegion(regionName);
				logger.info("Countries by region " + regionName + " " + countriesByRegion.size());
			}
			return ResponseEntity.ok().body(countriesByRegion);

		} catch (Exception e) {
			e.printStackTrace();
			return ((BodyBuilder) ResponseEntity.notFound()).body(new ErrorCodes(404, "NOT FOUND"));

		}
	}

	@GetMapping(value = { "getCountryByName/{name}" }, produces = "application/json")
	public ResponseEntity<?> getCountryByNativeName(@PathVariable String name) {
		Optional<Countries> countryByName = null;
		try {
			logger.info("**********************in getCountryByNativeName*************************");
			if (countryservice.validateInputValue(name)) {
				return ((BodyBuilder) ResponseEntity.badRequest())
						.body(new ErrorCodes(400, "Numeric   name not allowed "));
			} else {

				countryByName = capitalCityRepository.findByName(name);
				logger.info("Countries by Name " + name + " " + countryByName);
			}
			return ResponseEntity.ok().body(countryByName);

		} catch (Exception e) {
			e.printStackTrace();
			return ((BodyBuilder) ResponseEntity.notFound()).body(new ErrorCodes(404, "NOT FOUND"));

		}
	}

	@GetMapping(value = { "getCountriesByLangauge/{languageName}" }, produces = "application/json")

	public ResponseEntity<?> getCountriesByLangauge(@PathVariable String languageName) {
		List<Languages> languages = new ArrayList<Languages>();
		Optional<Countries> CountriesFoundByLanguage = null;
		List<Countries> countriesByLangauge = new ArrayList<Countries>();
		try {
			logger.info("**********************in getCountriesByLangauge *************************");

			if (countryservice.validateInputValue(languageName)) {
				return ((BodyBuilder) ResponseEntity.badRequest())
						.body(new ErrorCodes(400, "Numeric  language name not allowed "));
			}

			else {

				languages = languageRepository.findByName(languageName);
				logger.info("language size " + languages.size());
				for (int i = 0; i < languages.size(); i++) {
					logger.info("i *********" + i);
					CountriesFoundByLanguage = capitalCityRepository.findById(languages.get(i).getCapital_id());
					countriesByLangauge.add(CountriesFoundByLanguage.get());
				}

				logger.info("Countries by countriesByLangauge " + languageName + " Size " + countriesByLangauge.size()
						+ "\n " + countriesByLangauge.toString());
			}
			return ResponseEntity.ok().body(countriesByLangauge);

		} catch (Exception e) {
			e.printStackTrace();
			return ((BodyBuilder) ResponseEntity.notFound()).body(new ErrorCodes(404, "NOT FOUND"));

		}
	}

	@GetMapping(value = { "getCountriesByRegionalAcronym/{regionalAcronym}" }, produces = "application/json")
	public ResponseEntity<?> getCountriesByRegionalAcronym(@PathVariable String regionalAcronym) {
		List<RegionalBlocs> regionalblock = new ArrayList<RegionalBlocs>();
		Optional<Countries> CountriesFoundByRegionalAcronym = null;
		List<Countries> countriesByRegionalAcronym = new ArrayList<Countries>();
		try {
			logger.info("**********************in getCountriesByRegionalAcronym *************************");

			if (countryservice.validateInputValue(regionalAcronym)) {
				return ((BodyBuilder) ResponseEntity.badRequest())
						.body(new ErrorCodes(400, "Numeric  Acronym name not allowed "));
			}

			else {

				regionalblock = regionalBlockAcronymRepo.findByAcronym(regionalAcronym);
				logger.info("language size " + regionalblock.size());
				for (int i = 0; i < regionalblock.size(); i++) {
					logger.info("i *********" + i);
					CountriesFoundByRegionalAcronym = capitalCityRepository
							.findById(regionalblock.get(i).getCapital_id());
					countriesByRegionalAcronym.add(CountriesFoundByRegionalAcronym.get());
				}

				logger.info("Countries by countriesByRegionalAcronym " + regionalAcronym + " Size "
						+ countriesByRegionalAcronym.size() + "\n " + countriesByRegionalAcronym.toString());
			}
			return ResponseEntity.ok().body(countriesByRegionalAcronym);

		} catch (Exception e) {
			e.printStackTrace();
			return ((BodyBuilder) ResponseEntity.notFound()).body(new ErrorCodes(404, "NOT FOUND"));

		}
	}

	@GetMapping(value = { "getByCurrencyName/{currencyName}" }, produces = "application/json")
	public ResponseEntity<?> getByCurrencyFullName(@PathVariable String currencyName) {
		List<Currencies> countryCurrency = new ArrayList<Currencies>();
		Optional<Countries> countriesFoundByCurrencies = null;
		List<Countries> countriesByCurrency = new ArrayList<Countries>();
		try {
			logger.info("**********************in getByCurrencyFullName *************************");

			if (countryservice.validateInputValue(currencyName)) {
				return ((BodyBuilder) ResponseEntity.badRequest())
						.body(new ErrorCodes(400, "Numeric  Currency Name not allowed "));
			}

			else {

				countryCurrency = currencyRepository.findByName(currencyName);
				logger.info("countryCurrency size " + countryCurrency.size());
				for (int i = 0; i < countryCurrency.size(); i++) {
					logger.info("i *********" + i);
					countriesFoundByCurrencies = capitalCityRepository.findById(countryCurrency.get(i).getCapital_id());
					countriesByCurrency.add(countriesFoundByCurrencies.get());
				}

				logger.info("Countries by countriesCurrencyName " + currencyName + " Size " + countriesByCurrency.size()
						+ "\n " + countriesByCurrency.toString());
			}
			return ResponseEntity.ok().body(countriesByCurrency);

		} catch (Exception e) {
			e.printStackTrace();
			return ((BodyBuilder) ResponseEntity.notFound()).body(new ErrorCodes(404, "NOT FOUND"));

		}
	}

	@GetMapping(value = { "getByCurrencyCode/{currencyCode}" }, produces = "application/json")
	public ResponseEntity<?> getByCurrencyCode(@PathVariable String currencyCode) {
		List<Currencies> countryCurrencyCode = new ArrayList<Currencies>();
		Optional<Countries> countriesFoundByCurrencyCode = null;
		List<Countries> countriesByCurrencyCode = new ArrayList<Countries>();
		try {
			logger.info("**********************in getByCurrencyFullName *************************");

			if (countryservice.validateInputValue(currencyCode)) {
				return ((BodyBuilder) ResponseEntity.badRequest())
						.body(new ErrorCodes(400, "Numeric Code not allowed "));
			}

			else {

				countryCurrencyCode = currencyRepository.findByCode(currencyCode);
				logger.info("countryCurrency size " + countryCurrencyCode.size());
				for (int i = 0; i < countryCurrencyCode.size(); i++) {
					logger.info("i *********" + i);
					countriesFoundByCurrencyCode = capitalCityRepository
							.findById(countryCurrencyCode.get(i).getCapital_id());
					countriesByCurrencyCode.add(countriesFoundByCurrencyCode.get());
				}

				logger.info("Countries by countriesByCurrencyCode " + currencyCode + " Size "
						+ countriesByCurrencyCode.size() + "\n " + countriesByCurrencyCode.toString());
			}
			return ResponseEntity.ok().body(countriesByCurrencyCode);

		} catch (Exception e) {
			e.printStackTrace();
			return ((BodyBuilder) ResponseEntity.notFound()).body(new ErrorCodes(404, "NOT FOUND"));

		}
	}

	@GetMapping(value = { "getByCurrencySymbol/{currencySymbol}" }, produces = "application/json")
	public ResponseEntity<?> getByCurrencySymbol(@PathVariable String currencySymbol) {
		List<Currencies> countryCurrencySymbol = new ArrayList<Currencies>();
		Optional<Countries> countriesFoundByCurrencySymbol = null;
		List<Countries> countriesByCurrencySymbol = new ArrayList<Countries>();
		try {
			logger.info("**********************in getByCurrencyFullName *************************");

			if (countryservice.validateInputValue(currencySymbol)) {
				return ((BodyBuilder) ResponseEntity.badRequest())
						.body(new ErrorCodes(400, "Numeric Code not allowed "));
			}

			else {

				countryCurrencySymbol = currencyRepository.findBySymbol(currencySymbol);
				logger.info("countryCurrency size " + countryCurrencySymbol.size());
				for (int i = 0; i < countryCurrencySymbol.size(); i++) {
					logger.info("i *********" + i);
					countriesFoundByCurrencySymbol = capitalCityRepository
							.findById(countryCurrencySymbol.get(i).getCapital_id());
					countriesByCurrencySymbol.add(countriesFoundByCurrencySymbol.get());
				}

				logger.info("Countries by countriesByCurrencySymbol " + currencySymbol + " Size "
						+ countriesByCurrencySymbol.size() + "\n " + countriesByCurrencySymbol.toString());
			}
			return ResponseEntity.ok().body(countriesByCurrencySymbol);

		} catch (Exception e) {
			e.printStackTrace();
			return ((BodyBuilder) ResponseEntity.notFound()).body(new ErrorCodes(404, "NOT FOUND"));

		}
	}

}
