package org.sudu.experiments.parser.javascript.parser;

import org.antlr.v4.runtime.*;
import org.sudu.experiments.parser.common.base.BaseFullParser;
import org.sudu.experiments.parser.common.SplitRules;
import org.sudu.experiments.parser.common.tree.IntervalNode;
import org.sudu.experiments.parser.javascript.JsSplitRules;
import org.sudu.experiments.parser.javascript.gen.LightJavaScriptLexer;
import org.sudu.experiments.parser.javascript.parser.highlighting.JavaScriptLexerHighlighting;
import org.sudu.experiments.parser.javascript.parser.highlighting.LightJavaScriptLexerHighlighting;

import java.util.Arrays;

public class JavaScriptFirstLinesLexer extends BaseFullParser {

  public int[] parse(String source, int numOfStrings) {
    long parsingStartTime = System.currentTimeMillis();
    initLexer(prepareString(source, numOfStrings));

    highlightTokens();

    var result = getInts(null);
    System.out.println("Lexing viewport js time " + (System.currentTimeMillis() - parsingStartTime) + "ms");
    return result;
  }

  private String prepareString(String source, int numOfStrings) {
    String[] lines = source.split("\n", -1);
    if (lines.length < numOfStrings) return source;
    else return String.join("\n", Arrays.copyOf(lines, numOfStrings));
  }

  @Override
  protected void highlightTokens() {
    LightJavaScriptLexerHighlighting.highlightTokens(allTokens, tokenTypes);
  }

  @Override
  protected Lexer initLexer(CharStream stream) {
    return new LightJavaScriptLexer(stream);
  }

  @Override
  protected Parser initParser() {
    return null;
  }

  @Override
  protected SplitRules initSplitRules() {
    return new JsSplitRules();
  }

  @Override
  protected boolean tokenFilter(Token token) {
    int type = token.getType();
    return type != LightJavaScriptLexer.LineTerminator
        && type != LightJavaScriptLexer.EOF;
  }

  @Override
  protected ParserRuleContext getStartRule(Parser parser) {
    return null;
  }

  @Override
  protected IntervalNode walk(ParserRuleContext startRule) {
    return null;
  }

  public static boolean isComment(int tokenType) {
    return JavaScriptLexerHighlighting.isComment(tokenType);
  }

}
