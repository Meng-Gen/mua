package com.menggen.mua;

public class Token {
  private TokenType type;
  private String value;

  public static final Token INVALID_TOKEN = new Token(TokenType.INVALID);
  public static final Token EOL_TOKEN = new Token(TokenType.EOL);
  public static final Token NIL_TOKEN = new Token(TokenType.RESERVED, "nil");
  public static final Token RETURN_TOKEN = new Token(TokenType.RESERVED, "return");
  public static final Token BREAK_TOKEN = new Token(TokenType.RESERVED, "break");
  public static final Token SEMICOLON_TOKEN = new Token(TokenType.SYMBOL, ";");

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

  public boolean equals(Token other) {
    return type.equals(other.type) && value.equals(other.value);
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