package com.menggen.mua;

import com.menggen.mua.ast.BooleanType;
import com.menggen.mua.ast.BreakStatement;
import com.menggen.mua.ast.DecimalNumberType;
import com.menggen.mua.ast.Expression;
import com.menggen.mua.ast.FloatingNumberType;
import com.menggen.mua.ast.LocalStatement;
import com.menggen.mua.ast.Name;
import com.menggen.mua.ast.NilType;
import com.menggen.mua.ast.ReturnStatement;
import com.menggen.mua.ast.Statement;
import com.menggen.mua.ast.StringType;
import com.menggen.mua.ast.TableType;

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
  public static final Token TK_ASSIGN = new Token(TokenType.SYMBOL, "=");
  public static final Token TK_CONCAT = new Token(TokenType.SYMBOL, "..");
  public static final Token TK_EQUAL = new Token(TokenType.SYMBOL, "==");
  public static final Token TK_EXP = new Token(TokenType.SYMBOL, "^");
  public static final Token TK_FLOAT_DIV = new Token(TokenType.SYMBOL, "/");
  public static final Token TK_GREATER = new Token(TokenType.SYMBOL, ">");
  public static final Token TK_GREATEREQUAL = new Token(TokenType.SYMBOL, ">=");
  public static final Token TK_INEQUAL = new Token(TokenType.SYMBOL, "~=");
  public static final Token TK_LEFTBRACE = new Token(TokenType.SYMBOL, "{");
  public static final Token TK_LENGTH = new Token(TokenType.SYMBOL, "#");
  public static final Token TK_LESS = new Token(TokenType.SYMBOL, "<");
  public static final Token TK_LESSEQUAL = new Token(TokenType.SYMBOL, "<=");
  public static final Token TK_MOD = new Token(TokenType.SYMBOL, "%");
  public static final Token TK_MUL = new Token(TokenType.SYMBOL, "*");
  public static final Token TK_RIGHTBRACE = new Token(TokenType.SYMBOL, "}");
  public static final Token TK_SEMICOLON = new Token(TokenType.SYMBOL, ";");
  public static final Token TK_SUB = new Token(TokenType.SYMBOL, "-");
  public static final Token TK_UNARYMINUS = new Token(TokenType.SYMBOL, "-");
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
    while (!isBlockFollow(currToken)) {
      if (currToken.equals(TK_RETURN)) {
        parseReturnStatement();
        break;
      }
      parseStatement();
      consume(TK_SEMICOLON);
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
  private Statement parseStatement() {
    System.out.println("parseStatement()");

    if (currToken.equals(TK_LOCAL)) {
      return parseLocalStatement();
    } else if (currToken.equals(TK_IF)) {
      return parseIfStatement();
    } else if (currToken.equals(TK_FUNCTION)) {

    } else if (currToken.equals(TK_WHILE)) {
      return parseWhileStatement();
    } else if (currToken.equals(TK_FOR)) {
      return parseForStatement();
    } else if (currToken.equals(TK_REPEAT)) {
      return parseRepeatStatement();
    } else if (currToken.equals(TK_BREAK)) {
      return parseBreakStatement();
    } else if (currToken.equals(TK_DO)) {
      return parseDoStatement();
    }
/*
    if (Keyword === token.type) {
      switch (token.value) {
        case 'local':    next(); return parseLocalStatement();
        case 'if':       next(); return parseIfStatement();
        case 'function': next();
          var name = parseFunctionName();
          return parseFunctionDeclaration(name);
        case 'while':    next(); return parseWhileStatement();
        case 'for':      next(); return parseForStatement();
        case 'repeat':   next(); return parseRepeatStatement();
        case 'break':    next(); return parseBreakStatement();
        case 'do':       next(); return parseDoStatement();
      }
    }


    if (Punctuator === token.type) {
      if (consume('::')) return parseLabelStatement();
    }
    // Assignments memorizes the location and pushes it manually for wrapper
    // nodes. Additionally empty `;` statements should not mark a location.
    if (trackLocations) locations.pop();

    // When a `;` is encounted, simply eat it without storing it.
    if (features.emptyStatement) {
      if (consume(';')) return;
    }

    return parseAssignmentOrCallStatement();

*/
    return null;
  }

  // local ::= local Name [‘=’ exp]
  private Statement parseLocalStatement() {
    System.out.println("parseLocalStatement()");

    expect(TK_LOCAL);
    Name name = parseName();
    if (consume(TK_ASSIGN)) {
      Expression expression = parseExpression();
      return new LocalStatement(name, expression);
    } else {
      return new LocalStatement(name);
    }
  }

  // if ::= if exp then block {elseif exp then block} [else block] end
  private Statement parseIfStatement() {
    System.out.println("parseIfStatement()");
    expect(TK_IF);
    return null;
  }

  // while ::= while exp do block end
  private Statement parseWhileStatement() {
    System.out.println("parseWhileStatement()");
    expect(TK_WHILE);
    return null;
  }

  // for ::= for Name ‘=’ exp ‘,’ exp [‘,’ exp] do block end |
  //     for Name in exp do block end |
  private Statement parseForStatement() {
    System.out.println("parseForStatement()");
    expect(TK_FOR);
    return null;
  }

  // repeat ::= repeat block until exp
  private Statement parseRepeatStatement() {
    System.out.println("parseRepeatStatement()");
    expect(TK_REPEAT);
    return null;
  }

  // break ::= break
  private Statement parseBreakStatement() {
    System.out.println("parseBreakStatement()");
    expect(TK_BREAK);
    return new BreakStatement();
  }

  // do ::= do block end
  private Statement parseDoStatement() {
    System.out.println("parseDoStatement()");
    expect(TK_DO);
    return null;
  }

  // retstat ::= return [exp] [‘;’]
  private Statement parseReturnStatement() {
    System.out.println("parseReturnStatement()");

    expect(TK_RETURN);
    Expression expression = parseExpression();
    consume(TK_SEMICOLON);
    return new ReturnStatement(expression);
  }

  // exp ::= Number | String | nil | false | true | ‘{’ ‘}’ | prefixexp |
  //     exp binop exp | unop exp
  private Expression parseExpression() {
    System.out.println("parseExpression()");

    Expression expression = parseSubExpression(0);
    return expression;
  }

  // TODO: Incompleted
  // exp ::= (unop exp | primary | prefixexp ) { binop exp }
  private Expression parseSubExpression(int minPrecedence) {
    System.out.println("parseSubExpression()");

    Expression expression = null;
    if (isUnaryToken(currToken)) {
      System.out.println("isUnaryToken() is true");
      next();
      expression = parseSubExpression(10);
    }

    if (expression == null) {
      System.out.println("expression == null");
      expression = parsePrimaryExpression();

      if (expression == null) {
        System.out.println("expression == null again!!!");
        expression = parsePrefixExpression();
      }
    }

    if (expression == null) {
      return null;
    }

    while (true) {
      int precedence = getBinaryPrecedence(currToken);
      //System.out.println(currToken);
      //System.out.println("getBinaryPrecedence() = " + precedence);

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

    return expression;
  }

  // primary ::= Number | String | nil | false | true | ‘{’ ‘}’
  private Expression parsePrimaryExpression() {
    System.out.println("parsePrimaryExpression()");


    TokenType type = currToken.getType();
    String value = currToken.getValue();

    if (type.equals(TokenType.DECIMAL_NUMBER)) {
      //System.out.println("TokenType.DECIMAL_NUMBER");

      next();
      return new DecimalNumberType(value, 10);
    } else if (type.equals(TokenType.DECIMAL_HEX_NUMBER)) {
      next();
      return new DecimalNumberType(value.substring(2), 16);
    } else if (type.equals(TokenType.STRING)) {
      next();
      return new StringType(value);
    } else if (currToken.equals(TK_NIL)) {
      next();
      return new NilType();
    } else if (currToken.equals(TK_FALSE)) {
      next();
      return new BooleanType(false);
    } else if (currToken.equals(TK_TRUE)) {
      next();
      return new BooleanType(true);
    } else if (consume(TK_LEFTBRACE)) {
      expect(TK_RIGHTBRACE);
      return new TableType();
    }
    return null;
  }

  // TODO: Incompleted
  // prefixexp ::= var | functioncall | ‘(’ exp ‘)’
  private Expression parsePrefixExpression() {
    return null;
  }

  private Name parseName() {
    TokenType type = currToken.getType();
    String value = currToken.getValue();
    if (type.equals(TokenType.NAME)) {
      next();
      return new Name(value);
    } else {
      // TODO: throw an exception
    }
    return null;
  }

  private boolean isBlockFollow(Token token) {
    return token.equals(TK_INVALID) || token.equals(TK_ELSE) ||
        token.equals(TK_ELSEIF) || token.equals(TK_END) ||
        token.equals(TK_UNTIL);
  }

  // unop ::= ‘-’ | not | ‘#’
  private boolean isUnaryToken(Token token) {
    return token.equals(TK_UNARYMINUS) || token.equals(TK_NOT) ||
        token.equals(TK_LENGTH);
  }

  private int getBinaryPrecedence(Token token) {
    if (token.equals(TK_OR)) {
      return 1;
    } else if (token.equals(TK_AND)) {
      return 2;
    } else if (token.equals(TK_EQUAL) || token.equals(TK_INEQUAL) ||
        token.equals(TK_LESS) || token.equals(TK_GREATER) ||
        token.equals(TK_LESSEQUAL) || token.equals(TK_GREATEREQUAL)) {
      return 3;
    } else if (token.equals(TK_CONCAT)) {
      return 8;
    } else if (token.equals(TK_ADD) || token.equals(TK_SUB)) {
      return 9;
    } else if (token.equals(TK_MUL) || token.equals(TK_FLOAT_DIV) ||
        token.equals(TK_MOD)) {
      return 10;
    } else if (token.equals(TK_EXP)) {
      return 12;
    } else {
      return 0;
    }
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

  // TODO: Incompleted
  private void expect(Token token) {
    if (currToken.equals(token)) {
      next();
    } else {
      // TODO: throw an exception
    }
  }
}