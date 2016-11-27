package gui;


import java.util.ArrayList;

public class Schedule {
ArrayList <Match> current = new ArrayList<Match>();
ArrayList <Match> previous = new ArrayList<Match>();

public void addMatch(Team a, Team b){
	Match x = new Match(a,b);
	current.add(x);
}
}
