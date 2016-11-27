package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class RegisterTeamDialog extends JDialog {
	private JPanel contentPanel;
	private JTextField nameField;
	private JComboBox coachList;
	private JTextField playerField;
	private ArrayList<Coach> coachs;
	
	private ReadObjectList reader = new ReadObjectList();
	private WriteObjectList writer = new WriteObjectList();
	
	private String coachFile = "Coach";
	private String teamFile = "Team";

	public RegisterTeamDialog() {
		coachs = new ArrayList<Coach>();
		contentPanel = new JPanel();
		setTitle("Register Team");
		setContentPane(contentPanel);
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setLayout(new GridLayout(5, 1, 5, 5));
		JLabel lblNewLabel_0 = new JLabel("Enter team Information");
		contentPanel.add(lblNewLabel_0);
		JPanel namepane = new JPanel();
		namepane.setLayout(new GridLayout(1, 2));
		JLabel lblNewLabel = new JLabel("Team Name:");
		namepane.add(lblNewLabel);
		nameField = new JTextField();
		nameField.setText("");
		nameField.setColumns(15);
		namepane.add(nameField);
		contentPanel.add(namepane);

		JPanel coachPane = new JPanel();
		coachPane.setLayout(new GridLayout(1, 2));
		JLabel lblNewLabel_1 = new JLabel("Coach: ");
		coachList = new JComboBox();
		coachList.addItem(" ");
		readCoach();
		coachPane.add(lblNewLabel_1);
		coachPane.add(coachList);
		contentPanel.add(coachPane);
		// number of play
		JPanel playerPane = new JPanel();
		playerPane.setLayout(new GridLayout(1, 2));
		JLabel lblNewLabel_2 = new JLabel("# Of Players");
		playerField = new JTextField();
		playerField.setText("");
		playerField.setColumns(5);
		playerPane.add(lblNewLabel_2);
		playerPane.add(playerField);

		contentPanel.add(playerPane);

		JPanel buttonPane = new JPanel();
		JButton add_b = new JButton("Add");
		JButton reset_b = new JButton("Reset");
		JButton cancel_b = new JButton("Cancel");

		// when cancel button is click, the window will automatic close
		ActionListener ButtonLister = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(cancel_b)) {
					dispose();
				}

				else if (e.getSource().equals(reset_b)) {
					nameField.setText("");
					playerField.setText("");
					coachList.setSelectedIndex(0);
				}

				else if (e.getSource().equals(add_b)) {
			
					Team t = new Team(nameField.getText(), coachs.get(coachList.getSelectedIndex() - 1));
					
					writer.write(teamFile,t);
	
					nameField.setText("");
					playerField.setText("");
					coachList.setSelectedIndex(0);					
					
					JOptionPane.showMessageDialog(null, "Add record successfully", "Tournament",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		};
		add_b.addActionListener(ButtonLister);
		reset_b.addActionListener(ButtonLister);
		cancel_b.addActionListener(ButtonLister);
		buttonPane.add(add_b);
		buttonPane.add(reset_b);
		buttonPane.add(cancel_b);
		contentPanel.add(buttonPane);

		setResizable(false);
		setVisible(true);

	}

	private void readCoach() {
		ArrayList<Object> objects = reader.read(coachFile);

		for (int i = 0; i < objects.size(); i++) {
			coachs.add((Coach) objects.get(i));
			coachList.addItem(coachs.get(i).getName().toString());
		}

	}
}
