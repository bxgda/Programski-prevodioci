package com.mycompany.noerrorcup.AST;


public class AndExpression extends BinaryExpression {
    public AndExpression(Expression left, Expression right) {
        super(left, right, "And");
    }
}