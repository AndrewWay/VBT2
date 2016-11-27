package gui;

import java.util.ArrayList;
import java.util.Collections;

public class singleElimination implements TournamentType {
	private treeS schedule;
	private int t = 0;
	private ArrayList<Match> matches = new ArrayList<Match>();
	private int teamN = 0;
	Coach byecoach= new Coach();
	
	private treeS game;
	public treeS seed(ArrayList<Team> teams){
		int n = 0;
		while (Math.pow(2, n)<teams.size()){
			n++;	
		}
		int shawnsucks = (int) Math.pow(2,n);
		while (shawnsucks>teams.size()){
			Team bye = new Team("bye",null);
			teams.add(bye);
		}
		Collections.shuffle(teams);
		int depth = (int) (Math.floor((Math.log(teams.size()))/ (Math.log(2))));
		schedule = createTree(depth,teams);
		return schedule;
	}
	
	public treeS createTree (int depth, ArrayList<Team> teams){
		
		//if depth is negative, stop
		if (depth < 0) return null;
		//create a node in the current position
		treeS current = new treeS();
		//if the depth is 0, then it's a leave where a team needs to be placed
		//from the array of teams with t to iterate through it
		//System.out.println("yeah boi"+t);
		if( depth == 0){
			current.data = teams.get(t);
			t++;
			return current;
		}
		
		//if not, then continue expanding the tree
		Match game = new Match(teamN);
		String teamName = "Winner of match "+ teamN;
		Team temp = new Team(teamName);
		game.setWinner(temp);
		current.data = game.getWinner();
		matches.add(game);
		
		
		teamN++;
		current.left = createTree(depth-1,teams);
		current.right = createTree(depth-1,teams);
		//create a match with the leaves
		
		//the data of the current node is the winner of the match
		return current;
	}
	
	
	public treeS update (int id, treeS root){
		treeS match = null;
		if( root == null){
			System.out.println("No tree");
			return null;
		}
		if(root.data.getId() == id){
			match =root;
		}
		if (match == null){
			match = update(id, root.left);
		}
		if (match == null){
			match = update(id, root.right);
		}
		return match;
			
			
		
	}
	
	
	
}
