package com.menggen.mua.ast;

abstract public class Expression {
  public Expression() {
  }

  public boolean isNilType() {
    return false;
  }

  public boolean isBooleanType() {
    return false;
  }

  public boolean isDecimalNumberType() {
    return false;
  }

  public boolean isFloatingNumberType() {
    return false;
  }

  public boolean isStringType() {
    return false;
  }

  public boolean isTableType() {
    return false;
  }
}