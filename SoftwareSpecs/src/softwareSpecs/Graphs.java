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
import java.awt.geom.Arc2D;
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
    JPanel north;
    JComboBox<String> events;
    JComboBox<String> routes;
    JButton update;
    JButton color ;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();;
    double[] difference;
    int dataCount;
    Object[] eventKeys;

    Excelerator excelerator;

	public Graphs(String chartTitle) throws IOException {



        north = new JPanel();
        events = new JComboBox<String>();
        routes = new JComboBox<String>();
        update = new JButton("Update");
        color = new JButton("Choose Color");

        excelerator = new Excelerator();
        excelerator.setInputFile("/Users/austinnafziger/Software/SoftwareSpecs/Corridor_Report.xls");
        excelerator.Read();
        excelerator.Analyze("/Users/austinnafziger/Software/SoftwareSpecs/settings.dat");



        Set a = excelerator.avgTravelTimes.keySet();
        eventKeys = a.toArray();

        Set b = excelerator.avgTravelTimes.get(eventKeys[0]).keySet();
        Object[] routeKeys = b.toArray();

        for(int i =1; i<eventKeys.length ; i++){

            events.addItem((String)eventKeys[i]);
        }

        for(int i =0; i<routeKeys.length ; i++){

            routes.addItem((String)routeKeys[i]);
        }

        dataCount = excelerator.avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size();
        difference = new double[dataCount];

        createDataSet();



        panel = update(chartTitle);

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

                panel.getChart().setTitle((String)routes.getSelectedItem());
                panel.getChart().getCategoryPlot().getRenderer().setSeriesPaint(0, barCol);
                try {
                    createDataSet();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

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
                dataset,
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
        rangeAxis.setRange(0, Math.abs(maximum));
        plot.setRangeAxis(rangeAxis);

        renderer.setBarPainter(new StandardBarPainter());

        renderer.setSeriesPaint(0, barCol);

        ChartPanel panel = new ChartPanel(barchart);

        panel.setSize(900, 700);

        panel.setBounds(0, 50, 900, 650);

        panel.setVisible(true);

        return panel;
    }

    private void createDataSet() throws IOException {

        for(int i = 0 ; i < dataCount; i++){

            difference[i] = 100*(excelerator.avgTravelTimes.get(events.getSelectedItem()).get(routes.getSelectedItem()).get(i) -
                    excelerator.avgTravelTimes.get(eventKeys[0]).get(routes.getSelectedItem()).get(i))
                    /excelerator.avgTravelTimes.get(eventKeys[0]).get(routes.getSelectedItem()).get(i);


        }


        dataset.clear();

            for (int i = 0; i<difference.length-1; i++) {

                if(Double.isNaN(difference[i])){

                    continue;
                }

                dataset.addValue((Number)difference[i], 0, i);


                if (difference[i] > maximum) {

                    maximum = difference[i];
                }
            }

    }

}
