//last edit by James Palmisano
//at 2/2/15
package src.softwareSpecs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class Gui extends JFrame {
	private File selectedFile;

	public Gui() throws FileNotFoundException {

		this.setSize(720, 580);// set size
		this.setResizable(false);// can't resize
		this.getContentPane().setBackground(Color.decode("#001F48"));// FGCU//
																		// Blue

		// make it show up in the center of the screen each time
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		this.setTitle("Traffex Graphing Utility");

		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BorderLayout());
		wrapper.setBackground(Color.decode("#001F48"));
		wrapper.setBorder(new EmptyBorder(10, 10, 10, 10));

		/*
		 * @setMnemonicAt by pressing alt and some number allows use to navigate
		 * the tabs
		 */
		// start panel 1

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.decode("#014D33"));// FGCU Green

		/*
		 * @setMnemonicAt by pressing alt and some number allows use to navigate
		 * the tabs
		 */
		JComponent panel1 = new JPanel();
		tabbedPane.addTab(
				"<html><H3 color=\"#00b2b2\">Open File</H3></html>",
				panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		panel1.setSize(700, 500);
		panel1.setVisible(true);
		panel1.setLayout(null);
		panel1.add(new GetProjectOrCreateNew());
		// end panel 1

		// start panel 2
		JComponent panel2 = new JPanel();
		tabbedPane
				.addTab("<html><H3 color=\"#00b2b2\">Data Settings</H3></html>",
						panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		panel2.setSize(700, 500);
		panel2.setVisible(true);
		panel2.setLayout(null);
		panel2.add(new DataSettings());
		// end panel 2

		// start panel 3
		JComponent panel3 = new JPanel();
		tabbedPane.addTab("<html><H3 color=\"#00b2b2\">Graphs</H3></html>",panel3);


		tabbedPane.addTab("<html><H3 color=\"#00b2b2\">Step 3 Graphing</H3></html>",panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);


		
		panel3.setSize(700,500);

		tabbedPane
				.addTab("<html><H3 color=\"#00b2b2\">Step 3 Graphing and Export</H3></html>",
						panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		panel3.setSize(700, 500);
		panel3.setVisible(true);
		panel3.add(new Graphs());
		// end panel 3

		wrapper.add(tabbedPane);
		this.add(wrapper);
		this.show();


        JComponent panel4 = new JPanel();
        tabbedPane.addTab("<html><H3 color=\"#00b2b2\">Export</H3></html>", panel4);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        panel4.setSize(700,500);
        panel4.setVisible(true);
        panel4.setLayout(null);
        Export export = new Export();
        panel4.add(export);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// end Gui

	public static void main(String[] args) throws FileNotFoundException {
		new Gui();
	}// end main

}// end Gui
