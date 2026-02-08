package com.mycompany.noerrorcup.AST;

public class OrExpression extends BinaryExpression {
    public OrExpression(Expression left, Expression right) {
        super(left, right, "Or");
    }
}