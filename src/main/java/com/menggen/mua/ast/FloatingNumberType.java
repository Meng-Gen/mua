package com.menggen.mua.ast;

import java.math.BigDecimal;

public class FloatingNumberType extends Expression {
  private BigDecimal value;

  public FloatingNumberType(String value) {
    this.value = new BigDecimal(value);
  }

  @Override
  public boolean isFloatingNumberType() {
    return true;
  }

  public BigDecimal getValue() {
    return value;
  }
}