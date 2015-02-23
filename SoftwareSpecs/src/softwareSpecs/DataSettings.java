package softwareSpecs;

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
				
		String day[] = new String[1];
		String time[] = new String[1];
		
		day[0] = "Start";
		time[0] = "Time";
		
		//next four lines needs some work. method addItem doesn't add an entire array...
		//todo see above
		startDay.addItem(day[0]);
		endDay.addItem(day[0]);
		startTime.addItem(time[0]);
		endTime.addItem(time[0]);
		
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
		
		//initialize components for northbound panel
		JCheckBox northboundCheckBox = new JCheckBox();
		JComboBox northboundComboBox = new JComboBox();
		JScrollPane northboundScrollPane = new JScrollPane();
		JList northboundJList = new JList();
		JButton northboundAddButton = new JButton();
		JButton northboundRemoveButton = new JButton();
		
		//set text for northbound components
		northboundCheckBox.setText("NorthBound");
		northboundComboBox.addItem("A to B");
		northboundAddButton.setText("Add Route");
		northboundRemoveButton.setText("Remove Route");
		
		//set bounds for northbound components
		northboundCheckBox.setBounds(5,5,130,36);
		northboundComboBox.setBounds(5,46,130,36);
		northboundJList.setBounds(5,87,130,126);
		northboundAddButton.setBounds(5,218,130,36);
		northboundRemoveButton.setBounds(5,259,130,36);
		
		//add components to northbound panel
		this.add(northboundCheckBox);
		this.add(northboundComboBox);
		this.add(northboundJList);
		this.add(northboundAddButton);
		this.add(northboundRemoveButton);
				
	}//end Constructor
	
}//end NorthBound

class SouthBound extends JPanel{
	
	 public SouthBound(){
	
		//initialize components for panel
		JCheckBox southboundCheckBox = new JCheckBox();
		JComboBox southboundComboBox = new JComboBox();
		JScrollPane southboundScrollPane = new JScrollPane();
		JList southboundJList = new JList();
		JButton southboundAddButton = new JButton();
		JButton southboundRemoveButton = new JButton();
		
		//set text for components
		southboundCheckBox.setText("SouthBound");
		southboundComboBox.addItem("A to B");
		southboundAddButton.setText("Add Route");
		southboundRemoveButton.setText("Remove Route");
		
		//set bounds for components
		southboundCheckBox.setBounds(5,5,130,36);
		southboundComboBox.setBounds(5,46,130,36);
		southboundJList.setBounds(5,87,130,126);
		southboundAddButton.setBounds(5,218,130,36);
		southboundRemoveButton.setBounds(5,259,130,36);
		
		//add components to panel
		this.add(southboundCheckBox);
		this.add(southboundComboBox);
		this.add(southboundJList);
		this.add(southboundAddButton);
		this.add(southboundRemoveButton);
		 
		 
	 }//end SounthBound
	
}//end SouthBound

class EastBound extends JPanel{
	
	public EastBound(){
		
		//initialize components for panel
		JCheckBox eastboundCheckBox = new JCheckBox();
		JComboBox eastboundComboBox = new JComboBox();
		JScrollPane eastboundScrollPane = new JScrollPane();
		JList eastboundJList = new JList();
		JButton eastboundAddButton = new JButton();
		JButton eastboundRemoveButton = new JButton();
		
		//set text for components
		eastboundCheckBox.setText("EastBound");
		eastboundComboBox.addItem("A to B");
		eastboundAddButton.setText("Add Route");
		eastboundRemoveButton.setText("Remove Route");
		
		//set bounds for components
		eastboundCheckBox.setBounds(5,5,130,36);
		eastboundComboBox.setBounds(5,46,130,36);
		eastboundJList.setBounds(5,87,130,126);
		eastboundAddButton.setBounds(5,218,130,36);
		eastboundRemoveButton.setBounds(5,259,130,36);
		
		//add components to panel
		this.add(eastboundCheckBox);
		this.add(eastboundComboBox);
		this.add(eastboundJList);
		this.add(eastboundAddButton);
		this.add(eastboundRemoveButton);
				
	}//end constructor
	
}//end EastBound

class WestBound extends JPanel{
	
	public WestBound(){
		
		//initialize components for panel
		JCheckBox westboundCheckBox = new JCheckBox();
		JComboBox westboundComboBox = new JComboBox();
		JScrollPane westboundScrollPane = new JScrollPane();
		JList westboundJList = new JList();
		JButton westboundAddButton = new JButton();
		JButton westboundRemoveButton = new JButton();
		
		//set text for components
		westboundCheckBox.setText("WestBound");
		westboundComboBox.addItem("A to B");
		westboundAddButton.setText("Add Route");
		westboundRemoveButton.setText("Remove Route");
		
		//set bounds for components
		westboundCheckBox.setBounds(5,5,130,36);
		westboundComboBox.setBounds(5,46,130,36);
		westboundJList.setBounds(5,87,130,126);
		westboundAddButton.setBounds(5,218,130,36);
		westboundRemoveButton.setBounds(5,259,130,36);
		
		//add components to panel
		this.add(westboundCheckBox);
		this.add(westboundComboBox);
		this.add(westboundJList);
		this.add(westboundAddButton);
		this.add(westboundRemoveButton);

	}//end constructor
	
}//end WestBound

class EventDay extends JPanel{
	
	public EventDay(){
		
		//initialize components for panel
		
		JComboBox eventDayComboBox = new JComboBox();
		JScrollPane eventDayScrollPane = new JScrollPane();
		JList eventDayJList = new JList();
		JButton eventDayAddButton = new JButton();
		JButton eventDayRemoveButton = new JButton();
		
		//set text for components
		
		eventDayComboBox.addItem("A to B");
		eventDayAddButton.setText("Add Route");
		eventDayRemoveButton.setText("Remove Route");
		
		//set bounds for components
		
		eventDayComboBox.setBounds(5,46,130,36);
		eventDayJList.setBounds(5,87,130,126);
		eventDayAddButton.setBounds(5,218,130,36);
		eventDayRemoveButton.setBounds(5,259,130,36);
		
		//add components to panel
		
		this.add(eventDayComboBox);
		this.add(eventDayJList);
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
