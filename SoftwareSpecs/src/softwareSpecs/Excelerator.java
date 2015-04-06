package src.softwareSpecs;

import java.io.*;
import java.lang.reflect.Array;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.*;

import jxl.Cell;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.*;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.*;


/**
 * Created by Austin Nafziger on 3/11/15.
 */
public class Excelerator {
    LinkedHashMap<String, Double> distance;

    //used to read into program
    LinkedHashMap<String, HashMap<String, double[]>> data; // <date, <route name,
                                                     // travel time>> read
                                                     // from raw data.

    //built from analyzing data
    LinkedHashMap<String, LinkedHashMap<String, ArrayList<Double>>> avgTravelTimes;  //<EventName, <routeName, Travel Times>>

    //built from analyzing data
    LinkedHashMap<String, LinkedHashMap<String, ArrayList<Double>>> avgSpeed;  //<EventName, <routeName, speeds>>

	private String inputFile;
    int startTime;
    int endTime;


	public void setInputFile(String input) {

		inputFile = input;
	}


    /**
     * Reads from a corridor_report.xls file.
     * builds raw data into inverted index
     *
     * occurs when user clicks submit in the data settings panel
     *
     * @throws IOException
     */
    public void Read() throws IOException{


        //initialize maps

        distance = new LinkedHashMap<String, Double>();
        data = new LinkedHashMap<String, HashMap<String, double[]>>();


        //open workbook
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // get sheets

            for(int k = 0; k < w.getSheets().length - 1; k++){
                Sheet sheet = w.getSheet(k);

                LinkedHashMap<String, double[]> avgTravelTimes = new LinkedHashMap<String, double[]>();

                //get date
                Cell date = sheet.getCell(1,7);
                //System.out.println(date.getContents());

                for (int j = 3; j < sheet.getColumns(); j++) {

                    double[] routeTime = new double[sheet.getRows() - 16 - 8];


                    //get route and distance
                    Cell route = sheet.getCell(j,13);


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

                //build raw data table
                data.put(date.getContents(), avgTravelTimes);
            }

            //build table of distances
            Sheet sheet = w.getSheet(0);

            //for each route
            for (int j = 3; j < sheet.getColumns(); j++) {

                Cell routeName = sheet.getCell(j,13);
                Cell distanceLocal = sheet.getCell(j,14);

                //add to distance table
                distance.put(routeName.getContents(), Double.parseDouble(distanceLocal.getContents()));


            }//end for each route

            w.close();



        } catch (BiffException e) {
            e.printStackTrace();
        }

    }  //end read

    /**
     *  Analysis of data
     *  Will create averages of route travel times and speeds and store on inverted indecies.
     *
     *  Anaysis according to DataSettings File
     *
     * occurs when user hits submit in data settings panel
     * after software reads from .xls file
     *
     * @param dataSettings
     * @throws IOException
     */
    public void Analyze(String dataSettings) throws IOException{

        //analyze will build these HashMaps
        avgTravelTimes = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Double>>>();
        avgSpeed = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Double>>>();

        File file = new File("processed.dat");
        PrintWriter writer = new PrintWriter(file);

        //Anayize parameters taken from data settings
        Scanner scan = new Scanner(new File(dataSettings));
        scan.useDelimiter("\n");
        String startDate = scan.next();
        String endDate = scan.next();
        startTime = Integer.parseInt(scan.next());
        endTime = Integer.parseInt(scan.next());
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
        }   //end setting up parameters

        /********************************
         *       begin analysis
         ********************************/

        //build set of dates
        Set a = data.keySet();
        Object[] keys = a.toArray();

        //build set of routes
        Set b = data.get(keys[3]).keySet();
        Object[] routesKey = b.toArray();

