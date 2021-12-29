package ae1;

/**
 * Implements the Quicksort with Middle of Three Partition Algorithm:
 * 
 * A variant of QUICKSORT using the median-of-three partitioning scheme.
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public abstract class QuicksortWithMo3 {
	
	/**
	 * Median-of-three partitioning implementation (choose the median from 3 elements)
	 * @param p
	 * @param q
	 * @param r
	 * @return
	 */
	private static int medianOfThree(int[] A, int p,int q,int r) {
		if(A[p] > A[q]) Algorithms.swap(A,p,q);
		if(A[p] > A[r]) Algorithms.swap(A,p,q);
		if(A[q] > A[r]) Algorithms.swap(A,p,r);
		
		Algorithms.swap(A,q,r);
		return A[r];
	}
	
	/**
	 * Partition algorithm with the modified implementation of the pivot (median of 3 integers)
	 * @param A
	 * @param p (first index of array)
	 * @param r (last index of array)
	 */
	private static final int partitionWithMedianOfThree(int[] A, int p, int r) {
		// x is the PIVOT
		int x = medianOfThree(A,p,((r+p)/2),r); // We want the first (p), middle index and last(r) of array A
		int i = p - 1;
		
		for(int j=p; j<=r-1;j++) {
			if(A[j] <= x) {
				i++;
				Algorithms.swap(A, i, j);
			}
		}
		Algorithms.swap(A,i+1, r);
		
		return i + 1;
	}
	
	/**
	 * Another variation of the quicksort algorithm (we use the median of three values to calculate the pivot)
	 * @param A
	 * @param p
	 * @param r
	 */
	public static final void quicksortWithMedianOfThree(int[] A, int p, int r) {
		if(p<r) {
			int q = partitionWithMedianOfThree(A, p, r);
			quicksortWithMedianOfThree(A, p, q-1);
			quicksortWithMedianOfThree(A, q+1, r);
		}
	}
	
}
