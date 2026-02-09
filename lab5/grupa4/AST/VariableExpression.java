package AST;
import java.io.*;

public class VariableExpression extends Expression {
	private String varName;
	
	// Konstruktor za novu verziju (Grupa 4)
	public VariableExpression(String name) {
		this.varName = name;
	}
	
	public String getVarName() {
		return varName;
	}
	
	@Override
	public void translate(BufferedWriter out) throws IOException {
		if (varName != null) {
			// Prvo proveri da li je ovo parametar funkcije
			String paramMemLocation = FunctionDeclaration.getParameterMemLocation(varName);
			if (paramMemLocation != null) {
				this.result = paramMemLocation;
			} else {
				// Inače je obična varijabla
				this.result = varName;
			}
		}
	}
}
