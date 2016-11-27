package roundRobin;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public abstract class Format {
	private LinkedList<Integer> idleTeams;
	private LinkedList<Match> newMatches;
	private LinkedList<Match> currentMatches;
	private LinkedList<Match> oldMatches;
	private int teamQuantity;
	private int num_of_courts;
	private int occ_courts;
	private String desc;
	
	public abstract void checkMatchup();
	public abstract void createMatch(int T1ID,int T2ID);
	public abstract void playMatch();
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
	public LinkedList<Integer> getIdleTeams(){
		return idleTeams;
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
	public void createIdleTeamsList(){
		this.idleTeams = new LinkedList<Integer>();
		for(int i = 0;i<teamQuantity;i++){
			idleTeams.add(i);
		}
	}
	public void setTeamQuantity(int tq){
		teamQuantity=tq;
	}
	public void setNumofCourts(int num){
		num_of_courts = num;
	}
	public int getNumOfCourts(){
		return num_of_courts;
	}
	public boolean unoccupyCourt(){
		boolean occupiedCourt;
		if(occ_courts>0){
			occ_courts--;
			occupiedCourt = true;
		}
		else{
			System.out.println("All courts are already empty");
			occupiedCourt = false;
		}
		return occupiedCourt;
	}
	public boolean occupyCourt(){
		boolean vacantCourt;
		if(occ_courts<num_of_courts){
			occ_courts++;
			vacantCourt = true;
		}
		else{
			System.out.println("No free courts: match initiation prevented");
			vacantCourt = false;
		}
		return vacantCourt;
	}
	public void printList(LinkedList<Match> mat){
		if(mat.size()==0){
			System.out.println("NO MATCHES");
		}
		else{
			for(int i=0;i<mat.size();i++){
				Match m = mat.get(i);
				int t1 = m.getTeam1();
				int t2 = m.getTeam2();
				System.out.println("Match "+i+": "+t1+" "+t2);
			}
		}
		System.out.println();
	}
	public void printIdleTeamList(){
		System.out.println("IDLE TEAM LIST-------");
		for(int i=0;i<idleTeams.size();i++){
			System.out.print(idleTeams.get(i)+" ");
		}
		System.out.println("");
	}
}
