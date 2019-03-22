package com.menggen.mua.ast;

public class ForNumericStatement extends Statement {
  private Name variable;
  private Expression start;
  private Expression end;
  private Expression step;
  private Block body;

  public ForNumericStatement(Name variable, Expression start, Expression end,
      Expression step, Block body) {
    this.variable = variable;
    this.start = start;
    this.end = end;
    this.step = step;
    this.body = body;
  }
}