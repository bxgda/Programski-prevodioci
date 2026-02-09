package AST;

import java.io.BufferedWriter;
import java.io.IOException;

public class Assignment extends Statement {
	private String varName;
	private Expression right;
	
	public Assignment(String name, Expression e) {
		varName = name;
		right = e;
	}
	
	public void translate(BufferedWriter out) throws IOException {
		right.translate(out);
		right.genLoad("R1", out);
		out.write("\tStore\t\tR1, " + varName);
		out.newLine();
	}
}
