package testingApplication;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import shapeDomain.*;

/**
 * This program implements and performs experimental analysis on six sorting algorithms:
 * bubble, insertion, selection, merge, quick and heap.
 * We are using three-dimensional geometrical shapes by their height, base area and volume
 * with a collection of Comparables.
 *  
 * @author Jihoon Oh, Jonghan Park, Bushra Osman, Eunji Lee
 * @version 05/10/2021
 */
public class AppDriver {
	private static String fileName;
	private static String compareType;
	private static String sortType;
	private static long start;
	private static long stop;
	
	public static void main(String[] args) {
		// Parse the arguments from a command line
		new AppDriver().parseArgs(args);
		
		// Create an array of the shapes from the file
		Shape[] shapeArray = loadShapeArray();	
		
		// Verify the sort type
		String sortingAlgorithm = verifySortType();
		
		// Verify the compare type and sort the array
		try {
			Class<?> sortClass = Class.forName("utilities." + sortingAlgorithm);	
			Object sortObject = sortClass.newInstance();		
			Method sortMethod = null;
			int argslength = 0;
			
			if(compareType.equals("h")) {
				argslength = 1;				
				sortMethod = sortClass.getDeclaredMethod("descendingSort", Comparable[].class);				
			} else if(compareType.equals("v") || compareType.equals("a")) {
				argslength = 2;				
				sortMethod = sortClass.getDeclaredMethod("descendingSort", Object[].class, Comparator.class);
			} else {
				System.out.println("The compare typs must be h v a or H V A");
				System.exit(1);
			}
			
			Object[] arguments = new Object[argslength];
			arguments[0] = shapeArray;
			
			if(argslength == 2 && compareType.equals("v")) {
				VolumeCompare volumeComparator = new VolumeCompare();
				arguments[1] = volumeComparator;
			} else if(argslength == 2 && compareType.equals("a")) {
				BaseAreaCompare baseAreaComparator = new BaseAreaCompare();
				arguments[1] = baseAreaComparator;
			}
			
			start = System.nanoTime();
			sortMethod.invoke(sortObject, arguments);
			stop = System.nanoTime();
		} catch(Exception e) {
			System.exit(1);
		}
		
		// Display the results
		displayResults(shapeArray, sortingAlgorithm);
	}

	// Parse the arguments from a command line
	private void parseArgs(String[] args) {
		if(args.length != 3) {
			System.out.println("File name, the compare type and the sort type "
					+ "must be provided as parameters (-f -t -s) (order and case insensitive)");
			System.exit(1);
		}
		
		for(int i = 0; i < args.length; i++) {
			String paramCheck = args[i].substring(0, 2).toLowerCase();
			String content = args[i].substring(2).toLowerCase();
					
			if(paramCheck.equals("–f")) {
				fileName = "res/" + content;
			} else if(paramCheck.equals("–t")) {
				compareType = content;
			} else if(paramCheck.equals("–s")) {
				sortType = content;
			} else {
				System.out.println("The parameters' prefixes must be -f -t -s (order and case insensitive)");
				System.exit(1);
			}
		}
		
		if(fileName == null || compareType == null || sortType == null) {
			System.out.println("Please use all three prefixes that are -f -t -s (order and case insensitive)");
			System.exit(1);
		}
	}

	// Create an array of the shapes from the file
	private static Shape[] loadShapeArray() {
		File polyFile = new File(fileName);		
		Scanner loadFile = null;
			
		try {
			loadFile = new Scanner(polyFile);
		} catch(FileNotFoundException e1) {
			System.out.println("The file not found!");
			System.exit(1);
		}
				
		String line = loadFile.nextLine();
		String[] values = line.split("\\s+");
			
		int numberOfShapes = Integer.parseInt(values[0]);
		Shape[] shapeArray = new Shape[numberOfShapes];
		
		for(int i = 1, arrayIndex = 0; i < values.length; i += 3, arrayIndex++) {
			String shapeType = values[i];
			double firstValue = Double.parseDouble(values[i + 1]);
			double secondValue = Double.parseDouble(values[i + 2]);
				
			try {
				Class<?> shapeClass = Class.forName("shapeDomain." + shapeType);
				Constructor<?> constructor = shapeClass.getConstructor(Double.TYPE, Double.TYPE);
				Shape shape = (Shape) constructor.newInstance(firstValue, secondValue);
				shapeArray[arrayIndex] = shape;				
			} catch(Exception e) {
				System.out.println("The shape " + shapeType + " not found!");
				System.exit(1);
			}
		}
				
		loadFile.close();
			
		return shapeArray;
	}
		
	// Verify the sort type
	private static String verifySortType() {
		String sortingAlgorithm = null;
		
		if(sortType.equals("b")) {
			sortingAlgorithm = "BubbleSort";
		} else if(sortType.equals("s")) {
			sortingAlgorithm = "SelectionSort";
		} else if(sortType.equals("i")) {
			sortingAlgorithm = "InsertionSort";
		} else if(sortType.equals("m")) {
			sortingAlgorithm = "MergeSort";
		} else if(sortType.equals("q")) {
			sortingAlgorithm = "QuickSort";
		} else if(sortType.equals("z")) {
			sortingAlgorithm = "HeapSort";
		} else {
			System.out.println("The sort typs must be b s i m q z or B S I M Q Z");
			System.exit(1);
		}
		
		return sortingAlgorithm;
	}
	
	// Display the results
	private static void displayResults(Shape[] shapeArray, String sortingAlgorithm) {
		System.out.println("The Time for " + sortingAlgorithm + ": " + (stop - start) + "(ns)");
		System.out.print("The first value: " + shapeArray[0]);		
		displayType(shapeArray, 0);		
		
		for(int i = 999; i < shapeArray.length - 1; i += 1000) {
			System.out.print("The " + (i + 1) + "th value: " + shapeArray[i]);			
			displayType(shapeArray, i);
		}
		
		System.out.print("The last value: " + shapeArray[shapeArray.length - 1]);		
		displayType(shapeArray, shapeArray.length - 1);
	}

	// Display the shape by the compare type
	private static void displayType(Shape[] shapeArray, int index) {
		if(compareType.equals("h")) {
			System.out.println(" - Height) " + shapeArray[index].getHeight());
		} else if(compareType.equals("v")) {
			System.out.println(" - Volume) " + shapeArray[index].calcVolume());
		} else if(compareType.equals("a")) {
			System.out.println(" - BaseArea) " + shapeArray[index].calcBaseArea());
		}
	}
}