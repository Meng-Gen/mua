package com.menggen.mua;

import junit.framework.TestCase;

import java.util.List;

public class ScannerTest extends TestCase {
  public void testScanProgram() {
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

    String[] expectedStrings = new String[] {
      "[NAME] print",
      "[SYMBOL] (",
      "[STRING] \"Hello\"",
      "[SYMBOL] ..",
      "[STRING] \" \"",
      "[SYMBOL] ..",
      "[STRING] \"--\\\"World\\\"--\"",
      "[SYMBOL] )",
      "[EOL]",
      "[NAME] x",
      "[SYMBOL] =",
      "[SYMBOL] -",
      "[NUMBER] 3",
      "[SYMBOL] +",
      "[NUMBER] 4",
      "[EOL]",
      "[NAME] this_1s_a_variable",
      "[SYMBOL] =",
      "[NUMBER] 0xabcF",
      "[SYMBOL] -",
      "[NUMBER] .012e-56",
      "[SYMBOL] +",
      "[NUMBER] 7.e8",
      "[SYMBOL] +",
      "[NUMBER] 9e+10",
      "[COMMENT] 8",
      "[EOL]",
      "[RESERVED] if",
      "[NUMBER] 1",
      "[RESERVED] then",
      "[EOL]",
      "[NAME] y",
      "[SYMBOL] =",
      "[NAME] x",
      "[EOL]",
      "[RESERVED] end",
      "[EOL]",
      "[NAME] print",
      "[SYMBOL] (",
      "[NAME] y",
      "[SYMBOL] )",
      "[EOL]",
    };

    assertEquals(expectedStrings.length, tokens.size());
    for (int i = 0; i < 42; i++) {
      assertEquals(expectedStrings[i], tokens.get(i).toString());
    }
  }
}