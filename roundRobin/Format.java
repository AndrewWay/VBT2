package roundRobin;

import java.util.LinkedList;

public abstract class Format {
	private LinkedList<Integer> idleTeams;
	private LinkedList<Match> newMatches;
	private LinkedList<Match> currentMatches;
	private LinkedList<Match> oldMatches;
	private int teamQuantity;
	private String desc;
	
	public abstract void checkMatchup();
	public abstract void createMatch(int T1ID,int T2ID);
	public abstract void playMatch(int matchID);
	public abstract void endMatch(int matchID);
	
	public LinkedList<Match> getNM(){
		return newMatches;
	}
	public LinkedList<Match> getCM(){
		return currentMatches;
	}
	public LinkedList<Match> getOM(){
		return oldMatches;
	}
	public int getTeamQuantity(){
		return teamQuantity;
	}
	public void createNM(){
		this.newMatches=new LinkedList<Match>();
	}
	public void createCM(){
		this.currentMatches=new LinkedList<Match>();
	}
	public void createOM(){
		this.oldMatches=new LinkedList<Match>();
	}
	public void setTeamQuantity(int tq){
		teamQuantity=tq;
	}
}
