package gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AddTeamDialog extends JDialog {
	private JPanel contentPane, boxPane, buttonPane;
	private JComboBox teamBox;
	private JButton add_b,cancel_b,reset_b;
	private JLabel teamLabel;
	private ArrayList<Team> teams;
	
	private ReadObjectList reader = new ReadObjectList();
	private WriteObjectList writer = new WriteObjectList();
	
	//These should really be stored somewhere so they can be accessed later
	private String teamFile = "Team";
	private String teamListFile;

	public AddTeamDialog(Tournament t) {
		teams = new ArrayList<Team>();
		teamListFile = t.getName()+"_teams";
		
		contentPane = new JPanel();
		setTitle("Add Team");
		setBounds(100, 100, 400, 300);
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane.setLayout(new GridLayout(3, 1));
		boxPane = new JPanel();
		boxPane.setLayout(new GridLayout(1, 2));
		teamLabel = new JLabel("Team");
		
		teamBox = new JComboBox();
		teamBox.addItem(" ");
		
		readTeam();
		
		
		boxPane.add(teamLabel);
		boxPane.add(teamBox);

		buttonPane = new JPanel();
		add_b = new JButton("Add");
		cancel_b = new JButton("Cancel");
		reset_b = new JButton("Reset");

		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(add_b)) {
					writer.write(teamListFile,teams.get(teamBox.getSelectedIndex() - 1));
					
					JOptionPane.showMessageDialog(null, "Add Successful ", "Tournament", JOptionPane.ERROR_MESSAGE);
				}
				else if (e.getSource().equals(reset_b)) {
					
				}
				else if (e.getSource().equals(cancel_b)) {
					dispose();
				}

			}

		};
		
		add_b.addActionListener(buttonListener);
		reset_b.addActionListener(buttonListener);
		cancel_b.addActionListener(buttonListener);
		buttonPane.add(add_b);
		buttonPane.add(reset_b);
		buttonPane.add(cancel_b);
		
		contentPane.add(boxPane);
		contentPane.add(buttonPane);
		
		setResizable(false);
		setVisible(true);

	}
	
	private void readTeam() {
			ArrayList<Object> objects = reader.read(teamFile);
		
			for (int i = 0; i < objects.size(); i++) {
				teams.add((Team) objects.get(i));
				teamBox.addItem((teams.get(i).getTeamName().toString()));
			}

	}
}
