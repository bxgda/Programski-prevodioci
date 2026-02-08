package com.mycompany.noerrorcup.AST;


import java.io.BufferedWriter;
import java.io.IOException;

public class CaseNode extends ASTNode {
    private Expression condition;
    private Statement statement;

    public CaseNode(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public void translate(BufferedWriter out) throws IOException {
        String endLabel = ASTNode.genLab();
        
        condition.translate(out);
        condition.genLoad("R1", out);
        out.write("\tJumpIfZero\tR1, " + endLabel);
        out.newLine();
        
        statement.translate(out);
        
        out.write(endLabel + ":");
        out.newLine();
    }
}