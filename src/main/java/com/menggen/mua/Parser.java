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
    next();
    parseBlock();
  }

  // block ::= {stat} [retstat]
  private void parseBlock() {
    System.out.println("parseBlock()");
    while (!isBlockFollow()) {
      if (currToken.equals(Token.RETURN_TOKEN)) {
        //block.push(parseStatement());
        parseReturnStatement();
        break;
      }
      break;
    }
  }

  // stat ::= ‘;’ |
  //     var ‘=’ exp |
  //     functioncall |
  //     break |
  //     do block end |
  //     while exp do block end |
  //     repeat block until exp |
  //     if exp then block {elseif exp then block} [else block] end |
  //     for Name ‘=’ exp ‘,’ exp [‘,’ exp] do block end |
  //     for Name in exp do block end |
  //     function funcname funcbody |
  //     local Name [‘=’ exp]
  private void parseStatement() {
    System.out.println("parseStatement()");
  }

  // retstat ::= return [exp] [‘;’]
  private void parseReturnStatement() {
    System.out.println("parseReturnStatement()");

    expect(Token.RETURN_TOKEN);
    parseExpression();
    consume(Token.SEMICOLON_TOKEN);
  }

  // exp ::= Number | String | nil | false | true | ‘{’ ‘}’ | prefixexp |
  //     exp binop exp | unop exp
  private void parseExpression() {
    System.out.println("parseExpression()");
    parseSubExpression(0);
  }

  private void parseSubExpression(int minPrecedence) {
    System.out.println("parseSubExpression()");
    if (isUnaryToken(currToken)) {

    }
    parsePrimaryExpression();
  }

  private void parsePrimaryExpression() {
    System.out.println("parsePrimaryExpression()");
    if (currToken.getType().equals(TokenType.DECIMAL_NUMBER)) {
      System.out.println(currToken);
      System.out.println("TODO: return ast node");
      // return AST node
      next();
    } else if (currToken.equals(Token.NIL_TOKEN)) {
      System.out.println(currToken);
      System.out.println("TODO: return ast node");
      // return AST node
      next();
    }
    /*
    var literals = StringLiteral | NumericLiteral | BooleanLiteral | NilLiteral | VarargLiteral
      , value = token.value
      , type = token.type
      , marker;

    if (trackLocations) marker = createLocationMarker();

    if (type & literals) {
      pushLocation(marker);
      var raw = input.slice(token.range[0], token.range[1]);
      next();
      return finishNode(ast.literal(type, value, raw));
    } else if (Keyword === type && 'function' === value) {
      pushLocation(marker);
      next();
      if (options.scope) createScope();
      return parseFunctionDeclaration(null);
    } else if (consume('{')) {
      pushLocation(marker);
      return parseTableConstructor();
    }
    */
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

  private boolean isUnaryToken(Token token) {
    return false;
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
    if (currToken.equals(token)) {
      next();
      return true;
    }
    return false;
  }

  private void expect(Token token) {
    if (currToken.equals(token)) {
      next();
    } else {
      // TODO: throw an exception
    }
  }
}