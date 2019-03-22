package com.menggen.mua.ast;

public class UnaryExpression extends Expression {
  private String operator;
  private Expression expression;

  public UnaryExpression(String operator, Expression expression) {
    this.operator = operator;
    this.expression = expression;
  }
}