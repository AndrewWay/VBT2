package roundRobin;

import gui.Tournament;
import singleElim.*;
import java.util.Scanner;

public class tester {
	public static void main(String[]args){
		
		//Replace input values with methods from GUI class
		String tournaName = "Fiech Tournament";
		String location = "EN1054";
		String date = "Today";
		int num_of_teams = 5;
		int num_of_courts = 1;
		RoundRobin form = new RoundRobin(num_of_teams,num_of_courts);
		//Tournament needs to pass integer to RoundRobin. 
		Tournament tourney = new Tournament(tournaName,form,location,date);
		
		form.printIdleTeamList();
		form.checkMatchup();
		form.printMatchTracker();
		//USER INPUT BEGINS HERE
		Scanner in = new Scanner(System.in);
		boolean breakLoop = false;
		while(breakLoop == false){
			form.printNM();
			form.printCM();
			form.printOM();
			form.printIdleTeamList();
			
			String input = in.next();
			if(input.equals("1")){
				form.checkMatchup();
			}
			if(input.equals("2")){
				form.playMatch();
			}
			if(input.equals("3")){
				form.endMatch(0);
			}
			if(input.equals("4")){
				System.out.println("Exiting");
				breakLoop = true;
			}
		}
		in.close();
	}
}
