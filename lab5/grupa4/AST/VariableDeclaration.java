package AST;

import java.io.*;

/**
 * VariableDeclaration predstavlja deklaraciju varijable.
 * Primer: a:int;
 */
public class VariableDeclaration extends ASTNode {
    private String name;
    private String type;  // "int", "float", "char"
    
    public VariableDeclaration(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    @Override
    public void translate(BufferedWriter out) throws IOException {
        // Deklaracije se ne prevode direktno, samo se mapiraju
        // Mapiranje se dešava u EnvManager
    }
}
