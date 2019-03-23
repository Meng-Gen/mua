package com.menggen.mua.ast;

public class BinaryExpression extends Expression {
  private String operator;
  private Expression left;
  private Expression right;

  public BinaryExpression(String operator, Expression left, Expression right) {
    this.operator = operator;
    this.left = left;
    this.right = right;
  }
}