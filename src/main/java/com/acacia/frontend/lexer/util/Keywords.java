package main.java.com.acacia.frontend.lexer.util;

import java.util.HashMap;

import main.java.com.acacia.frontend.token.TokenType;

/**
 * A constant time lookup for words that are reserved.
 */
public class Keywords {
    private static final HashMap<String, TokenType> keywords;

    static {
        keywords = new HashMap<String, TokenType>();
        keywords.put("final", TokenType.FINAL);
        keywords.put("var", TokenType.VAR);
        keywords.put("true", TokenType.TRUE);
        keywords.put("false", TokenType.FALSE);
        keywords.put("fn", TokenType.FUNCTION);
        keywords.put("return", TokenType.RETURN);
        keywords.put("enum", TokenType.ENUM);
        keywords.put("if", TokenType.IF);
        keywords.put("elif", TokenType.ELIF);
        keywords.put("else", TokenType.ELSE);
        keywords.put("for", TokenType.FOR);
    }

    public static TokenType getTokenTypeForKeyword(String keyword) {
        return keywords.get(keyword);
    }
}
