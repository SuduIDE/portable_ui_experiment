// Generated from parser-generator\src\main\resources\grammar\activity\ActivityLexer.g4 by ANTLR 4.12.0
package org.sudu.experiments.parser.activity.gen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ActivityLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, OR=2, NOT=3, EQ=4, COMMA=5, SEMI=6, LPAREN=7, RPAREN=8, LCURLY=9, 
		RCURLY=10, CONS=11, ACTIVITY=12, SELECT=13, REPEAT=14, SCHEDULE=15, IF=16, 
		ELSE=17, INT=18, ID=19, WS=20, JAVADOC=21, COMMENT=22, LINE_COMMENT=23, 
		NEW_LINE=24, ERROR=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"AND", "OR", "NOT", "EQ", "COMMA", "SEMI", "LPAREN", "RPAREN", "LCURLY", 
			"RCURLY", "CONS", "ACTIVITY", "SELECT", "REPEAT", "SCHEDULE", "IF", "ELSE", 
			"INT", "ID", "WS", "JAVADOC", "COMMENT", "LINE_COMMENT", "NEW_LINE", 
			"ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'and'", "'or'", "'not'", "'='", "','", "';'", "'('", "')'", "'{'", 
			"'}'", "'->'", "'activity'", "'select'", "'repeat'", "'schedule'", "'if'", 
			"'else'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AND", "OR", "NOT", "EQ", "COMMA", "SEMI", "LPAREN", "RPAREN", 
			"LCURLY", "RCURLY", "CONS", "ACTIVITY", "SELECT", "REPEAT", "SCHEDULE", 
			"IF", "ELSE", "INT", "ID", "WS", "JAVADOC", "COMMENT", "LINE_COMMENT", 
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


	public ActivityLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ActivityLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0019\u00bd\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0004\u0011y\b\u0011"+
		"\u000b\u0011\f\u0011z\u0001\u0012\u0001\u0012\u0005\u0012\u007f\b\u0012"+
		"\n\u0012\f\u0012\u0082\t\u0012\u0001\u0013\u0004\u0013\u0085\b\u0013\u000b"+
		"\u0013\f\u0013\u0086\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u0090\b\u0014\n\u0014\f\u0014"+
		"\u0093\t\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u009e\b\u0015"+
		"\n\u0015\f\u0015\u00a1\t\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0005"+
		"\u0016\u00ac\b\u0016\n\u0016\f\u0016\u00af\t\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0003\u0017\u00b4\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0002\u0091"+
		"\u009f\u0000\u0019\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015"+
		"+\u0016-\u0017/\u00181\u0019\u0001\u0000\u0005\u0001\u000009\u0003\u0000"+
		"AZ__az\u0004\u000009AZ__az\u0003\u0000\t\t\f\f  \u0002\u0000\n\n\r\r\u00c3"+
		"\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00013\u0001\u0000"+
		"\u0000\u0000\u00037\u0001\u0000\u0000\u0000\u0005:\u0001\u0000\u0000\u0000"+
		"\u0007>\u0001\u0000\u0000\u0000\t@\u0001\u0000\u0000\u0000\u000bB\u0001"+
		"\u0000\u0000\u0000\rD\u0001\u0000\u0000\u0000\u000fF\u0001\u0000\u0000"+
		"\u0000\u0011H\u0001\u0000\u0000\u0000\u0013J\u0001\u0000\u0000\u0000\u0015"+
		"L\u0001\u0000\u0000\u0000\u0017O\u0001\u0000\u0000\u0000\u0019X\u0001"+
		"\u0000\u0000\u0000\u001b_\u0001\u0000\u0000\u0000\u001df\u0001\u0000\u0000"+
		"\u0000\u001fo\u0001\u0000\u0000\u0000!r\u0001\u0000\u0000\u0000#x\u0001"+
		"\u0000\u0000\u0000%|\u0001\u0000\u0000\u0000\'\u0084\u0001\u0000\u0000"+
		"\u0000)\u008a\u0001\u0000\u0000\u0000+\u0099\u0001\u0000\u0000\u0000-"+
		"\u00a7\u0001\u0000\u0000\u0000/\u00b3\u0001\u0000\u0000\u00001\u00b9\u0001"+
		"\u0000\u0000\u000034\u0005a\u0000\u000045\u0005n\u0000\u000056\u0005d"+
		"\u0000\u00006\u0002\u0001\u0000\u0000\u000078\u0005o\u0000\u000089\u0005"+
		"r\u0000\u00009\u0004\u0001\u0000\u0000\u0000:;\u0005n\u0000\u0000;<\u0005"+
		"o\u0000\u0000<=\u0005t\u0000\u0000=\u0006\u0001\u0000\u0000\u0000>?\u0005"+
		"=\u0000\u0000?\b\u0001\u0000\u0000\u0000@A\u0005,\u0000\u0000A\n\u0001"+
		"\u0000\u0000\u0000BC\u0005;\u0000\u0000C\f\u0001\u0000\u0000\u0000DE\u0005"+
		"(\u0000\u0000E\u000e\u0001\u0000\u0000\u0000FG\u0005)\u0000\u0000G\u0010"+
		"\u0001\u0000\u0000\u0000HI\u0005{\u0000\u0000I\u0012\u0001\u0000\u0000"+
		"\u0000JK\u0005}\u0000\u0000K\u0014\u0001\u0000\u0000\u0000LM\u0005-\u0000"+
		"\u0000MN\u0005>\u0000\u0000N\u0016\u0001\u0000\u0000\u0000OP\u0005a\u0000"+
		"\u0000PQ\u0005c\u0000\u0000QR\u0005t\u0000\u0000RS\u0005i\u0000\u0000"+
		"ST\u0005v\u0000\u0000TU\u0005i\u0000\u0000UV\u0005t\u0000\u0000VW\u0005"+
		"y\u0000\u0000W\u0018\u0001\u0000\u0000\u0000XY\u0005s\u0000\u0000YZ\u0005"+
		"e\u0000\u0000Z[\u0005l\u0000\u0000[\\\u0005e\u0000\u0000\\]\u0005c\u0000"+
		"\u0000]^\u0005t\u0000\u0000^\u001a\u0001\u0000\u0000\u0000_`\u0005r\u0000"+
		"\u0000`a\u0005e\u0000\u0000ab\u0005p\u0000\u0000bc\u0005e\u0000\u0000"+
		"cd\u0005a\u0000\u0000de\u0005t\u0000\u0000e\u001c\u0001\u0000\u0000\u0000"+
		"fg\u0005s\u0000\u0000gh\u0005c\u0000\u0000hi\u0005h\u0000\u0000ij\u0005"+
		"e\u0000\u0000jk\u0005d\u0000\u0000kl\u0005u\u0000\u0000lm\u0005l\u0000"+
		"\u0000mn\u0005e\u0000\u0000n\u001e\u0001\u0000\u0000\u0000op\u0005i\u0000"+
		"\u0000pq\u0005f\u0000\u0000q \u0001\u0000\u0000\u0000rs\u0005e\u0000\u0000"+
		"st\u0005l\u0000\u0000tu\u0005s\u0000\u0000uv\u0005e\u0000\u0000v\"\u0001"+
		"\u0000\u0000\u0000wy\u0007\u0000\u0000\u0000xw\u0001\u0000\u0000\u0000"+
		"yz\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000"+
		"\u0000{$\u0001\u0000\u0000\u0000|\u0080\u0007\u0001\u0000\u0000}\u007f"+
		"\u0007\u0002\u0000\u0000~}\u0001\u0000\u0000\u0000\u007f\u0082\u0001\u0000"+
		"\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000"+
		"\u0000\u0081&\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000"+
		"\u0083\u0085\u0007\u0003\u0000\u0000\u0084\u0083\u0001\u0000\u0000\u0000"+
		"\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000\u0000\u0000"+
		"\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000"+
		"\u0088\u0089\u0006\u0013\u0000\u0000\u0089(\u0001\u0000\u0000\u0000\u008a"+
		"\u008b\u0005/\u0000\u0000\u008b\u008c\u0005*\u0000\u0000\u008c\u008d\u0005"+
		"*\u0000\u0000\u008d\u0091\u0001\u0000\u0000\u0000\u008e\u0090\t\u0000"+
		"\u0000\u0000\u008f\u008e\u0001\u0000\u0000\u0000\u0090\u0093\u0001\u0000"+
		"\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000"+
		"\u0000\u0000\u0092\u0094\u0001\u0000\u0000\u0000\u0093\u0091\u0001\u0000"+
		"\u0000\u0000\u0094\u0095\u0005*\u0000\u0000\u0095\u0096\u0005/\u0000\u0000"+
		"\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u0098\u0006\u0014\u0000\u0000"+
		"\u0098*\u0001\u0000\u0000\u0000\u0099\u009a\u0005/\u0000\u0000\u009a\u009b"+
		"\u0005*\u0000\u0000\u009b\u009f\u0001\u0000\u0000\u0000\u009c\u009e\t"+
		"\u0000\u0000\u0000\u009d\u009c\u0001\u0000\u0000\u0000\u009e\u00a1\u0001"+
		"\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u009f\u009d\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a2\u0001\u0000\u0000\u0000\u00a1\u009f\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a3\u0005*\u0000\u0000\u00a3\u00a4\u0005/\u0000"+
		"\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6\u0006\u0015\u0000"+
		"\u0000\u00a6,\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005/\u0000\u0000\u00a8"+
		"\u00a9\u0005/\u0000\u0000\u00a9\u00ad\u0001\u0000\u0000\u0000\u00aa\u00ac"+
		"\b\u0004\u0000\u0000\u00ab\u00aa\u0001\u0000\u0000\u0000\u00ac\u00af\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001"+
		"\u0000\u0000\u0000\u00ae\u00b0\u0001\u0000\u0000\u0000\u00af\u00ad\u0001"+
		"\u0000\u0000\u0000\u00b0\u00b1\u0006\u0016\u0000\u0000\u00b1.\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b4\u0005\r\u0000\u0000\u00b3\u00b2\u0001\u0000\u0000"+
		"\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b6\u0005\n\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b8\u0006\u0017\u0000\u0000\u00b80\u0001\u0000\u0000\u0000\u00b9"+
		"\u00ba\t\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb\u00bc"+
		"\u0006\u0018\u0000\u0000\u00bc2\u0001\u0000\u0000\u0000\b\u0000z\u0080"+
		"\u0086\u0091\u009f\u00ad\u00b3\u0001\u0000\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}