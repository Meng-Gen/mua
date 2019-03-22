package com.menggen.mua.ast;

public class WhileStatement extends Statement {
  private Expression condition;
  private Block body;

  public WhileStatement(Expression condition, Block body) {
    this.condition = condition;
    this.body = body;
  }
}