package src.softwareSpecs;

import java.awt.Color;
import javax.swing.*;

public class DataSettings extends JPanel{
	
	public DataSettings(){
		
		this.setLayout(null);
		this.setSize(700, 500);
		this.setVisible(true);
		//this.setBackground(Color.green);
		
		TimeInterval timeInterval = new TimeInterval();
		NorthBound northBound = new NorthBound();
		SouthBound southBound = new SouthBound();
		EastBound eastBound = new EastBound();
		WestBound westBound = new WestBound();
		EventDay eventDay = new EventDay();
		Submit submit = new Submit();
		
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
		submit.setLayout(null);
		
		//set location of panels
		timeInterval.setBounds(0, 0, 700, 100);
		northBound.setBounds(0, 100, 140, 300);
		southBound.setBounds(140, 100, 140, 300);
		eastBound.setBounds(280, 100, 140, 300);
		westBound.setBounds(420, 100, 140, 300);
		eventDay.setBounds(560, 100, 140, 300);
		submit.setBounds(0, 400, 700, 100);
		
				
		
		//add panels to frame
		this.add(timeInterval);
		this.add(northBound);
		this.add(southBound);
		this.add(eastBound);
		this.add(westBound);
		this.add(eventDay);
		this.add(submit);
		
	}

}

class TimeInterval extends JPanel{
	
