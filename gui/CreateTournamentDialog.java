package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class CreateTournamentDialog extends JDialog{
	private JPanel ctPanel, textPanel, datePanel, ButtonPanel;
	private JLabel tName, tLocate, tDeadline;
	
	private JTextField tNameField;
	private JTextField tLocateField;
	private Calendar tDate = Calendar.getInstance();
	private JComboBox tDay,tMonth,tYear;
	private JButton createT_b, cancel_b;
	
	private ButtonGroup typeChoice;
	
	private ReadObjectList reader = new ReadObjectList();
	private WriteObjectList writer = new WriteObjectList();
	
	public CreateTournamentDialog(DefaultListModel tournament, String filename) {
		setTitle("Create New Tournament");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100,100,400,300);
		setLayout(new GridLayout(3,1));
		
		tName = new JLabel("Tournament Name: ");
		tLocate = new JLabel("Tournament Location: ");
		tDeadline = new JLabel("Registration Deadline: ");
		
		tNameField = new JTextField(50);
		tLocateField = new JTextField(50);
		
		textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(2,2));
		
		textPanel.add(tName);
		textPanel.add(tNameField);
		textPanel.add(tLocate);
		textPanel.add(tLocateField);
		
		tYear = new JComboBox();
		buildYears(tYear);
		tYear.setSelectedItem(Calendar.YEAR);
        tYear.addItemListener(new ChangeDate());
		tMonth = new JComboBox();
		try {
			buildMonths(tMonth);
		} catch (ParseException e) {
			// This should never ever ever ever happen
			e.printStackTrace();
		}
        tMonth.addItemListener(new ChangeDate());
		tMonth.setSelectedItem(Calendar.MONTH);
		tDay = new JComboBox();
		buildDays(tDate, tDay, tMonth);
		tDay.addItemListener(new ChangeDate());
		
        datePanel = new JPanel();
        datePanel.setLayout(new GridLayout(1,4));
        
        datePanel.add(tDeadline);
        datePanel.add(tDay);
        datePanel.add(tMonth);
        datePanel.add(tYear);
        
        ButtonPanel = new JPanel();
        createT_b = new JButton("Create Tournament");
        cancel_b = new JButton("Cancel");
        ButtonPanel.add(createT_b);
        ButtonPanel.add(cancel_b);
        ActionListener buttonListener = new ActionListener(){

    			@Override
    			public void actionPerformed(ActionEvent e) {
    				if (e.getSource().equals(createT_b)){
    					Tournament t = new Tournament(tNameField.getText(),tLocateField.getText(),tDate.getTime() );
    					
    					writer.write(filename, t);
    					
    					JOptionPane.showMessageDialog(null, "Add record successfully ", "Tournament", JOptionPane.ERROR_MESSAGE); 					
    					
    					dispose();
    				}
    				
    				if(e.getSource().equals(cancel_b)){
    					dispose();
    				}
    				
    			}
            	
            };        
            createT_b.addActionListener(buttonListener);
            cancel_b.addActionListener(buttonListener);
        
        
        add(textPanel);
        add(datePanel);
        add(ButtonPanel);
        
        
        setResizable(false);
        
        setVisible(true);
	}
	
	//Build date picker
	
	private void buildYears(JComboBox years) {

        int currentYear = tDate.get(Calendar.YEAR);

        for (int yearCount = currentYear; yearCount <= currentYear + 10; yearCount++)
            years.addItem(Integer.toString(yearCount));
    }
	
    private void buildMonths(JComboBox months) throws ParseException{
	    SimpleDateFormat monthParse = new SimpleDateFormat("MM");
	    SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
        for (int monthCount = 0; monthCount < 12; monthCount++)
        	months.addItem(monthDisplay.format(monthParse.parse(Integer.toString(monthCount+1))));
        	//months.addItem(Integer.toString(monthCount+1));
    }
	
	private void buildDays(Calendar dateIn, JComboBox days, JComboBox months) {

        days.removeAllItems();
        dateIn.set(Calendar.MONTH, months.getSelectedIndex());
        int lastDay = tDate.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int dayCount = 1; dayCount <= lastDay; dayCount++)
            days.addItem(Integer.toString(dayCount));
    }
	
	class ChangeDate implements ItemListener  {

		public void itemStateChanged(ItemEvent event){
        if (event.getSource() == tYear &&
            event.getStateChange() == ItemEvent.SELECTED) {

            int year = Integer.parseInt((String)tYear.getSelectedItem());
            tDate.set(Calendar.YEAR, year);
            tMonth.setSelectedIndex(0);
            tDate.set(Calendar.MONTH, 0);
            buildDays(tDate, tDay, tMonth);
            tDate.set(Calendar.DATE, 1);
        }
        else if (event.getSource() == tMonth &&
            event.getStateChange() == ItemEvent.SELECTED) {

            tDate.set(Calendar.MONTH, tMonth.getSelectedIndex());
            buildDays(tDate, tDay, tMonth);
            tDate.set(Calendar.DATE, 1);
        }
        else if (event.getSource() == tDay &&
            event.getStateChange() == ItemEvent.SELECTED) {

            int day = Integer.parseInt((String)tDay.getSelectedItem());
            tDate.set(Calendar.DATE, day);
    }
}
}

}

