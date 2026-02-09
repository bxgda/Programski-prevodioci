package AST;

import java.io.*;

public class ConstantExpression extends Expression {
	private String value;
	
	// Konstruktor za novu verziju (Grupa 4)
	public ConstantExpression(String val) {
		this.value = val;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public void translate(BufferedWriter out) throws IOException {
		if (value != null) {
			this.result = value;
		}
	}
	
	@Override
	protected void genLoad(String reg, BufferedWriter out) throws IOException {
		out.write("\tLoad_Const\t" + reg + ", " + result);
		out.newLine();
	}
}
