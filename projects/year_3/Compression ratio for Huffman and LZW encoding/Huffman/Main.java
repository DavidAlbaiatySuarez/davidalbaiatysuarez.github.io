import java.io.*;
import java.util.*;

/** program to find compression ratio using Huffman coding
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();
		String inputFileName = args[0];
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		
		// read in the data and do the work here
		// read a line at a time to enable newlines to be detected and included in the compression
		
		HuffmanEncoding hm = new HuffmanEncoding();
		int[] characters = hm.fileReader(in);
		hm.setLeafNodes(characters);
		hm.createHuffmanTree(hm.getLeafNodes());
		
		in.close();
		reader.close();
		
		// output the results here
		
		System.out.println("Original file length in bits = " + hm.getOriginalFileSize());
		System.out.println("Compressed file length in bits = " + hm.getWpl());
		double compressionRatio = (double)hm.getWpl() / (double)hm.getOriginalFileSize();
		System.out.println("Compression ratio = " + compressionRatio);
		
		
		
		// end timer and print elapsed time as last line of output
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
	}

}

