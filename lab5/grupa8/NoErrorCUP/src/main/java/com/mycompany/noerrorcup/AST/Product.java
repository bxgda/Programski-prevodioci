package com.mycompany.noerrorcup.AST;

public class Product extends BinaryExpression {
	
	public Product( Expression left, Expression right )
	{
                super(left, right, "Mul");
	}
	
	protected String opCode()
	{
		return "Mul";
	}
}
