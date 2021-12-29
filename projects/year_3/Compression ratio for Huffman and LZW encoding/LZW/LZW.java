import java.util.LinkedList;
import java.util.Scanner;

/**
 * Custom LZW implementation for calculating the compression ratio of any file (containing only AScii characters) provided
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public class LZW {
	
	/* Field of type int containing the codeword length for the LZW algorithm - initialized to 8 */
	private int codewordLength = 8;
	/* Field of type trie initializing a new trie */
	private Trie dict = new Trie();
	/* Field of type LinkedList<Character> containing all characters from the file provided */
	private LinkedList<Character> file = new LinkedList<>();
	/* Field of type int storing the size of the file provided - initialized to 0 */
	private int originalFileSize = 0;
	/* Field of type int storing the currentCodeword - initialized to 128 */
	private int currentCodeword = 128;
	/* Field of type int storing the compression size of the file */
	private int compressionSize = 8;
	
	/** 
	 * Helper method to initialize the trie with the 128 AScii characters 
	 * 
	 * Worst case complexity: O(1) since we iterate a fixed sized loop O(1) and insertion to the trie is also O(1) (see trie implementation)
	 * 
	 */
	private void initializeDictionary() {
		dict.initializeDictionary();;
	}
	
	/**
	 * Reads the contents of the file and inserts them into a linked list
	 * 
	 * Efficient steps: a linked list is used to insert each character since we do not know beforehand the 
	 * 					size of the file provided (more efficient than using an ArrayList)
	 * 
	 * Worst-case complexity: O(n^2)
	 * 
	 * @param in of type Scanner
	 */
	protected void readFile(Scanner in) {
		while (in.hasNextLine()) {
			// array of each character in the current line for iteration
			char[] chars = in.nextLine().toCharArray();
			for (char ch : chars) file.add(ch);
			file.add('\n');
			originalFileSize += (chars.length + 1);
		}	
	}
	
	/**
	 * Compression of a file using a custom LZW (no actual compression file produced)
	 * 
	 * Efficient steps: Since we only want to find the compression ration: 
	 * 						We do not store the actual codeword for each string, we just want the size
	 * 						For insertion, we just pass in a character as argument (implementation explained in trie class)
	 * 						Updating compression size by just adding the current word length (no need to know the specific bits)
	 * 						Search for each word reduced since we do not start from the root (see search implementation in trie class)
	 * 
	 * Worst-case complexity: O(nm) where n is the file length and m is the size of the string
	 * 								Note that for many cases actual complexity of search() is O(1)
	 * 								Also, for the vast majority of files (m.length() < 6) so we could
	 * 								treat m as being a sort of 'constant' value
	 * 
	 */
	protected void compression() {
		// Initialize the dictionary (trie)
		initializeDictionary();
		// set current string s to be empty
		String s = "";
		
		// iterate the array containing all the characters from the .txt file
		for(char i : file) {
			String wc = s + Character.toString(i);
			
			// checks if wc in dictionary (trie)
			if (dict.search(wc) == true) s = wc;
			else {
				// checks whether dictionary is full, if so increase codewordLength
				if (currentCodeword == Math.pow(2, codewordLength)) codewordLength += 1;
				// Increase value of codeword by one
				currentCodeword = currentCodeword + 1;
				// since we are keeping track of the node to insert the new letter (we just need to pass the char instead of the string)
				dict.insert(i);
				// update compressionSize
				compressionSize += codewordLength;
				
				s = ""+ i;
				
			}
		}
	}
	
	
	// only required getters
	protected int getOriginalFileSize() {
		// converts file size from bytes to bits (*8)
		return originalFileSize * 8;
	}
	
	protected int getCompressionSize() {
		return compressionSize;
	}
	
	
}
