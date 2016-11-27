package roundRobin;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public abstract class Format {
	private LinkedList<Integer> idleTeams;
	private LinkedList<Match> newMatches;
	private Map<Integer,Match> currentMatches;
	private LinkedList<Match> oldMatches;
	private int teamQuantity;
	private int num_of_courts;
	private int occ_courts;
	private int num_of_matches;
	private String desc;
	
	public abstract void checkMatchup();
	public abstract void createMatch(int T1ID,int T2ID);
	public abstract void playMatch();
	public abstract void endMatch(int matchID);
	
	public LinkedList<Match> getNM(){
		return newMatches;
	}
	public Map<Integer,Match> getCM(){
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
		this.currentMatches=new HashMap<Integer,Match>();
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
	public void unoccupyCourt(){
		if(!(allCourtsEmpty())){
			occ_courts--;
		}
		else{
			System.out.println("All courts are already empty");
		}
	}
	public void incrementMatchCounter(){
		num_of_matches++;
	}
	public int getMatchCount(){
		return num_of_matches;
	}
	public boolean allCourtsOccupied(){
		boolean verdict;
		if(occ_courts<num_of_courts){
			verdict = false;
		}
		else{
			verdict = true;
		}
		return verdict;
	}
	public boolean allCourtsEmpty(){
		boolean verdict;
		if(occ_courts>0){
			verdict = false;
		}
		else{
			verdict = true;
		}
		return verdict;
	}
	public void occupyCourt(){
		if(!(allCourtsOccupied())){
			occ_courts++;
		}
		else{
			System.out.println("No free courts: match initiation prevented");
		}
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
				int id = m.getID();
				System.out.println("Match "+id+": "+t1+" "+t2);
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
