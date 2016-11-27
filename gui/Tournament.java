package gui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import roundRobin.Format;

public class Tournament implements Serializable {
	private Format type;
	private String location, name;
	private String deadline;
	private ArrayList<Team> teams;

	public Tournament(String name, Format type, String location, String deadline) {
		this.name = name;
		this.type = type; 
		this.location = location;
		this.deadline = deadline;
		this.teams = new ArrayList<Team>();
	}

	public Tournament() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Format getType() {
		return type;
	}

	public void setType(Format type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	// Change publish for whatever the system needs
	public void publishDeadline() {
		System.out.println("deadline" + deadline);
	}

	public void register(Team x) {
		this.teams.add(x);
	}
}