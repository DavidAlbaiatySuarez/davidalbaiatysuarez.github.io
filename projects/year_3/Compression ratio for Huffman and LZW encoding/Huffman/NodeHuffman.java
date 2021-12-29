
/**
 * Class representing the nodes for the huffman encoding
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public class NodeHuffman {
	
	private char character;
	/* Field equal to the frequency of characters */
	private int weight;
	private NodeHuffman leftChild;
	private NodeHuffman rightChild;
	
	
	public int compareTo(NodeHuffman other) {
		return Integer.compare(this.weight, other.weight);
	}
	
	
	
	protected void setWeight(int f) {
		weight = f;
	}
	
	protected void setCharacter(char c) {
		character = c;
	}
	
	protected void setLeftChild(NodeHuffman x) {
		leftChild = x;
	}
	
	protected void setRightChild(NodeHuffman x) {
		rightChild = x;
	}

	protected char getCharacter() {
		return character;
	}

	protected int getWeight() {
		return weight;
	}

	protected NodeHuffman getLeftChild() {
		return leftChild;
	}

	protected NodeHuffman getRightChild() {
		return rightChild;
	}
	
}
