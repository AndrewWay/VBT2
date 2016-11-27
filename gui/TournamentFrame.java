package gui;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

//All such classes could easily be taken under one abstract classes since there is so much generalization to be made
public class TournamentFrame extends JFrame {

	private JPanel content_panel, list_panel, button_panel;
	private JButton choose_b,teamAdd_b, teamView_b, schedule_b, refresh_b;
	private JList team_list;
	private JScrollPane listScroller;
	
	private DefaultListModel team;

	private String name;
	
	private ArrayList<Team> teamList;
	
	private ReadObjectList reader = new ReadObjectList();
	private WriteObjectList writer = new WriteObjectList();
	private int frameWidth = 500;
	private int frameHeight = 300;
	
	private String teamListFile;

	// public Tournament_Frame(Tournament tournament) {
	public TournamentFrame(Tournament t) {
		teamList = new ArrayList<Team>();
		teamListFile = t.getName()+"_teams";
		
		// this.name=tournament.getName();
		this.name = t.getName().toString();
		setTitle(name);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, frameWidth, frameHeight);

		content_panel = new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		content_panel.setLayout(new GridLayout(1, 2, 10, 10));
		setContentPane(content_panel);

		team = new DefaultListModel();

		team_list = new JList(team);
		team_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		team_list.setVisibleRowCount(-1);
		team_list.setFixedCellWidth((int) (0.45 * frameWidth));

		readTeams();

		listScroller = new JScrollPane(team_list);
		listScroller.setPreferredSize(new Dimension(250, 80));

		list_panel = new JPanel();
		list_panel.setLayout(new BorderLayout());
		list_panel.setBorder(new LineBorder(Color.BLACK));
		list_panel.setBackground(Color.WHITE);

		button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(10, 1));
		button_panel.setBounds(360, 350, (int) (frameWidth / 2), (int) (0.45 * frameWidth));

		choose_b = new JButton("Choose Tournament Format");
		teamAdd_b = new JButton("Add Team");
		teamView_b = new JButton("View Selected Team");
		schedule_b = new JButton("View Schedule");
		refresh_b = new JButton("Refresh");

		// ActionListener could be generalized as well!
		ActionListener ButtonListener = new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(choose_b)){
					new ChooseTournamentFormatDialog();
				}
				if (e.getSource().equals(teamAdd_b)) {
					new AddTeamDialog(t);
				}

				 if (e.getSource().equals(teamView_b)) {

				}

				 if (e.getSource().equals(schedule_b)) {

				}

				 if (e.getSource().equals(refresh_b)) {
					 teamList.clear();
					 team.clear();
					 readTeams();
				}
			}
		};

		// IS this necessary?
		ListSelectionListener listListener = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

		};
		choose_b .addActionListener(ButtonListener);
		teamAdd_b.addActionListener(ButtonListener);
		teamView_b.addActionListener(ButtonListener);
		schedule_b.addActionListener(ButtonListener);
		refresh_b.addActionListener(ButtonListener);

		list_panel.add(team_list);
		button_panel.add(choose_b);
		button_panel.add(teamAdd_b);
		button_panel.add(teamView_b);
		button_panel.add(schedule_b);
		button_panel.add(refresh_b);

		content_panel.add(list_panel);
		content_panel.add(button_panel);

		setVisible(true);
	}

	// This and all code like it code be easily added to another helper class
	// and take references
	private void readTeams() {
		ArrayList<Object> objects = reader.read(teamListFile);

		for (int i = 0; i < objects.size(); i++) {
			teamList.add((Team) objects.get(i));
			team.addElement(teamList.get(i).getTeamName());
		}
	}
}
