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
import io.test.football.entity.League;
import io.test.football.exception.DataNotFoundException;
import io.test.football.service.LeagueService;

@RestController
@Api(tags = "League Controller")
@RequestMapping(path = "/league")
public class LeagueController {

	private static final String COUNTRY_ADDED = "League added successfully";
	private static final String COUNTRY_NOT_ADDED = "League not added successfully";
	private static final String COUNTRY_UPDATED = "League updated successfully";
	private static final String COUNTRY_NOT_UPDATED = "League not updated successfully";
	private static final String COUNTRY_NOT_DELETED = "League not deleted. league name not matching with id";
	private static final String COUNTRY_DELETED = "League deleted";


	@Autowired
	LeagueService leagueService;

	@GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> search() {
		List<League> leagues = leagueService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(leagues);
	}

	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getById(@PathVariable int leagueId) {
		try {
			League league = leagueService.getById(leagueId);
			return ResponseEntity.status(HttpStatus.OK).body(league);
		} catch (DataNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}

	}

	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> add(@RequestBody League league) {
		if (leagueService.add(league))
			return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_ADDED);
		return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_NOT_ADDED);
	}

	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody League league) {
		if (leagueService.update(league))
			return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_UPDATED);
		return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_NOT_UPDATED);
	}

	@DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(@RequestBody League league) {
		League tempLeague =null;
		try {
			tempLeague = leagueService.getById(league.getLeagueId());
		} catch (DataNotFoundException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		if (tempLeague.getLeagueName().matches(league.getLeagueName())) {
			leagueService.delete(tempLeague);
			return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_DELETED);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(COUNTRY_NOT_DELETED);
		}
	}

}
