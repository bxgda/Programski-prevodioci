package AST;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Program je root čvor AST-a.
 * Sadrži listu deklaracija funkcija i glavni blok sa izjavama.
 */
public class Program extends ASTNode {
    private List<FunctionDeclaration> functions;
    private List<VariableDeclaration> variables;
    private List<Statement> statements;
    
    public Program() {
        this.functions = new ArrayList<>();
        this.variables = new ArrayList<>();
        this.statements = new ArrayList<>();
    }
    
    public void addFunction(FunctionDeclaration func) {
        functions.add(func);
    }
    
    public void addVariable(VariableDeclaration var) {
        variables.add(var);
    }
    
    public void addStatement(Statement stmt) {
        statements.add(stmt);
    }
    
    @Override
    public void translate(BufferedWriter out) throws IOException {
        // Prvo generiši skok preko deklaracija funkcija
        String mainLabel = genLab();
        out.write("\tJump\t\t" + mainLabel);
        out.newLine();
        
        // Prevedi sve deklaracije funkcija
        for (FunctionDeclaration func : functions) {
            func.translate(out);
        }
        
        // Označi početak glavnog programa
        out.write(mainLabel + ":");
        out.newLine();
        
        // Prevedi sve varijable (samo deklaracije, bez inicijalizacije u medukodu)
        // Varijable se memorijski mapiraju, ali se inicijalizacija radi u Assignment
        
        // Prevedi sve izjave
        for (Statement stmt : statements) {
            stmt.translate(out);
        }
    }
}
