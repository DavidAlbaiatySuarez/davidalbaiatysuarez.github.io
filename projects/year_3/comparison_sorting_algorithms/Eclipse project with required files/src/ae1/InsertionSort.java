package ae1;

/**
 * Insertion Sort implementation
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public abstract class InsertionSort {
	
	/**
	 * Insertion-Sort algorithm implementation
	 * @param a
	 * @return The sorted array
	 */
	protected static final int[] insertionSort(int[] a) {
		//length of the given array (a)
		int n = a.length;
		
		//loop implementing required sorting algorithm
		for (int j=1;j<=n-1;j++) {
			int key = a[j];
			int i = j-1;
			
			while (i>=0 && a[i] > key) {
				a[i+1] = a[i];
				i--;
			}
			a[i+1] = key;
		}

		return a;
	}
	
}
