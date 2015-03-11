package src.softwareSpecs;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Graphs extends JPanel {

	public Graphs() throws FileNotFoundException {

		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Linux", 29);
		result.setValue("Mac", 20);
		result.setValue("Windows", 51);

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(7445, "JFreeSVG", "Warm-up");
		dataset.addValue(24448, "Batik", "Warm-up");
		dataset.addValue(4297, "JFreeSVG", "Test");
		dataset.addValue(21022, "Batik", "Test");

		JFreeChart chart = ChartFactory.createBarChart("Test", "Time",
				"Percent Difference", dataset);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		// NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		ChartPanel chartPanel = new ChartPanel(chart);

		this.add(chartPanel);

		DataReader read = new DataReader();

		ArrayList<double[]> data = read.getData();
		ArrayList<String> names = read.getNames();
		ArrayList<Integer> types = read.getTypes();
		
	}
}
