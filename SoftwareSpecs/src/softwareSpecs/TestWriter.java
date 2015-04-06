package src.softwareSpecs;

import jxl.write.WriteException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 4/6/15
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestWriter {

    public static void main(String[] args) {

        try {
            double startTime = System.nanoTime();

            double startReadTime = System.nanoTime();

            Excelerator excelerator = new Excelerator();
            excelerator.setInputFile("C:\\Users\\Gabriel\\Documents\\Incognito\\SoftwareSpecs\\Corridor_Report.xls");
            excelerator.Read();

            double endReadTime = System.nanoTime();
            System.out.println("\n\t Took " + (endReadTime- startReadTime)/1000000000 + "s to read");

            //end reading


            //start analyzing

            double startAnalyzeTime = System.nanoTime();

            excelerator.Analyze("C:\\Users\\Gabriel\\Documents\\Incognito\\SoftwareSpecs\\settings.dat");

            double endAnalyzeTime = System.nanoTime();
            System.out.println("\n\t Took " + (endAnalyzeTime- startAnalyzeTime)/1000000000 + "s to analyze");

            //end analyze

            //start writing

            double startWriteTime = System.nanoTime();

            excelerator.Write("TestMaster");

            double endWriteTime = System.nanoTime();
            System.out.println("\n\t Took " + (endWriteTime- startWriteTime)/1000000000 + "s to Write");

            double endTime = System.nanoTime();
            System.out.println("\n\t Took " + (endTime- startTime)/1000000000 + "s for full program");





        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (WriteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }//end main




}
