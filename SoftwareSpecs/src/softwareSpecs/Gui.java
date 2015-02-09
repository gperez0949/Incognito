//last edit by James Palmisano
//at 2/2/15
package softwareSpecs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class Gui extends JFrame {

	public Gui() {
		
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
		
		// make TabbedPane
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.decode("#014D33"));// FGCU Green

		/*
		 * @setMnemonicAt by pressing alt and some number allows use to navigate
		 * the tabs
		 */
		JComponent panel1 = new JPanel();
		tabbedPane.addTab("<html><font color=\"grey\">Step 1 Get Text File</font></html>",panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = new JPanel();
		tabbedPane.addTab("<html><font color=\"grey\">Step 2 Data Extraction</font></html>",panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		panel2.setSize(700, 500);
		panel2.setVisible(true);
		panel2.setLayout(null);
		panel2.add(new DataSettings());

		JComponent panel3 = new JPanel();
		tabbedPane.addTab("<html><font color=\"grey\">Step 3 Graphing and Export</font></html>",panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		wrapper.add(tabbedPane);
		this.add(wrapper);
		this.show();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// end Gui

	public static void main(String[] args) {
		
		new Gui();
	}// end main

}// end Gui
