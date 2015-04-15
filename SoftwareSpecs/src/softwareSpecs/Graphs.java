package src.softwareSpecs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graphs extends JPanel {

    //create variables for class wide access
    private static double maximum = 0;
    private static Color barCol = Color.BLUE;
    ChartPanel panel;
    JPanel north;
    JComboBox<String> events;
    JComboBox<String> routes;
    JButton update;
    JButton color;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();;
    double[] difference;
    int dataCount;
    Object[] eventKeys;
    Excelerator excelerator;
    Dimension screen;
    JTextField width;
    JTextField height;
    JTextField path;
    JButton export;
    JFreeChart barchart;
    boolean firstData = true;

    //empty constructor for the class
    public Graphs() throws IOException{
        //do nothing
    }

    //constructor that takes in the title of the graph and the analyzed data
    public Graphs(String chartTitle , Excelerator excel) throws IOException {

        //create UI components for the graph update and export functions
        north = new JPanel();
        events = new JComboBox<String>();
        routes = new JComboBox<String>();
        update = new JButton("Update");
        color = new JButton("Choose Color");
        width = new JTextField("Width");
        height = new JTextField("Height");
        path = new JTextField("Name of File");
        export = new JButton("Export");

        //set the tool tip text for the components
        update.setToolTipText("Click to update the graph with the most recent settings.");
        color.setToolTipText("Click to choose a new color for the bars.");
        width.setToolTipText("Enter the width of the image in pixels.");
        height.setToolTipText("Enter the height of the image in pixels.");
        path.setToolTipText("Enter the name of the image you want to make.");
        export.setToolTipText("Export the graph as a PNG image.");

        //get the size of the current computer's display
        screen = Toolkit.getDefaultToolkit().getScreenSize();

        //set the imported excelerator instance to a local instance
        this.excelerator = excel;

        //set the layout of the class wide jpanel to absolute
        this.setLayout(null);

        //get the event keys from the analyzed data
        Set a = excelerator.avgTravelTimes.keySet();
        eventKeys = a.toArray();
        //get the route keys from the analyzed data
        Set b = excelerator.avgTravelTimes.get(eventKeys[0]).keySet();
        Object[] routeKeys = b.toArray();

        //set the event keys to the drop down box for events
        for (int i = 1; i < eventKeys.length; i++) {
            events.addItem((String) eventKeys[i]);
        }
        //set the route keys to the drop down box for routes
        for (int i = 0; i < routeKeys.length; i++) {
            routes.addItem((String) routeKeys[i]);
        }

        //create the percent difference array with length of data count
        dataCount = excelerator.avgTravelTimes.get(eventKeys[0])
                .get(routeKeys[0]).size();
        difference = new double[dataCount];

        //create the data set for the graph to use
        createDataSet();

        //create the panel that holds the graph to be added to the UI
        panel = update(chartTitle);

        //create the action listener for the color selection
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //create the color chooser in a new window
                final JColorChooser colorChooser = new JColorChooser();
                JFrame secondary = new JFrame();
                secondary.setSize(500, 400);
                secondary.add(colorChooser);

                //set the color model and add a change listener to update the color with the new
                //chosen color
                ColorSelectionModel model = colorChooser.getSelectionModel();
                ChangeListener changeListener = new ChangeListener() {
                    public void stateChanged(ChangeEvent changeEvent) {

                        //update the class variable barCol with the new color
                        barCol = colorChooser.getColor();
                    }
                };

                model.addChangeListener(changeListener);
                secondary.setVisible(true);
            }
        });

        //create the action listener for the update graph button
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //set the title of the new chart to the currently selected item in the route drop down
                panel.getChart().setTitle((String) routes.getSelectedItem());

                //set the color of the bars in the chart to the class variable barCol
                panel.getChart().getCategoryPlot().getRenderer().setSeriesPaint(0, barCol);

                //create the new data set for the chart and the new route
                try {

                    createDataSet();

                } catch (IOException e1) {

                    e1.printStackTrace();
                }
            }
        });

        //create an action listener for the export button
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //call the export function
                export();
            }
        });

        //add the control components to the north panel
        north.add(events);
        north.add(routes);
        north.add(color);
        north.add(update);
        north.add(width);
        north.add(height);
        north.add(path);
        north.add(export);

        //set the size and position of the north panel
        north.setBounds(0, 0, (int)(1277*(screen.getWidth()/1366.0)), ((int)(30*screen.getHeight()/768.0)));

        //set the size of the graph panel
        this.setSize((int)(1150*(screen.getWidth()/1366.0)), ((int)(489*screen.getHeight()/768.0)));

        //add the north panel and the graph to the graph panel
        this.add(north);
        this.add(panel);
        this.setVisible(true);
    }

    //create a method to update the graph with the most recent settings
    private ChartPanel update(String chartTitle) throws IOException {

        //create the new barchart
        barchart = ChartFactory.createBarChart(
                chartTitle,
                "Time Interval",
                "Percent Change",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);

        //create a temporary local variable to modify elements of the barchart
        CategoryPlot plot = barchart.getCategoryPlot();

        //create a temporary variable to hold the renderer
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        //create a new scale array for the y axis
        String[] scale = new String[(int) maximum];

        //populate the scale array
        for (int i = 0; i < scale.length - 1; i++) {
            scale[i] = Double.toString(i);
        }

        //set the y axis of the graph
        SymbolAxis rangeAxis = new SymbolAxis("", scale);
        rangeAxis.setTickUnit(new NumberTickUnit(25));
        rangeAxis.setAutoRange(true);
        rangeAxis.setRange(0, Math.abs(maximum));
        plot.setRangeAxis(rangeAxis);

        //set the color of the bars
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setSeriesPaint(0, barCol);

        //create a jpanel with the barchart
        ChartPanel panel = new ChartPanel(barchart);

        //set the size and position of the panel
        panel.setBounds(((int)(51*(screen.getWidth()/1366.0))), ((int)(30*screen.getHeight()/768.0)),(int)(1150*(screen.getWidth()/1366.0)), (((int)(489*screen.getHeight()/768.0))-((int)(30*screen.getHeight()/768.0))));
        panel.setVisible(true);

        return panel;
    }

    //create a method to make the dataset for the barchart
    private void createDataSet() throws IOException {

        //a for loop to get and calculate the percent difference
        for (int i = 0; i < dataCount; i++) {
            difference[i] = 100
                    * (excelerator.avgTravelTimes.get(events.getSelectedItem())
                    .get(routes.getSelectedItem()).get(i) - excelerator.avgTravelTimes
                    .get(eventKeys[0]).get(routes.getSelectedItem())
                    .get(i))
                    / excelerator.avgTravelTimes.get(eventKeys[0])
                    .get(routes.getSelectedItem()).get(i);
        }

        //clear the old dataset
        dataset.clear();

        //create a counter and a for loop to add data to the new dataset
        int counter = 0;
        for (int i = excelerator.startTime; i < excelerator.endTime; i++) {

            //if the data is NaN then skip it
            if (Double.isNaN(difference[counter])) {
                continue;
            }

            //add the percent difference to the dataset and set the scale for the x axis
            dataset.addValue((Number) difference[counter], 0, i + ":00");

            //set the maximum to the largest percent difference
            if (difference[counter] > maximum) {

                maximum = difference[counter];

                //if this is not the first run of the method then update the y axis information
                //this code is the same as the y axis code above
                if(firstData == false){

                    CategoryPlot plot = barchart.getCategoryPlot();
                    BarRenderer renderer = (BarRenderer) plot.getRenderer();
                    String[] scale = new String[(int) maximum];

                    for (int j = 0; j < scale.length - 1; j++) {
                        scale[j] = Double.toString(j);
                    }

                    SymbolAxis rangeAxis = new SymbolAxis("", scale);
                    rangeAxis.setTickUnit(new NumberTickUnit(25));
                    rangeAxis.setAutoRange(true);
                    rangeAxis.setRange(0, Math.abs(maximum));
                    plot.setRangeAxis(rangeAxis);
                }
            }

            //increment the counter
            counter++;
        }

        //set the first run to false after the first run is complete
        firstData = false;
    }

    //create a method to export an image
    private void export(){

        //create a new image from the barchart with the width and height that are specified in the UI
        BufferedImage image = barchart.createBufferedImage(Integer.parseInt(width.getText()), Integer.parseInt(height.getText()));

        //create a new file with the name that is specified in the UI
        File file = new File(path.getText()+".png");

        //write to the new file with the image that was created previously
        try {

            ImageIO.write(image, "png", file);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}