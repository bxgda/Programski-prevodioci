package com.mycompany.noerrorcup.AST;

import java.io.BufferedWriter;
import java.io.IOException;

public class BlockNode extends Statement {
    private StatementList statements;

    public BlockNode(StatementList statements) {
        this.statements = statements;
    }

    @Override
    public void translate(BufferedWriter out) throws IOException {
        if (statements != null) {
            statements.translate(out);
        }
    }
}