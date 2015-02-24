package src.softwareSpecs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by austinnafziger on 2/23/15.
 */
public class Export extends JPanel {

    public Export(){

        this.setLayout(null);
        this.setSize(700,500);
        this.setVisible(true);

        JComboBox fileType = new JComboBox();
        JFileChooser path = new JFileChooser();
        JTextField path1 = new JTextField();
        JButton browse = new JButton();
        JButton export = new JButton();

        fileType.setBounds(10, 10, 50, 30);
        path1.setBounds(10, 50, 300, 30);
        browse.setBounds(330, 50, 50, 30);



        this.add(fileType);
        this.add(path);
        this.add(path1);
        this.add(browse);
        this.add(export);
    }
}
