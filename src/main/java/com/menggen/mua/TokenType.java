package com.menggen.mua;

public enum TokenType {
  INVALID("INVALID"),
  RESERVED("RESERVED"),
  DECIMAL_NUMBER("NUMBER"),
  DECIMAL_HEX_NUMBER("NUMBER"),
  FLOATING_NUMBER("NUMBER"),
  STRING("STRING"),
  SYMBOL("SYMBOL"),
  NAME("NAME"),
  EOL("EOL"),
  COMMENT("COMMENT");

  private final String name;

  private TokenType(String name) {
    this.name = name;
  }

  public String toString() {
    return name;
  }
}