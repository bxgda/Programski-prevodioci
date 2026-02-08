package com.mycompany.noerrorcup.AST;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class SelectStatement extends Statement {
    private List<CaseNode> caseList;

    public SelectStatement(List<CaseNode> caseList) {
        this.caseList = caseList;
    }

    @Override
    public void translate(BufferedWriter out) throws IOException {
        for (CaseNode caseNode : caseList) {
            caseNode.translate(out);
        }
    }
}