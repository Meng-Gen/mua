package com.menggen.mua;

import java.util.List;

// https://github.com/pqkallio/MiniPascalTranslator/blob/master/Compiler/src/services/analyzers/Parser.cs
// https://github.com/cojeung/X-parser/blob/master/Parser.java
// https://github.com/awcurtin/c-minus-compiler/blob/master/Parser.cc

public class Parser {
  private List<Token> tokens;
  private int pos;
  private Token currToken;

  public Parser(Scanner scanner) {
    scanner.scan();
    this.tokens = scanner.getTokens();
    this.pos = -1;
    this.currToken = Token.INVALID_TOKEN;
  }

  public void parse() {
    parseChunk();
  }

  private void parseChunk() {
    parseBlock();
  }

  private void parseBlock() {

  }

  private void parseStatement() {

  }

  private void parseExpression() {

  }

  private void parseSimpleExpression() {

  }

  private void parseNumber() {

  }

  private void parseString() {

  }

  private void advance() {
    pos++;
    if (pos < tokens.size()) {
      currToken = tokens.get(pos);
    } else {
      currToken = Token.INVALID_TOKEN;
    }
  }
}