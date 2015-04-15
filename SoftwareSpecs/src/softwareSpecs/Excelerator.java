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
import jxl.write.Number;


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

        double startRTime = System.nanoTime();

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

            //display statues
            double endRTime = System.nanoTime();
            System.out.println("Excel read successful: " + (endRTime -startRTime) / 1000000000 + "s");



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

        double startATime = System.nanoTime();

        //analyze will build these HashMaps
        avgTravelTimes = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Double>>>();
        avgSpeed = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Double>>>();

        //File file = new File("processed.dat");
        //PrintWriter writer = new PrintWriter(file);

        //Anayize parameters taken from data settings
        Scanner scan = new Scanner(new File(dataSettings));
        scan.useDelimiter("\r\n");
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
                        //writer.println("route: " + routesKey[i]);


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

                            //speed = d/t * 60
                            avgSpeedData.add(60*distance.get(routesKey[i])/avg);

                            //write to process.dat to store data for future referance
                            String average = String.format("%.3f",takeAvg(nonEventTravelTimes));
                            //writer.println(average);

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

        //writer.close();

        double endATime = System.nanoTime();
        System.out.println("Data Analysis Successful: " + (endATime - startATime)/1000000000 + "s");

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

        double startWTime = System.nanoTime();

        //create writable workbook
        File excelFile = new File(filename + ".xls");
        WritableWorkbook  workbook = Workbook.createWorkbook(excelFile);

        //create writable sheet
        WritableSheet sheet = workbook.createSheet("Data Summary" , 0);

        //create formats
        NumberFormat threeDps = new NumberFormat("#.###"); //three decimal points
        WritableCellFormat numberFormat = new WritableCellFormat(threeDps);
        numberFormat.setAlignment(Alignment.RIGHT);

        //percent formats
        NumberFormat percentF = new NumberFormat("#%");
        WritableCellFormat percentFormat = new WritableCellFormat(percentF);

        //title format
        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setAlignment(Alignment.CENTRE); //center alignment format
        cellFormat.setBackground(Colour.YELLOW2);

        //main title format
        WritableFont mainTitleFont = new WritableFont(WritableFont.ARIAL, 18, WritableFont.BOLD, true);//set font
        WritableCellFormat titleFormat = new WritableCellFormat(mainTitleFont);
        titleFormat.setBackground(Colour.RED);
        titleFormat.setAlignment(Alignment.CENTRE);

        //format for black separator
        WritableCellFormat blackFormat = new WritableCellFormat();
        blackFormat.setBackground(Colour.PALETTE_BLACK);

        //speed format
        NumberFormat speed = new NumberFormat("#.#");
        WritableCellFormat speedFormat = new WritableCellFormat(speed);




        /**
         * create tables of average times
         */


        //Create array of keys
        Set a = avgTravelTimes.keySet();
        Object[] eventKeys = a.toArray();   //array of events

        Set c = avgTravelTimes.get(eventKeys[0]).keySet();
        Object[] routeKeys = c.toArray(); //array of routes

        int dataCount = avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size();

        //create sheet titles
        sheet.mergeCells(0,0,routeKeys.length,1);
        Label travelTimeLabel = new Label(0,0,"Average Route Travel Times",titleFormat);
        sheet.addCell(travelTimeLabel);

        //create title for travel time percent difference
        sheet.mergeCells(routeKeys.length + 3,0 ,7 + routeKeys.length + 9*(eventKeys.length-1) ,1 );
        Label travelTimePerDiffLabel = new Label(routeKeys.length + 3 , 0, "Travel Time Analysis", titleFormat);
        sheet.addCell(travelTimePerDiffLabel);

        //create title for avg speed percent differences
        sheet.mergeCells(routeKeys.length + 9*(eventKeys.length-1) + 10,0 ,
                routeKeys.length + 9*(eventKeys.length-1) + 14 + 9*(eventKeys.length-1) ,1 );
        Label avgSpeedPerDiffLabel = new Label(routeKeys.length + 9*(eventKeys.length-1) + 10 , 0, "Average Speed Analysis", titleFormat);
        sheet.addCell(avgSpeedPerDiffLabel);


        //for each eventType
        for(int i = 0; i< eventKeys.length;i++){


            //mergeCells for title
            sheet.mergeCells(0, 4 + 5*i + i*avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size(),
                    routeKeys.length , 4 + 5*i + i*avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size());

            //create title and add to cell

            Label titleLabel = new Label(0,4 + 5*i + i*avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size()
                    , eventKeys[i] + " Average Route Travel Time (minutes) ",cellFormat);
            sheet.addCell(titleLabel);

            //for each selected route
            for(int j = 0; j < routeKeys.length;j++){

                sheet.setColumnView(j+1,20);

                //create route title and add to cell
                Label routeLabel = new Label(j+1,5 + 5*i + i*avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size()
                        ,(String)routeKeys[j]);
                sheet.addCell(routeLabel);

                //for each cell
                for(int k = 0; k < avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size(); k++){

                    //add data to table

                    if(avgTravelTimes.get(eventKeys[i]).get(routeKeys[j]).get(k).equals(Double.NaN))  {

                        Label travelTime = new Label(j+1, 6 + k + 5*i + i*avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size(),
                                "",numberFormat);
                        sheet.addCell(travelTime);
                    }else{

                        Number travelTime = new Number(j+1, 6 + k + 5*i + i*avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size(),
                                avgTravelTimes.get(eventKeys[i]).get(routeKeys[j]).get(k), numberFormat);
                        sheet.addCell(travelTime);
                    }



                    //add time interval to sheet
                    Label time = new Label(0, 6 + k + 5*i + i*avgTravelTimes.get(eventKeys[0]).get(routeKeys[0]).size(),
                            (startTime+k) + ":00");
                    sheet.addCell(time);

                }//end for each cell

            }//end for all routes

        }//end for each event

        //end write travel times

        /*****************************************************
         *   start write Travel Time percent differences
         ****************************************************/


        int rowCount = 0;
        int routeCount = 0;


        //for each route
        while(routeCount < routeKeys.length){

            int i = 0;
            //for each column
            while(i < 3 && routeCount < routeKeys.length){


                //title table
                sheet.mergeCells(routeKeys.length + 3 +i*(2+3*(eventKeys.length-1)),                       //start column
                        4 + rowCount*(4 + 1 + dataCount),  //start row
                        routeKeys.length + 1 +(i+1)*(2+3*(eventKeys.length-1)),                            //end column
                        4 + rowCount*(4 + 1 + dataCount)); //end row

                Label routeTimeLabel = new Label(routeKeys.length + 3 +i*(2+3*(eventKeys.length-1)),       //selected column
                        4 + rowCount*(4 + 1 + dataCount),                                                  //selected row
                        "Travel Time Analysis: " + routeKeys[routeCount],                                  //label
                        cellFormat);                                                                       //format
                sheet.addCell(routeTimeLabel);

                //route distance
                sheet.mergeCells(routeKeys.length + 4 +i*(2+3*(eventKeys.length-1)),                        //start column
                        5 + rowCount*(4 + 1  + dataCount),                                                  //start row
                        routeKeys.length + 5 +(i)*(2+3*(eventKeys.length-1)),                               //end column
                        5 + rowCount*(4 + 1  + dataCount));                                                 //end row)

                Label routeDistanceLabel = new Label(routeKeys.length + 4 +i*(2+3*(eventKeys.length-1)),   //selected column
                        5 + rowCount*(4 + 1 + dataCount),                                                  //selected row
                        "Route Distance(mi): ");
                sheet.addCell(routeDistanceLabel);

                Number routeDistance = new Number(routeKeys.length + 6 +i*(2+3*(eventKeys.length-1)),      //selected column
                        5 + rowCount*(4 + 1 +dataCount),                                                   //selected row
                        distance.get(routeKeys[routeCount]));
                sheet.addCell(routeDistance);

                //for each event
                for(int k = 0; k < eventKeys.length -1;k++){

                    //event type labels
                    Label eventLabel = new Label(routeKeys.length + 4 +i*(2+3*(eventKeys.length-1)) + k*3,     //selected column
                            6 + rowCount*(4 + 1 + dataCount),                                                  //selected row
                            (String)eventKeys[k+1]);
                    Label nonEventLabel = new Label(routeKeys.length + 5 +i*(2+3*(eventKeys.length-1))+ k*3,   //selected column
                            6 + rowCount*(4 + 1 + dataCount),                                                  //selected row
                            "Non-Event ");
                    Label perDiffLabel = new Label(routeKeys.length + 6 +i*(2+3*(eventKeys.length-1))+ k*3,    //selected column
                            6 + rowCount*(4 + 1 + dataCount),                                                   //selected row
                            "%Diff");
                    Label travelt1Label = new Label(routeKeys.length + 4 +i*(2+3*(eventKeys.length-1))+ k*3,        //selected column
                            7 + rowCount*(4 + 1 + dataCount),                                                       //selected row
                            "Travel Time");
                    Label travelt2Label = new Label(routeKeys.length + 5 +i*(2+3*(eventKeys.length-1))+ k*3,        //selected column
                            7 + rowCount*(4 + 1 + dataCount),                                                       //selected row
                            "Travel Time");



                    sheet.addCell(eventLabel);
                    sheet.addCell(nonEventLabel);
                    sheet.addCell(perDiffLabel);
                    sheet.addCell(travelt1Label);
                    sheet.addCell(travelt2Label);

                    //enlarge column width
                    sheet.setColumnView(routeKeys.length + 4 +i*(2+3*(eventKeys.length-1))+ k*3,15);
                    sheet.setColumnView(routeKeys.length + 5 +i*(2+3*(eventKeys.length-1))+ k*3,15);

                    //for each time interval
                    for(int j = 0; j < dataCount; j++){

                        //check for missing data
                        //if both are NaN
                        if(avgTravelTimes.get(eventKeys[0]).get(routeKeys[routeCount]).get(j).equals(Double.NaN) &&
                                avgTravelTimes.get(eventKeys[k+1]).get(routeKeys[routeCount]).get(j).equals(Double.NaN)){

                            //do nothing

                            //if non event data equals NaN
                        }else if(avgTravelTimes.get(eventKeys[0]).get(routeKeys[routeCount]).get(j).equals(Double.NaN)){

                            //only write event data and do not calculate diff.
                            Number  eventTimeData = new Number(routeKeys.length + 4 +i*(2+3*(eventKeys.length-1))+ k*3,  //selected column
                                    8 + rowCount*(4 + 1 + dataCount) + j,                                                //selected row
                                    avgTravelTimes.get(eventKeys[k+1]).get(routeKeys[routeCount]).get(j),
                                    numberFormat);
                            sheet.addCell(eventTimeData);

                            //if event data equals NaN
                        }else if( avgTravelTimes.get(eventKeys[k+1]).get(routeKeys[routeCount]).get(j).equals(Double.NaN)){

                            //only write non event data and do not calculate difference
                            Number nonEventTimeData = new Number(routeKeys.length + 5 +i*(2+3*(eventKeys.length-1))+ k*3,//selected column
                                    8 + rowCount*(4 + 1 + dataCount) + j,                                                //selected row
                                    avgTravelTimes.get(eventKeys[0]).get(routeKeys[routeCount]).get(j),
                                    numberFormat);
                            sheet.addCell(nonEventTimeData);

                            //both data exists.
                        }else{

                            Number nonEventTimeData = new Number(routeKeys.length + 5 +i*(2+3*(eventKeys.length-1))+ k*3,//selected column
                                    8 + rowCount*(4 + 1 + dataCount) + j,                                                //selected row
                                    avgTravelTimes.get(eventKeys[0]).get(routeKeys[routeCount]).get(j),
                                    numberFormat);

                            Number  eventTimeData = new Number(routeKeys.length + 4 +i*(2+3*(eventKeys.length-1))+ k*3,  //selected column
                                    8 + rowCount*(4 + 1 + dataCount) + j,                                                //selected row
                                    avgTravelTimes.get(eventKeys[k+1]).get(routeKeys[routeCount]).get(j),
                                    numberFormat);

                            //get a percent difference of the data
                            double difference = (avgTravelTimes.get(eventKeys[k+1]).get(routeKeys[routeCount]).get(j) -
                                    avgTravelTimes.get(eventKeys[0]).get(routeKeys[routeCount]).get(j))/
                                    avgTravelTimes.get(eventKeys[0]).get(routeKeys[routeCount]).get(j);

                            Number percentDifference = new Number(routeKeys.length + 6 +i*(2+3*(eventKeys.length-1))+ k*3,  //selected column
                                    8 + rowCount*(4 + 1 + dataCount) + j,                                                   //selected row
                                    difference,
                                    percentFormat);



                            sheet.addCell(nonEventTimeData);
                            sheet.addCell(eventTimeData);
                            sheet.addCell(percentDifference);


                        }

                    }
                    if(k + 1 > 1){
                        //insert black separator
                        sheet.mergeCells(routeKeys.length + 7 +i*(2+3*(eventKeys.length-1)),        //start column
                                5 + rowCount*(4 + 1 + dataCount),                                   //start row
                                routeKeys.length + 9 +i*(2+3*(eventKeys.length-1)),                 //end column
                                5 + rowCount*(4 + 1 + dataCount));                                  //end row

                        Label blackR = new Label(routeKeys.length + 7 +i*(2+3*(eventKeys.length-1)), //selected column
                                5 + rowCount*(4 + 1 + dataCount),
                                "",blackFormat);

                        sheet.addCell(blackR);

                    }//end for each time interval



                } //end for each event

                //insert time labels
                for(int w = 0; w < dataCount;w++){

                    Label timeLabel = new Label(routeKeys.length + 3 +i*(2+3*(eventKeys.length-1)), //selected column
                            8 + rowCount*(4 + 1 + dataCount) + w,                                        //selected row
                            (startTime+w)+ ":00");

                    sheet.addCell(timeLabel);



                }//end insert time labels

                sheet.mergeCells(routeKeys.length + 3 +i*(2+3*(eventKeys.length-1)),        //start column
                        5 + rowCount*(4 + 1 + dataCount),                                   //start row
                        routeKeys.length + 3 +i*(2+3*(eventKeys.length-1)),                 //end column
                        7 + rowCount*(4 + 1 + dataCount));                                  //end row

                Label blackC = new Label(routeKeys.length + 3 +i*(2+3*(eventKeys.length-1)), //selected column
                        5 + rowCount*(4 + 1 + dataCount),
                        "",blackFormat);

                sheet.addCell(blackC);

                i++;
                routeCount++;

            }//end for each column


            rowCount++;

        }//end for each route

        /***********************************
         *        Start Avg Speed tables
         ***********************************/


        rowCount = 0;
        routeCount = 0;

        //for each route
        while(routeCount < routeKeys.length){

            int i = 0;
            //for each column
            while(i < 3 && routeCount < routeKeys.length){


                //title table
                sheet.mergeCells(routeKeys.length + 9*(eventKeys.length-1) + 10 +i*(2+3*(eventKeys.length-1)),                       //start column
                        4 + rowCount*(4 + 1 + dataCount),  //start row
                        routeKeys.length + 9*(eventKeys.length-1)+ 8 +(i+1)*(2+3*(eventKeys.length-1)),                            //end column
                        4 + rowCount*(4 + 1 + dataCount)); //end row

                Label routeTimeLabel = new Label(routeKeys.length + 9*(eventKeys.length-1) + 10 +i*(2+3*(eventKeys.length-1)),       //selected column
                        4 + rowCount*(4 + 1 + dataCount),                                                  //selected row
                        "Average Speed Analysis: " + routeKeys[routeCount],                                  //label
                        cellFormat);                                                                       //format
                sheet.addCell(routeTimeLabel);

                //route distance
                sheet.mergeCells(routeKeys.length + 9*(eventKeys.length-1)+ 11 +i*(2+3*(eventKeys.length-1)),                        //start column
                        5 + rowCount*(4 + 1  + dataCount),                                                  //start row
                        routeKeys.length + 9*(eventKeys.length-1)+12 +(i)*(2+3*(eventKeys.length-1)),                               //end column
                        5 + rowCount*(4 + 1  + dataCount));                                                 //end row)

                Label routeDistanceLabel = new Label(routeKeys.length + 9*(eventKeys.length-1) + 11 +i*(2+3*(eventKeys.length-1)),   //selected column
                        5 + rowCount*(4 + 1 + dataCount),                                                  //selected row
                        "Route Distance(mi): ");
                sheet.addCell(routeDistanceLabel);

                Number routeDistance = new Number(routeKeys.length + 9*(eventKeys.length-1) + 13 +i*(2+3*(eventKeys.length-1)),      //selected column
                        5 + rowCount*(4 + 1 +dataCount),                                                   //selected row
                        distance.get(routeKeys[routeCount]));
                sheet.addCell(routeDistance);

                //for each event
                for(int k = 0; k < eventKeys.length -1;k++){

                    //event type labels
                    Label eventLabel = new Label(routeKeys.length + 9*(eventKeys.length-1)+ 11 +i*(2+3*(eventKeys.length-1)) + k*3,     //selected column
                            6 + rowCount*(4 + 1 + dataCount),                                                  //selected row
                            (String)eventKeys[k+1]);
                    Label nonEventLabel = new Label(routeKeys.length + 9*(eventKeys.length-1) +12 +i*(2+3*(eventKeys.length-1))+ k*3,   //selected column
                            6 + rowCount*(4 + 1 + dataCount),                                                  //selected row
                            "Non-Event ");
                    Label perDiffLabel = new Label(routeKeys.length + 9*(eventKeys.length-1) + 13 +i*(2+3*(eventKeys.length-1))+ k*3,    //selected column
                            6 + rowCount*(4 + 1 + dataCount),                                                   //selected row
                            "%Diff");
                    Label travelt1Label = new Label(routeKeys.length + 9*(eventKeys.length-1) + 11 +i*(2+3*(eventKeys.length-1))+ k*3,        //selected column
                            7 + rowCount*(4 + 1 + dataCount),                                                       //selected row
                            "Travel Time");
                    Label travelt2Label = new Label(routeKeys.length + 9*(eventKeys.length-1) + 12 +i*(2+3*(eventKeys.length-1))+ k*3,        //selected column
                            7 + rowCount*(4 + 1 + dataCount),                                                       //selected row
                            "Travel Time");



                    sheet.addCell(eventLabel);
                    sheet.addCell(nonEventLabel);
                    sheet.addCell(perDiffLabel);
                    sheet.addCell(travelt1Label);
                    sheet.addCell(travelt2Label);

                    //enlarge column width
                    sheet.setColumnView(routeKeys.length + 9*(eventKeys.length-1) + 11 +i*(2+3*(eventKeys.length-1))+ k*3,15);
                    sheet.setColumnView(routeKeys.length + 9*(eventKeys.length-1) +12 +i*(2+3*(eventKeys.length-1))+ k*3,15);

                    //for each time interval
                    for(int j = 0; j < dataCount; j++){

                        //check for missing data
                        //if both are NaN
                        if(avgSpeed.get(eventKeys[0]).get(routeKeys[routeCount]).get(j).equals(Double.NaN) &&
                                avgSpeed.get(eventKeys[k+1]).get(routeKeys[routeCount]).get(j).equals(Double.NaN)){

                            //do nothing

                            //if non event data equals NaN
                        }else if(avgSpeed.get(eventKeys[0]).get(routeKeys[routeCount]).get(j).equals(Double.NaN)){

                            //only write event data and do not calculate diff.
                            Number  eventTimeData = new Number(routeKeys.length + 9*(eventKeys.length-1) + 11 +i*(2+3*(eventKeys.length-1))+ k*3,  //selected column
                                    8 + rowCount*(4 + 1 + dataCount) + j,                                                //selected row
                                    avgSpeed.get(eventKeys[k+1]).get(routeKeys[routeCount]).get(j),
                                    numberFormat);
                            sheet.addCell(eventTimeData);

                            //if event data equals NaN
                        }else if( avgSpeed.get(eventKeys[k+1]).get(routeKeys[routeCount]).get(j).equals(Double.NaN)){

                            //only write non event data and do not calculate difference
                            Number nonEventTimeData = new Number(routeKeys.length + 9*(eventKeys.length-1) + 12 +i*(2+3*(eventKeys.length-1))+ k*3,//selected column
                                    8 + rowCount*(4 + 1 + dataCount) + j,                                                //selected row
                                    avgSpeed.get(eventKeys[0]).get(routeKeys[routeCount]).get(j),
                                    numberFormat);
                            sheet.addCell(nonEventTimeData);

                            //both data exists.
                        }else{

                            Number nonEventTimeData = new Number(routeKeys.length + 9*(eventKeys.length-1) + 12 +i*(2+3*(eventKeys.length-1))+ k*3,//selected column
                                    8 + rowCount*(4 + 1 + dataCount) + j,                                                //selected row
                                    avgSpeed.get(eventKeys[0]).get(routeKeys[routeCount]).get(j),
                                    speedFormat);

                            Number  eventTimeData = new Number(routeKeys.length + 9*(eventKeys.length-1) + 11 +i*(2+3*(eventKeys.length-1))+ k*3,  //selected column
                                    8 + rowCount*(4 + 1 + dataCount) + j,                                                //selected row
                                    avgSpeed.get(eventKeys[k+1]).get(routeKeys[routeCount]).get(j),
                                    speedFormat);

                            //get a percent difference of the data
                            double difference = (avgSpeed.get(eventKeys[k+1]).get(routeKeys[routeCount]).get(j) -
                                    avgSpeed.get(eventKeys[0]).get(routeKeys[routeCount]).get(j))/
                                    avgSpeed.get(eventKeys[0]).get(routeKeys[routeCount]).get(j);

                            Number percentDifference = new Number(routeKeys.length + 9*(eventKeys.length-1) + 13 +i*(2+3*(eventKeys.length-1))+ k*3,  //selected column
                                    8 + rowCount*(4 + 1 + dataCount) + j,                                                   //selected row
                                    difference,
                                    percentFormat);



                            sheet.addCell(nonEventTimeData);
                            sheet.addCell(eventTimeData);
                            sheet.addCell(percentDifference);


                        }

                    }
                    if(k + 1 > 1){
                        //insert black separator
                        sheet.mergeCells(routeKeys.length + 9*(eventKeys.length-1) + 14 +i*(2+3*(eventKeys.length-1)),        //start column
                                5 + rowCount*(4 + 1 + dataCount),                                   //start row
                                routeKeys.length + 9*(eventKeys.length-1) + 16 +i*(2+3*(eventKeys.length-1)),                 //end column
                                5 + rowCount*(4 + 1 + dataCount));                                  //end row

                        Label blackR = new Label(routeKeys.length + 9*(eventKeys.length-1) + 14 +i*(2+3*(eventKeys.length-1)), //selected column
                                5 + rowCount*(4 + 1 + dataCount),
                                "",blackFormat);

                        sheet.addCell(blackR);

                    }//end for each time interval



                } //end for each event

                //insert time labels
                for(int w = 0; w < dataCount;w++){

                    Label timeLabel = new Label(routeKeys.length + 9*(eventKeys.length-1) + 10 +i*(2+3*(eventKeys.length-1)), //selected column
                            8 + rowCount*(4 + 1 + dataCount) + w,                                        //selected row
                            (startTime+w)+ ":00");

                    sheet.addCell(timeLabel);



                }//end insert time labels

                sheet.mergeCells(routeKeys.length + 9*(eventKeys.length-1) + 10 +i*(2+3*(eventKeys.length-1)),        //start column
                        5 + rowCount*(4 + 1 + dataCount),                                   //start row
                        routeKeys.length + 9*(eventKeys.length-1) + 10 +i*(2+3*(eventKeys.length-1)),                 //end column
                        7 + rowCount*(4 + 1 + dataCount));                                  //end row

                Label blackC = new Label(routeKeys.length + 9*(eventKeys.length-1) + 10 +i*(2+3*(eventKeys.length-1)), //selected column
                        5 + rowCount*(4 + 1 + dataCount),
                        "",blackFormat);

                sheet.addCell(blackC);

                i++;
                routeCount++;

            }//end for each column


            rowCount++;

        }//end for each route


        //write file and then close
        workbook.write();
        workbook.close();

        double endWTime = System.nanoTime();
        System.out.println("Excel write successful: " + (endWTime - startWTime)/1000000000 + "s");

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
