package ae1;

/**
 * Implements the first basic quicksort:
 * 
 * The pseudocode for QUICKSORT, including
 * procedure PARTITION implementing right-most element pivot selection.
 * 
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 */
public abstract class BasicQuicksort {
	
	/**
	 * Partition algorithm for the basic Quicksort and the InsertionSort Quicksort
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
	 * Basic quick sort algorithm that uses the partition method to order an array of length n
	 * @param A
	 * @param p
	 * @param r
	 */
	public static final void quicksort(int[] A, int p, int r) {
		if(p<r) {
			int q = partition(A, p, r);
			quicksort(A, p, q-1);
			quicksort(A, q+1, r);
		}
	}
}
