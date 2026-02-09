package AST;

import java.io.*;

/**
 * AssignmentStatement predstavlja dodelu vrednosti varijabli.
 * Primer: a = 5; ili a = f(3);
 */
public class AssignmentStatement extends Statement {
    private String varName;
    private Expression value;
    
    public AssignmentStatement(String varName, Expression value) {
        this.varName = varName;
        this.value = value;
    }
    
    @Override
    public void translate(BufferedWriter out) throws IOException {
        // Prevedi desnu stranu izraza
        value.translate(out);
        
        // Ako je FunctionCall, emituj return label nakon skoka
        if (value instanceof FunctionCall) {
            FunctionCall fc = (FunctionCall) value;
            String returnLabel = genLab();
            out.write(returnLabel + ":");
            out.newLine();
        }
        
        // Učitaj rezultat u registar R1
        value.genLoad("R1", out);
        
        // Upiši u memoriju varijable
        out.write("\tStore\t\tR1, " + varName);
        out.newLine();
    }
}
