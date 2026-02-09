package AST;

import java.io.*;
import java.util.ArrayList;

public class Block extends Statement {
    private ArrayList variables;
    private StatementList statementList;

    public Block(ArrayList variables, StatementList statementList) {
        this.variables = variables;
        this.statementList = statementList;
    }

    @Override
    public void translate(BufferedWriter out) throws IOException {
        if (statementList != null) {
            statementList.translate(out);
        }
    }
}