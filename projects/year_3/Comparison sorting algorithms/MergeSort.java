package ae1;

import java.util.Arrays;

/**
 * MergeSort implementation
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public abstract class MergeSort {
	
	/**
	 * Helper function for the merge-sort algorithm
	 * @param A
	 * @param p
	 * @param q
	 * @param r
	 */
	protected static final void mergeAlgorithm(int[] A, int p, int q, int r) {
		//creates two separate arrays and assigns last value to infinity
		int n1 = q - p + 1;
		int n2 = r - q;
		
		int[] L = new int[n1+1];
		int[] R = new int[n2+1];
		int[] temp = Arrays.copyOfRange(A, p, q+1);
		//.arraycopy(Object arrayContainingData, int startingOfFirstArray, Object ArrayToCopyData, int destPos, int length)
		System.arraycopy(temp, 0, L, 0, (temp.length));
		temp = Arrays.copyOfRange(A, q+1, r+1);
		System.arraycopy(temp, 0, R, 0, (temp.length));
		//L[n1]=R[n2]=infinity
		R[n2] = L[n1] = Integer.MAX_VALUE;
		
		int i = 0;
		int j = 0;
		
		for(int k=p;k<=r;k++) {
			if(L[i]<=R[j]) {
				A[k] = L[i];
				i++;
			}else {
				A[k] = R[j];
				j++;
			}
		}
	}
	
	/**
	 * MERGE-SORT algorithm implementation. 
	 * @param A
	 * @param p
	 * @param r
	 */
	public static void mergeSort(int[] A, int p, int r) {
		if (p < r) {
			int q = (p+r)/2;
			mergeSort(A,p,q);
			mergeSort(A,q+1,r);
			mergeAlgorithm(A,p,q,r);
		}
	}
}
