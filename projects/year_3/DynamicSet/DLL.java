package DynamicSet;

/**
 * Class representing nodes in our Doubly Linked List (DLL)
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
class NodeDLL { 
    
	NodeDLL previousNode, nextNode;
	int key; 
	
    public NodeDLL(int value){ 
        this.key = value; 
        this.previousNode = null;
        this.nextNode = null; 
    } 
}

/**
 * Doubly linked list (DLL) implementation containing the following operations and set-theoretical operations:
 * 		add(), remove(), isElement(), setEmpty(), setSize(), union(), intersection(), difference(), subset()
 * 				
 * @author 2474364A
 *
 */
public class DLL {
	
	/** head field initialized to null */
	private NodeDLL head = null;
	/** tail field initialized to null */
	private NodeDLL tail = null;
	
	
	/**
	 * Inserts a new node z AT THE END of the doubly linked list
	 * Overall, O(n) since we need to check if the key provided is already in the DLL.
	 * The adding operation is O(1) - performed in constant time - since we do not traverse the doubly linked list
	 * @param z (element of type int)
	 */
	public final void add(int z) {
		// If key z already in the DLL terminate method since our implementation CANNOT contain duplicates
		if(isElement(z)) {
			return;
		}
		
		NodeDLL tempNode = new NodeDLL(z);
		
		// If we have an empty DLL
		if(head == null) {
			head = tempNode;
			tail = tempNode;
			tail.nextNode = null;
			head.previousNode = null;
		// DLL not empty
		}else {
			tail.nextNode = tempNode;
			tempNode.previousNode = tail;
			tail = tempNode;
			tail.nextNode = null;
		}
		
	}
	
	/**
	 * Removes a given key from a Doubly Linked List.
	 * Overall, O(n) since we need to traverse the DLL to find the node that has the value of the given key (z)
	 * @param z (element of type int)
	 */
	public final void remove(int key) {
		NodeDLL current = this.head;
		
		// Empty doubly linked list (terminate method)
		if(head == null) return;
		
		// We traverse the DLL to find the Node of the desired key
		while(current != null && current.key != key) {
			current = current.nextNode;
		}
		
		// Only if a valid node was found (remove it)
		if(current != null) {
			remove(current);
		}
	}
	/**
	 * O(1) - performed in constant time - since we do not need to traverse the doubly linked list
	 */
	private final void remove(NodeDLL z) {
		
		// Pointing to the previous node of the current node (node to delete)
		if(z.previousNode != null) z.previousNode.nextNode = z.nextNode;
		else this.head = z.nextNode;
		
		// Pointing to the next node of the current node (node to delete)
		if(z.nextNode != null) z.nextNode.previousNode = z.previousNode;
		else this.tail = z.previousNode;
		
	}
	
	/**
	 * Search if element x in the Doubly Linked List (iteratively) 
	 * O(n)
	 * @param x (element of type int)
	 */
	public final boolean isElement(int x) {
		
		NodeDLL current = head;
		
		// Empty doubly linked list (exit method)
		if(head == null) return false;
		
		while(current != null) {
			// Element found return true
			if(current.key == x) return true;
			current = current.nextNode;
		}
		
		// Element not found return false
		return false;
	}
	
	/**
	 * checks whether the doubly linked list s has no elements
	 * O(1)
	 * @return true or false
	 */
	public final boolean setEmpty() {
		if(this.head == null) return true;
		else return false;
	}
	
	/**
	 * returns the number of elements of set S
	 * O(n) since we iterate the whole doubly linked list
	 * @return size of the DLL
	 */
	public final int setSize() {
		return setSize(this.head);
	}
	private final int setSize(NodeDLL z) {
		int size = 0;
		if(z == null) return 0;
		
		while(z != null) {
			z = z.nextNode;
			size++;
		}
		return size;
	}
	
