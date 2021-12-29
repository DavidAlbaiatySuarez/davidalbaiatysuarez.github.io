package ae1;

/**
 * Implements the Quicksort with Insertion sort algorithm:
 * 
 * A variant of QUICKSORT which returns without sorting subarrays with fewer than k
 * elements and then uses INSERTION-SORT to sort the entire nearly-sorted array.
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public abstract class QuicksortWithIS {
	
	/**
	 * Insertion-Sort algorithm implementation (different from InsertionSort.java) 
	 * includes the first and last index (as parameters) of the given array
	 * @param a (array)
	 * @param p (first index of array)
	 * @param r (last index of array)
	 */
	private static final void insertionSort(int[] a, int p, int r) {
		for (int j=p+1;j<r;j++) {
			int key = a[j];
			int i = j-1;
			
			while (i>=0 && a[i] > key) {
				a[i+1] = a[i];
				i--;
			}
			a[i+1] = key;
		}
	}
	
	/**
	 * Partition algorithm for the InsertionSort Quicksort
	 * @param A
	 * @param p
	 * @param r
	 * @return i+1
	 */
	private static final int partition(int[] A, int p, int r) {
		int x = A[r];
		int i = p - 1;
		
		for(int j=p; j<=r-1;j++) {
			if(A[j] <= x) {
				i++;
				Algorithms.swap(A, i, j);
			}
		}
		
		Algorithms.swap(A, i+1, r);
		
		return i + 1;
	}
	
	/**
	 * Slightly modified quicksort which implements the insertion sort algorithm (Algorithms.insertionSort)
	 * iff the length of the array is less than K (constant value, set to 10)
	 * @param A
	 * @param p (first index of array)
	 * @param r (last index of array)
	 */
	public static final void quicksortWithInsertionSort(int[] A, int p, int r) {
		// K value used to decide which algorithm to run
		final int SIZE = 40;
		if(p<r) {
			//If the length of the array (difference between r and p) is less than K, switch to insertionSort (end of recursion)
			if((r-p) < SIZE) {
				insertionSort(A,p,r+1);
			}else {
				int q = partition(A, p, r);
				quicksortWithInsertionSort(A, p, q-1);
				quicksortWithInsertionSort(A, q+1, r);
			}
				
		}
	}
}
