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
@Table(name="league")
public class League {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="league_id")
	private int leagueId;
	
	@Column(name="league_name")
	private String leagueName;

	@Column(name="league_size")
	private int leagueSize;
	
	@OneToMany(mappedBy="league")
    private Set<Country> country;
	
	@OneToMany(mappedBy="league")
    private Set<Team> team;

	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public int getLeagueSize() {
		return leagueSize;
	}

	public void setLeagueSize(int leagueSize) {
		this.leagueSize = leagueSize;
	}

	public Set<Country> getCountry() {
		return country;
	}

	public void setCountry(Set<Country> country) {
		this.country = country;
	}

	public Set<Team> getTeam() {
		return team;
	}

	public void setTeam(Set<Team> team) {
		this.team = team;
	}

	public League(int leagueId, String leagueName, int leagueSize, Set<Country> country, Set<Team> team) {
		super();
		this.leagueId = leagueId;
		this.leagueName = leagueName;
		this.leagueSize = leagueSize;
		this.country = country;
		this.team = team;
	}

	public League() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
