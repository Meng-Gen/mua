package com.menggen.mua.ast;

public class BooleanType extends Expression {
  private boolean value;

  public BooleanType(boolean value) {
    this.value = value;
  }

  @Override
  public boolean isBooleanType() {
    return true;
  }

  public boolean getValue() {
    return value;
  }
}