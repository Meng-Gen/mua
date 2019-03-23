package com.menggen.mua.ast;

import java.util.List;

public class FunctionDefinition extends Statement {
  private Expression name;
  private List<Name> parameters;
  private Block body;

  public FunctionDefinition(Expression name, List<Name> parameters,
      Block body) {
    this.name = name;
    this.parameters = parameters;
    this.body = body;
  }
}