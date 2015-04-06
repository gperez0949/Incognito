package src.softwareSpecs;

import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 3/11/15
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestExcelerator {

    public static void main(String[] args) {

        try {
            Excelerator excelerator = new Excelerator();
            excelerator.setInputFile("C:\\Users\\Gabriel\\Documents\\Incognito\\SoftwareSpecs\\Corridor_Report.xls");
            excelerator.Read();

            Set a = excelerator.data.keySet();
            Object[] keys = a.toArray();

            System.out.println(keys.length);

            for(int i = 0; i < excelerator.data.size(); i++){

                System.out.println(keys[i]);

                Set b = excelerator.data.get(keys[i]).keySet();
                Object[] routesKey = b.toArray();

                for(int j = 0;j < excelerator.data.get(keys[i]).size();j++){

                    System.out.println(routesKey[j]);

                    for(double number: excelerator.data.get(keys[i]).get(routesKey[j])) {

                        System.out.println(number);
                    }

                }



            }




        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
