package com.menggen.mua;

import java.util.ArrayList;
import java.util.List;

// https://github.com/oxyc/luaparse/blob/master/luaparse.js

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

  // chunk ::= block
  private void parseChunk() {
    parseBlock();
  }

  // block ::= {stat} [retstat]
  private void parseBlock() {
    
    while (!isBlockFollow()) {
      if (currToken == Token.RETURN_TOKEN) {
        //block.push(parseStatement());
        break;
      }
    }
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

  private boolean isBlockFollow() {
    return false;
    /*
        if (EOF === token.type) return true;
    if (Keyword !== token.type) return false;
    switch (token.value) {
      case 'else': case 'elseif':
      case 'end': case 'until':
        return true;
      default:
        return false;
    }
    */
  }

  private void next() {
    pos++;
    if (pos < tokens.size()) {
      currToken = tokens.get(pos);
    } else {
      currToken = Token.INVALID_TOKEN;
    }
  }

  private boolean consume(Token token) {
    if (currToken == token) {
      next();
      return true;
    }
    return false;
  }

  private void expect(Token token) {
    if (currToken == token) {
      next();
    } else {
      // TODO: throw an exception
    }
  }
}