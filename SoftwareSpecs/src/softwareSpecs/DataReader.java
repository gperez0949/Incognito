package src.softwareSpecs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
	
	private int numOfPairs;
	private int numOfElements;
	private ArrayList<double[]> data;
	private ArrayList<String> names;
	private ArrayList<Integer> types;

	public DataReader() throws FileNotFoundException{
		
		Scanner scan = new Scanner(new File("D:/workspace/softSpecs/SoftwareSpecs/processed.dat"));
		numOfPairs = Integer.parseInt(scan.nextLine());
		numOfElements = Integer.parseInt(scan.nextLine());
		
		data = new ArrayList<double[]>();
        names = new ArrayList<String>();
        types = new ArrayList<Integer>();
		
		for(int i=0; i<numOfPairs; i++){
			
			names.add(scan.nextLine());
			types.add(Integer.parseInt(scan.nextLine()));
			
			for(int j=0; j<numOfElements; j++){
				
				double[] temp = new double[numOfElements];
				temp[j]=Double.parseDouble(scan.nextLine());
			}
		}
	}
	
	public int getPairs(){
		
		return numOfPairs;
	}
	
	public int getElements(){
		
		return numOfElements;
	}
	
	public ArrayList<double[]> getData(){
		
		return data;
	}
	
	public ArrayList<String> getNames(){
		
		return names;
	}
	
	public ArrayList<Integer> getTypes(){
		
		return types;
	}
}
