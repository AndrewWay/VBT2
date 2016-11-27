package gui;


public class Match{
	private Team team1,team2;
	private int score1,score2;
	private Team winner;
	boolean played;
	private int id;
	
	//sets a winner if the other team is null 
	public Match(Team t,Team t2){
		if(t.getTeamName() == "bye") winner = t2;
		else if(t2.getTeamName() == "bye") winner = t;
		
		team1=t;
		team2=t2;
		played=false;
		t.setId(id);
		t2.setId(id);
	}
	public Match(int id) {
		Team temp1 = new Team("temp1");
		Team temp2 = new Team("temp2");
		this.id = id;
		team1=temp1;
		team2=temp2;
		team1.setId(id);
		team2.setId(id);
	}
	public Team getTeam1(){
		return team1;
	}
	public Team getTeam2(){
		return team2;
	}
	public void setTeam1(Team t1){
		team1 = t1;
	}
	public void setTeam2(Team t2){
		team2 = t2;
	}
	public void setScore1(int s1){
		score1=s1;
	}
	public void setScore2(int s2){
		score2=s2;
	}
	public int getScore1(){
		return score1;
	}
	public int getScore2(){
		return score2;
	}
	//sets a winner based on scores if there isn't one set already
	public Team getWinner(){
		if(winner != null){
			return winner;
		}
		else {
			if(score1>score2){
				winner = team1;
				return winner;
			}
			else {
				winner = team2;
				return winner;
			}
		}
	}
	//class called in schedule to input the scores
	public void gamePlayed(int score1, int score2){
		this.setScore1(score1);
		this.setScore2(score2);
		this.played = true;
	}
	public void tString() {
		System.out.println("Match [team1=" + team1.getTeamName() + ", team2=" + team2.getTeamName() + "]");
	}
	public void setId(int teamN) {
		this.id = teamN;
		
	}
	public int getId() {
		return id;
		
	}
	public void setWinner(Team temp) {
		this.winner = temp;
		
	}
	
	

}
