package com.menggen.mua.ast;

public class ForGenericStatement extends Statement {
  private Name variable;
  private Expression iterator;
  private Block body;

  public ForGenericStatement(Name variable, Expression iterator, Block body) {
    this.variable = variable;
    this.iterator = iterator;
    this.body = body;
  }
}