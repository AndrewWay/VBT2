package matchmakers;

import java.util.ArrayList;

import matchmaking.TreeS;
import personnel.Team;
import gui.Tournament;

public class BracketTester {
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
			
			
			
			ArrayList<Team> teams= new ArrayList<Team>();
			
			Team a = new Team("Team a");
			Team b = new Team("Team b");
			Team c = new Team("Team c");
			Team d = new Team("Team d");
			Team e = new Team("Team e");
			Team f = new Team("Team f");
			Team g = new Team("Team g");
			Team y = new Team("Team y");
			//Team z = new Team("Team z");
			
			teams.add(a);
			teams.add(b);
			teams.add(c);
			teams.add(d);
			teams.add(e);
			teams.add(f);
			teams.add(g);
			teams.add(y);
			//teams.add(z);
			
			Bracket tournament =  new Bracket(1,2);
			
			TreeS result = tournament.seed(teams);
			
	}
}
