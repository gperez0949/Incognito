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
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;


public class Graphs extends JPanel{

    private static int maximum = 0;

	public Graphs(String chartTitle) throws IOException {

        JFreeChart barchart = ChartFactory.createBarChart(chartTitle,
                "Time Interval",
                "Percent Change",
                createDataSet(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel panel = new ChartPanel(barchart);
        panel.setVisible(true);
        panel.setSize(700, 500);
        this.setVisible(true);
        this.setSize(700, 500);
        this.setLayout(null);

        JPanel north = new JPanel();
        JComboBox<String> events = new JComboBox<String>();
        JComboBox<String> routes = new JComboBox<String>();
        JButton update = new JButton("Update");

        north.add(events);
        north.add(routes);
        north.add(update);

        north.setBounds(0,0,700,50);
        panel.setBounds(0, 50, 700, 450);

        this.add(north);
        this.add(panel);

	}

    private CategoryDataset createDataSet() throws IOException {

        Excelerator excelerator = new Excelerator();
        excelerator.setInputFile("/Users/austinnafziger/Software/SoftwareSpecs/Corridor_Report.xls");
        excelerator.Read();
        excelerator.Analyze("/Users/austinnafziger/Software/SoftwareSpecs/settings.dat");

//        final String fiat = "FIAT";
//        final String audi = "AUDI";
//        final String ford = "FORD";
//        final String speed = "Speed";
//        final String millage = "Millage";
//        final String userrating = "User Rating";
//        final String safety = "safety";

        int start = excelerator.startTime;
        int end = excelerator.endTime;

        double[][] data = createTable(start, end);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < end - start; i++) {

            for(int j=0; j<4; j++){

                dataset.addValue(data[i][j], Double.toString(data[i][0]), Double.toString(data[i][0]));
                System.out.println(data[i][j]);
            }
        }

//        dataset.addValue( 1.0 , fiat , speed );
//        dataset.addValue( 3.0 , fiat , userrating );
//        dataset.addValue( 5.0 , fiat , millage );
//        dataset.addValue( 5.0 , fiat , safety );
//
//        dataset.addValue( 5.0 , audi , speed );
//        dataset.addValue( 6.0 , audi , userrating );
//        dataset.addValue( 10.0 , audi , millage );
//        dataset.addValue( 4.0 , audi , safety );
//
//        dataset.addValue( 4.0 , ford , speed );
//        dataset.addValue( 2.0 , ford , userrating );
//        dataset.addValue( 3.0 , ford , millage );
//        dataset.addValue( 6.0 , ford , safety );

        return dataset;
    }

    private double[][] createTable(int s, int e){

        double[][] temp = new double[e-s][4];

        for(int i = 0; i<e-s; i++){

            for(int j =0; j<4; j++){

                if(j==0){

                    temp[i][j] = i+s;

                }else if(j==3){

                    temp[i][j] = (temp[i][1] - temp[i][2])/temp[i][2] * 100;

                }else{

                    temp[i][j] = 20-j;
                }
            }
        }

        return temp;
    }
}
