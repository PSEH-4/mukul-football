package io.test.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.test.football.entity.League;

@Repository
public interface LeagueRepository  extends JpaRepository<League, Integer>{

}
