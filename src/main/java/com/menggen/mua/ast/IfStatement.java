package com.menggen.mua.ast;

import java.util.List;

public class IfStatement extends Statement {
  private List<Clause> clauses;

  public IfStatement(List<Clause> clauses) {
    this.clauses = clauses;
  }
}