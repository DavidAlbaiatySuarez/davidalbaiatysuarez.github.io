import java.util.Comparator;

/**
 * Comparator used by the priority queue which compares nodes based on their respective weights
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public class MyComparator implements Comparator<NodeHuffman> {
	
	public int compare(NodeHuffman node1, NodeHuffman node2) {
		return Integer.compare(node1.getWeight(), node2.getWeight());
	}
}
