package detectors;

import java.util.List;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Using the visitors design pattern, this class detects useless control flow in java program code.
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public class UselessControlFlowDetector extends VoidVisitorAdapter <List<String>> {
	
	/**
	 * Overridden visit method that detects useless control flow in IF, WHILE, DO...WHILE, FOR loops and SWITCH cases
	 */
	@Override
	public void visit(MethodDeclaration md, List<String> collector) {
		super.visit(md, collector);
		
		
		md.findAll(Statement.class).forEach(statement ->{
			
			/**
			 * Detects useless control flow in IF statements including if-else if-else statements.
			 * We first assume that the statement of type IfStmt() is an if or else if.
			 * We then get the else statement from the ifStmt; if this statement is NOT an else-if we find out if 
			 *    we have a case of an else statement with useless code.
			 */
			if(statement.isIfStmt()) {
				
				int begin, end;
				
				// We assume that we are dealing with the IF or ELSE IF part of the statement
				begin = statement.asIfStmt().getThenStmt().getRange().get().begin.line;
				end = statement.asIfStmt().getThenStmt().getRange().get().end.line;
				
				String stmtThen = statement.asIfStmt().clone().getThenStmt().toString();
				int indexThen = stmtThen.indexOf('{');
				int indexThenEnd = stmtThen.indexOf('}');
				
				// Checks if we have a useless IF or ELSE IF and if so, adds it to the collector list
				if (stmtThen.substring(indexThen+1, indexThenEnd).isBlank()) {
					collector.add(md.getNameAsString() + "," + begin + "," + end);
				}
				
				// This checks that the current statement is an ELSE and not ELSE IF
				if(!(statement.asIfStmt().getElseStmt().get().isIfStmt())) {
					begin = statement.asIfStmt().getElseStmt().get().getRange().get().begin.line;
					end = statement.asIfStmt().getElseStmt().get().getRange().get().end.line;
					String stmtElse = statement.asIfStmt().clone().getElseStmt().toString();
					int indexElse = stmtElse.indexOf("{");
					int indexElseEnd = stmtElse.indexOf("}");
					
					// Checks if we have a useless ELSE and if so, adds it to the collector list
					if (stmtElse.substring(indexElse+1, indexElseEnd).isBlank()) {
						collector.add(md.getNameAsString() + "," + begin + "," + end);
					}
				}
			/**
			 * Trivial else if that detects if there is code inside the for, while and do...while loops
			 */
			}else if(statement.isForStmt() || statement.isWhileStmt() || statement.isDoStmt()) {
				
				int begin = statement.getRange().get().begin.line;
				int end = statement.getRange().get().end.line;
				
				// String with spaces and new lines replaced 
				String loopStmt = statement.clone().toString().replaceAll(" ", "");
				// Index containing { (beginning of code block)
				int indexStart = loopStmt.indexOf("{");
				// Index containing } (end of code block)
				int indexEnd = loopStmt.indexOf("}");
				// If the substring is empty (no code in between) then we have a useless control flow
				if (loopStmt.substring(indexStart+1, indexEnd).isBlank()){
					collector.add(md.getNameAsString() + "," + begin + "," + end);
				}
			/**
			 * Finds out if INDIVIDUAL switch cases are useless, including the default part of the switch	
			 */
			}else if(statement.isSwitchStmt()) {
				int begin, end;
				
				NodeList<SwitchEntry> statementNodeList = statement.asSwitchStmt().getEntries();
				
				for(int i=0; i<statementNodeList.size();i++){
					// Returns true is the entry contains useless code (i.e.: comments or is empty)
					boolean isEntryEmpty = statement.asSwitchStmt().getEntry(i).isEmpty();
					
					if (isEntryEmpty) {
						begin = statement.asSwitchStmt().getEntry(i).getRange().get().begin.line;
						
						/** IF true, then we have reached the last entry (default or last case) 
						 *           so get the endline of the WHOLE switch minus 1
						 *  ELSE we get the startline of the next entry 
						 *           minus 1 to get the last line belonging to the previous entry
						 **/
						if(statementNodeList.size() == i+1) end = statement.asSwitchStmt().getRange().get().end.line -1;
						else end = statement.asSwitchStmt().getEntry(i+1).getRange().get().begin.line -1;
						
						collector.add(md.getNameAsString() + "," + begin + "," + end);
					}
				}
				
			}
		});	
	}
}
