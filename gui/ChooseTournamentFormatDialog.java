package gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChooseTournamentFormatDialog extends JDialog{
	private JPanel contentPane;
	public ChooseTournamentFormatDialog() {
		contentPane = new JPanel();
		setTitle("Choose Tournament Format");
		setContentPane(contentPane);
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane.setLayout(new GridLayout(5, 1, 5, 5));
		JLabel lbl = new JLabel("Format");
		JRadioButton singleButton = new JRadioButton("Single Elinmation");
		singleButton.setSelected(true);
		JRadioButton divisionButton = new JRadioButton("Divsion");
		ButtonGroup group = new ButtonGroup();
	    group.add(singleButton);
	    group.add(divisionButton);
	
	    JPanel textPane = new JPanel();
	    textPane.setLayout(new GridLayout(1,2));
	    JLabel lbl1 = new JLabel("# of Division");
	    JTextField numField= new JTextField(5);
	    numField.setEditable(false);
	    textPane.add(lbl1);
	    textPane.add(numField);
	    
	    JPanel buttonPane  = new JPanel();
	    JButton chooseButton= new JButton("Choose");
	    JButton cancelButton = new JButton("Cancel");
	    buttonPane.add(chooseButton);
	    buttonPane.add(cancelButton);
	    
	    
	    ActionListener  buttonListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(divisionButton)){
					numField.setEditable(true);
				}
				
				if(e.getSource().equals(singleButton)){
					numField.setEditable(false);
				}
				
				if (e.getSource().equals(cancelButton)){
					dispose();
				}
				if (e.getSource().equals(chooseButton)){
					
				}
				
			}
	    	
	    };
	    
	    singleButton.addActionListener(buttonListener);
	    divisionButton.addActionListener(buttonListener);
	    chooseButton.addActionListener(buttonListener);
	    cancelButton.addActionListener(buttonListener);
	    
	    contentPane.add(lbl);
	    contentPane.add(singleButton);
	    contentPane.add(divisionButton);
	    contentPane.add(textPane);
	    contentPane.add(buttonPane);
	    setResizable(false);
	    setVisible(true);
	}

}
