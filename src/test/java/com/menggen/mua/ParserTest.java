package com.menggen.mua;

import junit.framework.TestCase;

public class ParserTest extends TestCase {
  public void testParseProgram() {
    String program = "return true + 5";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    Parser parser = new Parser(scanner);
    parser.parse();
  }
}