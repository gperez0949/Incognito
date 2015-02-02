//last edit by James Palmisano
//at 2/2/15
package softwareSpecs;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Gui extends JFrame {

	public Gui() {
		this.setSize(700, 500);//set size
		this.setResizable(false);//can't resize
		//make it show up in the center of the screen each time
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation
			(dim.width/2-this.getSize().width/2,
			 dim.height/2-this.getSize().height/2);
		this.setTitle("Traffex Graphing Utility");
		this.show();
	}//end Gui

	public static void main(String[] args) {
		new Gui();
	}//end main
	
}//end Gui
