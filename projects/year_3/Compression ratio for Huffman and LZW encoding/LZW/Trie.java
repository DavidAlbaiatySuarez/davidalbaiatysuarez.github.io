
/**
 * Custom Trie to represent the dictionary for LZW compression
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public class Trie {
	
	/* Field of type Node containing the root of the trie */
	private Node root; 
	/* Field of type Node containing the last character found (for efficient insertion) - Initialized to root */
	private Node currentNode = root;
	/* Field of type Node[] containing a fixed array of Nodes for each AScii character */
	private Node[] ascii = new Node[127];
	
	public Trie() {
		// null character in the root
		root = new Node(Character.MIN_VALUE); 
	}        
	
	/** Custom search method for trie. 
	 *  
	 *  Efficient steps: instead of starting from the root, we retrieve the first character node from the ascii array.
	 * 					 No time is spend looking for the first character (efficient) and we only need to check for the 
	 * 					 child nodes and occasionally the siblings.
	 * 					 
	 * 					 Also, we keep track of the node representing the last character found.
	 * 
	 *  Worst-case complexity: O(m) where m is the length of the string
	 * 
	 * @param w of type String (word)
	 * 
	 * @return boolean value (true if word w found, false otherwise)
	 * 
	 */
	protected boolean search(String w) {
		// start from begin of word 
		int i = 0;
		Node current = ascii[(int)w.charAt(0)];
		
		while (true) {
			// dead-end so end while loop
			if (current == null) return false;
			// positions match
			else if (current.getLetter() == w.charAt(i)) {
				// update current node
				currentNode = current;
				// matched word end while loop
				if (i == w.length() - 1) return true; 
				// descend one level in the trie
				else { 
					current = current.getChild();
					i++; 
				}
			// check for siblings
			}else current = current.getSibling();
		}
	}
	
	/**
	 * Initializes the trie with the the 128 AScii characters.
	 * 
	 * Efficient steps: we iterate over a FIXED range and for each character we just create a new node
	 * 				    and set this as a child of root. We then store each node in the fixed array ascii for 
	 * 				    later retrieval by the search() method
	 * Worst-case complexity: O(1) since no need to traverse the trie (constant time insertion)
	 * 
	 */
	protected void initializeDictionary() {
		for(int i=0; i<127; i++) {
			char temp = ((char)i);
			Node x = new Node(temp);
			root.setChild(x);
			ascii[i] = x;
		}
	}
	
	/**
	 * Custom insert method for efficient insertion to the trie
	 * 
	 * Efficient steps: since we are keeping track of the node for the last character found, when inserting 
	 * 					(by design of LZW) we just need to create a child node for that node and this can be done
	 * 					in constant time
	 * 
	 * Worst-case complexity: O(1) since we do not need to traverse the trie.
	 * 
	 * @param w of type char to be inserted in the trie
	 * 
	 */
	protected void insert(char w){
		Node x = new Node(w); 
		x.setSibling(currentNode.getChild());
		currentNode.setChild(x);
	}
	
	// No getters and setter required
}
