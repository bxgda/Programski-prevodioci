package com.mycompany.noerrorcup.AST;

public class RelExpression extends BinaryExpression {

    public RelExpression(Expression left, Expression right, String opCode) {
        super(left, right, opCode);
    }
    
}