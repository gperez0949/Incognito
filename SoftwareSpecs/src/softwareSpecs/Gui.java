//last edit by James Palmisano
//at 3/13/15
package src.softwareSpecs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

public class Gui extends JFrame {
	private File selectedFile;
	public String[] routes;
	public String[] dates;
	public String[] times;
	HashMap<String, double[]> avgTravelTimes; // <route name , average travel
												// time>
	HashMap<String, double[]> avgSpeeds; // <route name , average speed>
	HashMap<String, HashMap<String, double[]>> data; // <date, <route name,
														// travel time>> read
														// from raw data.
	final JComponent panel3 = new JPanel();
	final JComponent panel4 = new JPanel();

	public Gui() throws FileNotFoundException {
		routes = new String[5];
		dates = new String[5];
		times = new String[5];

		this.setSize(698, 545);
		this.getContentPane().setBackground(Color.decode("#001F48"));// FGCU//
																		// Blue

		// make it show up in the center of the screen each time
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		this.setTitle("DynamicStat");

//		JPanel wrapper = new JPanel();
//		wrapper.setLayout(new BorderLayout());
//		wrapper.setBackground(Color.decode("#001F48"));
//		wrapper.setBorder(new EmptyBorder(10, 10, 10, 10));

		/*
		 * @setMnemonicAt by pressing alt and some number allows use to navigate
		 * the tabs
		 */
		// start panel 1

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.decode("#014D33"));// FGCU Green
		tabbedPane.setLayout(null);
		tabbedPane.setSize(750,550);
		/*
		 * @setMnemonicAt by pressing alt and some number allows use to navigate
		 * the tabs
		 */
		final GetProjectOrCreateNew panel1 = new GetProjectOrCreateNew();
		panel1.goBtn.setBounds(87, 202, 520, 201);
		tabbedPane.addTab("<html><H3 color=\"#00b2b2\">Open File</H3></html>",
				panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		panel1.setSize(700, 500);
		panel1.setVisible(true);
		// end panel 1

		// start panel 2
		final DataSettings panel2 = new DataSettings(routes, dates, times);
		tabbedPane
				.addTab("<html><H3 color=\"#00b2b2\">Data Settings</H3></html>",
						panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		panel2.setVisible(false);
		panel2.setSize(700, 500);
		panel2.setLayout(null);
		panel2.submitPanel.submitButton.addActionListener(new ActionListener() {
			//When the submit btn is click do this...
			@Override
			public void actionPerformed(ActionEvent e) {

				// change their title colors as well
				tabbedPane
						.setTitleAt(1,
								"<html><H3 color=\"#00b2b2\">Data Settings</H3></html>");
				tabbedPane.setTitleAt(2,
						"<html><H3 color=\"#00b2b2\">Graphs</H3></html>");
				tabbedPane.setTitleAt(3,
						"<html><H3 color=\"#00b2b2\">Export</H3></html>");
				// make sure they are not active in the
				// tabbed pane as well
				tabbedPane.setEnabledAt(0, true);
				tabbedPane.setEnabledAt(1, true);
				tabbedPane.setEnabledAt(2, true);
				tabbedPane.setEnabledAt(3, true);
				tabbedPane.setSelectedIndex(2);

			}
		});
		// end panel 2

		// start panel 3
		tabbedPane.addTab("<html><H3 color=\"#00b2b2\">Graphs</H3></html>",
				panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		panel3.setSize(700, 500);

		tabbedPane.addTab("<html><H3 color=\"#00b2b2\">Graphs</H3></html>",
				panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		panel3.setSize(700, 500);
		panel3.setVisible(true);

		Graphs graphs = null;
		try {
			graphs = new Graphs();
			panel3.add(graphs);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		//panel3.add(new Graphs());

		// end panel 3

		//wrapper.add(tabbedPane);
		getContentPane().add(tabbedPane);
		getContentPane().add(tabbedPane);
		this.show();
		// start panel 4
		
		tabbedPane.addTab("<html><H3 color=\"#00b2b2\">Export</H3></html>",
				panel4);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		panel4.setSize(700, 500);
		panel4.setVisible(true);
		panel4.setLayout(null);
		Export export = new Export();
		panel4.add(export);
		// end panel 4

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel2.setVisible(false);
		panel3.setVisible(false);
		panel4.setVisible(false);
		// change their title colors as well
		tabbedPane.setTitleAt(1,
				"<html><H3 color=\"#3D3D4C\">Data Settings</H3></html>");
		tabbedPane.setTitleAt(2,
				"<html><H3 color=\"#3D3D4C\">Graphs</H3></html>");
		tabbedPane.setTitleAt(3,
				"<html><H3 color=\"#3D3D4C\">Export</H3></html>");
		// make sure they are not active in the
		// tabbed pane as well
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
		tabbedPane.setEnabledAt(3, false);
		// add actionlisteners
		panel1.goBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(true);
				panel3.setVisible(false);
				panel4.setVisible(false);
				// change their title colors as well
				tabbedPane
						.setTitleAt(1,
								"<html><H3 color=\"#00b2b2\">Data Settings</H3></html>");
				tabbedPane.setTitleAt(2,
						"<html><H3 color=\"#3D3D4C\">Graphs</H3></html>");
				tabbedPane.setTitleAt(3,
						"<html><H3 color=\"#3D3D4C\">Export</H3></html>");
				// make sure they are not active in the
				// tabbed pane as well
				tabbedPane.setEnabledAt(0, true);
				tabbedPane.setEnabledAt(1, true);
				tabbedPane.setEnabledAt(2, false);
				tabbedPane.setEnabledAt(3, false);
				tabbedPane.setSelectedIndex(1);
			}// end action listener
		});// end add action listener

		panel1.btnFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewButtonActionPerformed(e);
			}
		});
		panel1.setLayout(null);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), tabbedPane, panel1, panel1.goBtn, panel2, panel3, graphs, panel4, export}));

	}// end Gui

	/**
	 * handles action when go button is pressed
	 *
	 * enables all tabs and loads all project data into program.
	 *
	 * @param e
	 */
	public void goButtonActionPerformed(ActionEvent e) {

	} // end goButton

	/**
	 * handles action when create new button is pressed
	 *
	 * enables the data settings button and loads raw data into program
	 *
	 * Also creates all files and folders in the project workspace
	 *
	 *
	 * @param e
	 */
	public void createNewButtonActionPerformed(ActionEvent e) {

	} // end create new Button

	public static void main(String[] args) throws FileNotFoundException {
		new Gui();
	}// end main
}// end Gui
