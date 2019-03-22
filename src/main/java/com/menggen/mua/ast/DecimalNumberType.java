package com.menggen.mua.ast;

import java.math.BigInteger;

public class DecimalNumberType extends Expression {
  private BigInteger value;

  public DecimalNumberType(String value, int radix) {
    this.value = new BigInteger(value, radix);
  }

  @Override
  public boolean isDecimalNumberType() {
    return true;
  }

  public BigInteger getValue() {
    return value;
  }
}