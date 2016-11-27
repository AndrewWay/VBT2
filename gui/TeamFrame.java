package gui;

import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

//All such classes could easily be taken under one abstract classes since there is so much generalization to be made
public class TeamFrame extends JFrame {

	private JPanel content_panel, list_panel, button_panel;
	private JButton teamView_b, refresh_b;
	private JList team_list;
	private JScrollPane listScroller;

	private DefaultListModel teamListModel;
	private ArrayList<Team> teams;
	private String name;
	
	
	private ReadObjectList reader = new ReadObjectList();
	private WriteObjectList writer = new WriteObjectList();
	
	private String teamFile = "Team";
	private int frameWidth = 500;
	private int frameHeight = 300;

	// public Tournament_Frame(Tournament tournament) {
	public TeamFrame(String n) {
		teams = new ArrayList<Team>();
		
		// this.name=tournament.getName();
		if (n != null) {
			name = n;
			setTitle(name);
		} else {

			setTitle("Show All team");
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, frameWidth, frameHeight);

		content_panel = new JPanel();
		content_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		content_panel.setLayout(new GridLayout(1, 2, 10, 10));
		setContentPane(content_panel);

		teamListModel = new DefaultListModel();

		team_list = new JList(teamListModel);
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

		teamView_b = new JButton("View Selected Team");
		teamView_b.setEnabled(false);
		refresh_b = new JButton("Refresh");

		// ActionListener could be generalized as well!
		ActionListener ButtonListener = new ActionListener() {


			public void actionPerformed(ActionEvent e) {


				if (e.getSource().equals(teamView_b)) {

				}


				if (e.getSource().equals(refresh_b)) {

				}
			}
		};
		
		ListSelectionListener listListener = new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {
				teamView_b.setEnabled(true);

			}

		};

		team_list.addListSelectionListener(listListener);
		
		teamView_b.addActionListener(ButtonListener);
		refresh_b.addActionListener(ButtonListener);

		list_panel.add(team_list);
		button_panel.add(teamView_b);
		button_panel.add(refresh_b);

		content_panel.add(list_panel);
		content_panel.add(button_panel);

		setVisible(true);
	}

	// This and all code like it code be easily added to another helper class
	// and take references
	private void readTeams() {
		ArrayList<Object> objects = reader.read(teamFile);

		for (int i = 0; i < objects.size(); i++) {
			teams.add((Team) objects.get(i));
			teamListModel.addElement(teams.get(i).getTeamName().toString());
		}
	}
}
