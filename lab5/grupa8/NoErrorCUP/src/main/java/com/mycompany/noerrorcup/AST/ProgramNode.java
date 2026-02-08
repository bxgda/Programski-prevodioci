package com.mycompany.noerrorcup.AST;

import java.io.BufferedWriter;
import java.io.IOException;

public class ProgramNode extends ASTNode {
    private BlockNode block;

    public ProgramNode(BlockNode block) {
        this.block = block;
    }

    @Override
    public void translate(BufferedWriter out) throws IOException {
        block.translate(out);
        out.write("HALT");
        out.newLine();
    }
}