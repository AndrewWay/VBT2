package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//Not Happy with this.

public class PlayerRegisterDialog extends JDialog {
	
	private JLabel pName, pPhone, pEmail, pTeam;
	private JTextField pNameField_giv, pNameField_sur, pPhoneField_area, pPhoneField_pre, pPhoneField_line, pEmailField;
	private JPanel namePanel, phonePanel, emailPanel;
	private JComboBox teamDropBox;
	private JButton register_b;
	
	//Set size parameters for dialog box
	private int dialogWidth=500;
	private int dialogHeight=200;
	
	public PlayerRegisterDialog () {
		setTitle("Register New Player");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100,100,dialogWidth,dialogHeight);
		setLayout(new GridLayout(5,2));

		
		pName = new JLabel("Name: ");
		pPhone = new JLabel("Phone Number: ");
		pTeam = new JLabel("Team: ");
		pEmail = new JLabel("E-Mail: ");
		
		pNameField_giv = new JTextField(10);
		pNameField_sur = new JTextField(10);
		pPhoneField_area = new JTextField(3);
		pPhoneField_pre = new JTextField(3);
		pPhoneField_line = new JTextField(4);
		pEmailField = new JTextField(20);
		
		teamDropBox = new JComboBox();
		
		register_b = new JButton("Register");
		register_b.addActionListener(new register_Player());
		
		phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout());
		
		phonePanel.add(pPhoneField_area);
		phonePanel.add(pPhoneField_pre);
		phonePanel.add(pPhoneField_line);
		
		namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout());
		
		namePanel.add(pNameField_giv);
		namePanel.add(pNameField_sur);	

		emailPanel = new JPanel();
		emailPanel.setLayout(new FlowLayout());
		
		emailPanel.add(pEmailField);
		
		add(pName);
		add(namePanel);
		add(pPhone);
		add(phonePanel);
		add(pEmail);
		add(emailPanel);
		add(pTeam);
		add(teamDropBox);
		add(new JPanel());
		add(register_b);
		
		setResizable(false);
		setVisible(true);
	}
	
	public static void main ( String[] args) {
		new PlayerRegisterDialog();
	}
	
	public class register_Player implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Create Player here using the data?
			JOptionPane.showMessageDialog(null, "CREATION OF PLAYER IMPLEMENTED HERE");
			dispose();
		}
		
	}
}
