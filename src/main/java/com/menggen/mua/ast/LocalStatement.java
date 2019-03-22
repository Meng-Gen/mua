package com.menggen.mua.ast;

public class LocalStatement extends Statement {
  private Name name;
  private Expression expression;

  public LocalStatement(Name name) {
    this(name, new NilType());
  }

  public LocalStatement(Name name, Expression expression) {
    this.name = name;
    this.expression = expression;
  }
}