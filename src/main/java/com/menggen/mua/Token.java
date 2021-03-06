package com.menggen.mua;

public class Token {
  private TokenType type;
  private String value;

  public Token(TokenType type) {
    this(type, "");
  }

  public Token(TokenType type, String value) {
    this.type = type;
    this.value = value;
  }

  public TokenType getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("[").append(type).append("]");
    if (type != TokenType.EOL) {
      builder.append(" ").append(value);
    }
    return builder.toString();
  }
}