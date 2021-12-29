package DynamicSet;

/**
 * Class representing nodes in our Binary Search Tree (BST)
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
class Node { 
    
	Node left, right;
	int key; 
	
    public Node(int value){ 
        this.key = value; 
        this.left = null;
        this.right = null; 
    } 
}

/**
 * Binary Search Tree (BST) implementation containing the following operations and set-theoretical operations:
 * 		add(), remove(), isElement(), setEmpty(), setSize(), union(), intersection(), difference(), subset()
 * 
 * @author 2474364A
 *
 */
public class BST {
	
	/** unique boolean field (for difference method) */
	private static boolean unique = true;
	
	/** root of type Node field*/
	// Initially, root is null since tree is empty!
	private Node root = null;
	
	/**
	 * Inserts a new node z into an appropriate position in tree t
	 * O(n) since we need to check if the value is in the binary search tree
	 * @param z (the new key to be inserted)
	 */
	public final void add(int x) {
		// If x is NOT in the BST call the add method, else terminate
		if(!(isElement(x))) this.root = add(root, x);
	}
	private final Node add(Node root, int x) {
		// Case 1: empty tree (the first element added is the root)
		if(root == null) {
			root = new Node(x);
			return root;
		}
		// Case 2: root.key < x (traverse and insert node right subtree)
		if(root.key < x) root.right = add(root.right, x);
		// Case 3: root.ley > x (traverse and insert node left subtree)
		else if(root.key > x) root.left = add(root.left, x);
		
		// return root when node inserted in correct position
		return root;
	}
	
	/**
	 * Searches for the minimum Node in the binary search tree
	 * @param x (Node)
	 * @return minimum Node (node with the smallest key)
	 */
	private final Node minimum(Node x) {
		while(x.left != null) x = x.left;
		return x;
	}
	
	/**
	 * Removes the smallest key from the binary search tree (modified version of .minimum())
	 * @param x
	 * @return
	 */
	private final Node minimumRemove(Node x) {
		if(x.left == null) return x.right;
		x.left = minimumRemove(x.left);
		return x;
	}
	
	/**
	 * Removes a given element from the BST
	 * O(n) since we need to traverse the BST to find the node with the required key (done recursively)
	 * @param key (desired data)
	 */
	public final void remove(int key) {
		this.root = remove(root, key);
		
	}
	private final Node remove(Node z, int key) {
		// Initial Case: key not found in the BST
		if(z == null) return null;
		
		// IF the key provided is less than the value of the current node z, traverse the left part of the tree
		if(key < z.key) {
			z.left = remove(z.left, key);
		}
		// ELSE IF key provided is greater than the value of the current node z, traverse the right part of the tree
		else if(key > z.key) {
			z.right = remove(z.right, key);
		}
		// ELSE the key has been found so we need to take into account three different cases
		else {
			// Case 1: we do not have a left subtree
			if(z.left == null) return z.right;
			// Case 2: we do not have a right subtree
			if(z.right == null) return z.left;
			// Case 3:
			Node predecessor = z;
			z = minimum(predecessor.right);
			z.right = minimumRemove(predecessor.right);
			z.left = predecessor.left;
		}
		return z;
	}
	
	/**
	 * Search if element x in BST (iteratively)
	 * O(n) in the worst case - in general, O(h) where h is the height of the tree
	 * @param k (key)
	 * @return true or false
	 */
	public final boolean isElement(int k){
		Node x = this.root;
		
		while(!(x==null) && !(k==x.key)) {
			if(k < x.key) x = x.left;
			else x = x.right;
		}
		return (!(x == null));    
	}
	
	/**
	 * checks whether the tree s has no elements
	 * O(1)
	 * @param s 
	 * @return true if empty, false otherwise
	 */
	public final boolean setEmpty() {
		if(this.root == null) return true;
		else return false;
	}
	
	/**
	 * returns the number of elements of set S
	 * O(n)
	 * @param s (tree)
	 * @return element of type int
	 */
	public final int setSize() {
		return setSize(this.root);
	}
	private final int setSize(Node t) {
		if(t == null) return 0;
		else return setSize(t.left) + 1 + setSize(t.right);
	}
	
	/**
	 * Creates an array containing the keys (inOrder) of all the nodes in the BST 
	 * O(n)
	 */
	public final int[] inOrderArrayTraversal(int[] nodes) {
		inOrderArrayTraversal(root, nodes, 0);
		return nodes;
	}
	private final int inOrderArrayTraversal(Node root, int[] nodes, int curIndex) { 
	    if (root == null) return curIndex;
	    else { 
	    	curIndex = inOrderArrayTraversal(root.left, nodes, curIndex); 
	        nodes[curIndex++] = root.key;
	        curIndex = inOrderArrayTraversal(root.right, nodes, curIndex);
	        return curIndex;
	    } 
	} 
	
	/**
	 * returns the union of sets S and T
	 * O(n)
	 * @param t (tree 1)
	 * @param s (tree 2)
	 */
	public static final BST union(BST t, BST s) {
		BST x = new BST();
		
		int[] arrayT = new int[t.setSize()];
		t.inOrderArrayTraversal(arrayT);
		int[] arrayS = new int[s.setSize()];
		s.inOrderArrayTraversal(arrayS);
		
		for(int i : arrayT) x.add(i);
		for(int j : arrayS) x.add(j);
		
		return x;
	}
	
	
	/**
	 * returns the union of two binary search trees
	 * O(k^2) � O(m*n) where m and n are the size of the two BST�s
	 * @param t (tree 1)
	 * @param s (tree 2)
	 */
	public static final BST intersection(BST t, BST s) {
	    BST x = new BST();
		
		int[] arrayT = new int[t.setSize()];
		t.inOrderArrayTraversal(arrayT);
		int[] arrayS = new int[s.setSize()];
		s.inOrderArrayTraversal(arrayS);
		
		for(int i : arrayT) {
			for(int j : arrayS) {
				if(i == j) x.add(i);
			}
		}
		
		return x;
	}
	
	/**
	 * returns the difference of two binary search trees
	 * (set of nodes in s not present in t)
	 * O(k^2) � O(m*n) where m and n are the size of the two BST�s
	 * @param t (tree 1)
	 * @param s (tree 2)
	 */
	public static final BST difference(BST s, BST t) {
		BST x = new BST();
		
		int[] arrayT = new int[t.setSize()];
		t.inOrderArrayTraversal(arrayT);
		int[] arrayS = new int[s.setSize()];
		s.inOrderArrayTraversal(arrayS);
		
		
		for(int i : arrayT) {
			for(int j : arrayS) {
				if(i == j) unique = false;
			}
			if(unique==true) x.add(i);
		}
		
		return x;
		
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
	 * checks whether a binary search tree (converted to an array) is a subset of another binary search tree (also converted to array)
	 * O(k^2) � O(m*n) where m and n are the size of the two BST�s
	 * @param s (tree 1)
	 * @param t (tree 2)
	 * @return true or false
	 */
	public static final boolean subset(BST s, BST t) {
		int[] arrayT = new int[t.setSize()];
		t.inOrderArrayTraversal(arrayT);
		int[] arrayS = new int[s.setSize()];
		s.inOrderArrayTraversal(arrayS);
		
		return isSubarray(arrayS, arrayT, arrayS.length, arrayT.length);
	}
	
	/**
	 * method to find the height of a BST
	 * @return height of the BST
	 */
	public final int height() {
		return height(this.root);
	}
	private final int height(Node x) {
		if(x == null) return 0;
		if(x.left == null && x.right == null) return 0;
		return Math.max(height(x.left), height(x.right)) + 1;
	}
	
}
