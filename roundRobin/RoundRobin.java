package roundRobin; 

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RoundRobin extends Format{
	private int[][] matchTracker;
	private int[] matchFreq;
	
	
	public RoundRobin(int tq, int cq){
		createNM();
		createCM();
		createOM();
		setTeamQuantity(tq);
		createIdleTeamsList();
		createMatchTracker();
		setNumofCourts(cq);
		matchFreq = new int[tq];
	}
	
	public void createMatchTracker(){
		int tq = getTeamQuantity();
		matchTracker = new int[tq][tq];
		for(int i=0;i<tq;i++){
			Arrays.fill(matchTracker[i], -1);
			matchTracker[i][i]=-2;
		}
	}
	public void checkMatchup(){
		LinkedList<Integer> idleTeamList = getIdleTeams();
		if(idleTeamList.size()>1){
		for(int i=0;i<idleTeamList.size();i++){
			for(int j=i+1;j<idleTeamList.size();j++){
				int t1ID=idleTeamList.remove(j);
				int t2ID=idleTeamList.remove(i);
				if(matchTracker[t1ID][t2ID] == -1 && matchTracker[t2ID][t1ID] == -1){
					matchTracker[t1ID][t2ID]=-3;
					matchTracker[t2ID][t1ID]=-3;
					createMatch(t1ID,t2ID);
					if(j==idleTeamList.size()-1){
						i=i-1;
					}
				}
				else{
					idleTeamList.offerFirst(t1ID);
					idleTeamList.offerFirst(t2ID);
				}
			}
		}
		}
		else{
			System.out.println("No teams left to matchup");
		}
	}
	public void createMatch(int T1ID, int T2ID) {
		Match m = new Match(T1ID,T2ID,getMatchCount());
		incrementMatchCounter();
		getNM().add(m);	
	}
	public void playMatch(){
		
		if(getNM().size()>0 && !(allCourtsOccupied())){
			occupyCourt();//Could easily put in an array to track the courts
			//occupyCourt() is a boolean array with index equal to court id and return value equal to occupancy state
			Match m = getNM().get(0);
			int t1ID = m.getTeam1();
			int t2ID = m.getTeam2();
			int mid = m.getID();
			matchTracker[t1ID][t2ID]=-4;
			matchTracker[t2ID][t1ID]=-4;
			getCM().put(mid,getNM().remove());
		}
		else{ 
			if(!(getNM().size()>0)){
			System.out.println("No new matches to play");
			}
			if(allCourtsOccupied()){
				System.out.println("All courts occupied: match initiation prevented");
			}
		}
	}
	public void endMatch(int matchID){
		//Put in prompt for scores!
		Map CM = getCM();
		if(CM.size()>0 && !(allCourtsEmpty()) && CM.containsKey(matchID)){
			unoccupyCourt();
			Match finishedMat = getCM().remove(matchID);
			getOM().add(finishedMat);
			int t1ID = finishedMat.getTeam1();
			int t2ID = finishedMat.getTeam2();
			matchFreq[t1ID]=matchFreq[t1ID]+1;
			matchFreq[t2ID]=matchFreq[t2ID]+1;
			
			matchTracker[t1ID][t2ID]=0;
			matchTracker[t2ID][t1ID]=0;
			int score1 = finishedMat.getScore1();
			int score2 = finishedMat.getScore2();
			matchTracker[t1ID][t2ID]=ThreadLocalRandom.current().nextInt(0, 100);//score2;
			matchTracker[t2ID][t1ID]=ThreadLocalRandom.current().nextInt(0, 100);//score1;

			boolean t1completion=checkMatchCompletion(t1ID);
			boolean t2completion=checkMatchCompletion(t2ID);	
			if(t1completion == false){
				getIdleTeams().offerFirst(t1ID);
			}
			if(t2completion == false){
				getIdleTeams().offerLast(t2ID);
			}
			
		}
		else{
			System.out.println("Match does not exist");
		}
	}
	public boolean checkMatchCompletion(int tID){
		boolean finished = true; 
		for(int i=0;i<getTeamQuantity();i++){
			if(matchTracker[tID][i] == -1){
				finished = false;
			}
		}
		return finished;	
	}

	public void printMatchTracker(){
		for(int j=0;j<getTeamQuantity();j++){
			for(int i=0;i<getTeamQuantity();i++){
				System.out.format("%4d",matchTracker[i][j]);
			}
			System.out.println("\n");
		}
	}
	public void printNM(){
		System.out.println("New matches");
		printList(getNM());
	}
	public void printCM(){
		Map<Integer,Match> CM = getCM();
		System.out.println("Current matches");
		if(CM.size()>0){
		for(int key: CM.keySet()){
			System.out.println("ID: "+key + " T1: " + CM.get(key).getTeam1()+" T2: " + CM.get(key).getTeam2());
		}
		}
		else{
			System.out.println("NO CURRENT MATCHES");
		}
	}
	public void printOM(){
		System.out.println("Old matches");
		printList(getOM());
	}
}