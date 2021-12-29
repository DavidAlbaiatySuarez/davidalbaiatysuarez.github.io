package detectors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;

/**
 * Driver (main) class
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public class Driver {
	
	/** Contains the name of the class in the .java file*/
	private static String className;
	
	public static void main(String[] args) {
	
		String fileName = args[0];
		
		try {
			//CompilationUnit cu = JavaParser.parse(new FileInputStream("Calculator.java"));
			CompilationUnit cu = JavaParser.parse(new FileInputStream(fileName));
			VoidVisitor<List<String>> methodVisitor = new UselessControlFlowDetector();
			VoidVisitor<List<String>> recursionVisitor = new RecursionDetector();
			
			List<String> collector = new ArrayList<>();
			cu.findAll(ClassOrInterfaceDeclaration.class).forEach(c -> {
				className = c.getNameAsString();
				});
			
			// Gets all the methods inside the class (as well as the className for all the methods)
			methodVisitor.visit(cu, collector);
			
			System.out.println("Useless Control Flows:");
			collector.forEach(statement -> {
				String[] temp = statement.split(",");
				Breakpoints uselessControlFlow = new Breakpoints(className,
																temp[0],
																temp[1],
																temp[2]
																);
				System.out.println(uselessControlFlow);
			});
			
			System.out.println("---------------------------------");
			
			System.out.println("Recursion: ");
			List<String> collectorRecursion = new ArrayList<>();
			recursionVisitor.visit(cu, collectorRecursion );
			collectorRecursion.forEach(statement -> {
				String[] temp = statement.split(",");
				Breakpoints recursionDetector = new Breakpoints(className,
																temp[0],
																temp[1],
																temp[2]
																);
				System.out.println(recursionDetector);
			});
			
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
