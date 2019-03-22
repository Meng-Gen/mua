package com.menggen.mua.ast;

public class ReturnStatement extends Statement {
  private Expression expression;

  public ReturnStatement(Expression expression) {
    this.expression = expression;
  }
}