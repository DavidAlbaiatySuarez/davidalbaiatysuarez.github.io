package ae1;

/**
 * Implements the 3-Way Partition Quicksort Algorithm:
 * 
 * 3-WAY-QUICKSORT.
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public abstract class Quicksort3WP {
	/**
	 * Partition algorithm that breaks the given array into 3 parts
	 * @param A (an array of type int[])
	 * @param p 
	 * @param r 
	 * @return an array with the two indices (less than and greater than pivot)
	 */
	private static final int[] threeWayPartition(int[] A, int p, int r) {
		// Pivot (first element of the array)
		int pivot = A[p];
		
		// First index 
		int indexLTpivot = p;
		// Last index 
		int indexGTpivot = r;
		// Iterate the array rightwards (from left to right)
		int currentIndex = p;
		
		
		while(currentIndex<=indexGTpivot) {
			if(A[currentIndex] < pivot) {
				
				Algorithms.swap(A, currentIndex, indexLTpivot);
				indexLTpivot++;
				currentIndex++;
				
			}else if(A[currentIndex] > pivot) {
				
				Algorithms.swap(A, currentIndex, indexGTpivot);
				indexGTpivot--;
				
			}else {
				currentIndex++;
			}
		}
		// Returns an array containing the indexes less than / greater than pivot
		return new int[]{indexLTpivot,indexGTpivot};
	}
	
	
	/**
	 * A more complex variation of the quicksort algorithm which breaks the given array into 3 subarrays and orders it
	 * @param A
	 * @param p
	 * @param r
	 */
	public static final void quicksortWith3wayPartition(int[] A, int p, int r) {
		if(p<r) {
			// extract the indices from the partition algorithm and call the quicksort algorithm
			int[] indexes = threeWayPartition(A, p, r);
			int indexLTpivot = indexes[0];
			int indexGTpivot = indexes[1];
			quicksortWith3wayPartition(A, p, indexLTpivot-1);
			quicksortWith3wayPartition(A, indexGTpivot+1, r);
		}
	}
}