	public TimeInterval(){
		
		//set Layout
		this.setLayout(null);
				
		//initialize components for timeInterval panel
		JLabel timeIntervalLabel = new JLabel("Time Interval: ");
		JComboBox startDay = new JComboBox();
		JComboBox endDay = new JComboBox();
		JComboBox startTime = new JComboBox();
		JComboBox endTime = new JComboBox();
				
		String day[] = new String[2];
		String time[] = new String[2];
		
		day[0] = "Start Day";
		day[1] = "End Day";
		
		time[1] = "End Time";
		time[0] = "Start Time";	
		
		//next four lines needs some work. method addItem doesn't add an entire array...
		//todo see above
		startDay.addItem(day[0]);
		endDay.addItem(day[1]);
		startTime.addItem(time[0]);
		endTime.addItem(time[1]);
		
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

class NorthBound extends JPanel{
	
	public NorthBound(){
		
		//list for combobox
		String[] routes = {"A to B" , "B to C" , "G to A"};
		
		//initialize components for northbound panel
		JCheckBox northboundCheckBox = new JCheckBox();
		JComboBox northboundComboBox = new JComboBox(routes);
		JList northboundJList = new JList();
		JScrollPane northboundScrollPane = new JScrollPane(northboundJList);
		JButton northboundAddButton = new JButton();
		JButton northboundRemoveButton = new JButton();
		
		
		//set text for northbound components
		northboundCheckBox.setText("NorthBound");
		northboundAddButton.setText("Add Route");
		northboundRemoveButton.setText("Remove Route");
		
		//set bounds for northbound components
		northboundCheckBox.setBounds(5,5,130,36);
		northboundComboBox.setBounds(5,46,130,36);
		northboundScrollPane.setBounds(5,87,130,126);
		northboundAddButton.setBounds(5,218,130,36);
		northboundRemoveButton.setBounds(5,259,130,36);
		
		//add components to northbound panel
		this.add(northboundCheckBox);
		this.add(northboundComboBox);
		this.add(northboundScrollPane);
		this.add(northboundAddButton);
		this.add(northboundRemoveButton);
				
	}//end Constructor
	
}//end NorthBound

class SouthBound extends JPanel{
	
	 public SouthBound(){
		 
		//list for combobox
		String[] routes = {"A to B" , "B to C" , "G to A"};
	
		//initialize components for panel
		JCheckBox southboundCheckBox = new JCheckBox();
		JComboBox southboundComboBox = new JComboBox(routes);
		JList southboundJList = new JList();
		JScrollPane southboundScrollPane = new JScrollPane(southboundJList);
		JButton southboundAddButton = new JButton();
		JButton southboundRemoveButton = new JButton();
		
		//set text for components
		southboundCheckBox.setText("SouthBound");
		southboundAddButton.setText("Add Route");
		southboundRemoveButton.setText("Remove Route");
		
		//set bounds for components
		southboundCheckBox.setBounds(5,5,130,36);
		southboundComboBox.setBounds(5,46,130,36);
		southboundScrollPane.setBounds(5,87,130,126);
		southboundAddButton.setBounds(5,218,130,36);
		southboundRemoveButton.setBounds(5,259,130,36);
		
		//add components to panel
		this.add(southboundCheckBox);
		this.add(southboundComboBox);
		this.add(southboundScrollPane);
		this.add(southboundAddButton);
		this.add(southboundRemoveButton);
		 
		 
	 }//end SounthBound
	
}//end SouthBound

class EastBound extends JPanel{
	
	public EastBound(){
		
		//list for combobox
		String[] routes = {"A to B" , "B to C" , "G to A"};
		
		//initialize components for panel
		JCheckBox eastboundCheckBox = new JCheckBox();
		JComboBox eastboundComboBox = new JComboBox(routes);
		JList eastboundJList = new JList();
		JScrollPane eastboundScrollPane = new JScrollPane(eastboundJList);
		JButton eastboundAddButton = new JButton();
		JButton eastboundRemoveButton = new JButton();
		
		//set text for components
		eastboundCheckBox.setText("EastBound");
		eastboundAddButton.setText("Add Route");
		eastboundRemoveButton.setText("Remove Route");
		
		//set bounds for components
		eastboundCheckBox.setBounds(5,5,130,36);
		eastboundComboBox.setBounds(5,46,130,36);
		eastboundScrollPane.setBounds(5,87,130,126);
		eastboundAddButton.setBounds(5,218,130,36);
		eastboundRemoveButton.setBounds(5,259,130,36);
		
		//add components to panel
		this.add(eastboundCheckBox);
		this.add(eastboundComboBox);
		this.add(eastboundScrollPane);
		this.add(eastboundAddButton);
		this.add(eastboundRemoveButton);
				
	}//end constructor
	
}//end EastBound

class WestBound extends JPanel{
	
	public WestBound(){
		
		//list for combobox
		String[] routes = {"A to B" , "B to C" , "G to A"};
		
		//initialize components for panel
		JCheckBox westboundCheckBox = new JCheckBox();
		JComboBox westboundComboBox = new JComboBox(routes);
		JList westboundJList = new JList();
		JScrollPane westboundScrollPane = new JScrollPane(westboundJList);
		JButton westboundAddButton = new JButton();
		JButton westboundRemoveButton = new JButton();
		
		//set text for components
		westboundCheckBox.setText("WestBound");
		westboundAddButton.setText("Add Route");
		westboundRemoveButton.setText("Remove Route");
		
		//set bounds for components
		westboundCheckBox.setBounds(5,5,130,36);
		westboundComboBox.setBounds(5,46,130,36);
		westboundScrollPane.setBounds(5,87,130,126);
		westboundAddButton.setBounds(5,218,130,36);
		westboundRemoveButton.setBounds(5,259,130,36);
		
		//add components to panel
		this.add(westboundCheckBox);
		this.add(westboundComboBox);
		this.add(westboundScrollPane);
		this.add(westboundAddButton);
		this.add(westboundRemoveButton);

	}//end constructor
	
}//end WestBound

class EventDay extends JPanel{
	
	public EventDay(){
		
		//initialize components for panel
		
		JComboBox eventDayComboBox = new JComboBox();
		JLabel eventLabel = new JLabel();
		JList eventDayJList = new JList();
		JScrollPane eventDayScrollPane = new JScrollPane(eventDayJList);
		JButton eventDayAddButton = new JButton();
		JButton eventDayRemoveButton = new JButton();
		
		//set text for components
		eventLabel.setText("Events");
		eventDayComboBox.addItem("Event 1");
		eventDayAddButton.setText("Add Event");
		eventDayRemoveButton.setText("Remove Event");
		
		//set bounds for components
		eventLabel.setBounds(50,5,130,36);
		eventDayComboBox.setBounds(5,46,130,36);
		eventDayScrollPane.setBounds(5,87,130,126);
		eventDayAddButton.setBounds(5,218,130,36);
		eventDayRemoveButton.setBounds(5,259,130,36);
		
		//add components to panel
		this.add(eventLabel);
		this.add(eventDayComboBox);
		this.add(eventDayScrollPane);
		this.add(eventDayAddButton);
		this.add(eventDayRemoveButton);
	
		
	}//end Constructor
	
}//end EventDay

class Submit extends JPanel{
	
	public Submit(){
		
		//initialize components
		JButton submitButton = new JButton("Submit");
		
		//set location 
		submitButton.setBounds(350-130/2,50-36/2,130,36);
		
		//add components
		this.add(submitButton);
		
	}//end constructor
	
}// end Submit
