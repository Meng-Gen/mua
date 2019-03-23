package com.menggen.mua.ast;

public class UnaryExpression extends Expression {
  private String operator;
  private Expression argument;

  public UnaryExpression(String operator, Expression argument) {
    this.operator = operator;
    this.argument = argument;
  }
}