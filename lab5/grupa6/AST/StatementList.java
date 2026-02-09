package AST;

import java.io.*;
import java.util.ArrayList;

public class StatementList extends ASTNode {
    private ArrayList<Statement> statements = new ArrayList<Statement>();

    public void addStatement(Statement s) {
        statements.add(s);
    }

    @Override
    public void translate(BufferedWriter out) throws IOException {
        for (Statement s : statements) {
            s.translate(out);
        }
    }
}