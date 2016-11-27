package roundRobin; 

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
/*
 * ALGORITHM FLOW
 * Step 1: Create a collection of teams
 * Step 2: Create a team stat tracker array
 * Step 3: Create a queue of teams that aren't playing 
 * Step 4: Create a linkedlist to contain all the created match objects
 * Step 5: Create match objects from the idleTeams Queue
 * Step 6: Put teams that have just finished a match back into the idle teams queue
 * Step 6a: Ensure that teams aren't matched up again immediately
 * Step 6b: Put the scores of finished matches into stat tracker 
 * Step 7: Continue matchplay by pairing teams up into new matches 
 * 
*/
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
		Match m = new Match(T1ID,T2ID);
		getNM().add(m);	
	}
	public void playMatch(){
		
		if(getNM().size()>0 && occupyCourt()){
			Match m = getNM().get(0);
			int t1ID = m.getTeam1();
			int t2ID = m.getTeam2();
			matchTracker[t1ID][t2ID]=-4;
			getCM().add(getNM().remove());
		}
		else if(!(getNM().size()>0)){
			System.out.println("No new matches to play");
		}
	}
	public void endMatch(int matchID){
		//Ask for the scores here? Or have it added elsewhere and have it stored in object for 
		//future use?
		if(getCM().size()>0 && unoccupyCourt()){
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
//			if(t1ID==1 || t2ID == 1){
//				System.out.println();
//				System.out.printf("MATCH: %d %d SCORE: %d %d\n",t1ID,t2ID,score1,score2);
//				//printMatchTracker();
//			}
//			double halfwayPt = Math.floor((idleTeamList.size()-1)/2);//Use this for inserting idle teams
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
		System.out.println("Current matches");
		printList(getCM());
	}
	public void printOM(){
		System.out.println("Old matches");
		printList(getOM());
	}


//	public static void main(String[]args){
//	teamQuantity = 50;
//	matchTracker = new int[teamQuantity][teamQuantity];
//	matchFreq=new int[teamQuantity];
//	for(int i = 0;i<teamQuantity;i++){
//		idleTeamList.add(i);
//	}
//	for(int i=0;i<teamQuantity;i++){
//		Arrays.fill(matchTracker[i], -1);
//		matchTracker[i][i]=-2;
//	}
//	Arrays.fill(matchFreq,0);
//	//printMatchTracker();
//	boolean verdict = false;
//	
//	while(verdict==false){
//		checkMatchup();
//		playMatch();
//		checkMatchup();
//		endMatch(0);
//		boolean check = true;
//		for(int i=0;i<teamQuantity;i++){
//			for(int j=0;j<teamQuantity;j++){
//				if(matchTracker[i][j] == -1 || matchTracker[i][j] == -3 || matchTracker[i][j] == -4){
//					check=false;
//				}
//			}
//		}
//	System.out.println(Arrays.toString(matchFreq));
//	printIdleTeamList();
//	printList(newMatches);
//	printMatchTracker();
//	if(check==true){
//		verdict=true;
//	}
//	}
//	System.out.println(Arrays.toString(matchFreq));
//	printIdleTeamList();
//	printMatchTracker();
//	printList(newMatches);
//}


}