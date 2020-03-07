package io.test.football.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.test.football.entity.Team;
import io.test.football.exception.DataNotFoundException;

@Service
public interface TeamService {
	public List<Team> getAll();
	
	public Team getById(int teamId) throws DataNotFoundException;
	
	public boolean add(Team team);
	
	public boolean update(Team team);
	
	public void delete(Team team);
}

