package src.softwareSpecs;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import jxl.Cell;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Created by Austin Nafziger on 3/11/15.
 */
public class Excelerator {
    double[] distance;
    LinkedHashMap<String, double[]> avgTravelTimes; // <route name , average travel time>
    LinkedHashMap<String, HashMap<String, double[]>> data; // <date, <route name,
                                                     // travel time>> read
                                                     // from raw data.

	private String inputFile;

	public void setInputFile(String input) {

		inputFile = input;
	}



    public void Read() throws IOException{


        //initialize maps

        data = new LinkedHashMap<String, HashMap<String, double[]>>();


        //open workbook
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // get sheets

            for(int k = 0; k < w.getSheets().length - 1; k++){
                Sheet sheet = w.getSheet(k);

                avgTravelTimes = new LinkedHashMap<String, double[]>();

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

    public void Analyze(String dataSettings) throws IOException{

        File file = new File("processed.dat");
        PrintWriter writer = new PrintWriter(file);

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

        Scanner scan = new Scanner(new File(dataSettings));
        scan.useDelimiter("\n");
        String startDate = scan.next();
        String endDate = scan.next();
        int startTime = Integer.parseInt(scan.next());
        int endTime = Integer.parseInt(scan.next());
        String north = scan.next();
        String south = scan.next();
        String east = scan.next();
        String west = scan.next();
        ArrayList<String> northRoutes = new ArrayList<String>();
        ArrayList<String> southRoutes = new ArrayList<String>();
        ArrayList<String> eastRoutes = new ArrayList<String>();
        ArrayList<String> westRoutes = new ArrayList<String>();
        ArrayList<ArrayList<String>> events = new ArrayList<ArrayList<String>>();

        for(int i=0; i<4; i++){

            Scanner arrayScan = new Scanner(scan.next());
            arrayScan.useDelimiter(";");

            while(arrayScan.hasNext()){

                if(i==0){

                    northRoutes.add(arrayScan.next());
                }
                if(i==1){

                    southRoutes.add(arrayScan.next());
                }
                if(i==2){

                    eastRoutes.add(arrayScan.next());
                }
                if(i==3){

                    westRoutes.add(arrayScan.next());
                }
            }
        }

        while(scan.hasNext()){

            Scanner eventScan = new Scanner(scan.next());
            eventScan.useDelimiter(";");

            ArrayList<String> temp = new ArrayList<String>();

            while(eventScan.hasNext()){

                temp.add(eventScan.next());
            }

            events.add(temp);
        }

        //build set of dates
        Set a = data.keySet();
        Object[] keys = a.toArray();

        //build set of routes
        Set b = data.get(keys[3]).keySet();
        Object[] routesKey = b.toArray();

        for(int i = 0; i < routesKey.length; i ++){



            //check if route is selected and in the an enables direction. Otherwise ignore.
            if((northRoutes.contains(routesKey[i]) && north.equals("true"))||(southRoutes.contains(routesKey[i])&& south.equals("true"))
                    ||(eastRoutes.contains(routesKey[i])&&east.equals("true"))||(westRoutes.contains(routesKey[i])&& west.equals("true"))){

                System.out.println("route: " + routesKey[i]);


                //for all cells in the time interval
                for(int j = startTime; j < endTime;j++){

                    System.out.println("cells");
                    ArrayList<Double> nonEventTravelTimes = new ArrayList<Double>();

                    //for each date
                    for(int k = 0; k < keys.length; k++){

                        System.out.println(keys.length);

                        //check if date is an event date
                        boolean isEvent = false;
                        for(int n = 0 ; n < events.size(); n++) {
                            if (events.get(n).contains(keys[k])) {
                                //is event

                                writer.println(keys[k]);
                                isEvent = true;


                            }
                        }
                        if (!isEvent) {
                            //is not event

                            //add to arraylist of travel times
                            //nonEventTravelTimes.add(data.get(keys[k]).get(routesKey[i])[j]);

                            //writer.println(data.get(keys[k]).get(routesKey[i])[j]);
                            for(double dob: data.get(keys[k]).get(routesKey[i])){

                                //System.out.println(dob);
                            }
                        }



                    }//end for each date
                }//end for cells
            }//end if selected routes
        }// end for all routes


        writer.close();


        /*
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(north);
        System.out.println(south);
        System.out.println(east);
        System.out.println(west);
        for(String data: northRoutes){

            System.out.println(data);
        }
        for(String data: southRoutes){

            System.out.println(data);
        }
        for(String data: eastRoutes){

            System.out.println(data);
        }
        for(String data: westRoutes){

            System.out.println(data);
        }
        for(ArrayList<String> list: events){

            for(String data: list){

                System.out.println(data);
            }
        }*/

    }
}
