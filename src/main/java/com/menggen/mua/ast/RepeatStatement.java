package com.menggen.mua.ast;

public class RepeatStatement extends Statement {
  private Block body;
  private Expression condition;

  public RepeatStatement(Block body, Expression condition) {
    this.body = body;
    this.condition = condition;
  }
}