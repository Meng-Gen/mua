package com.menggen.mua.ast;

public class AssignmentStatement extends Statement {
  private Expression variable;
  private Expression init;

  public AssignmentStatement(Expression variable, Expression init) {
    this.variable = variable;
    this.init = init;
  }
}