package AST;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FunctionCall predstavlja poziv funkcije.
 * Primer: f(5) 
 * 
 * Prevod:
 * 1) Prevedi sve argumente i upiši ih u memoriju parametara
 * 2) Jump na label funkcije
 * 3) Return label generiše se nakon skoka (u AssignmentStatement ili drugom kontekstu)
 */
public class FunctionCall extends Expression {
    private String functionName;
    private List<Expression> arguments;
    
    public FunctionCall(String functionName, List<Expression> arguments) {
        this.functionName = functionName;
        this.arguments = arguments != null ? arguments : new ArrayList<>();
    }
    
    public String getFunctionName() {
        return functionName;
    }
    
    public List<Expression> getArguments() {
        return arguments;
    }
    
    @Override
    public void translate(BufferedWriter out) throws IOException {
        // 1) Prevedi sve argumente i upiši ih u memoriju parametara
        for (int i = 0; i < arguments.size(); i++) {
            Expression arg = arguments.get(i);
            arg.translate(out);
            arg.genLoad("R1", out);
            
            // Upiši u memoriju parametra param_<funcname>_<i>
            String paramMemName = "param_" + functionName + "_" + i;
            out.write("\tStore\t\tR1, " + paramMemName);
            out.newLine();
        }
        
        // 2) Jump na label funkcije
        out.write("\tJump\t\t" + functionName);
        out.newLine();
        
        // 3) Rezultat je memorijska lokacija povratne vrednosti
        String retMemName = "ret_" + functionName;
        this.result = retMemName;  // Rezultat je memorijska lokacija
    }
}
