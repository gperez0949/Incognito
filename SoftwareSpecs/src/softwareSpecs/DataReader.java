package src.softwareSpecs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
	
	private int numOfPairs;
	private int numOfElements;
	private ArrayList<double[]> data;

	public DataReader() throws FileNotFoundException{
		
		Scanner scan = new Scanner(new File("processed.dat"));
		numOfPairs = Integer.parseInt(scan.nextLine());
		numOfElements = Integer.parseInt(scan.nextLine());
		
		data = new ArrayList<double[]>();
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
}
