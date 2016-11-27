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
public class RoundRobin {
	//This is all temporarily static. 
	static int[][] matchTracker;
	static LinkedList<Match> currentMatches = new LinkedList<Match>();
	static LinkedList<Match> oldMatches = new LinkedList<Match>();
	static LinkedList<Match> matches = new LinkedList<Match>();
	static int teamQuantity;
	static LinkedList<Integer> idleTeams = new LinkedList<Integer>();
	static int[] matchFreq;
	public static void main(String[]args){
		teamQuantity = 10;
		matchTracker = new int[teamQuantity][teamQuantity];
		matchFreq=new int[teamQuantity];
		for(int i = 0;i<teamQuantity;i++){
			idleTeams.add(i);
		}
		for(int i=0;i<teamQuantity;i++){
			Arrays.fill(matchTracker[i], -1);
			matchTracker[i][i]=-2;
		}
		Arrays.fill(matchFreq,0);
		//printMatchTracker();
		boolean verdict = false;
		
		while(verdict==false){
			checkMatchup();
			playMatch();
			checkMatchup();
			endMatch(0);
			boolean check = true;
			for(int i=0;i<teamQuantity;i++){
				for(int j=0;j<teamQuantity;j++){
					if(matchTracker[i][j] == -1 || matchTracker[i][j] == -3 || matchTracker[i][j] == -4){
						check=false;
					}
				}
			}
		System.out.println(Arrays.toString(matchFreq));
		printIdleTeamList();
		printList(matches);
		printMatchTracker();
		if(check==true){
			verdict=true;
		}
		}
		System.out.println(Arrays.toString(matchFreq));
		printIdleTeamList();
		printMatchTracker();
		printList(matches);
	}
	
	
	public static void checkMatchup(){
		if(idleTeams.size()>1){
		for(int i=0;i<idleTeams.size();i++){
			for(int j=i+1;j<idleTeams.size();j++){;
				int t1ID=idleTeams.remove(j);
				int t2ID=idleTeams.remove(i);
				if(matchTracker[t1ID][t2ID] == -1 && matchTracker[t2ID][t1ID] == -1){
					printMatchTracker();
					matchTracker[t1ID][t2ID]=-3;
					matchTracker[t2ID][t1ID]=-3;
					matchup(t1ID,t2ID);
					if(j==idleTeams.size()-1){
						i=i-1;
					}
				}
				else{
					idleTeams.offerFirst(t1ID);
					idleTeams.offerFirst(t2ID);
				}
			}
		}
		}
		else{
			System.out.println("No teams left to matchup");
		}
	}
	public static void matchup(int t1ID,int t2ID){
			//System.out.println("(MATCH QUEUED)");
			Match m = new Match(t1ID,t2ID);
			matches.add(m);	
	}
	public static void playMatch(){
		if(matches.size()>0){
			//System.out.println("++MATCH STARTED++");
			Match m = matches.get(0);
			int t1ID = m.getTeam1();
			int t2ID = m.getTeam2();
			matchTracker[t1ID][t2ID]=-4;
			currentMatches.add(matches.remove());
		}
		else{
			System.out.println("No new matches to play");
		}
	}
	public static void endMatch(int matchID){
		//Ask for the scores here? Or have it added elsewhere and have it stored in object for 
		//future use?
		if(currentMatches.size()>0){
			//System.out.println("--MATCH ENDED--");
			Match finishedMat = currentMatches.remove(matchID);
			oldMatches.add(finishedMat);
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
//			double halfwayPt = Math.floor((idleTeams.size()-1)/2);//Use this for inserting idle teams
			boolean t1completion=checkMatchCompletion(t1ID);
			boolean t2completion=checkMatchCompletion(t2ID);
			if(t1completion == false){
				idleTeams.offerFirst(t1ID);
			}
			if(t2completion == false){
				idleTeams.offerLast(t2ID);
			}
			
		}
		else{
			System.out.println("Match does not exist");
		}
	}
	public static boolean checkMatchCompletion(int tID){
		boolean finished = true; 
		for(int i=0;i<teamQuantity;i++){
			if(matchTracker[tID][i] == -1){
				finished = false;
			}
		}
		return finished;	
	}
	public static void printIdleTeamList(){
		System.out.println("IDLE TEAM LIST-------");
		for(int i=0;i<idleTeams.size();i++){
			System.out.print(idleTeams.get(i)+" ");
		}
		System.out.println("");
	}
	public static void printMatchTracker(){
		for(int j=0;j<teamQuantity;j++){
			for(int i=0;i<teamQuantity;i++){
				System.out.format("%4d",matchTracker[i][j]);
			}
			System.out.println();
		}
	}
	public static void printList(LinkedList<Match> mat){
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
	public static void testMatchPlay(){
		System.out.println("Old Matches");
		printList(oldMatches);
		
		System.out.println("New Matches");
		printList(matches);
		
		System.out.println("Current Matches");
		printList(currentMatches);
		
		System.out.println("----------------");
		
		playMatch();
		
		System.out.println("Old Matches");
		printList(oldMatches);
		
		System.out.println("New Matches");
		printList(matches);
		
		System.out.println("Current Matches");
		printList(currentMatches);
		
		
		endMatch(0);
		
		System.out.println("----------------");
		
		playMatch();
		
		System.out.println("Old Matches");
		printList(oldMatches);
		
		System.out.println("New Matches");
		printList(matches);
		
		System.out.println("Current Matches");
		printList(currentMatches);
	}
}
