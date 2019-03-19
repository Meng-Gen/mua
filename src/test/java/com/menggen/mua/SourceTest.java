package com.menggen.mua;

import junit.framework.TestCase;

public class SourceTest extends TestCase {
  public void testSource() {
    String content = "May you do good and not evil.\n";
    Source source = new Source(content);
    assertEquals(Source.INVALID_CHAR, source.c0());
    source.advance();
    assertEquals('M', source.c0());
    source.advance();
    source.pushBack();
    assertEquals('M', source.c0());
    assertEquals('M', source.c0());
    source.advance();
    assertEquals('a', source.c0());
    source.advance();
    assertEquals('y', source.c0());
    for (int i = 3; i < content.length(); i++) {
      source.advance();
      assertEquals(content.charAt(i), source.c0());
    }
  }
}