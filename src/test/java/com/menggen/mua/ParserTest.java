package com.menggen.mua;

import junit.framework.TestCase;

public class ParserTest extends TestCase {
  public void testParseProgram() {
    String program = "print(\"Hello\"..\" \"..\"--\\\"World\\\"--\")\n"
      + "x=-3+4\n"
      + "this_1s_a_variable = 0xabcF-.012e-56+7.e8+9e+10--8\n"
      + "if 1 then\n"
      + "  y=x\n"
      + "end\n"
      + "print (y)\n";
  }
}