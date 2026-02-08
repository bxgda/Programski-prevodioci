package com.mycompany.noerrorcup.AST;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatementList extends ASTNode {
    private List<Statement> list;

    public StatementList(Statement s) {
        list = new ArrayList<>();
        list.add(s);
    }

    public void add(Statement s) {
        list.add(s);
    }
    
    @Override
    public void translate(BufferedWriter out) throws IOException {
        for (Statement s : list) {
            s.translate(out);
        }
    }
}