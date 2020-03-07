package io.test.football.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.test.football.entity.Country;
import io.test.football.entity.League;
import io.test.football.exception.DataNotFoundException;
import io.test.football.repository.LeagueRepository;
import io.test.football.service.LeagueService;

@Component
public class LeagueServiceImpl implements LeagueService{

	private static final String NOT_FOUND = "Leage not found in DB";
	
	@Autowired
	LeagueRepository repository;
	
	@Override
	public List<League> getAll() {
		return repository.findAll();
	}

	@Override
	public League getById(int leagueId) throws DataNotFoundException {
		// TODO Auto-generated method stub
		try {
			return repository.getOne(leagueId);
		}catch(EntityNotFoundException e) {
			throw new DataNotFoundException(NOT_FOUND, e);
		}
	}

	@Override
	public boolean add(League league) {
		// TODO Auto-generated method stub
		if(repository.save(league)!= null)
			return true;
		return false;
	}

	@Override
	public boolean update(League league) {
		// TODO Auto-generated method stub
		League tempLeague = repository.getOne(league.getLeagueId());
		tempLeague.setLeagueName(league.getLeagueName());
		return add(tempLeague);

	}

	@Override
	public void delete(League league) {
		// TODO Auto-generated method stub
		repository.delete(league);
	}

}
