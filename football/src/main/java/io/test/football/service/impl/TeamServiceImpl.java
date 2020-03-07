package io.test.football.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.test.football.entity.Team;
import io.test.football.exception.DataNotFoundException;
import io.test.football.repository.TeamRepository;
import io.test.football.service.TeamService;

@Component
public class TeamServiceImpl implements TeamService {

	private static final String NOT_FOUND = "Leage not found in DB";

	@Autowired
	TeamRepository repository;

	@Override
	public List<Team> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Team getById(int teamId) throws DataNotFoundException {
		// TODO Auto-generated method stub
		try {
			return repository.getOne(teamId);
		} catch (EntityNotFoundException e) {
			throw new DataNotFoundException(NOT_FOUND, e);
		}
	}

	@Override
	public boolean add(Team team) {
		// TODO Auto-generated method stub
		if (repository.save(team) != null)
			return true;
		return false;
	}

	@Override
	public boolean update(Team team) {
		// TODO Auto-generated method stub
		Team tempTeam = repository.getOne(team.getTeamId());
		tempTeam.setCountry(team.getCountry());
		tempTeam.setLeague(team.getLeague());
		tempTeam.setTeamName(team.getTeamName());
		return add(tempTeam);
	}

	@Override
	public void delete(Team team) {
		// TODO Auto-generated method stub
		repository.delete(team);
	}

}
