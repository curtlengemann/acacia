package test.java.frontend.token;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.frontend.token.Token;
import main.java.com.acacia.frontend.token.TokenType;

public class TokenTest {

    @Test
    public void testToken_getTokenType() {
        final Token testToken = new Token(TokenType.NUMBER);
        assertEquals(TokenType.NUMBER, testToken.getTokenType());
    }

    @Test
    public void testToken_getValue_null() {
        final Token testToken = new Token(TokenType.NUMBER);
        assertNull(testToken.getValue());
    }

    @Test
    public void testToken_getValue() {
        final Token testToken = new Token("2.0", TokenType.NUMBER);
        assertEquals("2.0", testToken.getValue());
    }

    @Test
    public void testToken_equals_self() {
        final Token testToken = new Token(TokenType.NUMBER);
        assertTrue(testToken.equals(testToken));
    }

    @Test
    public void testToken_equals_symmetrical() {
        final Token testToken = new Token(TokenType.NUMBER);
        assertTrue(new Token(TokenType.NUMBER).equals(testToken));
        assertTrue(testToken.equals(new Token(TokenType.NUMBER)));
    }

    @Test
    public void testToken_equals_value() {
        final Token testToken = new Token("2", TokenType.NUMBER);
        assertTrue(testToken.equals(new Token("2", TokenType.NUMBER)));
    }

    @Test
    public void testToken_notEquals_null() {
        final Token testToken = new Token(TokenType.NUMBER);
        assertFalse(testToken.equals(null));
    }

    @Test
    public void testToken_notEquals_changedTokenType() {
        final Token testToken = new Token(TokenType.NUMBER);
        assertFalse(testToken.equals(new Token(TokenType.BINARY_OPERATOR)));
    }

    @Test
    public void testToken_notEquals_nullValue() {
        final Token testToken = new Token(TokenType.NUMBER);
        assertFalse(testToken.equals(new Token("2", TokenType.NUMBER)));
    }

    @Test
    public void testToken_notEquals_changedValue() {
        final Token testToken = new Token(TokenType.NUMBER);
        assertFalse(testToken.equals(new Token("3", TokenType.NUMBER)));
    }

    @Test
    public void testToken_hashcode_equals_self() {
        final Token testToken = new Token(TokenType.NUMBER);
        assertEquals(testToken.hashCode(), testToken.hashCode());
    }

    @Test
    public void testToken_hashcode_equals_noValue() {
        final Token testToken = new Token(TokenType.NUMBER);
        assertEquals(new Token(TokenType.NUMBER).hashCode(), testToken.hashCode());
    }

    @Test
    public void testToken_hashcode_equals_value() {
        final Token testToken = new Token("2", TokenType.NUMBER);
        assertEquals(new Token("2", TokenType.NUMBER).hashCode(), testToken.hashCode());
    }

    @Test
    public void testToken_hashcode_notEquals_changedTokenType() {
        final Token testToken = new Token(TokenType.NUMBER);
        assertNotEquals(new Token(TokenType.BINARY_OPERATOR).hashCode(), testToken.hashCode());
    }

    @Test
    public void testToken_hashcode_notEquals_nullValue() {
        final Token testToken = new Token("2", TokenType.NUMBER);
        assertNotEquals(new Token(TokenType.NUMBER).hashCode(), testToken.hashCode());
    }

    @Test
    public void testToken_hashcode_notEquals_changedValue() {
        final Token testToken = new Token("2", TokenType.NUMBER);
        assertNotEquals(new Token("3",TokenType.NUMBER).hashCode(), testToken.hashCode());
    }
}
