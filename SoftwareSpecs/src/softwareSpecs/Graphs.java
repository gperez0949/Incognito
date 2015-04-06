package src.softwareSpecs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Graphs extends JPanel{

    private static int maximum = 0;

	public Graphs(String chartTitle) throws FileNotFoundException {

        JFreeChart barchart = ChartFactory.createBarChart(chartTitle,
                "Time Interval",
                "Percent Change",
                createDataSet(),
                PlotOrientation.VERTICAL,
                true, true, false);
	}

    private CategoryDataset createDataSet(){

        

        for()
    }
}
