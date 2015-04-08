package src.softwareSpecs;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.renderer.category.BarPainter;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.image.ColorModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Graphs extends JPanel{

    private static double maximum = 0;
    private static Color barCol = Color.BLUE;
    ChartPanel panel;

	public Graphs(String chartTitle) throws IOException {

        panel = update(chartTitle);

        JPanel north = new JPanel();
        JComboBox<String> events = new JComboBox<String>();
        JComboBox<String> routes = new JComboBox<String>();
        final JButton update = new JButton("Update");
        JButton color = new JButton("Choose Color");

        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final JColorChooser colorChooser = new JColorChooser();

                JFrame secondary = new JFrame();
                secondary.setSize(500, 400);

                secondary.add(colorChooser);

                ColorSelectionModel model = colorChooser.getSelectionModel();
                ChangeListener changeListener = new ChangeListener() {
                    public void stateChanged(ChangeEvent changeEvent) {
                        Color newForegroundColor = colorChooser.getColor();
                        barCol = newForegroundColor;
                    }
                };
                model.addChangeListener(changeListener);

                secondary.setVisible(true);
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    panel.getChart().setTitle("hello");
                    panel.getChart().getCategoryPlot().getRenderer().setSeriesPaint(0, barCol);
            }
        });

        north.add(events);
        north.add(routes);
        north.add(update);
        north.add(color);

        north.setBounds(0,0,900,50);

        this.setVisible(true);
        this.setSize(900, 700);
        this.setLayout(null);

        this.add(north);
        this.add(panel);
	}

    private ChartPanel update(String chartTitle) throws IOException {

        JFreeChart barchart = ChartFactory.createBarChart(chartTitle,
                "Time Interval",
                "Percent Change",
                createDataSet(),
                PlotOrientation.VERTICAL,
                false, true, false);

        CategoryPlot plot = barchart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer)plot.getRenderer();

        String[] scale = new String[(int)maximum];

        for(int i = 0; i< scale.length-1; i++){

            scale[i] = Double.toString(i);
        }

        SymbolAxis rangeAxis = new SymbolAxis("", scale);
        rangeAxis.setTickUnit(new NumberTickUnit(25));
        rangeAxis.setAutoRange(false);
        rangeAxis.setRange(0, maximum);
        plot.setRangeAxis(rangeAxis);

        renderer.setBarPainter(new StandardBarPainter());

        renderer.setSeriesPaint(0, barCol);

        ChartPanel panel = new ChartPanel(barchart);

        panel.setSize(900, 700);

        panel.setBounds(0, 50, 900, 650);

        panel.setVisible(true);
        
        return panel;
    }

    private CategoryDataset createDataSet() throws IOException {

        Excelerator excelerator = new Excelerator();
        excelerator.setInputFile("/Users/austinnafziger/Software/SoftwareSpecs/Corridor_Report.xls");
        excelerator.Read();
        excelerator.Analyze("/Users/austinnafziger/Software/SoftwareSpecs/settings.dat");


        int start = excelerator.startTime;
        int end = excelerator.endTime;

        double[][] data = createTable(start, end);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < end - start+1; i++) {

            for(int j=0; j<4; j++){

                dataset.addValue(data[i][j], "0", Double.toString(data[i][0]));

            }
        }

        return dataset;
    }

    private double[][] createTable(int s, int e){

        double[][] temp = new double[e-s+1][4];

        for(int i = 0; i<e-s+1; i++){

            for(int j =0; j<4; j++){

                if(j==0){

                    temp[i][j] = i+s;

                }else if(j==3){

                    temp[i][j] = (temp[i][1] - temp[i][2])/temp[i][2] * 100 + 70;
                    //System.out.println(temp[i][j]);

                    if(temp[i][j] > maximum){

                       // System.out.println("Max changed");
                        maximum = temp[i][j];
                    }

                }else{

                        temp[i][j]= 20+j;
                }
            }
        }

        return temp;
    }
}
