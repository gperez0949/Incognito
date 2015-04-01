package src.softwareSpecs;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.Timer;

/**
 * Created by austinnafziger on 3/25/15.
 */
public class TestAnalyze {

    public static void main(String[] args) {

        try {

            Excelerator excelerator = new Excelerator();
            excelerator.setInputFile("C:\\Users\\Gabriel\\Documents\\Incognito\\SoftwareSpecs\\Corridor_Report.xls");
            excelerator.Read();

            //end reading


            //start analyzing

            double startTime = System.nanoTime();

            excelerator.Analyze("C:\\Users\\Gabriel\\Documents\\Incognito\\SoftwareSpecs\\settings.dat");

            //end analyze

            double endTime = System.nanoTime();
            System.out.println("\n\t Took " + (endTime- startTime)/1000000000 + "s");





        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }




    }
}
