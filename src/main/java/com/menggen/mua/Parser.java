package com.menggen.mua;

import java.util.ArrayList;
import java.util.List;

// https://github.com/oxyc/luaparse/blob/master/luaparse.js

public class Parser {
  // TokenType.INVALID 
  public static final Token TK_INVALID = new Token(TokenType.INVALID);
  // TokenType.RESERVED
  public static final Token TK_AND = new Token(TokenType.RESERVED, "and");
  public static final Token TK_BREAK = new Token(TokenType.RESERVED, "break");
  public static final Token TK_DO = new Token(TokenType.RESERVED, "do");
  public static final Token TK_ELSE = new Token(TokenType.RESERVED, "else");
  public static final Token TK_ELSEIF = new Token(TokenType.RESERVED, "elseif");
  public static final Token TK_END = new Token(TokenType.RESERVED, "end");
  public static final Token TK_FALSE = new Token(TokenType.RESERVED, "false");
  public static final Token TK_FOR = new Token(TokenType.RESERVED, "for");
  public static final Token TK_FUNCTION = new Token(TokenType.RESERVED, "function");
  public static final Token TK_IF = new Token(TokenType.RESERVED, "if");
  public static final Token TK_IN = new Token(TokenType.RESERVED, "in");
  public static final Token TK_LOCAL = new Token(TokenType.RESERVED, "local");
  public static final Token TK_NIL = new Token(TokenType.RESERVED, "nil");
  public static final Token TK_NOT = new Token(TokenType.RESERVED, "not");
  public static final Token TK_OR = new Token(TokenType.RESERVED, "or");
  public static final Token TK_REPEAT = new Token(TokenType.RESERVED, "repeat");
  public static final Token TK_RETURN = new Token(TokenType.RESERVED, "return");
  public static final Token TK_THEN = new Token(TokenType.RESERVED, "then");
  public static final Token TK_TRUE = new Token(TokenType.RESERVED, "true");
  public static final Token TK_UNTIL = new Token(TokenType.RESERVED, "until");
  public static final Token TK_WHILE = new Token(TokenType.RESERVED, "while");
  // TokenType.SYMBOL
  public static final Token TK_ADD = new Token(TokenType.SYMBOL, "+");
  public static final Token TK_LEFT_BRACE = new Token(TokenType.SYMBOL, "{");
  public static final Token TK_MINUS = new Token(TokenType.SYMBOL, "-");
  public static final Token TK_RIGHT_BRACE = new Token(TokenType.SYMBOL, "}");
  public static final Token TK_SEMICOLON = new Token(TokenType.SYMBOL, ";");
  public static final Token TK_SHARP = new Token(TokenType.SYMBOL, "#");
  // TokenType.EOL
  public static final Token TK_EOL = new Token(TokenType.EOL);

  private List<Token> tokens;
  private int pos;
  private Token currToken;

  public Parser(Scanner scanner) {
    scanner.scan();
    this.tokens = scanner.getTokens();
    this.pos = -1;
    this.currToken = TK_INVALID;
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
      if (currToken.equals(TK_RETURN)) {
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

    expect(TK_RETURN);
    parseExpression();
    consume(TK_SEMICOLON);
  }

  // exp ::= Number | String | nil | false | true | ‘{’ ‘}’ | prefixexp |
  //     exp binop exp | unop exp
  private void parseExpression() {
    System.out.println("parseExpression()");
    parseSubExpression(0);
  }

  // exp ::= (unop exp | primary | prefixexp ) { binop exp }
  private void parseSubExpression(int minPrecedence) {
    System.out.println("parseSubExpression()");
    if (isUnaryToken(currToken)) {
      System.out.println("isUnaryToken() is true");
      next();
      parseSubExpression(10);
    }
    parsePrimaryExpression();

    while (true) {
      int precedence = getBinaryPrecedence(currToken);
      System.out.println(currToken);
      System.out.println("getBinaryPrecedence() = " + precedence);

      if (precedence == 0 || precedence <= minPrecedence) {
        break;
      }
      // Right-hand precedence operators
      String operator = currToken.getValue();
      if (operator.equals("^") || operator.equals("..")) {
        precedence--;
      }
      next();
      parseSubExpression(precedence);
      //if (null == right) raiseUnexpectedToken('<expression>', token);
      //expression = finishNode(ast.binaryExpression(operator, expression, right));
    }
  }

  // primary ::= Number | String | nil | false | true | ‘{’ ‘}’
  private void parsePrimaryExpression() {
    System.out.println("parsePrimaryExpression()");
    if (currToken.getType().equals(TokenType.DECIMAL_NUMBER)) {
      System.out.println("TokenType.DECIMAL_NUMBER");
      System.out.println(currToken);
      next();
      System.out.println("TODO: return ast DECIMAL_NUMBER node");
      // return AST DECIMAL_NUMBER node
      next();
    } else if (currToken.getType().equals(TokenType.STRING)) {
      System.out.println("TokenType.STRING");
      System.out.println(currToken);
      next();
      System.out.println("TODO: return ast STRING node");
      // return AST STRING node
      next();
    } else if (currToken.equals(TK_NIL)) {
      System.out.println("TK_NIL");
      System.out.println(currToken);
      next();
      System.out.println("TODO: return ast NIL node");
      // return AST NIL node
      next();
    } else if (currToken.equals(TK_FALSE)) {
      System.out.println("TK_FALSE");
      System.out.println(currToken);
      next();
      System.out.println("TODO: return ast FALSE node");
      // return AST FALSE node
    } else if (currToken.equals(TK_TRUE)) {
      System.out.println("TK_TRUE");
      System.out.println(currToken);
      next();
      System.out.println("TODO: return ast TRUE node");
      // return AST TRUE node
    } else if (consume(TK_LEFT_BRACE)) {
      System.out.println("TK_LEFT_BRACE");
      expect(TK_RIGHT_BRACE);
      System.out.println("TODO: return ast {} node");
      // return AST {} node
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

  // unop ::= ‘-’ | not | ‘#’
  private boolean isUnaryToken(Token token) {
    return token.equals(TK_MINUS) || token.equals(TK_NOT) || token.equals(TK_SHARP);
  }

  private int getBinaryPrecedence(Token token) {
    if (token.equals(TK_ADD) || token.equals(TK_MINUS)) {
      return 9;
    }
    return 0;
  }

  private void next() {
    pos++;
    if (pos < tokens.size()) {
      currToken = tokens.get(pos);
    } else {
      currToken = TK_INVALID;
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