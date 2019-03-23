package com.menggen.mua;

import junit.framework.TestCase;

public class ParserTest extends TestCase {
  public void testParseTableConstructor() {
    String program = "a[1][2][3] = {}";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    Parser parser = new Parser(scanner);
    parser.parse();
  }

  /*
  public void testParseFunctionStatement() {
    String program = "function foo(a,b,c) x = a + b + c end";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    Parser parser = new Parser(scanner);
    parser.parse();
  }

  public void testParseUnaryExpression() {
    String program = "x = -1";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    Parser parser = new Parser(scanner);
    parser.parse();
  }

  public void testParseAssignment() {
    String program = "x = 1 + 3 * 5 + 2";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    Parser parser = new Parser(scanner);
    parser.parse();
  }

  public void testParseAssignment() {
    String program = "x = 10";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    Parser parser = new Parser(scanner);
    parser.parse();
  }

  public void testParseEmptyStatement() {
    String program = ";";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    Parser parser = new Parser(scanner);
    parser.parse();
  }

  public void testParseForGenericStatement() {
    String program = "for var_1 in 1 do local x = 1 end";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    Parser parser = new Parser(scanner);
    parser.parse();
  }

  public void testParseForNumericStatement() {
    String program = "for var_1 = 1, 10 do local x = 1 end";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    Parser parser = new Parser(scanner);
    parser.parse();
  }

  public void testParseRepeatStatement() {
    String program = "repeat local x = 3 until false";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    Parser parser = new Parser(scanner);
    parser.parse();
  }

  public void testParseIfStatement() {
    String program = "if true then local x = 3 elseif false then local z = 4 else local y = 5 end";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    Parser parser = new Parser(scanner);
    parser.parse();
  }

  public void testParseLocalStatement() {
    String program = "local x = 3";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    Parser parser = new Parser(scanner);
    parser.parse();
  }
  */
}