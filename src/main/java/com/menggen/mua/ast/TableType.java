package com.menggen.mua.ast;

public class TableType extends Expression {
  public TableType() {
  }

  @Override
  public boolean isTableType() {
    return true;
  }
}