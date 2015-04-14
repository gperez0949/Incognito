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

    public Graphs() throws IOException{
        //do nothing
    }


    public Graphs(String chartTitle , Excelerator excel) throws IOException {
        north = new JPanel();
        events = new JComboBox<String>();
        routes = new JComboBox<String>();
        update = new JButton("Update");
        color = new JButton("Choose Color");
        width = new JTextField("Width");
        height = new JTextField("Height");
        path = new JTextField("Name of File");
        export = new JButton("Export");

       screen = Toolkit.getDefaultToolkit().getScreenSize();

        this.excelerator = excel;
        this.setLayout(null);

        Set a = excelerator.avgTravelTimes.keySet();
        eventKeys = a.toArray();
        Set b = excelerator.avgTravelTimes.get(eventKeys[0]).keySet();
        Object[] routeKeys = b.toArray();
        for (int i = 1; i < eventKeys.length; i++) {
            events.addItem((String) eventKeys[i]);
        }
        for (int i = 0; i < routeKeys.length; i++) {
            routes.addItem((String) routeKeys[i]);
        }

        dataCount = excelerator.avgTravelTimes.get(eventKeys[0])
                .get(routeKeys[0]).size();
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

                        barCol = colorChooser.getColor();
                    }
                };
                model.addChangeListener(changeListener);
                secondary.setVisible(true);
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.getChart().setTitle((String) routes.getSelectedItem());
                panel.getChart().getCategoryPlot().getRenderer()
                        .setSeriesPaint(0, barCol);
                try {
                    createDataSet();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                export();
            }
        });

        north.add(events);
        north.add(routes);
        north.add(color);
        north.add(update);
        north.add(width);
        north.add(height);
        north.add(path);
        north.add(export);

        north.setBounds(0, 0, (int)(1277*(screen.getWidth()/1366.0)), ((int)(30*screen.getHeight()/768.0)));

        this.setSize((int)(1150*(screen.getWidth()/1366.0)), ((int)(489*screen.getHeight()/768.0)));

        this.add(north);
        this.add(panel);
        this.setVisible(true);
    }

    private ChartPanel update(String chartTitle) throws IOException {
        barchart = ChartFactory.createBarChart(chartTitle,
                "Time Interval", "Percent Change", dataset,
                PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = barchart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        String[] scale = new String[(int) maximum];

        for (int i = 0; i < scale.length - 1; i++) {
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
        panel.setBounds(((int)(51*(screen.getWidth()/1366.0))), ((int)(30*screen.getHeight()/768.0)),(int)(1150*(screen.getWidth()/1366.0)), (((int)(489*screen.getHeight()/768.0))-((int)(30*screen.getHeight()/768.0))));
        panel.setVisible(true);



        return panel;
    }

    private void createDataSet() throws IOException {

        for (int i = 0; i < dataCount; i++) {
            difference[i] = 100
                    * (excelerator.avgTravelTimes.get(events.getSelectedItem())
                    .get(routes.getSelectedItem()).get(i) - excelerator.avgTravelTimes
                    .get(eventKeys[0]).get(routes.getSelectedItem())
                    .get(i))
                    / excelerator.avgTravelTimes.get(eventKeys[0])
                    .get(routes.getSelectedItem()).get(i);
        }

        dataset.clear();

        for (int i = 0; i < difference.length - 1; i++) {
            if (Double.isNaN(difference[i])) {
                continue;
            }
            dataset.addValue((Number) difference[i], 0, i);
            if (difference[i] > maximum) {
                maximum = difference[i];
            }
        }

    }

    private void export(){

        BufferedImage image = barchart.createBufferedImage(Integer.parseInt(width.getText()), Integer.parseInt(height.getText()));

        File file = new File(path.getText()+".png");

        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}