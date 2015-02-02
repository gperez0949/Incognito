//last edit by James Palmisano
//at 2/2/15
package softwareSpecs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Gui extends JFrame {

	public Gui() {
		this.setSize(700, 500);//set size
		this.setResizable(false);//can't resize
		this.getContentPane().setBackground(Color.decode("#009900"));
		//make it show up in the center of the screen each time
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation
			(dim.width/2-this.getSize().width/2,
			 dim.height/2-this.getSize().height/2);
		this.setTitle("Traffex Graphing Utility");
		//make TabbedPane
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JComponent panel1 = new JPanel();
		tabbedPane.addTab("Step 1 Get Text File", panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = new JPanel();
		tabbedPane.addTab("Step 2 Data extraction", panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = new JPanel();
		tabbedPane.addTab("Step 3 Graphing and Export", panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		//by pressing alt and some number allows use to navigate the 
		//tabs
		this.add(tabbedPane);
		this.show();
	}//end Gui

	public static void main(String[] args) {
		new Gui();
	}//end main
	
}//end Gui
