package test.java.frontend.lexer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import main.java.com.acacia.frontend.lexer.Lexer;
import main.java.com.acacia.frontend.token.Token;
import main.java.com.acacia.frontend.token.TokenType;

public class LexerTest {
    private Lexer lexer;

    @Before
    public void setUp() {
        lexer = new Lexer();
    }

    private void testTokenize(String sourceCode, Token[] expectedTokens) throws Exception {
        final Queue<Token> result = lexer.tokenize(sourceCode);
        assertArrayEquals(expectedTokens, result.toArray());
    }
    
    @Test
    public void testLexer_nullSourceCode() throws Exception {
        testTokenize(null, new Token[]{new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_emptySourceCode() throws Exception {
        testTokenize("", new Token[]{new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_openParen() throws Exception {
        testTokenize("(", new Token[]{new Token(TokenType.OPEN_PAREN), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_closeParen() throws Exception {
        testTokenize(")", new Token[]{new Token(TokenType.CLOSE_PAREN), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_openBrace() throws Exception {
        testTokenize("{", new Token[]{new Token(TokenType.OPEN_BRACE), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_closeBrace() throws Exception {
        testTokenize("}", new Token[]{new Token(TokenType.CLOSE_BRACE), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_openBracket() throws Exception {
        testTokenize("[", new Token[]{new Token(TokenType.OPEN_BRACKET), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_closeBracket() throws Exception {
        testTokenize("]", new Token[]{new Token(TokenType.CLOSE_BRACKET), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_equals() throws Exception {
        testTokenize("=", new Token[]{new Token(TokenType.EQUALS), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_comma() throws Exception {
        testTokenize(",", new Token[]{new Token(TokenType.COMMA), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_colon() throws Exception {
        testTokenize(":", new Token[]{new Token(TokenType.COLON), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_period() throws Exception {
        testTokenize(".", new Token[]{new Token(TokenType.PERIOD), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_exclaimationPoint() throws Exception {
        testTokenize("!", new Token[]{new Token(TokenType.EXCLAIMATION_POINT), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_plus() throws Exception {
        testTokenize("+", new Token[]{new Token("+", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_minus() throws Exception {
        testTokenize("-", new Token[]{new Token("-", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_multiply() throws Exception {
        testTokenize("*", new Token[]{new Token("*", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_divide() throws Exception {
        testTokenize("/", new Token[]{new Token("/", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_modulo() throws Exception {
        testTokenize("%", new Token[]{new Token("%", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_power() throws Exception {
        testTokenize("^", new Token[]{new Token("^", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_equals() throws Exception {
        testTokenize("==", new Token[]{new Token("==", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_notEquals() throws Exception {
        testTokenize("!=", new Token[]{new Token("!=", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_greaterThan() throws Exception {
        testTokenize(">", new Token[]{new Token(">", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_greaterThanEquals() throws Exception {
        testTokenize(">=", new Token[]{new Token(">=", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_lessThan() throws Exception {
        testTokenize("<", new Token[]{new Token("<", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_lessThanEquals() throws Exception {
        testTokenize("<=", new Token[]{new Token("<=", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_and() throws Exception {
        testTokenize("&", new Token[]{new Token("&", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperator_or() throws Exception {
        testTokenize("|", new Token[]{new Token("|", TokenType.BINARY_OPERATOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperatorEquals_plus() throws Exception {
        testTokenize("+=", new Token[]{new Token("+", TokenType.BINARY_OPERATOR_EQUALS), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperatorEquals_minus() throws Exception {
        testTokenize("-=", new Token[]{new Token("-", TokenType.BINARY_OPERATOR_EQUALS), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperatorEquals_multiply() throws Exception {
        testTokenize("*=", new Token[]{new Token("*", TokenType.BINARY_OPERATOR_EQUALS), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperatorEquals_divide() throws Exception {
        testTokenize("/=", new Token[]{new Token("/", TokenType.BINARY_OPERATOR_EQUALS), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperatorEquals_modulo() throws Exception {
        testTokenize("%=", new Token[]{new Token("%", TokenType.BINARY_OPERATOR_EQUALS), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperatorEquals_power() throws Exception {
        testTokenize("^=", new Token[]{new Token("^", TokenType.BINARY_OPERATOR_EQUALS), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperatorEquals_and() throws Exception {
        testTokenize("&=", new Token[]{new Token("&", TokenType.BINARY_OPERATOR_EQUALS), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_binaryOperatorEquals_or() throws Exception {
        testTokenize("|=", new Token[]{new Token("|", TokenType.BINARY_OPERATOR_EQUALS), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_increment() throws Exception {
        testTokenize("++", new Token[]{new Token(TokenType.INCREMENT), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_decrement() throws Exception {
        testTokenize("--", new Token[]{new Token(TokenType.DECREMENT), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_getStringToken_singleQuotes() throws Exception {
        testTokenize("`Testing my fun string`", new Token[]{new Token("Testing my fun string", TokenType.STRING), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_getStringToken_doubleQuotes() throws Exception {
        testTokenize("\"Testing my fun string\"", new Token[]{new Token("Testing my fun string", TokenType.STRING), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_getStringToken_noMatchingEndQuote() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> lexer.tokenize("`Testing my fun string"));
        assertEquals("Expected matching ending ` for string.", e.getMessage());
    }

    @Test
    public void testLexer_processNumber_positiveInteger() throws Exception {
        testTokenize("1", new Token[]{new Token("1", TokenType.NUMBER), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processNumber_negativeInteger() throws Exception {
        testTokenize("-1", new Token[]{new Token("-1", TokenType.NUMBER), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processNumber_positiveFloat() throws Exception {
        testTokenize("100.2", new Token[]{new Token("100.2", TokenType.NUMBER), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processNumber_negativeFloat() throws Exception {
        testTokenize("-100.2", new Token[]{new Token("-100.2", TokenType.NUMBER), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processNumber_positiveDecimalFirstFloat() throws Exception {
        testTokenize(".123", new Token[]{new Token(".123", TokenType.NUMBER), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processNumber_negativeDecimalFirstFloat() throws Exception {
        testTokenize("-.123", new Token[]{new Token("-.123", TokenType.NUMBER), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processNumber_malformedNumber() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> lexer.tokenize("1.2.3"));
        assertEquals("Malformed number. Cannot have 2 decimals in number.", e.getMessage());
    }

    @Test
    public void testLexer_processIdentifier() throws Exception {
        testTokenize("_myC00L_3_V4R", new Token[]{new Token("_myC00L_3_V4R", TokenType.IDENTIFIER), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processIdentifier_cannotStartWithNumber() throws Exception {
        testTokenize("2x", new Token[]{new Token("2", TokenType.NUMBER), new Token("x", TokenType.IDENTIFIER), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processKeywords_var() throws Exception {
        testTokenize("var", new Token[]{new Token(TokenType.VAR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processKeywords_final() throws Exception {
        testTokenize("final", new Token[]{new Token(TokenType.FINAL), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processKeywords_true() throws Exception {
        testTokenize("true", new Token[]{new Token(TokenType.TRUE), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processKeywords_fn() throws Exception {
        testTokenize("fn", new Token[]{new Token(TokenType.FUNCTION), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processKeywords_return() throws Exception {
        testTokenize("return", new Token[]{new Token(TokenType.RETURN), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processKeywords_enum() throws Exception {
        testTokenize("enum", new Token[]{new Token(TokenType.ENUM), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processKeywords_if() throws Exception {
        testTokenize("if", new Token[]{new Token(TokenType.IF), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processKeywords_elif() throws Exception {
        testTokenize("elif", new Token[]{new Token(TokenType.ELIF), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processKeywords_else() throws Exception {
        testTokenize("else", new Token[]{new Token(TokenType.ELSE), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_processKeywords_for() throws Exception {
        testTokenize("for", new Token[]{new Token(TokenType.FOR), new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_skipWhitespace_carriageReturn() throws Exception {
        testTokenize("var\ra", new Token[]{
            new Token(TokenType.VAR),
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.EOF),
        });
    }

    @Test
    public void testLexer_skipWhitespace_space() throws Exception {
        testTokenize("var a", new Token[]{
            new Token(TokenType.VAR),
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.EOF),
        });
    }

    @Test
    public void testLexer_skipWhitespace_lineFeed() throws Exception {
        testTokenize("var\na", new Token[]{
            new Token(TokenType.VAR),
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.EOF),
        });
    }

    @Test
    public void testLexer_skipWhitespace_tab() throws Exception {
        testTokenize("var\ta", new Token[]{
            new Token(TokenType.VAR),
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.EOF),
        });
    }

    @Test
    public void testLexer_skipComment() throws Exception {
        testTokenize("#comment here#", new Token[]{new Token(TokenType.EOF)});
    }

    @Test
    public void testLexer_skipComment_missingEnd() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> lexer.tokenize("#comment here"));
        assertEquals("Expected end # to close comment.", e.getMessage());
    }
}
