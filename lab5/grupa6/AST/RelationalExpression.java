package AST;

import java.io.*;

public class RelationalExpression extends Expression {
    private Expression left;
    private Expression right;
    private String op;

    public RelationalExpression(Expression l, String operator, Expression r) {
        this.left = l;
        this.op = operator;
        this.right = r;
    }

    @Override
    public void translate(BufferedWriter out) throws IOException {
        left.translate(out);
        right.translate(out);
        left.genLoad("R1", out);
        right.genLoad("R2", out);

        // mapiranje operatora na asemblerske instrukcije 
        String opCode = "";
        if (op.equals("==")) opCode = "Compare_Equal";
        else if (op.equals("<=")) {
            opCode = "Compare_Less_Equal"; 
        }
        else if (op.equals(">=")) opCode = "Compare_Greater_Equal";

        out.write("\t" + opCode + "\t\tR1, R2");
        out.newLine();
        this.result = ASTNode.genVar();
        out.write("\tStore\t\tR1, " + this.result);
        out.newLine();
    }
}