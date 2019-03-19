package com.menggen.mua;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Scanner {
  private static final Set<String> ReservedWords;

  static {
    String[] reservedWords = new String[] {
      "and", "break", "do", "else", "elseif",
      "end", "false", "for", "function", "if",
      "in", "local", "nil", "not", "or",
      "repeat", "return", "then", "true", "until", "while",
    };
    ReservedWords = new HashSet<String>(Arrays.asList(reservedWords));
  }

  private Source source;
  private StringBuilder buffer;
  private List<Token> tokens;

  public Scanner(Source source) {
    this.source = source;
    this.buffer = new StringBuilder();
    this.tokens = new ArrayList<Token>();
  }

  public List<Token> getTokens() {
    return tokens;
  }

  public void scan() {
    do {
      advance();
      switch (c0()) {
        case '\n':
          tokens.add(new Token(TokenType.EOL));
          break;
        case '+':
          tokens.add(new Token(TokenType.SYMBOL, "+"));
          break;
        case '-':
          // - --
          advance();
          if (c0() == '-') {
            scanComment();
          } else {
            tokens.add(new Token(TokenType.SYMBOL, "-"));
            pushBack();
          }
          break;
        case '*':
          tokens.add(new Token(TokenType.SYMBOL, "*"));
          break;
        case '/':
          tokens.add(new Token(TokenType.SYMBOL, "/"));
          break;
        case '%':
          tokens.add(new Token(TokenType.SYMBOL, "%"));
          break;
        case '^':
          tokens.add(new Token(TokenType.SYMBOL, "^"));
          break;
        case '#':
          tokens.add(new Token(TokenType.SYMBOL, "#"));
          break;
        case '=':
          // = ==
          advance();
          if (c0() == '=') {
            tokens.add(new Token(TokenType.SYMBOL, "=="));
          } else {
            tokens.add(new Token(TokenType.SYMBOL, "="));
            pushBack();
          }
          break;
        case '~':
          // ~=
          advance();
          if (c0() == '=') {
            tokens.add(new Token(TokenType.SYMBOL, "~="));
          } else {
            // It should not be here.
            pushBack();
          }
          break;
        case '<':
          // < <=
          advance();
          if (c0() == '=') {
            tokens.add(new Token(TokenType.SYMBOL, "<="));
          } else {
            tokens.add(new Token(TokenType.SYMBOL, "<"));
            pushBack();
          }
          break;
        case '>':
          // > >=
          advance();
          if (c0() == '=') {
            tokens.add(new Token(TokenType.SYMBOL, ">="));
          } else {
            tokens.add(new Token(TokenType.SYMBOL, ">"));
            pushBack();
          }
          break;
        case '(':
          tokens.add(new Token(TokenType.SYMBOL, "("));
          break;
        case ')':
          tokens.add(new Token(TokenType.SYMBOL, ")"));
          break;
        case '{':
          tokens.add(new Token(TokenType.SYMBOL, "{"));
          break;
        case '}':
          tokens.add(new Token(TokenType.SYMBOL, "}"));
          break;
        case '[':
          tokens.add(new Token(TokenType.SYMBOL, "["));
          break;
        case ']':
          tokens.add(new Token(TokenType.SYMBOL, "]"));
          break;
        case ';':
          tokens.add(new Token(TokenType.SYMBOL, ";"));
          break;
        case ':':
          tokens.add(new Token(TokenType.SYMBOL, ":"));
          break;
        case ',':
          tokens.add(new Token(TokenType.SYMBOL, ","));
          break;
        case '.':
          // . .. ... number
          advance();
          if (c0() == '.') {
            advance();
            if (c0() == '.') {
                tokens.add(new Token(TokenType.SYMBOL, "..."));
            } else {
              pushBack();
              tokens.add(new Token(TokenType.SYMBOL, ".."));
            }
          } else if (isDecimalDigit(c0())) {
            pushBack();
            scanFloatingNumber();
          } else {
            pushBack();
            tokens.add(new Token(TokenType.SYMBOL, "."));
          }
          break;
        case '0':
          advance();
          // hexadecimal number
          if (asciiAlphaToLower(c0()) == 'x') {
            scanHexNumber();
          } else {
            pushBack();
            scanNumber();
          }
          break;
        case '\"':
          scanString();
          break;
        default:
          // number
          if (isDecimalDigit(c0())) {
            scanNumber();
          // name or reserved word
          } else if (isAsciiAlpha(c0())) {
            scanWord();
          } else {
            // It should not be here.
          }
          break;
      }
    } while (c0() != Source.INVALID_CHAR);
  }

  private char c0() {
    return source.c0();
  }

  public void advance() {
    source.advance();
  }

  public void pushBack() {
    source.pushBack();
  }

  private int asciiAlphaToLower(char c) {
    return c | 0x20;
  }

  private boolean isInRange(int value, int lowerLimit, int higherLimit) {
    return value >= lowerLimit && value <= higherLimit;
  }

  private boolean isDecimalDigit(char c) {
    return isInRange(c, '0', '9');
  }

  private boolean isAsciiAlpha(char c) {
    return isInRange(asciiAlphaToLower(c), 'a', 'z');
  }

  private boolean isHexDigit(char c) {
    return isDecimalDigit(c) || isInRange(asciiAlphaToLower(c), 'a', 'f');
  }

  private void scanComment() {
    while (true) {
      advance();
      if (c0() == '\n' || c0() == Source.INVALID_CHAR) {
        pushBack();
        break;
      }
      buffer.append(c0());
    }
    tokens.add(new Token(TokenType.COMMENT, buffer.toString()));
    buffer.setLength(0);
  }

  private void scanFloatingNumber() {
    buffer.append(c0());
    boolean hasExponent = false;
    while (true) {
      advance();
      if (asciiAlphaToLower(c0()) == 'e') {
        hasExponent = true;
        scanFloatingNumberExponent();
        break;
      }
      if (!isDecimalDigit(c0())) {
        pushBack();
        break;
      }
      buffer.append(c0());
    }
    if (!hasExponent) {
      tokens.add(new Token(TokenType.FLOATING_NUMBER, buffer.toString()));
      buffer.setLength(0);
    }
  }

  private void scanFloatingNumberExponent() {
    buffer.append(c0());
    advance();
    if (c0() == '+' || c0() == '-') {
      buffer.append(c0());
    } else {
      pushBack();
    }
    scanFloatingNumberExponentPrefixed();
  }

  private void scanFloatingNumberExponentPrefixed() {
    while (true) {
      advance();
      if (!isDecimalDigit(c0())) {
        pushBack();
        break;
      }
      buffer.append(c0());
    }
    tokens.add(new Token(TokenType.FLOATING_NUMBER, buffer.toString())); 
    buffer.setLength(0);
  }

  private void scanNumber() {
    buffer.append(c0());
    boolean isDecimalNumber = true;
    while (true) {
      advance();
      if (isDecimalDigit(c0())) {
        buffer.append(c0());
      } else if (c0() == '.') {
        scanFloatingNumber();
        isDecimalNumber = false;
        break;
      } else if (asciiAlphaToLower(c0()) == 'e') {
        scanFloatingNumberExponent();
        isDecimalNumber = false;
        break;
      } else {
        pushBack();
        break;
      }
    }
    if (isDecimalNumber) {
      tokens.add(new Token(TokenType.DECIMAL_NUMBER, buffer.toString()));
      buffer.setLength(0);
    }
  }

  private void scanHexNumber() {
    buffer.append("0").append(c0());
    while (true) {
      advance();
      if (!isHexDigit(c0())) {
        pushBack();
        break;
      }
      buffer.append(c0());
    }
    tokens.add(new Token(TokenType.DECIMAL_HEX_NUMBER, buffer.toString()));
    buffer.setLength(0);
  }

  private void scanString() {
    buffer.append(c0());
    boolean isEnclosed = false;
    while (!isEnclosed) {
      advance();
      buffer.append(c0());
      switch (c0()) {
        case '\\':
          advance();
          // escaped characters
          if (c0() == '\"' || c0() == '\'' || c0() == '\\' || c0() == 'n') {
            buffer.append(c0());
          } else {
            pushBack();
          }
          break;
        case '\"':
          isEnclosed = true;
          break;
        default:
          break;
      }
    }
    tokens.add(new Token(TokenType.STRING, buffer.toString()));
    buffer.setLength(0);
  }

  private void scanWord() {
    buffer.append(c0());
    while (true) {
      advance();
      if (isAsciiAlpha(c0()) || isDecimalDigit(c0()) || c0() == '_') {
        buffer.append(c0());
      } else {
        pushBack();
        break;
      }
    }
    String word = buffer.toString();
    if (ReservedWords.contains(word)) {
      tokens.add(new Token(TokenType.RESERVED, word));
    } else {
      tokens.add(new Token(TokenType.NAME, word));
    }
    buffer.setLength(0);
  }
}