package roundRobin;

public class Match {
	int team1,team2,score1,score2,id;
	public Match(int t1,int t2,int id){
		team1=t1;
		team2=t2;
		score1=1;
		score2=2;//DEFAULT SCORES
		this.id=id;
	}
	public int getID(){
		return id;
	}
	public int getTeam1(){
		return team1;
	}
	public int getTeam2(){
		return team2;
	}
	public void setTeam1(int t1){
		team1 = t1;
	}
	public void setTeam2(int t2){
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
}
