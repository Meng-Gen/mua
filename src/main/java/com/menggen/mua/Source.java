package com.menggen.mua;

public class Source {
  public static final char INVALID_CHAR = (char) 0;

  private String content;
  private int pos;
  private char c0;

  public Source(String content) {
    this.content = content;
    this.pos = -1;
    this.c0 = INVALID_CHAR;
  }

  public char c0() {
    return c0;
  }

  public void advance() {
    pos++;
    if (pos < content.length()) {
      c0 = content.charAt(pos);
    } else {
      c0 = INVALID_CHAR;
    }
  }

  public void pushBack() {
    pos--;
    if (pos >= 0) {
      c0 = content.charAt(pos);
    } else {
      c0 = INVALID_CHAR;
    }
  }
}