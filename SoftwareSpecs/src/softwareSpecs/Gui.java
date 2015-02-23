//last edit by James Palmisano
//at 2/2/15
package src.softwareSpecs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class Gui extends JFrame {
	private File selectedFile; 

	public Gui() throws FileNotFoundException {
		
		this.setSize(720, 580);// set size
		this.setResizable(false);// can't resize
		this.getContentPane().setBackground(Color.decode("#001F48"));// FGCU// Blue
		
		// make it show up in the center of the screen each time
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height/ 2 - this.getSize().height / 2);
		this.setTitle("Traffex Graphing Utility");
		
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BorderLayout());
		wrapper.setBackground(Color.decode("#001F48"));
		wrapper.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		/*@setMnemonicAt 
		 * by pressing alt and some number allows use to navigate the 
		tabs*/
		//start panel 1
		JComponent panel1 = new JPanel();
		JButton btnFile = new JButton("Click to choose file");
		btnFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			//when Click I want to open a JavaFile chooser
				selectedFile = getFile();
			}//end actionPerformed method
		});
		btnFile.setLayout(new GridLayout(1,3,200,0));
		panel1.add(btnFile);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab
		("<html><font color=\"grey\">Step 1 Get Text File</font></html>", panel1);

		// make TabbedPane
		//JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.decode("#014D33"));// FGCU Green

		/*
		 * @setMnemonicAt by pressing alt and some number allows use to navigate
		 * the tabs
		 */
		tabbedPane.addTab("<html><H3 color=\"grey\">Step 1 Get Text File</H3></html>",panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		//end panel 1
		
		//start panel 2
		JComponent panel2 = new JPanel();
		tabbedPane.addTab("<html><H3 color=\"grey\">Step 2 Data Extraction</H3></html>",panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		panel2.setSize(700, 500);
		panel2.setVisible(true);
		panel2.setLayout(null);
		panel2.add(new DataSettings());

		JComponent panel3 = new JPanel();
		tabbedPane.addTab("<html><H3 color=\"grey\">Step 3 Graphing and Export</H3></html>",panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		
		panel3.setSize(700,500);
		panel3.setVisible(true);
		panel3.add(new Graphs());
		//end panel 3
		wrapper.add(tabbedPane);
		this.add(wrapper);
		this.show();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// end Gui

	public File getFile(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode
			(JFileChooser.FILES_AND_DIRECTORIES);
		int result = fileChooser.showOpenDialog(this);
		//if user clicked Cancel button on dialog, return
		File fileName = fileChooser.getSelectedFile();//get file
		return fileName;
	}//end getFile
	
	public static void main(String[] args) throws FileNotFoundException {
		
		new Gui();
	}// end main

}// end Gui
