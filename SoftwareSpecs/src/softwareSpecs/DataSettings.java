package softwareSpecs;

import java.awt.Color;

import javax.swing.*;

public class DataSettings extends JPanel{
	
	public DataSettings(){
		
		this.setLayout(null);
		this.setSize(700, 500);
		this.setVisible(true);
		this.setBackground(Color.green);
		
		JPanel timeInterval = new JPanel();
		JPanel northBound = new JPanel();
		JPanel southBound = new JPanel();
		JPanel eastBound = new JPanel();
		JPanel westBound = new JPanel();
		JPanel eventDay = new JPanel();
		JPanel submit = new JPanel();
		
		timeInterval.setBackground(Color.cyan);
		northBound.setBackground(Color.red);
		southBound.setBackground(Color.BLACK);
		eastBound.setBackground(Color.BLUE);
		westBound.setBackground(Color.ORANGE);
		eventDay.setBackground(Color.GRAY);
		submit.setBackground(Color.MAGENTA);
		
		timeInterval.setLayout(null);
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
		eventDay.setBounds(100, 100, 140, 300);
		submit.setBounds(0, 400, 700, 100);
		
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
		
		//set location for components
		timeIntervalLabel.setBounds(10, 10, 130, 36);
		startDay.setBounds(145,10,130,36);
		endDay.setBounds(280,10,130,36);
		startTime.setBounds(415,10,130,36);
		endTime.setBounds(550,10,130,36);
		
		//add components to timeInterval panel
		timeInterval.add(timeIntervalLabel);
		timeInterval.add(startDay);
		timeInterval.add(endDay);
		timeInterval.add(startTime);
		timeInterval.add(endTime);
		
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
