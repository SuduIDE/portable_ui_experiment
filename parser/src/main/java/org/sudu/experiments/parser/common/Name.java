package org.sudu.experiments.parser.common;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Objects;

public class Name implements Comparable<Name> {

  public String name;
  public int position;

  public Name(String name, int position) {
    this.name = name;
    this.position = position;
  }

  public static Name fromNode(TerminalNode node) {
    return new Name(node.getText(), node.getSymbol().getStartIndex());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Name name = (Name) o;
    return position == name.position && Objects.equals(this.name, name.name);
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, position);
  }

  @Override
  public int compareTo(Name o) {
    return Integer.compare(position, o.position);
  }
}
