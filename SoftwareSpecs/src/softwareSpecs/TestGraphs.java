package src.softwareSpecs;

import javafx.application.Application;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

/**
 * Created by austinnafziger on 4/6/15.
 */
public class TestGraphs{

    public static void main(String[] args) throws IOException {

       // Graphs graph = new Graphs("Title");






        JFrame window = new JFrame();

        window.setSize(700, 500);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Graphs g = new Graphs();






        window.add(g);




        window.setVisible(true);
    }
}
