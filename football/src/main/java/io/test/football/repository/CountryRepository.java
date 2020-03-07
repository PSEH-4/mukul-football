package io.test.football.repository;

import org.springframework.stereotype.Repository;

import io.test.football.entity.Country;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>{

}
