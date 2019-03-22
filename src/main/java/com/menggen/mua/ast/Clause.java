package com.menggen.mua.ast;

public class Clause {
  private Expression condition;
  private Block body;

  public Clause(Block body) {
    this.body = body;
  }

  public Clause(Expression condition, Block body) {
    this.condition = condition;
    this.body = body;
  }
}