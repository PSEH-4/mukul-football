package io.test.football.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.test.football.entity.Country;
import io.test.football.exception.DataNotFoundException;

@Service
public interface CountryService {

	public List<Country> getAll();
	
	public Country getById(int countryId) throws DataNotFoundException;
	
	public boolean add(Country country);
	
	public boolean update(Country country);
	
	public void delete(Country country);
	
}
