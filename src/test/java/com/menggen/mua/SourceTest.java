package com.menggen.mua;

import junit.framework.TestCase;

public class SourceTest extends TestCase {
  public void testSource() {
    String content = "May you do good and not evil.\n"
      + "May you find forgiveness for yourself and forgive others.\n"
      + "May you share freely, never taking more than you give.";
    Source source = new Source(content);
    assertEquals(Source.INVALID_CHAR, source.c0());
    source.advance();
    assertEquals('M', source.c0());
    source.advance();
    source.pushBack();
    assertEquals('M', source.c0());
    assertEquals('M', source.c0());
  }
}