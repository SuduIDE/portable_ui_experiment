// Generated from parser-generator/src/main/resources/grammar/activity/ActivityParser.g4 by ANTLR 4.12.0
package org.sudu.experiments.parser.activity.gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ActivityParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, OR=2, XOR=3, NOT=4, EQ=5, COMMA=6, SEMI=7, LPAREN=8, RPAREN=9, 
		LCURLY=10, RCURLY=11, CONS=12, ACTIVITY=13, SELECT=14, REPEAT=15, SCHEDULE=16, 
		RANDOM=17, IF=18, ELSE=19, INT=20, ID=21, WS=22, JAVADOC=23, COMMENT=24, 
		LINE_COMMENT=25, NEW_LINE=26, ERROR=27;
	public static final int
		RULE_activity = 0, RULE_blocksemi = 1, RULE_block = 2, RULE_stat = 3, 
		RULE_expr = 4, RULE_exprcomma = 5, RULE_exprcons = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"activity", "blocksemi", "block", "stat", "expr", "exprcomma", "exprcons"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'and'", "'or'", "'xor'", "'!'", "'='", "','", "';'", "'('", "')'", 
			"'{'", "'}'", "'->'", "'activity'", "'select'", "'repeat'", "'schedule'", 
			"'random'", "'if'", "'else'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AND", "OR", "XOR", "NOT", "EQ", "COMMA", "SEMI", "LPAREN", "RPAREN", 
			"LCURLY", "RCURLY", "CONS", "ACTIVITY", "SELECT", "REPEAT", "SCHEDULE", 
			"RANDOM", "IF", "ELSE", "INT", "ID", "WS", "JAVADOC", "COMMENT", "LINE_COMMENT", 
			"NEW_LINE", "ERROR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ActivityParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ActivityParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActivityContext extends ParserRuleContext {
		public TerminalNode ACTIVITY() { return getToken(ActivityParser.ACTIVITY, 0); }
		public BlocksemiContext blocksemi() {
			return getRuleContext(BlocksemiContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ActivityParser.EOF, 0); }
		public ActivityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_activity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).enterActivity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).exitActivity(this);
		}
	}

	public final ActivityContext activity() throws RecognitionException {
		ActivityContext _localctx = new ActivityContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_activity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			match(ACTIVITY);
			setState(15);
			blocksemi();
			setState(16);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlocksemiContext extends ParserRuleContext {
		public TerminalNode LCURLY() { return getToken(ActivityParser.LCURLY, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode RCURLY() { return getToken(ActivityParser.RCURLY, 0); }
		public List<TerminalNode> SEMI() { return getTokens(ActivityParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(ActivityParser.SEMI, i);
		}
		public BlocksemiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blocksemi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).enterBlocksemi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).exitBlocksemi(this);
		}
	}

	public final BlocksemiContext blocksemi() throws RecognitionException {
		BlocksemiContext _localctx = new BlocksemiContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_blocksemi);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			match(LCURLY);
			setState(19);
			stat();
			setState(24);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(20);
					match(SEMI);
					setState(21);
					stat();
					}
					} 
				}
				setState(26);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI) {
				{
				{
				setState(27);
				match(SEMI);
				}
				}
				setState(32);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(33);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LCURLY() { return getToken(ActivityParser.LCURLY, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode RCURLY() { return getToken(ActivityParser.RCURLY, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ActivityParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ActivityParser.COMMA, i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(LCURLY);
			setState(36);
			stat();
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(37);
				match(COMMA);
				setState(38);
				stat();
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(44);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ActivityParser.ID, 0); }
		public TerminalNode REPEAT() { return getToken(ActivityParser.REPEAT, 0); }
		public TerminalNode LPAREN() { return getToken(ActivityParser.LPAREN, 0); }
		public TerminalNode INT() { return getToken(ActivityParser.INT, 0); }
		public TerminalNode RPAREN() { return getToken(ActivityParser.RPAREN, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode SELECT() { return getToken(ActivityParser.SELECT, 0); }
		public TerminalNode SCHEDULE() { return getToken(ActivityParser.SCHEDULE, 0); }
		public TerminalNode RANDOM() { return getToken(ActivityParser.RANDOM, 0); }
		public TerminalNode IF() { return getToken(ActivityParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(ActivityParser.SEMI, 0); }
		public TerminalNode ELSE() { return getToken(ActivityParser.ELSE, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).exitStat(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stat);
		int _la;
		try {
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				match(ID);
				}
				break;
			case REPEAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				match(REPEAT);
				setState(48);
				match(LPAREN);
				setState(49);
				match(INT);
				setState(50);
				match(RPAREN);
				setState(51);
				block();
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 3);
				{
				setState(52);
				match(SELECT);
				setState(53);
				block();
				}
				break;
			case SCHEDULE:
				enterOuterAlt(_localctx, 4);
				{
				setState(54);
				match(SCHEDULE);
				setState(55);
				block();
				}
				break;
			case RANDOM:
				enterOuterAlt(_localctx, 5);
				{
				setState(56);
				match(RANDOM);
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(57);
					match(LPAREN);
					setState(58);
					match(INT);
					setState(59);
					match(RPAREN);
					}
				}

				setState(62);
				block();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 6);
				{
				setState(63);
				match(IF);
				setState(64);
				match(LPAREN);
				setState(65);
				expr(0);
				setState(66);
				match(RPAREN);
				setState(67);
				block();
				setState(71);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(68);
					match(SEMI);
					setState(69);
					match(ELSE);
					setState(70);
					block();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ActivityParser.ID, 0); }
		public TerminalNode LCURLY() { return getToken(ActivityParser.LCURLY, 0); }
		public ExprcommaContext exprcomma() {
			return getRuleContext(ExprcommaContext.class,0);
		}
		public TerminalNode RCURLY() { return getToken(ActivityParser.RCURLY, 0); }
		public TerminalNode LPAREN() { return getToken(ActivityParser.LPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(ActivityParser.RPAREN, 0); }
		public TerminalNode NOT() { return getToken(ActivityParser.NOT, 0); }
		public TerminalNode AND() { return getToken(ActivityParser.AND, 0); }
		public TerminalNode XOR() { return getToken(ActivityParser.XOR, 0); }
		public TerminalNode OR() { return getToken(ActivityParser.OR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(76);
				match(ID);
				}
				break;
			case LCURLY:
				{
				setState(77);
				match(LCURLY);
				setState(78);
				exprcomma();
				setState(79);
				match(RCURLY);
				}
				break;
			case LPAREN:
				{
				setState(81);
				match(LPAREN);
				setState(82);
				expr(0);
				setState(83);
				match(RPAREN);
				}
				break;
			case NOT:
				{
				setState(85);
				match(NOT);
				setState(86);
				expr(4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(100);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(98);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(89);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(90);
						match(AND);
						setState(91);
						expr(4);
						}
						break;

					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(92);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(93);
						match(XOR);
						setState(94);
						expr(3);
						}
						break;

					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(95);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(96);
						match(OR);
						setState(97);
						expr(2);
						}
						break;
					}
					} 
				}
				setState(102);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprcommaContext extends ParserRuleContext {
		public List<ExprconsContext> exprcons() {
			return getRuleContexts(ExprconsContext.class);
		}
		public ExprconsContext exprcons(int i) {
			return getRuleContext(ExprconsContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ActivityParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ActivityParser.COMMA, i);
		}
		public ExprcommaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprcomma; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).enterExprcomma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).exitExprcomma(this);
		}
	}

	public final ExprcommaContext exprcomma() throws RecognitionException {
		ExprcommaContext _localctx = new ExprcommaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_exprcomma);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			exprcons();
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(104);
				match(COMMA);
				setState(105);
				exprcons();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprconsContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(ActivityParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ActivityParser.ID, i);
		}
		public List<TerminalNode> CONS() { return getTokens(ActivityParser.CONS); }
		public TerminalNode CONS(int i) {
			return getToken(ActivityParser.CONS, i);
		}
		public ExprconsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprcons; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).enterExprcons(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ActivityParserListener ) ((ActivityParserListener)listener).exitExprcons(this);
		}
	}

	public final ExprconsContext exprcons() throws RecognitionException {
		ExprconsContext _localctx = new ExprconsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_exprcons);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(ID);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CONS) {
				{
				{
				setState(112);
				match(CONS);
				setState(113);
				match(ID);
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);

		case 1:
			return precpred(_ctx, 2);

		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001bx\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005"+
		"\u0001\u0017\b\u0001\n\u0001\f\u0001\u001a\t\u0001\u0001\u0001\u0005\u0001"+
		"\u001d\b\u0001\n\u0001\f\u0001 \t\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002(\b\u0002\n\u0002"+
		"\f\u0002+\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003=\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003H\b"+
		"\u0003\u0003\u0003J\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004X\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0005\u0004c\b\u0004\n\u0004\f\u0004f\t\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005k\b\u0005\n\u0005\f\u0005n\t"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006s\b\u0006\n\u0006"+
		"\f\u0006v\t\u0006\u0001\u0006\u0000\u0001\b\u0007\u0000\u0002\u0004\u0006"+
		"\b\n\f\u0000\u0000\u0082\u0000\u000e\u0001\u0000\u0000\u0000\u0002\u0012"+
		"\u0001\u0000\u0000\u0000\u0004#\u0001\u0000\u0000\u0000\u0006I\u0001\u0000"+
		"\u0000\u0000\bW\u0001\u0000\u0000\u0000\ng\u0001\u0000\u0000\u0000\fo"+
		"\u0001\u0000\u0000\u0000\u000e\u000f\u0005\r\u0000\u0000\u000f\u0010\u0003"+
		"\u0002\u0001\u0000\u0010\u0011\u0005\u0000\u0000\u0001\u0011\u0001\u0001"+
		"\u0000\u0000\u0000\u0012\u0013\u0005\n\u0000\u0000\u0013\u0018\u0003\u0006"+
		"\u0003\u0000\u0014\u0015\u0005\u0007\u0000\u0000\u0015\u0017\u0003\u0006"+
		"\u0003\u0000\u0016\u0014\u0001\u0000\u0000\u0000\u0017\u001a\u0001\u0000"+
		"\u0000\u0000\u0018\u0016\u0001\u0000\u0000\u0000\u0018\u0019\u0001\u0000"+
		"\u0000\u0000\u0019\u001e\u0001\u0000\u0000\u0000\u001a\u0018\u0001\u0000"+
		"\u0000\u0000\u001b\u001d\u0005\u0007\u0000\u0000\u001c\u001b\u0001\u0000"+
		"\u0000\u0000\u001d \u0001\u0000\u0000\u0000\u001e\u001c\u0001\u0000\u0000"+
		"\u0000\u001e\u001f\u0001\u0000\u0000\u0000\u001f!\u0001\u0000\u0000\u0000"+
		" \u001e\u0001\u0000\u0000\u0000!\"\u0005\u000b\u0000\u0000\"\u0003\u0001"+
		"\u0000\u0000\u0000#$\u0005\n\u0000\u0000$)\u0003\u0006\u0003\u0000%&\u0005"+
		"\u0006\u0000\u0000&(\u0003\u0006\u0003\u0000\'%\u0001\u0000\u0000\u0000"+
		"(+\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000"+
		"\u0000*,\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000,-\u0005\u000b"+
		"\u0000\u0000-\u0005\u0001\u0000\u0000\u0000.J\u0005\u0015\u0000\u0000"+
		"/0\u0005\u000f\u0000\u000001\u0005\b\u0000\u000012\u0005\u0014\u0000\u0000"+
		"23\u0005\t\u0000\u00003J\u0003\u0004\u0002\u000045\u0005\u000e\u0000\u0000"+
		"5J\u0003\u0004\u0002\u000067\u0005\u0010\u0000\u00007J\u0003\u0004\u0002"+
		"\u00008<\u0005\u0011\u0000\u00009:\u0005\b\u0000\u0000:;\u0005\u0014\u0000"+
		"\u0000;=\u0005\t\u0000\u0000<9\u0001\u0000\u0000\u0000<=\u0001\u0000\u0000"+
		"\u0000=>\u0001\u0000\u0000\u0000>J\u0003\u0004\u0002\u0000?@\u0005\u0012"+
		"\u0000\u0000@A\u0005\b\u0000\u0000AB\u0003\b\u0004\u0000BC\u0005\t\u0000"+
		"\u0000CG\u0003\u0004\u0002\u0000DE\u0005\u0007\u0000\u0000EF\u0005\u0013"+
		"\u0000\u0000FH\u0003\u0004\u0002\u0000GD\u0001\u0000\u0000\u0000GH\u0001"+
		"\u0000\u0000\u0000HJ\u0001\u0000\u0000\u0000I.\u0001\u0000\u0000\u0000"+
		"I/\u0001\u0000\u0000\u0000I4\u0001\u0000\u0000\u0000I6\u0001\u0000\u0000"+
		"\u0000I8\u0001\u0000\u0000\u0000I?\u0001\u0000\u0000\u0000J\u0007\u0001"+
		"\u0000\u0000\u0000KL\u0006\u0004\uffff\uffff\u0000LX\u0005\u0015\u0000"+
		"\u0000MN\u0005\n\u0000\u0000NO\u0003\n\u0005\u0000OP\u0005\u000b\u0000"+
		"\u0000PX\u0001\u0000\u0000\u0000QR\u0005\b\u0000\u0000RS\u0003\b\u0004"+
		"\u0000ST\u0005\t\u0000\u0000TX\u0001\u0000\u0000\u0000UV\u0005\u0004\u0000"+
		"\u0000VX\u0003\b\u0004\u0004WK\u0001\u0000\u0000\u0000WM\u0001\u0000\u0000"+
		"\u0000WQ\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000Xd\u0001\u0000"+
		"\u0000\u0000YZ\n\u0003\u0000\u0000Z[\u0005\u0001\u0000\u0000[c\u0003\b"+
		"\u0004\u0004\\]\n\u0002\u0000\u0000]^\u0005\u0003\u0000\u0000^c\u0003"+
		"\b\u0004\u0003_`\n\u0001\u0000\u0000`a\u0005\u0002\u0000\u0000ac\u0003"+
		"\b\u0004\u0002bY\u0001\u0000\u0000\u0000b\\\u0001\u0000\u0000\u0000b_"+
		"\u0001\u0000\u0000\u0000cf\u0001\u0000\u0000\u0000db\u0001\u0000\u0000"+
		"\u0000de\u0001\u0000\u0000\u0000e\t\u0001\u0000\u0000\u0000fd\u0001\u0000"+
		"\u0000\u0000gl\u0003\f\u0006\u0000hi\u0005\u0006\u0000\u0000ik\u0003\f"+
		"\u0006\u0000jh\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000\u0000lj\u0001"+
		"\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000m\u000b\u0001\u0000\u0000"+
		"\u0000nl\u0001\u0000\u0000\u0000ot\u0005\u0015\u0000\u0000pq\u0005\f\u0000"+
		"\u0000qs\u0005\u0015\u0000\u0000rp\u0001\u0000\u0000\u0000sv\u0001\u0000"+
		"\u0000\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000u\r\u0001"+
		"\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000\u000b\u0018\u001e)<GIWbd"+
		"lt";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}