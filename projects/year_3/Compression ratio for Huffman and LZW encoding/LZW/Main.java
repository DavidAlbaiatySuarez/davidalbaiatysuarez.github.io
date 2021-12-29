import java.io.*;
import java.util.*;

/** program to find compression ratio using LZW compression
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();
		String inputFileName = args[0];
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		
		// read in the data and do the work here
        // read a line at a time to enable newlines to be detected and included in the compression
		
		
		LZW lzw = new LZW();
		lzw.readFile(in);
		lzw.compression();
		
		reader.close();

		// output the results here
		System.out.println("Original file length in bits = " + lzw.getOriginalFileSize());
		System.out.println("Compressed file length in bits = " + lzw.getCompressionSize());
		double compressionRatio = (double)lzw.getCompressionSize() / (double)lzw.getOriginalFileSize();
		System.out.println("Compression ratio = " + compressionRatio);
		
		
		// end timer and print elapsed time as last line of output
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");
	}

}
