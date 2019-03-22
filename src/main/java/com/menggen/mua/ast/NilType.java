package com.menggen.mua.ast;

public class NilType extends Expression {
  public NilType() {
  }

  @Override
  public boolean isNilType() {
    return true;
  }
}