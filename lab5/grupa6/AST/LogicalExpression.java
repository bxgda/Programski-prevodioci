package AST;

import java.io.*;

public class LogicalExpression extends Expression {
    private Expression left;
    private Expression right;
    private String op; // "AND" ili "OR"

    public LogicalExpression(Expression l, String op, Expression r) {
        this.left = l;
        this.op = op;
        this.right = r;
    }

    @Override
    public void translate(BufferedWriter out) throws IOException {
        left.translate(out);
        right.translate(out);
        left.genLoad("R1", out);
        right.genLoad("R2", out);
        
        String opCode = op.equalsIgnoreCase("and") ? "And" : "Or";
        
        out.write("\t" + opCode + "\t\tR1, R2");
        out.newLine();
        this.result = ASTNode.genVar();
        out.write("\tStore\t\tR1, " + this.result);
        out.newLine();
    }
}