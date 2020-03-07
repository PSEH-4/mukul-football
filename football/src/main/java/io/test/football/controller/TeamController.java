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
import io.test.football.entity.Team;
import io.test.football.exception.DataNotFoundException;
import io.test.football.service.TeamService;

@RestController
@Api(tags = "Team Controller")
@RequestMapping(path = "/team")
public class TeamController {

	private static final String COUNTRY_ADDED = "Team added successfully";
	private static final String COUNTRY_NOT_ADDED = "Team not added successfully";
	private static final String COUNTRY_UPDATED = "Team updated successfully";
	private static final String COUNTRY_NOT_UPDATED = "Team not updated successfully";
	private static final String COUNTRY_NOT_DELETED = "Team not deleted. team name not matching with id";
	private static final String COUNTRY_DELETED = "Team deleted";


	@Autowired
	TeamService teamService;

	@GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> search() {
		List<Team> teams = teamService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(teams);
	}

	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getById(@PathVariable int teamId) {
		try {
			Team team = teamService.getById(teamId);
			return ResponseEntity.status(HttpStatus.OK).body(team);
		} catch (DataNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}

	}

	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> add(@RequestBody Team team) {
		if (teamService.add(team))
			return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_ADDED);
		return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_NOT_ADDED);
	}

	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody Team team) {
		if (teamService.update(team))
			return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_UPDATED);
		return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_NOT_UPDATED);
	}

	@DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(@RequestBody Team team) {
		Team tempTeam =null;
		try {
			tempTeam = teamService.getById(team.getTeamId());
		} catch (DataNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		if (tempTeam.getTeamName().matches(team.getTeamName())) {
			teamService.delete(team);
			return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_DELETED);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_NOT_DELETED);
		}
	}

	
}
