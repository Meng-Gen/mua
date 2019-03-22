package com.menggen.mua.ast;

public class ElseifClause extends Clause {
  public ElseifClause(Expression condition, Block body) {
    super(condition, body);
  }
}