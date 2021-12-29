package detectors;

import java.util.List;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Class that detects polymorphic recursion in java program code.
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public class RecursionDetector extends VoidVisitorAdapter<List<String>>{
	
	/**
	 * Overridden visit method that detects polymorphic recursion - that is, detects method calls
	 *   with the same name as the MethodDeclaration
	 */
	@Override
	public void visit(MethodDeclaration md, List<String> collector) {
		super.visit(md,  collector);
		
		
		md.findAll(MethodCallExpr.class).forEach(statement ->{
			// If condition met, polymorphic recursion detected
			if(statement.getNameAsString().equals(md.getNameAsString())){
				// Get method name as string
				String methodName = md.getNameAsString();
				// Get start line of the recursion 
				int begin = statement.getRange().get().begin.line;
				// Get end line of the recursion 
				int end = statement.getRange().get().end.line;
				
				collector.add(methodName+","+begin+","+end);
			}
		});
	}
	
}
