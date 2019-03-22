package com.menggen.mua.ast;

import java.util.List;

public class Block {
  private List<Statement> block;

  public Block(List<Statement> block) {
    this.block = block;
  }
}