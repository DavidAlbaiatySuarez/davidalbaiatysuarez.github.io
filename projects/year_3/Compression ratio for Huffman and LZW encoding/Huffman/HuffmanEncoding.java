import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Class to generate the compression ratio for a .txt file compressed using Huffman encoding
 * Note that the program does not generate the compressed file and is meant to generate the compression
 * ratio with as little work as possible (efficiency is the primary goal)
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public class HuffmanEncoding {
	
	/* Field of type NodeHuffman initializing the root of the Huffman tree to null */
	private NodeHuffman rootNode = null;
	/* Field of type int[] with each unique index(ascii character) containing its frequency */
	private int[] characters = new int[128];
	/* Field of type PriorityQueue<NodeHuffman> storing all leaf nodes and ordering their values according to their weight (frequencies) */
	private PriorityQueue<NodeHuffman> queue = new PriorityQueue<>(new MyComparator());
	/* Field of primitive type int which stores the weighted path length (wpl) of the huffman tree */
	private int wpl = 0;
	/* Field containing the original size of the file provided */
	private int originalFileSize = 0;
	
	/**
	 * Extracts all characters from a given file and stores them in an array of size 128.
	 * Each index represents an ascii character so the frequency of appearance of each character 
	 * is stored in the appropiate index
	 * 
	 * @param in (Scanner of the file to parse)
	 * 
	 * Worst case complexity: O(n^2) for parsing the file and storing the frequencies.
	 * 						  O(n)   for generating the frequencies
	 * 
	 * @return array of type int
	 */
	protected int[] fileReader(Scanner in){
		// parses the .txt file
		while(in.hasNextLine()) {
			// array of each character in the current line for iteration
			char[] chars = in.nextLine().toCharArray();
			// iterate array
			for(char ch : chars) {
				characters[(int)ch] += 1;
			}
			
			characters[(int)'\n'] += 1;
			originalFileSize += (chars.length + 1);
		}
		
		return characters;
	}
	
	/**
	 * Creates a leaf node of type NodeHuffman for every character in the array provided
	 * 
	 * 
	 * @param array of type int
	 * 
	 * Worst case complexity: O(logn) since adding to the queue with the implemented comparator is O(log n)
	 * 
	 */
	protected void setLeafNodes(int[] characters) {
		// creates leaf node for every character in characters
		for (int i = 0; i < characters.length; i++) {
			// if frequency non-zero and positive
			if (characters[i] != 0) {
				char c = (char)i;
				int f = characters[i];
				// create a new parentless node
				NodeHuffman leafNode = new NodeHuffman();
				leafNode.setCharacter(c);
				leafNode.setWeight(f);
				leafNode.setLeftChild(null);
				leafNode.setRightChild(null);
				// add leaf node to the queue
				queue.add(leafNode);
			}
		}
	}
	
	/**
	 * Constructs the branch nodes and links for the HuffmanTree
	 * The priority queue has elements added in order of weight (frequency) as specified in MyComparator
	 * 
	 * 
	 * @param queue (smallest weighted Nodes first)
	 * 
	 * Worst case complexity: O(n logn) since we are iterating the queue O(n) and the poll and add operations are each O(logn)
	 * 
	 */
	protected void createHuffmanTree(PriorityQueue<NodeHuffman> queue) {
		// while size of queue is greater than 1, extract nodes
		while(queue.size() > 1) {
			// Initialize two nodes of type NodeHuffman
			NodeHuffman x, y = new NodeHuffman();
			// get the first two nodes from the queue
			x = queue.poll();
			y = queue.poll();
			// create a new parentless node
			NodeHuffman z = new NodeHuffman();
			z.setLeftChild(x);
			z.setRightChild(y);
			// calculate and set weight of new node
			z.setWeight(x.getWeight()+y.getWeight());
			// add newly created node to the queue
			queue.add(z);
			// update root node to be the newly created node
			rootNode = z;
			wpl = wpl + z.getWeight();
		}
	}
	
	// getters and setters for the different fields in class HuffmanEnconding
	protected int[] getCharacters() {
		return characters;
	}
	
	protected PriorityQueue<NodeHuffman> getLeafNodes(){
		return queue;
	}

	protected NodeHuffman getRootNode() {
		return rootNode;
	}

	protected int getOriginalFileSize() {
		// from bytes to bits
		return originalFileSize * 8;
	}
	
	protected int getWpl() {
		return wpl;
	}
}
