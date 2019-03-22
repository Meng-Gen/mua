package com.menggen.mua.ast;

public class IfClause extends Clause {
  public IfClause(Expression condition, Block body) {
    super(condition, body);
  }
}