        //for each event type
        for(int w = 0; w < events.size()+1; w++){

            //temporary hashmaps to build instance variables later
            LinkedHashMap<String,ArrayList<Double>> avgRoutes = new LinkedHashMap<String, ArrayList<Double>>();
            LinkedHashMap<String, ArrayList<Double>> avgRouteSpeed = new LinkedHashMap<String, ArrayList<Double>>();

            //for each route
            for(int i = 0; i < routesKey.length; i++){

                //temp ArrayList for Hashmaps
                ArrayList<Double> avgRouteData = new ArrayList<Double>();
                ArrayList<Double> avgSpeedData = new ArrayList<Double>();

                try{

                //check if route is selected and in the an enables direction. Otherwise ignore.
                if((northRoutes.contains(routesKey[i]) && north.equals("true"))||(southRoutes.contains(routesKey[i])&& south.equals("true"))
                        ||(eastRoutes.contains(routesKey[i])&&east.equals("true"))||(westRoutes.contains(routesKey[i])&& west.equals("true"))){

                    //System.out.println("route: " + routesKey[i]);
                    writer.println("route: " + routesKey[i]);


                    //for all cells in the time interval
                    for(int j = startTime-1; j < endTime;j++){

                        //System.out.println("cells");
                        ArrayList<Double> nonEventTravelTimes = new ArrayList<Double>();

                        //for each date
                        for(int k = 0; k < keys.length-1; k++){

                            //check if date is an event date
                            boolean isEvent = false;
                            for(int n = 0 ; n < events.size(); n++) {
                                if (events.get(n).contains(keys[k])){

                                    if(w > 0 && events.get(w-1).contains(keys[k])){

                                        nonEventTravelTimes.add(data.get(keys[k]).get(routesKey[i])[j]);

                                    }

                                    isEvent = true;

                                }
                            }
                            if (!isEvent && w == 0){
                                //is not event

                                //add to arraylist of travel times
                                nonEventTravelTimes.add(data.get(keys[k]).get(routesKey[i])[j]);

                                //writer.println("NonEventDay" +"\t"+ keys[k]+"\t"+data.get(keys[k]).get(routesKey[i])[j]);
                                //for(double dob: data.get(keys[k]).get(routesKey[i])){



                                  //  System.out.println(dob);
                                //}
                            }



                        }//end for each date

                        //add data to arraylist
                        double avg = takeAvg(nonEventTravelTimes);
                        avgRouteData.add(avg);

                        //speed = d/t
                        avgSpeedData.add(distance.get(routesKey[i])/avg);

                        //write to process.dat to store data for future referance
                        String average = String.format("%.3f",takeAvg(nonEventTravelTimes));
                        writer.println(average);

                    }//end for cells

                    avgRoutes.put((String)routesKey[i],avgRouteData);
                    avgRouteSpeed.put((String)routesKey[i], avgSpeedData);

                }//end if selected routes
            }catch(NullPointerException n){

            }


            }// end for all routes
            if(w==0){
                //build hashmap if non event day
                avgTravelTimes.put("Non-Event", avgRoutes);
                avgSpeed.put("Non-Event",avgRouteSpeed);
            }else{
                //build haspmap if event day
                avgTravelTimes.put(events.get(w-1).get(0), avgRoutes);
                avgSpeed.put(events.get(w-1).get(0),avgRouteSpeed);
            }



        }//end for each event

        writer.close();

    }


    /**
     * Writes to filename.xls. Transferring all process data according to user settings
     * into a new excel file.
     *
     * Occurs when user wishes to export file
     *
     * @param filename
     */
    public void Write(String filename) throws IOException, WriteException {

        //todo finish this method

        //create writable workbook
        File excelFile = new File(filename + ".xls");
        WritableWorkbook  workbook = Workbook.createWorkbook(excelFile);

        //create writable sheet
        WritableSheet sheet = workbook.createSheet("Data Summary" , 0);

        //create formats
        NumberFormat threeDps = new NumberFormat("#.###"); //three decimal points

        //title format
        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setAlignment(Alignment.CENTRE); //center alignment format
        cellFormat.setBackground(Colour.YELLOW2);

        //main title format
        WritableFont mainTitleFont = new WritableFont(WritableFont.ARIAL, 18, WritableFont.BOLD, true);//set font
        WritableCellFormat titleFormat = new WritableCellFormat(mainTitleFont);
        titleFormat.setBackground(Colour.RED);
        titleFormat.setAlignment(Alignment.CENTRE);



        /**
         * create tables of average times
         */


        //Create array of keys
        Set a = avgTravelTimes.keySet();
        Object[] eventKeys = a.toArray();   //array of events

        Set c = avgTravelTimes.get(eventKeys[0]).keySet();
        Object[] routeKeys = c.toArray(); //array of routes

        //create sheet titles
        sheet.mergeCells(0,0,routeKeys.length,1);
        Label travelTimeLabel = new Label(0,0,"Average Route Travel Times",titleFormat);
        sheet.addCell(travelTimeLabel);


        //for each eventType
        for(int i = 0; i< eventKeys.length;i++){




            //mergeCells for title
            sheet.mergeCells(0, 4 + i * avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size(),
                    routeKeys.length , 4 + i * avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size());

            //create title and add to cell

            Label titleLabel = new Label(0,4 + i * avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size()
                    , eventKeys[i] + " Average Route Travel Time (minutes) ",cellFormat);
            sheet.addCell(titleLabel);

            //for each selected route
            for(int j = 0; j < routeKeys.length;j++){

                sheet.setColumnView(j+1,20);

                //create route title and add to cell
                Label routeLabel = new Label(j+1,5 + i*avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size()
                        ,(String)routeKeys[j]);
                sheet.addCell(routeLabel);

                //for each cell
                for(int k = 0; k < avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size(); k++){

                    //




                }//end for each cell

            }//end for all routes

        }//end for each event

        //write file and then close
        workbook.write();
        workbook.close();

    } //end write


    /**
     * takes average of an array list of doubles.
     *
     * @param numbers
     * @return  average
     */
    public double takeAvg(ArrayList<Double> numbers){


        int countNeg1 = 0;//counts the number of negative 1s
        double sum = 0;

        for(int i = 0; i < numbers.size();i++){

            //if the number is negative 1 do nothing
            if(numbers.get(i) == -1.0){

                countNeg1 += 1;
                //do nothing

            }else{

            sum += numbers.get(i);
            }


        }

        double average = sum/(numbers.size()-countNeg1);

        return average;

    }//end takeAvg


}
