package ae1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Tests to be carried on the algorithms (QUESTION 2 & 3)
 * @author David Al Baiaty Suarez
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public class Tests {
	
	/**
	 * Helper method that reads data from a file and adds the data (numbers) to a list of Integers
	 * @param fileName (name of file of type String)
	 * @return List<Integer>
	 */
	protected static final List<Integer> readFile(String fileName) {
		
		List<Integer> numbers = new ArrayList<>();
		try {
			File file = new File(fileName);
			@SuppressWarnings("resource")
			Scanner read = new Scanner(file);
			while(read.hasNextLine()) {
				String data = read.nextLine();
				numbers.add(Integer.parseInt(data));
			}
		}catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		return numbers;
	}
	
	/**
	 * Test (average running time in ms) for the InsertionSortAlgorithm (from InsertionSort.java)
	 * @param array
	 * @param n (number of times to carry test)
	 */
	private static final void insertionSortTest(int[] array,int n) {
		long start,end;
		List<Long> timeTakenArray = new ArrayList<>();
		int[] testArray = new int[array.length];
		for(int i=0;i<n;i++) {
			System.arraycopy(array,0,testArray,0,array.length-1);
			start = System.currentTimeMillis();
			try{
				InsertionSort.insertionSort(testArray);
			}
			finally {
				end = System.currentTimeMillis();
				timeTakenArray.add(end-start);
			}
		}
		long averageTime = (long) timeTakenArray.stream().mapToLong(val -> val).average().orElse(0);
		System.out.println("Insertion sort: " + (averageTime));
	}
	
	/**
	 * Test (average running time in ms) for the MergeSortAlgorithm (from MergeSort.java)
	 * @param array
	 * @param n (number of times to carry test)
	 */
	private static final void mergeSortTest(int[] array, int n) {
		long start,end;
		List<Long> timeTakenArray = new ArrayList<>();
		int[] testArray = new int[array.length];
		for(int i=0;i<n;i++) {
			System.arraycopy(array,0,testArray,0,array.length-1);
			start = System.currentTimeMillis();
			try{
				MergeSort.mergeSort(testArray,0,testArray.length-1);
			}
			finally {
				end = System.currentTimeMillis();
				timeTakenArray.add(end-start);
			}
		}
		long averageTime = (long) timeTakenArray.stream().mapToLong(val -> val).average().orElse(0);
		System.out.println("Merge sort: " + (averageTime));
	}
	
	/**
	 * Test (average running time in ms) for the QuickSortAlgorithm
	 * @param array
	 * @param n (number of times to carry test)
	 */
	private static final void quicksortTest(int[] array, int n) {
		long start,end;
		List<Long> timeTakenArray = new ArrayList<>();
		int[] testArray = new int[array.length];
		for(int i=0;i<n;i++) {
			System.arraycopy(array,0,testArray,0,array.length-1);
			start = System.currentTimeMillis();
			try {
				BasicQuicksort.quicksort(testArray,0,testArray.length-1);
			}finally {
				end = System.currentTimeMillis();
				timeTakenArray.add(end-start);
			}
		}
		long averageTime = (long) timeTakenArray.stream().mapToLong(val -> val).average().orElse(0);
		System.out.println("Quicksort: " + (averageTime));
	}
	
	/**
	 * Test (average running time in ms) for the QuickSort with Insertion Sort Algorithm
	 * @param array
	 * @param n (number of times to carry test)
	 */
	private static final void quicksortWithInsertionSortTest(int[] array, int n) {
		long start,end;
		List<Long> timeTakenArray = new ArrayList<>();
		int[] testArray = new int[array.length];
		for(int i=0;i<n;i++) {
			System.arraycopy(array,0,testArray,0,array.length-1);
			start = System.currentTimeMillis();
			try {
				QuicksortWithIS.quicksortWithInsertionSort(testArray,0,testArray.length-1);
			}finally {
				end = System.currentTimeMillis();
				timeTakenArray.add(end-start);
			}
		}
		long averageTime = (long) timeTakenArray.stream().mapToLong(val -> val).average().orElse(0);
		System.out.println("Quicksort (with insertion sort): " + (averageTime));
	}
	
	/**
	 * Test (average running time in ms) for the QuickSort with Median-of-Three Algorithm
	 * @param array
	 * @param n (number of times to carry test)
	 */
	private static final void quicksortWithMedianOfThreeTest(int[] array, int n) {
		long start,end;
		List<Long> timeTakenArray = new ArrayList<>();
		int[] testArray = new int[array.length];
		for(int i=0;i<n;i++) {
			System.arraycopy(array,0,testArray,0,array.length-1);
			start = System.currentTimeMillis();
			try {
				QuicksortWithMo3.quicksortWithMedianOfThree(testArray,0,testArray.length-1);
			}finally {
				end = System.currentTimeMillis();
				timeTakenArray.add(end-start);
				
			}
		}
		long averageTime = (long) timeTakenArray.stream().mapToLong(val -> val).average().orElse(0);
		System.out.println("Quicksort (with median of three): " + (averageTime));
	}
	
	/**
	 * Test (average running time in ms) for the QuickSort with 3-Way Partition
	 * @param array
	 * @param n (number of times to carry test)
	 */
	private static final void quicksortWith3WayPartition(int[] array, int n) {
		long start,end;
		List<Long> timeTakenArray = new ArrayList<>();
		int[] testArray = new int[array.length];
		for(int i=0;i<n;i++) {
			System.arraycopy(array,0,testArray,0,array.length-1);
			start = System.currentTimeMillis();
			try {
				Quicksort3WP.quicksortWith3wayPartition(testArray,0, testArray.length-1);
			}finally {
				end = System.currentTimeMillis();
				timeTakenArray.add(end-start);
			}
		}
		long averageTime = (long) timeTakenArray.stream().mapToLong(val -> val).average().orElse(0);
		System.out.println("3-way partion Quicksort: " + (averageTime));
	}
	
	/**
	 * Method to test the correctness of the algorithms implemented
	 * Complexity: O(n)
	 * @param array
	 * @return 1 (if array sorted) or 0 (array not properly sorted)
	 */
	public static int testSortingAlgorithm(int[] array) {
		int temp = -1;
		for(int number : array) {
			if(number < temp) {
				return 0;
			}
			temp = number;
		}
		
		return 1;
	}
	
	
	/**
	 * Iterates through an array of filenames (of type String) and performs tests on the data for different sorting algorithms
	 * 
	 * @param arrayOfFiles (name of File of type String)
	 */
	public static final void timeSortingAlgorithm(String[] arrayOfFiles) {
		// reads each file and outputs time taken to run
		for (String fileName : arrayOfFiles) {
			// list of numbers extracted from file
			List<Integer> numbers = readFile(fileName);
			
			// change from type List to type array
			int[] array = new int[numbers.size()];
			for(int i = 0; i < numbers.size(); i++) array[i] = numbers.get(i);
			
			// Constant value for the number of iterations per test carried out
			final int NUMBER_OF_ITERATIONS = 10;
			System.out.println("Time (ms) taken to sort (average of " + NUMBER_OF_ITERATIONS + " runs) " + fileName + ":");
			
			// Test for each sorting algorithm
			insertionSortTest(array,NUMBER_OF_ITERATIONS);
			mergeSortTest(array,NUMBER_OF_ITERATIONS);
			quicksortTest(array,NUMBER_OF_ITERATIONS);
			quicksortWithInsertionSortTest(array,NUMBER_OF_ITERATIONS);
			quicksortWithMedianOfThreeTest(array,NUMBER_OF_ITERATIONS);
			quicksortWith3WayPartition(array,NUMBER_OF_ITERATIONS);
			System.out.println("-------------------------------------------");
		}
	}
	
	/**
	 * Testing of the effect of the running time for the killer array on the Quicksort with Median of Three partition
	 * For each test, we increment the value in the array by 200 and we do this 50 times. 
	 * We will then plot the results using plotter.py (python file included in the final .zip file)
	 */
	public static final List<Long> killerArrayAlgorithm() {
		List<Long> timeTakenArray = new ArrayList<>();
		int n = 200;
		
		for(int i=0; i<100;i++) {
			int[] tempArray = Algorithms.killerSequenceQuicksort(n);
			long start = System.currentTimeMillis();
			QuicksortWithMo3.quicksortWithMedianOfThree(tempArray, 0, tempArray.length-1);
			long end = System.currentTimeMillis();
			timeTakenArray.add(end-start);
			// size of array increased by 200 every time (can be changed to any value but careful with plotter.py)
			n += 200;
		}
		
		return timeTakenArray;
	}
}
