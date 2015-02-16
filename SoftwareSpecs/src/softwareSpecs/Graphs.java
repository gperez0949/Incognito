package softwareSpecs;


import javax.swing.JPanel;

import org.jfree.JCommon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.demo.BarChartDemo1;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;


public class Graphs extends JPanel {
	
	public Graphs(){

		DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Linux", 29);
        result.setValue("Mac", 20);
        result.setValue("Windows", 51);
		
			JFreeChart chart = ChartFactory.createPieChart3D("test",          // chart title
	            result,                // data
	            true,                   // include legend
	            true,
	            false);

	        PiePlot3D plot = (PiePlot3D) chart.getPlot();
	        plot.setStartAngle(290);
	        plot.setDirection(Rotation.CLOCKWISE);
	        plot.setForegroundAlpha(0.5f);
	        
	        ChartPanel chartPanel = new ChartPanel(chart);
	        
	        this.add(chartPanel);
	}
}
