package com.menggen.mua.ast;

public class IndexExpression extends Expression {
  private Expression base;
  private Expression expression;

  public IndexExpression(Expression base, Expression expression) {
    this.base = base;
    this.expression = expression;
  }
}