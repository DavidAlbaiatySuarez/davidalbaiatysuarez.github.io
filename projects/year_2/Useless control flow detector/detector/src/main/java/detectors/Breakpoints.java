package detectors;

/**
 * Container class called Breakpoints.java to collect the observed behaviour.
 * 
 * @author David Al Baiaty Suarez
 * 
 * Following code subject to copyright - use is allowed if I am fully referenced in your work.
 *
 */
public class Breakpoints {
	/** name of the class of type String **/
	private String className;
	/** name of the method of type String **/
	private String methodName;
	/** start of the useless code of type int **/
	private String startline;
	/** end of the useless code of type int **/
	private String endline;
	
	
	public Breakpoints(String className, String methodName, String startline, String endline) {
		this.className = className;
		this.methodName = methodName;
		this.startline = startline;
		this.endline = endline;
	}
	
	@Override
	public String toString() {
		return className + ", " + methodName + ", " + startline + ", " + endline;
	}

}
