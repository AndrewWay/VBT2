package gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CoachDialog extends JDialog {
	private JPanel contentPanel;
	private JTextField fnameField, lnameField, emailField, phoneField;
	
	private ReadObjectList reader = new ReadObjectList();
	private WriteObjectList writer = new WriteObjectList();
	
	private String coachFile = "Coach";

	public CoachDialog() {
		contentPanel = new JPanel();
		setTitle("Register Coach");
		setContentPane(contentPanel);
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setLayout(new GridLayout(4, 1, 0, 10));
		JPanel namePane = new JPanel();
		namePane.setLayout(new GridLayout(2, 2, 0, 5));
		JLabel lbl_1 = new JLabel("First Name:");
		fnameField = new JTextField();
		fnameField.setColumns(15);
		JLabel lbl_2 = new JLabel("Last Name:");
		lnameField = new JTextField();
		lnameField.setColumns(15);
		namePane.add(lbl_1);
		namePane.add(fnameField);
		namePane.add(lbl_2);
		namePane.add(lnameField);
		contentPanel.add(namePane);

		JPanel infoPane = new JPanel();
		infoPane.setLayout(new GridLayout(2, 2, 0, 10));
		JLabel lbl_4 = new JLabel("Email");
		emailField = new JTextField();
		emailField.setText("");
		infoPane.add(lbl_4);
		infoPane.add(emailField);

		JLabel lbl_5 = new JLabel("Phone");
		phoneField = new JTextField();
		phoneField.setText("");
		infoPane.add(lbl_5);
		infoPane.add(phoneField);

		contentPanel.add(infoPane);

		JPanel buttonPanel = new JPanel();
		JButton add_b = new JButton("Add");
		JButton reset_b = new JButton("Reset");
		JButton cancel_b = new JButton("Canacel");

		ActionListener ButtonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(cancel_b)) {
					dispose();
				}

				if (e.getSource().equals(reset_b)) {
					lnameField.setText("");
					fnameField.setText("");
					emailField.setText("");
					phoneField.setText("");
				}
				if (e.getSource().equals(add_b)) {
					Coach c=  new Coach(fnameField.getText() + " " + lnameField.getText(),emailField.getText(), phoneField.getText());

					writer.write(coachFile, c);
					
					lnameField.setText("");
					fnameField.setText("");
					emailField.setText("");
					phoneField.setText("");
					
					JOptionPane.showMessageDialog(null, "Add Successful", "Tournament", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		};

		add_b.addActionListener(ButtonListener);
		reset_b.addActionListener(ButtonListener);
		cancel_b.addActionListener(ButtonListener);

		buttonPanel.add(add_b);
		buttonPanel.add(reset_b);
		buttonPanel.add(cancel_b);

		contentPanel.add(buttonPanel);

		setResizable(false);
		setVisible(true);
	}
}
