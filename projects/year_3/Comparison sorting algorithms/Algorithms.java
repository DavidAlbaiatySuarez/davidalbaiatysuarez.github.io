package ae1;

/**
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 */
public class Algorithms {
	
	/**
	 * Helper method that swaps two elements of a given array
	 * @param A (array)
	 * @param x (index1)
	 * @param y (index2)
	 */
	public static final void swap(int[] A, int x, int y) {
		int temp = A[x];
		A[x] = A[y];
		A[y] = temp;
	}
	
	
	
	/**
	 * Killer array generator that returns an array of integers that will be sorted in O(n^2) QUESTION 3
	 * @param n
	 */
	public static final int[] killerSequenceQuicksort(int n) {
		int[] killerArray = new int[n];
		// Generates an ordered array of integers of size n
		for(int i=0; i<n;i++) {
			killerArray[i] = i;
		}
		
		// last element of the array
		int lastElement = killerArray[killerArray.length-1];
		
		// here we shift the original array one place to the right
		int[] finalKillerArray = new int[killerArray.length];
		System.arraycopy(killerArray, 0, finalKillerArray, 1 , killerArray.length-1);
		// we place the last element of the original array to the first element of the new array (we shift the original array)
		finalKillerArray[0] = lastElement;
		
		return finalKillerArray;
	}
	
	//main
	public static void main(String[] args) {
		
		// TESTING 1
		String[] files = {"int10.txt", "int1000.txt","int50.txt","int100.txt","intBig.txt", "int500k.txt","int20k.txt","dutch.txt"};
		// Compares all the sorting algorithms
		Tests.timeSortingAlgorithm(files);
		
		// Correctness of the algorithm using Tests.testSortingAlgorithm()
		// Example:
		int[] arrayTest = {1,8,4,2,9,7};
		// If return value 1 (array sorted correctly) else value returned 0.
		// Run the following code:
		
		//System.out.println(Tests.testSortingAlgorithm(InsertionSort.insertionSort(arrayTest)));
		
		// TESTING 2
		/**killerSequenceQuicksort generates an array of length 200 (killer array)**/
		// Run the following code:
		
		// Algorithms.killerSequenceQuicksort(200);
		
		/**We then perform the test by calling the following method (note that this method calls killerSequenceQuicksort automatically)**/
		
		// Run the following code:
		
		// System.out.println(Tests.killerArrayAlgorithm()); // returns an array of running times which will then be copied into plotter.py
		
		
	}
}





