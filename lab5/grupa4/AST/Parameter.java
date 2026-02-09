package AST;

import java.io.*;

/**
 * Parameter predstavlja parametar funkcije.
 * Primer: x:int ili x:int=5
 */
public class Parameter extends ASTNode {
    private String name;
    private String type;  // "int", "float", "char"
    private String defaultValue;  // null ako nema default vrednosti
    
    public Parameter(String name, String type) {
        this(name, type, null);
    }
    
    public Parameter(String name, String type, String defaultValue) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public String getDefaultValue() {
        return defaultValue;
    }
    
    public boolean hasDefault() {
        return defaultValue != null;
    }
    
    @Override
    public void translate(BufferedWriter out) throws IOException {
        // Parametri se ne prevode direktno
    }
}
