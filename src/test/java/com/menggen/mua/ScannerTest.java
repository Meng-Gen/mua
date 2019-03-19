package com.menggen.mua;

import junit.framework.TestCase;

import java.util.List;

public class ScannerTest extends TestCase {
  public void testScan() {
    String program = "print(\"Hello\"..\" \"..\"--\\\"World\\\"--\")\n"
      + "x=-3+4\n"
      + "this_1s_a_variable = 0xabcF-.012e-56+7.e8+9e+10--8\n"
      + "if 1 then\n"
      + "  y=x\n"
      + "end\n"
      + "print (y)\n";
    Source source = new Source(program);
    Scanner scanner = new Scanner(source);
    scanner.scan();
    List<Token> tokens = scanner.getTokens();
    for (Token token : tokens) {
      System.out.println(token);
    }
  }
}