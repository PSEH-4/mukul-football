package io.test.football.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="team")
public class Team {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="team_id")
	private int teamId;
	
	@Column(name="team_name")
	private String teamName;

	@Column(name="team_size")
	private int teamSize;
	
	@ManyToOne
    @JoinColumn(name="country_id", nullable=false)
    private Country country;
	
	@ManyToOne
    @JoinColumn(name="league_id")
    private League league;

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getTeamSize() {
		return teamSize;
	}

	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Team(int teamId, String teamName, int teamSize, Country country, League league) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamSize = teamSize;
		this.country = country;
		this.league = league;
	}

	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}