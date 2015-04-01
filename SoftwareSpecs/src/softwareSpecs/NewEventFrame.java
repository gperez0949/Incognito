package src.softwareSpecs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 2/25/15
 * Time: 10:38 AM
 *
 * Will open only when the new Event button is pressed in the datasettings tab
 *
 */
public class NewEventFrame extends JFrame {


    //components
    JTextField newEventName;
    JButton submitButton;
    JButton cancelButton;
    JButton addButton;
    JButton removeButton;
    JLabel startTimeLabel;
    JComboBox startTimeCombo;
    JLabel availableDates;
    DefaultListModel optionsModel;
    JList optionsList;
    JScrollPane optionsPane;
    JLabel selectedDates;
    DefaultListModel selectedListModel;
    JList selectedList;
    JScrollPane selectedPane;

    public NewEventFrame(){

        //initilize Frame parameters
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(600,400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //setting locatoin at center of screen for now
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
                / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //initialize components
        newEventName = new JTextField("New Event Name");
        submitButton = new JButton("Submit");
        cancelButton = new JButton("cancel");
        addButton = new JButton(">>");
        removeButton = new JButton("<<");
        startTimeLabel = new JLabel("Event Start Time: ");
        startTimeCombo = new JComboBox();  //todo add an appropriate string array
        availableDates = new JLabel("Available Dates");
        optionsModel = new DefaultListModel();
        optionsList = new JList(optionsModel);
        optionsPane = new JScrollPane(optionsList);
        selectedDates = new JLabel("Selected Dates");
        selectedListModel = new DefaultListModel();
        selectedList = new JList(selectedListModel);
        selectedPane = new JScrollPane(selectedList);

        //set bounds
        newEventName.setBounds(350,15,180,20);
        startTimeLabel.setBounds(10,15,130,36);
        startTimeCombo.setBounds(145,15,130,36);
        addButton.setBounds(275,113,50,36);
        removeButton.setBounds(275,195,50,36);
        availableDates.setBounds(85,53,130,36);
        optionsPane.setBounds(10,94,255,210);
        selectedDates.setBounds(415,53,130,36);
        selectedPane.setBounds(335,94,255,210);
        submitButton.setBounds(165,330,130,36);
        cancelButton.setBounds(305,330,130,36);

        //initilize JList //todo
        optionsModel.addElement("03/15/2014");
        optionsModel.addElement("03/16/2014");
        optionsModel.addElement("03/17/2014");
        optionsModel.addElement("03/20/2014");

        //addActionListeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerformed(e);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeButtonActionPerformed(e);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButtonActionPerformed(e);
            }
        });

        //add to frame
        this.add(newEventName);
        this.add(submitButton);
        this.add(cancelButton);
        this.add(startTimeLabel);
        this.add(startTimeCombo);
        this.add(addButton);
        this.add(removeButton);
        this.add(selectedDates);
        this.add(selectedPane);
        this.add(availableDates);
        this.add(optionsPane);

        //visualize frame
        this.setVisible(true);

    } //ends constructor

    /**
     * ActionListeners
     */
    public void addButtonActionPerformed(ActionEvent e){

        selectedListModel.addElement(optionsList.getSelectedValue());
        optionsModel.remove(optionsList.getSelectedIndex());

    } //ends add button

    public void removeButtonActionPerformed(ActionEvent e){


        optionsModel.addElement(selectedList.getSelectedValue());
        selectedListModel.remove(selectedList.getSelectedIndex());

    }//ends remove button

    public void submitButtonActionPerformed(ActionEvent e){

    }


    public static void main(String[] args){

        NewEventFrame newEventFrame = new NewEventFrame();


    }






}


