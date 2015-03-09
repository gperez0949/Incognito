package src.softwareSpecs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {

	private int numOfPairs;
	private int numOfElements;
	private ArrayList<double[]> data;
	private ArrayList<String> names;
	private ArrayList<Integer> types;

	public DataReader() throws FileNotFoundException {

		// Scanner scan = new Scanner(new File(
		// "C:/Users/Gabriel/Incognito/SoftwareSpecs/processed.dat"));

		// Scanner scan = new Scanner(new
		// File("/Users/austinnafziger/SoftwareProject/SoftwareSpecs/processed.dat"));
		BufferedReader br = new BufferedReader(new FileReader("processed.dat"));
		try {
			String line = br.readLine();

			while (line != null) {
				// do thing to vars here
				numOfPairs = Integer.parseInt(line);
				// get the next line
				line = br.readLine();
				numOfElements = Integer.parseInt(line);

				data = new ArrayList<double[]>();
				names = new ArrayList<String>();
				types = new ArrayList<Integer>();

				for (int i = 0; i < numOfPairs; i++) {
					// get next line
					line = br.readLine();
					names.add(line);
					line = br.readLine();
					types.add(Integer.parseInt(line));

					for (int j = 0; j < numOfElements; j++) {
						double[] temp = new double[numOfElements];
						line = br.readLine();
						temp[j] = Double.parseDouble(line);
					}// end for
				}// end for

				line = br.readLine();
			}// end while
			br.close();// close file when we are done with it
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// end catch
	}// end DataReader

	public int getPairs() {

		return numOfPairs;
	}// end getPairs

	public int getElements() {

		return numOfElements;
	}// end getElements

	public ArrayList<double[]> getData() {

		return data;
	}// end getData

	public ArrayList<String> getNames() {

		return names;
	}// end getNames

	public ArrayList<Integer> getTypes() {

		return types;
	}// end getTypes
}// end dataReader class
