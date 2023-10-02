package org.sudu.experiments.parser.javascript.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.sudu.experiments.parser.Interval;
import org.sudu.experiments.parser.ParserConstants;
import org.sudu.experiments.parser.common.BaseIntervalParser;
import org.sudu.experiments.parser.common.IntervalNode;
import org.sudu.experiments.parser.common.SplitRules;
import org.sudu.experiments.parser.common.graph.type.Type;
import org.sudu.experiments.parser.javascript.JsSplitRules;
import org.sudu.experiments.parser.javascript.gen.JavaScriptLexer;
import org.sudu.experiments.parser.javascript.gen.JavaScriptParser;
import org.sudu.experiments.parser.javascript.parser.highlighting.JavaScriptLexerHighlighting;
import org.sudu.experiments.parser.javascript.walker.JsWalker;

import java.util.List;

public class JavaScriptIntervalParser extends BaseIntervalParser {

  @Override
  protected IntervalNode parseInterval(Interval interval, List<Type> types) {
    JavaScriptParser parser = new JavaScriptParser(tokenStream);
    ParserRuleContext ruleContext;

    ruleContext = switch (interval.intervalType) {
      case ParserConstants.IntervalTypes.Js.PROGRAM -> parser.programOrAny();
      default -> parser.unknownInterval();
    };
    ParseTreeWalker walker = new ParseTreeWalker();

    var classWalker = new JsWalker(tokenTypes, tokenStyles);
    walker.walk(classWalker, ruleContext);
    highlightTokens();

    if (interval.intervalType == ParserConstants.IntervalTypes.Java.COMP_UNIT) {
      var compUnitInterval = new Interval(0, fileSourceLength, ParserConstants.IntervalTypes.Java.COMP_UNIT);
      classWalker.intervals.add(0, compUnitInterval);
    }
    return defaultIntervalNode();
  }

  @Override
  protected Lexer initLexer(CharStream stream) {
    return new JavaScriptLexer(stream);
  }

  @Override
  protected SplitRules initSplitRules() {
    return new JsSplitRules();
  }

  @Override
  protected boolean tokenFilter(Token token) {
    int type = token.getType();
    return type != JavaScriptLexer.LineTerminator
        && type != JavaScriptLexer.EOF;
  }

  @Override
  protected void highlightTokens() {
    for (var token: allTokens) {
      int ind = token.getTokenIndex();
      if (isComment(token.getType())) tokenTypes[ind] = ParserConstants.TokenTypes.COMMENT;
      if (isErrorToken(token.getType())) tokenTypes[ind] = ParserConstants.TokenTypes.ERROR;
    }
  }

  public static boolean isComment(int tokenType) {
    return JavaScriptLexerHighlighting.isComment(tokenType);
  }

}
