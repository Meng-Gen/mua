package com.menggen.mua.ast;

public class StringType extends Expression {
  private String value;

  public StringType(String value) {
    this.value = value;
  }

  @Override
  public boolean isStringType() {
    return true;
  }

  public String getValue() {
    return value;
  }
}