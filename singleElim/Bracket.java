
import java.util.ArrayList;
import java.util.Collections;

public class Bracket {
	private TreeS schedule;
	private int t = 0;
	private ArrayList<Match> matches = new ArrayList<Match>();
	private int teamN = 0;
	
	public TreeS seed(ArrayList<Team> teams){
		int n = 0;
		while (Math.pow(2, n)<teams.size()){
			n++;	
		}
		int shawnsucks = (int) Math.pow(2,n);
		//System.out.println(teams.size());
		while (shawnsucks>teams.size()){
			Team bye = new Team("bye");
			teams.add(bye);
			//System.out.println(teams.size());
		}
		//System.out.println(teams.size());
	
		Collections.shuffle(teams);
		for(Team i:teams){
			//System.out.println(i.getName());
		}
		int depth = (int) (Math.floor((Math.log(teams.size()))/ (Math.log(2))));
		schedule = createTree(depth,teams);
		return schedule;
	}
	
	public TreeS createTree (int depth, ArrayList<Team> teams){
		
		//if depth is negative, stop
		for(Team i:teams){
			//System.out.println(i.getName());
		}
		if (depth < 0) return null;
		//create a node in the current position
		TreeS current = new TreeS();
		//if the depth is 0, then it's a leave where a Team needs to be placed
		//from the array of teams with t to iterate through it
		//System.out.println("yeah boi"+t);
		if( depth == 0){
			current.data = teams.get(t);
			//System.out.println(current.data.getName());
			System.out.println(teams.get(t).getTeamName());
			t++;
			//System.out.println("argg"+t);
			//current.setId(teams.size()+1);
			return current;
		}
		
		//if not, then continue expanding the tree
		Match game = new Match(teamN);
		String teamName = "Winner of match "+ teamN;
		Team temp = new Team(teamName);
		game.setWinner(temp);
		matches.add(game);
		
		
		teamN++;
		current.left = createTree(depth-1,teams);
		current.right = createTree(depth-1,teams);
		//create a match with the leaves
		
	
		//creates the match and sends it to the schedule class, one at a time
		//not implemented yet
		//scheduleItem.send(game);
		//the data of the current node is the winner of the match
		//current.data = game.getWinner();
		System.out.println(game.getWinner().getTeamName());
		return current;
	}
	
	
	public static void main (String[] args){
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
			
			Bracket tournament =  new Bracket();
			
			TreeS result = tournament.seed(teams);
	}
	
	
}
