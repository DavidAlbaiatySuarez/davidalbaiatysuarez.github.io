/**
 * Node class provided by lecturer with slight modifications. Nodes used in the efficient trie
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public class Node {
	
	private char letter; // label on incoming branch
	private Node sibling; // next sibling (when it exists)
	private Node child; // first child (when it exists)
	
	
	/** create a new node with letter c */
	public Node(char c){
		letter = c;
		sibling = null;
		child = null;
	}
	
	// necessary getters and setters 
	protected char getLetter() {
		return letter;
	}

	protected void setLetter(char letter) {
		this.letter = letter;
	}

	protected Node getSibling() {
		return sibling;
	}

	protected void setSibling(Node sibling) {
		this.sibling = sibling;
	}

	protected Node getChild() {
		return child;
	}

	protected void setChild(Node child) {
		this.child = child;
	}
	
}
