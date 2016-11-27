
import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable{
	private String teamName;
	private Coach coach;
	private ArrayList<Player> players;
	private String stats;
	private int id;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

	public Team(String teamName, Coach coach) {
		this.teamName = teamName;
		this.coach = coach;
		this.players = new ArrayList<Player>();
	}

	public Team(String string) {
		teamName = string;
	}

	public void addPlayer(Player p) {
		this.players.add(p);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
