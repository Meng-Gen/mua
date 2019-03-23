package com.menggen.mua.ast;

public class MemberExpression extends Expression {
  private Expression base;
  private Name name;

  public MemberExpression(Expression base, Name name) {
    this.base = base;
    this.name = name;
  }
}