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

            Sheet sheet = w.getSheet(0);
            // Loop over first 10 column and lines

            Cell date = sheet.getCell(1,7);
            System.out.println(date.getContents());

            for (int j = 0; j < sheet.getColumns(); j++) {
                for (int i = 17; i < sheet.getRows(); i++) {
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
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

}
