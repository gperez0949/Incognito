package src.softwareSpecs;

import javafx.application.Application;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * Created by austinnafziger on 4/6/15.
 */
public class TestGraphs{

    public static void main(String[] args) throws FileNotFoundException {

        Graphs graph = new Graphs("Title");

        JFrame window = new JFrame();
        window.setVisible(true);
        window.setSize(700, 500);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.add(graph);
    }
}
