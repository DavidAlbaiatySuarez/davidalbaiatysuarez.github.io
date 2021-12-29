package DynamicSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * DynamicSet class from where to run the DLL and BST implementations for our Dynamic Set
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public class DynamicSet {
	
	/**
	 * DLL working example
	 */
	private static final void DLLExample() {
		// Initialize two doubly linked lists
		DLL dll1 = new DLL();
		DLL dll2 = new DLL();
		
		dll1.add(10);
		dll1.add(50);
		dll1.add(30);
		dll1.add(20);
		dll1.add(10); // value ignored since we do not allow duplicates
		dll1.remove(10);
		dll1.remove(30);
		System.out.println("DLL 1: ");
		dll1.print(); // Expected result: 50,20
				
		dll2.add(20);
		dll2.add(70);
		dll2.add(50);
		dll2.add(5);
		dll2.remove(5); // Element 5 no longer in the DLL
		dll2.add(100);
		dll2.add(45);
		System.out.println("DLL 2: ");
		dll2.print(); // Expected result: 20, 70, 50, 100, 45
		
		
		DLL temp = DLL.union(dll1, dll2); 
		System.out.println("Union of the two DLL's: ");
		temp.print(); // Expected result: 50 20 70 100 45 
		System.out.println("Intersection of the two DLL's: ");
		temp = DLL.intersection(dll1, dll2);
		temp.print(); // Expected result: 50 20
		System.out.println("Difference of the two DLL's: ");
		temp = DLL.difference(dll1, dll2);
		temp.print(); // Expected result: no output
		System.out.println("Is DLL1 subset of DLL2: ");
		System.out.println(DLL.subset(dll1, dll2)); // Expected result: true
	}

	/**
	 * BST working example
	 */
	private static final void BSTExample() {
		BST t = new BST();
		BST s = new BST();
		
		t.add(40);
        t.add(4);
        t.add(6);
        t.add(30);
        t.add(4); // value ignored since we do not allow duplicates
        t.remove(4);
        System.out.println("BST 1: ");
        int[] array = new int[t.setSize()];
        System.out.println(Arrays.toString(t.inOrderArrayTraversal(array))); // Expected result: [6,30,40]
        
        s.add(50);
        s.add(30);
        s.add(20);
        s.add(40);
        s.add(70);
        s.add(6);
        s.add(12);
        System.out.println("BST 2: ");
        array = new int[s.setSize()];
        System.out.println(Arrays.toString(s.inOrderArrayTraversal(array)));
        
        System.out.println("Union of the two BST's: ");
        BST bst1 = BST.union(t, s); 
        int[] temp = new int[bst1.setSize()];
        System.out.println(Arrays.toString(bst1.inOrderArrayTraversal(temp))); // Expected result: [6, 12, 20, 30, 40, 50, 70]
        
        System.out.println("Intersection of the two BST's: ");
        BST bst2 = BST.intersection(t, s);
        temp = new int[bst2.setSize()];
        System.out.println(Arrays.toString(bst2.inOrderArrayTraversal(temp))); // Expected result: [6, 30, 40]
        
        System.out.println("Difference of the two BST's: ");
        BST bst3 = BST.difference(s, t);
        temp = new int[bst3.setSize()];
        System.out.println(Arrays.toString(bst3.inOrderArrayTraversal(temp))); // Expected result: []
        
        System.out.println("Is BST1 subset of BST2: ");
        System.out.println(BST.subset(t,s)); // Expected result: true
	}
	
	private static final BST BSTFromFile(String filename) {
		
		BST temp = new BST();
		
		// Reads the provided file and adds all the values to the DLL and BST
		try {
			File file = new File(filename);
			@SuppressWarnings("resource")
			Scanner read = new Scanner(file);
			while(read.hasNextLine()) {
				String data = read.nextLine();
				int value = Integer.parseInt(data);
				temp.add(value);
			}
		}catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		return temp;
	}
	
	private static final DLL DLLFromFile(String filename) {
			
			DLL temp = new DLL();
			
			// Reads the provided file and adds all the values to the DLL and BST
			try {
				File file = new File(filename);
				@SuppressWarnings("resource")
				Scanner read = new Scanner(file);
				while(read.hasNextLine()) {
					String data = read.nextLine();
					int value = Integer.parseInt(data);
					temp.add(value);
				}
			}catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
			
			return temp;
		}
	
	/**
	 * Method to carry out tests
	 */
	public static final void empiricalStudy() {
		
		List<Long> resultsDLL = new ArrayList<>();
		List<Long> resultsBST = new ArrayList<>();
		
		DLL DLLint20k = DLLFromFile("int20k.txt");
		BST BSTint20k = BSTFromFile("int20k.txt");
		
		// Generating 100 random numbers from [0, 49999]
		int[] randomValues = new int[100];
		Random rand = new Random();
		for(int i=0; i<randomValues.length;i++) {
			randomValues[i] = rand.nextInt(49999);
		}
		
		for(int i : randomValues) {
			long start = System.nanoTime();
			DLLint20k.isElement(i);
			long end = System.nanoTime();
			resultsDLL.add(end-start);
		}
		for(int i : randomValues) {
			long start = System.nanoTime();
			BSTint20k.isElement(i);
			long end = System.nanoTime();
			resultsBST.add(end-start);
		}
		
		// a)
		long averageTimeDLL = (long) resultsDLL.stream().mapToLong(val -> val).average().orElse(0);
		System.out.println("Average time (ns) for the DLL: " + (averageTimeDLL));
		
		long averageTimeBST = (long) resultsBST.stream().mapToLong(val -> val).average().orElse(0);
		System.out.println("Average time (ns) for the BST: " + (averageTimeBST));
		
		// b) Size of the set s (int20k.txt)
		int size = BSTint20k.setSize();
		System.out.println("Size of the int20k.txt: " + size);
		// c)
		int height = BSTint20k.height();
		System.out.println("Height of the int20k.txt: " + height);
		
	}
	
	
	
	public static void main(String[] args) {
		/* Examples of possible method calls for both DLL and BST:
		 * .add(int key)
		 * .remove(int key)
		 * .isElement(int key)
		 * .setEmpty()
		 * .setSize()
		 * 
		 * Static method calls (this example refers to DLL):
		 * DLL.union(S, T) - returns a DLL 
		 * DLL.intersection(S, T) - returns a DLL
		 * DLL.difference(S, T) - returns a DLL
		 * DLL.subset(S, T)
		 * 
		 */
		
		/** DLL example */
		//DynamicSet.DLLExample();
		
		/** BST example */
		//DynamicSet.BSTExample();
		
		/** Empirical Study Part 2 of the AE */
		DynamicSet.empiricalStudy();
	}

}
