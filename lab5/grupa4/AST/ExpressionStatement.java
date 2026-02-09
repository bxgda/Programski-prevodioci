package AST;

import java.io.*;

/**
 * ExpressionStatement predstavlja izjavu koja je samo izraz.
 * Koristi se za pozive funkcija kao izjave.
 */
public class ExpressionStatement extends Statement {
    private Expression expr;
    
    public ExpressionStatement(Expression expr) {
        this.expr = expr;
    }
    
    @Override
    public void translate(BufferedWriter out) throws IOException {
        expr.translate(out);
    }
}
