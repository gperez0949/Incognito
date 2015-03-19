package src.softwareSpecs;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import jxl.Cell;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Created by Austin Nafziger on 3/11/15.
 */
public class Excelerator {
    double[] distance;
    HashMap<String, double[]> avgTravelTimes; // <route name , average travel time>
    HashMap<String, HashMap<String, double[]>> data; // <date, <route name,
                                                     // travel time>> read
                                                     // from raw data.

	private String inputFile;

	public void setInputFile(String input) {

		inputFile = input;
	}



    public void Read() throws IOException{


        //initialize maps

        data = new HashMap<String, HashMap<String, double[]>>();


        //open workbook
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // get sheets

            for(int k = 0; k < w.getSheets().length - 1; k++){
                Sheet sheet = w.getSheet(k);

                avgTravelTimes = new HashMap<String, double[]>();

                //get date
                Cell date = sheet.getCell(1,7);
                System.out.println(date.getContents());

                for (int j = 3; j < sheet.getColumns(); j++) {

                    double[] routeTime = new double[sheet.getRows() - 16 - 8];


                    //get route and distance
                    Cell route = sheet.getCell(j,13);
                    Cell distanceLocal = sheet.getCell(j,14);

//                    distance[j-3] = Double.parseDouble(distanceLocal.getContents());

                    for (int i = 16; i < sheet.getRows()-8; i++) {

                        Cell cell = sheet.getCell(j,i);

                        //add cell content to array of route times

                        if(cell.getContents().equals("")){

                            routeTime[i-16] = -1;
                            continue;

                        }else{

                            routeTime[i-16] = Double.parseDouble(cell.getContents());
                        }

                    }

                    avgTravelTimes.put(route.getContents(), routeTime);
                }

                data.put(date.getContents(), avgTravelTimes);
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }

    }

    public void Write(){


    }

    public void Analyze(File dataSettings) throws IOException{

        PrintWriter writer = new PrintWriter("processed.dat", "UTF-8");

        /*
        0:00 = 0
        1:00 = 1
        2:00 = 2
        3:00 = 3
        4:00 = 4
        5:00 = 5
        6:00 = 6
        7:00 = 7
        8:00 = 8
        9:00 = 9
        10:00 = 10
        11:00 = 11
        12:00 = 12
         */

        Scanner scan = new Scanner(new File("settings.dat"));
        String startDate = scan.next();
        String endDate = scan.next();
        String startTime = scan.next();
        String endTime = scan.next();
        boolean north = scan.nextBoolean();
        boolean south = scan.nextBoolean();
        boolean east = scan.nextBoolean();
        boolean west = scan.nextBoolean();
        ArrayList<String> northRoutes = new ArrayList<String>();
        ArrayList<String> southRoutes = new ArrayList<String>();
        ArrayList<String> eastRoutes = new ArrayList<String>();
        ArrayList<String> westRoutes = new ArrayList<String>();
        ArrayList<ArrayList<String>> events = new ArrayList<ArrayList<String>>();

        for(int i=0; i<4; i++){

            Scanner arrayScan = new Scanner(scan.next());
            arrayScan.useDelimiter(";");
        }

        while(scan.hasNext()){


        }
    }



}
