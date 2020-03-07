package io.test.football.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "country_id")
	private int countryId;

	@Column(name = "country_name")
	private String countryName;

	@OneToMany(mappedBy = "country")
	private Set<Team> team;

	@ManyToOne
    @JoinColumn(name="league_id", nullable=false)
    private League league;

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Set<Team> getTeams() {
		return team;
	}

	public void setTeams(Set<Team> teams) {
		this.team = teams;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Country(int countryId, String countryName, Set<Team> teams, League league) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.team = teams;
		this.league = league;
	}

	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
