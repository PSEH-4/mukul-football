package io.test.football.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.test.football.entity.Country;
import io.test.football.exception.DataNotFoundException;
import io.test.football.repository.CountryRepository;
import io.test.football.service.CountryService;

@Component
public class CountryServiceImpl implements CountryService {

	private static final String NOT_FOUND = "Country not found in DB";
	
	@Autowired
	CountryRepository countryRepository;

	@Override
	public List<Country> getAll() {
		return countryRepository.findAll();
	}

	@Override
	public boolean add(Country country) {
		if (countryRepository.save(country) != null)
			return true;
		return false;
	}

	@Override
	public boolean update(Country country) {
		Country tempCountry = countryRepository.getOne(country.getCountryId());
		tempCountry.setCountryName(country.getCountryName());
		return add(tempCountry);
	}

	@Override
	public void delete(Country country) {
		countryRepository.delete(country);
	}

	@Override
	public Country getById(int countryId) throws DataNotFoundException {
		try {
			return countryRepository.getOne(countryId);
		} catch (EntityNotFoundException e) {
			throw new DataNotFoundException(NOT_FOUND, e);
		}
	}

}