	/**
	 * Returns the union of two doubly linked lists
	 * O(k) where k = n + m (size of first and second array respectively)
	 * @param s (DLL)
	 * @param t (DLL)
	 * @return a new DLL containing the union of s and t
	 */
	public static final DLL union(DLL s, DLL t) {
		NodeDLL tempS = s.head;
		NodeDLL tempT = t.head;
		
		DLL temp = new DLL();
		
		// We first insert all keys from the first DLL (s) into a new DLL
		while(tempS != null) {
			temp.add(tempS.key);
			tempS = tempS.nextNode;
		}
		
		// We then insert all non-repeating elements of the second DLL (t) into the new DLL
		while(tempT != null) {
			if(!(temp.isElement(tempT.key))) temp.add(tempT.key);
			tempT = tempT.nextNode;
		}
		
		return temp;
	}
	
	/**
	 * Returns the intersection of two doubly linked list
	 * O(n^2) since we have O(m*n) where m = size of DLL s and n = size of DLL t
	 * @param s (DLL)
	 * @param t (DLL)
	 * @return a new DLL containing the intersection of s and t
	 */
	public static final DLL intersection(DLL s, DLL t) {
		NodeDLL tempS = s.head;
		DLL temp = new DLL();
		
		// traverse the DLL s and see if there are elements present in DLL t
		while(tempS != null) {
			if(t.isElement(tempS.key)) temp.add(tempS.key);
			tempS = tempS.nextNode;
		}
		
		return temp;
	}
	
	/**
	 * Returns the difference of two doubly linked list (all elements in DLL s not present in DLL t)
	 * O(n^2) since we have O(m*n) where m = size of DLL s and n = size of DLL t
	 * @param s (DLL)
	 * @param t (DLL)
	 * @return a new DLL containing the difference of s and t
	 */
	public static final DLL difference(DLL s, DLL t) {
		NodeDLL tempS = s.head;
		DLL temp = new DLL();
		
		// traverse the DLL s and compare the elements with the DLL t
		while(tempS != null) {
			if(!(t.isElement(tempS.key))) temp.add(tempS.key);
			tempS = tempS.nextNode;
		}
		
		return temp;
	}
	
	/**
	 * Helper method to find if array A is a sub-array of array B
	 * @param A (array - check if sub-array)
	 * @param B (array)
	 * @param n (length of array A)
	 * @param m (length of array B)
	 * @return true or false
	 */
    private static final boolean isSubarray(int A[], int B[],  int n, int m) { 
        int j = 0; 
        
        for(int i=0; i<n;i++) {
        	for(j=0;j<m;j++) {
        		if(A[i] == B[j]) break;
        	}
        	if(j==m) return false;
        }
        
        return true;
    } 
	
	/**
	 * Helper method that converts a doubly linked list into an array
	 * O(n)
	 * @param z (DLL)
	 * @return new array of type int[]
	 */
	private static final int[] arrayConverter(DLL z) {
		NodeDLL currentNode = z.head;
		int[] temp = new int[z.setSize()];
		int i = 0;
		
		while(currentNode != null) {
			temp[i] = currentNode.key;
			i++;
			currentNode = currentNode.nextNode;
		}
		
		return temp;
	}
	
	/**
	 * Checks if DLL s is a subset of DLL t
	 * O(k^2) � O(m*n) where m and n are the size of the two BST�s
	 * @param s (DLL)
	 * @param t (DLL)
	 * @return true (if subset) or false otherwise
	 */
	public static final boolean subset(DLL s, DLL t) {
		int[] tempS = arrayConverter(s);
		int[] tempT = arrayConverter(t);
		
		return isSubarray(tempS, tempT, tempS.length, tempT.length);
	}
	
	/**
	 * Traverses the entire DLL and prints each of the elements inside
	 * O(n)
	 */
	public final void print() {
		NodeDLL current = head;
		
		if (head == null) return;
		else {
			while (current != null) {
				System.out.print(current.key + " ");
				current = current.nextNode;
			}
		}
		System.out.println();
	}
}

