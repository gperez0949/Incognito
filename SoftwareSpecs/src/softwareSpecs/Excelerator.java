package src.softwareSpecs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import jxl.Cell;
import jxl.CellType;
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

	public Excelerator() throws IOException {

		File inputWorkbook = new File(inputFile);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines

			for (int j = 0; j < sheet.getColumns(); j++) {
				for (int i = 0; i < sheet.getRows(); i++) {
					Cell cell = sheet.getCell(j, i);
					CellType type = cell.getType();
					if (type == CellType.LABEL) {
						System.out.println("I got a label "
								+ cell.getContents());
					}

					if (type == CellType.NUMBER) {
						System.out.println("I got a number "
								+ cell.getContents());
					}

				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}



    public Excelerator(String inputFile) throws IOException {

        //initialize maps
        distance = new double[20];//todo make distance array the same length as the routes.
        data = new HashMap<String, HashMap<String, double[]>>();
        avgTravelTimes = new HashMap<String, double[]>();

        //open workbook
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // get sheets

            for(int k = 0; k < w.getSheets().length; k++){
                Sheet sheet = w.getSheet(k);

                //get date
                Cell date = sheet.getCell(1,7);
                System.out.println(date.getContents());

                for (int j = 3; j < sheet.getColumns(); j++) {

                    double[] routeTime = new double[sheet.getRows() - 16];

                    //get route and distance
                    Cell route = sheet.getCell(j,13);
                    Cell distance = sheet.getCell(j,14);
                    System.out.println("Route = " + route.getContents() + "\t distance = " + distance.getContents());

                    for (int i = 16; i < sheet.getRows(); i++) {

                        Cell cell = sheet.getCell(j,i);

                        //add cell content to array of route times
                        routeTime[i-16] = Double.parseDouble(cell.getContents());





                        //I dont think we need this block of code anymore
                        //CellType type = cell.getType();


                        /*
                        if (type == CellType.LABEL) {
                            System.out.println("I got a label "
                                    + cell.getContents());
                        }

                        if (type == CellType.NUMBER) {
                            System.out.println("I got a number "
                                    + cell.getContents());
                        } */






                    }
                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}
