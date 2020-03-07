package io.test.football.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.test.football.entity.Country;
import io.test.football.exception.DataNotFoundException;
import io.test.football.service.CountryService;

@RestController
@Api(tags = "Country Controller")
@RequestMapping(path = "/country")
public class CountryController {

	private static final String COUNTRY_ADDED = "Country added successfully";
	private static final String COUNTRY_NOT_ADDED = "Country not added successfully";
	private static final String COUNTRY_UPDATED = "Country updated successfully";
	private static final String COUNTRY_NOT_UPDATED = "Country not updated successfully";
	private static final String COUNTRY_NOT_DELETED = "Country not deleted. country name not matching with id";
	private static final String COUNTRY_DELETED = "Country deleted";

	@Autowired
	CountryService countryService;

	@GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> search() {
		List<Country> countries = countryService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(countries);
	}

	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getById(@PathVariable int countryId) {
		try {
			Country country = countryService.getById(countryId);
			return ResponseEntity.status(HttpStatus.OK).body(country);
		} catch (DataNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}

	}

	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> add(@RequestBody Country country) {
		if (countryService.add(country))
			return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_ADDED);
		return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_NOT_ADDED);
	}

	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody Country country) {
		if (countryService.update(country))
			return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_UPDATED);
		return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_NOT_UPDATED);
	}

	@DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(@RequestBody Country country) {
		Country tempCountry =null;
		try {
			tempCountry = countryService.getById(country.getCountryId());
		} catch (DataNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		if (tempCountry.getCountryName().matches(country.getCountryName())) {
			countryService.delete(tempCountry);
			return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_DELETED);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_NOT_DELETED);
		}
	}

}
