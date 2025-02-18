package main.java.com.acacia.frontend.lexer;

import java.util.LinkedList;
import java.util.Queue;

import main.java.com.acacia.frontend.ILexer;
import main.java.com.acacia.frontend.lexer.util.Keywords;
import main.java.com.acacia.frontend.token.Token;
import main.java.com.acacia.frontend.token.TokenType;

public class Lexer implements ILexer {
    private String sourceCode;
    private int sourceCodeIndex;
    
    @Override
    public Queue<Token> tokenize(String sourceCode) throws Exception {
        final LinkedList<Token> tokens = new LinkedList<Token>();
        this.sourceCode = sourceCode;
        this.sourceCodeIndex = 0;

        Token curToken;
        do {
            curToken = getNextToken();
            if (curToken != null) {
                tokens.offer(curToken);
            }
        } while(curToken == null || curToken.getTokenType() != TokenType.EOF);

        return tokens;
    }

    
    
    private Token getNextToken() throws Exception {
        if (!hasNext()) {
            return new Token(TokenType.EOF);
        }

        final char curChar = next();
        switch(curChar) {
            case '(':
                return new Token(TokenType.OPEN_PAREN);
            case ')':
                return new Token(TokenType.CLOSE_PAREN);
            case '{':
                return new Token(TokenType.OPEN_BRACE);
            case '}':
                return new Token(TokenType.CLOSE_BRACE);
            case '[':
                return new Token(TokenType.OPEN_BRACKET);
            case ']':
                return new Token(TokenType.CLOSE_BRACKET);
            case '=':
                if (peek() == '=') {
                    return getBinaryOperatorToken(curChar, true);
                }
                return new Token(TokenType.EQUALS);
            case ',':
                return new Token(TokenType.COMMA);
            case ':':
                return new Token(TokenType.COLON);
            case '.':
                // Handle the case where this is a decimal first number.
                Token t = processNumber(curChar);
                if (t != null) return t;

                return new Token(TokenType.PERIOD);
            case '!':
                if (peek() == '=') {
                    return getBinaryOperatorToken(curChar, true);
                }	
                return new Token(TokenType.EXCLAIMATION_POINT);
            case '>':
            case '<':
                return getBinaryOperatorToken(curChar, true);
            case '+':
            case '*':
            case '/':
            case '%':
            case '^':
            case '&':
            case '|':
                return getBinaryOperatorToken(curChar, false);		
            case '-':
                // Handle the case where this is a negative number.
                t = processNumber(curChar);
                if (t != null) return t;

                return getBinaryOperatorToken(curChar, false);
            case '\"':
            case '`':
                return getStringToken(curChar);
            case '#':
                skipComment();	
                return null;
            default:
                // Try to process the token as a number
                t = processNumber(curChar);
                if (t != null) return t;

                // Try to process the token an an identifier.
                t = processIdentifier(curChar);
                if (t != null) return t;

                if(!isSkippable(curChar)) {
                    throw new Exception("Unrecognized character \"" + curChar + "\" found.");
                }
        }

        // Skipped character
        return null;
    }

    private boolean hasNext() {
        if (this.sourceCode == null) {
            return false;
        }
        return this.sourceCodeIndex < this.sourceCode.length();
    }
    
    private char next() {
        final char next = this.sourceCode.charAt(this.sourceCodeIndex);
        this.sourceCodeIndex++;
        return next;
    }

    private char peek() {
        if (!hasNext()) {
            return Character.MIN_VALUE;
        }
        return this.sourceCode.charAt(this.sourceCodeIndex);
    }
    
    private Token getBinaryOperatorToken(char c, boolean isRelationalOperator) {
        String value = Character.toString(c);
        if (!hasNext()) {
            return new Token(value, TokenType.BINARY_OPERATOR);
        }
        final char peekedValue = peek();
        if (peekedValue == '=') {
            if (isRelationalOperator) {
                // Handle relational binary operators with = in them such as >= and <=
                value += Character.toString(next());
                return new Token(value, TokenType.BINARY_OPERATOR);
            }
            next();
            return new Token(value, TokenType.BINARY_OPERATOR_EQUALS);
        }
        if (c == '+' && peekedValue == '+') {
            next();
            return new Token(TokenType.INCREMENT);
        }
        if (c == '-' && peekedValue == '-') {
            next();
            return new Token(TokenType.DECREMENT);
        }
        return new Token(value, TokenType.BINARY_OPERATOR);
    }
    
    private Token getStringToken(char c) throws Exception {
        String value = "";
        while(hasNext()) {
            final char curChar = next();
            if (curChar == c) {
                return new Token(value, TokenType.STRING);
            }
            value += Character.toString(curChar);
        }
        throw new Exception("Expected matching ending " + c + " for string.");
    }

    private boolean shouldProcessNumber(char c) {
        if (c == '.') {
            return Character.isDigit(peek());
        }
        if (c == '-') {
            final char peekedValue = peek();
            if (Character.isDigit(peekedValue)) return true;
            if (peekedValue == '.' && this.sourceCode.length() > this.sourceCodeIndex + 1) {
                return Character.isDigit(this.sourceCode.charAt(this.sourceCodeIndex + 1));
            }
        }
        return Character.isDigit(c);
    }

    private Token processNumber(char c) throws Exception {
        if (!shouldProcessNumber(c)) {
            return null;
        }
    
        String value = Character.toString(c);
        boolean encounteredDecimal = c == '.';
        char peekedValue = peek();
        while(hasNext() && (Character.isDigit(peekedValue) || peekedValue == '.')) {
            final char curChar = next();
            peekedValue = peek();

            if (curChar == '.') {
                if (encounteredDecimal) {
                    throw new Exception("Malformed number. Cannot have 2 decimals in number.");
                }
                encounteredDecimal = true;
                value += Character.toString(curChar);
                continue;
            }

            value += Character.toString(curChar);
        }
        return new Token(value, TokenType.NUMBER);
    }

    private Token processIdentifier(char c) {
        if (!isValidIdentifierChar(c, false)) {
            return null;
        }

        String value = Character.toString(c);
        while(hasNext() && isValidIdentifierChar(peek(), true)) {
            final char curChar = next();

            value += Character.toString(curChar);
            
            // Check to see if we found a key word.
            TokenType tokenType = Keywords.getTokenTypeForKeyword(value);
            if (tokenType != null) return new Token(tokenType);
        }
        return new Token(value, TokenType.IDENTIFIER);
    }

    private boolean isValidIdentifierChar(char c, boolean includeDigits) {
        if (Character.isLetter(c)) return true;
        if (c == '_') return true;
        return includeDigits ? Character.isDigit(c) : false;
    }

    private void skipComment() throws Exception {
        while(hasNext()) {
            final char curChar = next();
            if (curChar == '#') {
                return;
            }
        }
        throw new Exception("Expected end # to close comment.");
    }
    
    private boolean isSkippable(char c) {
        return c == ' ' || c == '\r' || c == '\t' || c == '\n';
    }
}
