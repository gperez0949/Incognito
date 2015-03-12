package src.softwareSpecs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DataSettings extends JPanel{

    Submit submitPanel;
	
	public DataSettings(String[] routes, String[] dates, String[] times){
		
		this.setLayout(null);
		this.setSize(700, 500);
		this.setVisible(true);
		
		
		
		//this.setBackground(Color.green);
		
		TimeInterval timeInterval = new TimeInterval(dates,times);
		RoutePanel northBound = new RoutePanel("NorthBound", routes);
        RoutePanel southBound = new RoutePanel("SouthBound", routes);
        RoutePanel eastBound = new RoutePanel("EastBound", routes);
        RoutePanel westBound = new RoutePanel("WestBound", routes);
		EventDay eventDay = new EventDay();
		submitPanel = new Submit();
		
		//timeInterval.setBackground(Color.cyan);
		//northBound.setBackground(Color.red);
		//southBound.setBackground(Color.BLACK);
		//eastBound.setBackground(Color.BLUE);
		//westBound.setBackground(Color.ORANGE);
		//eventDay.setBackground(Color.GRAY);
		//submit.setBackground(Color.MAGENTA);
		
		
		northBound.setLayout(null);
		southBound.setLayout(null);
		eastBound.setLayout(null);
		westBound.setLayout(null);
		eventDay.setLayout(null);
		submitPanel.setLayout(null);
		
		//set location of panels
		timeInterval.setBounds(0, 0, 700, 100);
		northBound.setBounds(0, 100, 140, 300);
		southBound.setBounds(140, 100, 140, 300);
		eastBound.setBounds(280, 100, 140, 300);
		westBound.setBounds(420, 100, 140, 300);
		eventDay.setBounds(560, 100, 140, 300);
		submitPanel.setBounds(0, 400, 700, 100);
		
				
		
		//add panels to frame
		this.add(timeInterval);
		this.add(northBound);
		this.add(southBound);
		this.add(eastBound);
		this.add(westBound);
		this.add(eventDay);
		this.add(submitPanel);
		
	}

}

class TimeInterval extends JPanel{
	
	public TimeInterval(String[] dates, String[] times){
		
		//set Layout
		this.setLayout(null);
				
		//initialize components for timeInterval panel
		JLabel timeIntervalLabel = new JLabel("Time Interval: ");
		JComboBox startDay = new JComboBox(dates);
		JComboBox endDay = new JComboBox(dates);
		JComboBox startTime = new JComboBox(times);
		JComboBox endTime = new JComboBox(times);

		
		//set location for components for timeInterval panel
		timeIntervalLabel.setBounds(10, 10, 130, 36);
		startDay.setBounds(145,10,130,36);
		endDay.setBounds(280,10,130,36);
		startTime.setBounds(415,10,130,36);
		endTime.setBounds(550,10,130,36);
		
		//add components to timeInterval panel
		this.add(timeIntervalLabel);
		this.add(startDay);
		this.add(endDay);
		this.add(startTime);
		this.add(endTime);

	}//end constructor
	
	
}//end timeInterval 

class RoutePanel extends JPanel{

    JCheckBox directionCheckBox;
    JComboBox routeComboBox;
    JScrollPane routeScrollPane;
    JList routeJList;
    JButton routeAddButton;
    JButton routeRemoveButton;
    DefaultListModel routeListModel;
	
	public RoutePanel(String routeDirection, String[] routes){

		//initialize components for northbound panel
        directionCheckBox = new JCheckBox();
        routeComboBox = new JComboBox(routes);
        routeListModel = new DefaultListModel();
        routeJList = new JList(routeListModel);
        routeScrollPane = new JScrollPane(routeJList);
        routeAddButton = new JButton();
        routeRemoveButton = new JButton();

		
		//set text for northbound components
        directionCheckBox.setText(routeDirection);
        routeAddButton.setText("Add Route");
        routeRemoveButton.setText("Remove Route");
		
		//set bounds for northbound components
        directionCheckBox.setBounds(5,5,130,36);
        routeComboBox.setBounds(5,46,130,36);
        routeScrollPane.setBounds(5,87,130,126);
        routeAddButton.setBounds(5,218,130,36);
        routeRemoveButton.setBounds(5,259,130,36);

        //add the action listeners. they are defined at the bottom of the class
        directionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                directionCheckBoxActionPerformed(e);
            }
        });

        routeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                routeComboBoxActionPerformed(e);
            }
        });

        routeAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                routeAddButtonActionPerformed(e);
            }
        });

        routeRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                routeRemoveButtonActionPerformed(e);
            }
        });

		//add components to northbound panel
		this.add(directionCheckBox);
		this.add(routeComboBox);
		this.add(routeScrollPane);
		this.add(routeAddButton);
		this.add(routeRemoveButton);
				
	}//end Constructor

    /**
     * Handles all events for the checkbox
     *
     * @param e
     */
    public void directionCheckBoxActionPerformed(ActionEvent e){



    }

    /**
     * Handles all events for the comboBox
     *
     * @param e
     */

    public void routeComboBoxActionPerformed(ActionEvent e){


    }


    /**
     * Handles events for the add button
     *
     * will take the selected element in the JComboBox and add it to the JList
     *
     * Elements in Jlist are considered for processing and eventually their data is viewed
     * from the user
     *
     *
     * @param e
     */
    public void routeAddButtonActionPerformed(ActionEvent e){

        routeListModel.addElement(routeComboBox.getItemAt(routeComboBox.getSelectedIndex()));

    }

    /**
     * Handles events for the remove button
     *
     * Takes the selected element in the Jlist and removes it from the Jlist.
     *
     * Elements in Jlist are considered for processing and eventually their data is viewed
     * from the user
     *
     * @param e
     */

    public void routeRemoveButtonActionPerformed(ActionEvent e){

        routeListModel.remove(routeJList.getSelectedIndex());

    }

	
}//end NorthBound

class EventDay extends JPanel{
	
	public EventDay(){
		
		//initialize components for panel

        JLabel eventLabel = new JLabel("Events");
		JButton newEventButton = new JButton();
		JList eventDayJList = new JList();
        JScrollPane eventDayScrollPane = new JScrollPane(eventDayJList);
		JButton editButton = new JButton();
		JButton removeButton = new JButton();
		
		//set text for components
        newEventButton.setText("New Event");
		editButton.setText("EditEvent");
		removeButton.setText("Remove Event");
		
		//set bounds for components

        eventLabel.setBounds(50,5,130,36);
        newEventButton.setBounds(5,46,130,36);
        eventDayScrollPane.setBounds(5,87,130,126);
		editButton.setBounds(5,218,130,36);
		removeButton.setBounds(5,259,130,36);

        //add Action Listeners
        newEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                newEventButtonActionPerformed(e);

            }
        });
		
		//add components to panel

        this.add(eventLabel);
		this.add(newEventButton);
		this.add(eventDayScrollPane);
		this.add(editButton);
		this.add(removeButton);
	
		
	}//end Constructor

    //Action performed

    public void newEventButtonActionPerformed(ActionEvent e){

        NewEventFrame newEventFrame = new NewEventFrame();

    }
	
}//end EventDay

class Submit extends JPanel{
	public JButton submitButton;
	public Submit(){
		
		//initialize components
		submitButton = new JButton("Submit");
		
		//set location 
		submitButton.setBounds(350-130/2,50-36/2,130,36);
		
		//add components
		this.add(submitButton);
		
	}//end constructor
	
}// end Submit
