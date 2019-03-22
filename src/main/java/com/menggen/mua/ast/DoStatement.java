package com.menggen.mua.ast;

public class DoStatement extends Statement {
  private Block body;

  public DoStatement(Block body) {
    this.body = body;
  }
}