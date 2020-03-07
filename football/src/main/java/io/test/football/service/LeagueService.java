package io.test.football.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.test.football.entity.League;
import io.test.football.exception.DataNotFoundException;

@Service
public interface LeagueService {
	public List<League> getAll();
	
	public League getById(int leagueId) throws DataNotFoundException;
	
	public boolean add(League league);
	
	public boolean update(League league);
	
	public void delete(League league);
}
