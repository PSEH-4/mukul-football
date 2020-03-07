package io.test.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.test.football.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{
	
}