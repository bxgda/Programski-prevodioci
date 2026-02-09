package AST;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import SymbolTable.Type;

public class Declaration extends ASTNode {
    private ArrayList<String> names;
    private Type type;

    public Declaration(ArrayList<String> names, Type type) {
        this.names = names;
        this.type = type;
    }

    @Override
    public void translate(BufferedWriter out) throws IOException {
        // Deklaracije obicno ne generisu izvrsni medjukod niskog nivoa, 
        // vec sluze za popunjavanje tabele simbola tokom parsiranja.
    }
}