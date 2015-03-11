package src.softwareSpecs;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

/**
 * Created by austinnafziger on 3/11/15.
 */
public class Excelerator {



    public Excelerator(String inputFile) throws IOException {



        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet

            for(int k = 0; k < w.getSheets().length; k++){
                Sheet sheet = w.getSheet(k);
                // Loop over first 10 column and lines

                Cell date = sheet.getCell(1,7);
                System.out.println(date.getContents());

                for (int j = 3; j < sheet.getColumns(); j++) {
                    Cell route = sheet.getCell(j,13);
                    Cell distance = sheet.getCell(j,14);
                    System.out.println("Route = " + route.getContents() + "\t distance = " + distance.getContents());
                    for (int i = 16; i < sheet.getRows(); i++) {
                        Cell cell = sheet.getCell(j,i);
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
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

}
