package src.softwareSpecs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GetProjectOrCreateNew extends JPanel {
	public File selectedFile;
    public JButton goBtn;
    JButton btnFile;

	public GetProjectOrCreateNew() {
		this.setLayout(null);
		this.setSize(700, 500);
		this.setVisible(true);

		JPanel NewFile = new JPanel();
		NewFile.setLayout(null);
		NewFile.setVisible(true);
		NewFile.setSize(300, 200);
		NewFile.setBounds(10, 10, 300, 200);

		JLabel newProject = new JLabel(
				"New Project? Click da button to choose the excel file");
		newProject.setBounds(10, 10, 300, 20);
		this.add(newProject);
		

		btnFile = new JButton("Click to choose file");
		btnFile.setBounds(10, 20, 200, 50);
		btnFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// when Click I want to open a JavaFile chooser
				selectedFile = getFile();
			}// end actionPerformed method
		});
		NewFile.add(btnFile);

		JPanel previous = new PreviousProjects();
		previous.setBounds(330, 10, 630, 200);
		
		// make giant submit button
		goBtn = new JButton("GO!");
		goBtn.setBounds(10, 210, 670, 250);
		goBtn.setVisible(true);
		
		add(goBtn);
		add(previous);
		add(NewFile);

	}// end constructor

	public File getFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int result = fileChooser.showOpenDialog(this);
		// if user clicked Cancel button on dialog, return
		File fileName = fileChooser.getSelectedFile();// get file
		return fileName;
	}// end getFile

}// end class

class PreviousProjects extends JPanel {

	public PreviousProjects() {
		// set Layout
		this.setLayout(null);

		// initialize components
		JLabel pickPrevious = new JLabel("Pick Previous Project: ");
		JComboBox choosePrevious = new JComboBox();
		choosePrevious.addItem("some project that I worked hard on");

		pickPrevious.setBounds(10, 10, 175, 25);
		choosePrevious.setBounds(10, 35, 200, 25);

		this.add(pickPrevious);
		this.add(choosePrevious);
		this.setVisible(true);
	}// end constructor

}// end class PreviousProjects
