package main.java.com.acacia.frontend.token;

/**
 * Represents tokens acacia understands in parsing.
 */
public enum TokenType {
    // Literal Types
    NUMBER,
    IDENTIFIER,
    STRING,
    
    // Keywords
    FINAL,
    VAR,
    TRUE,
    FALSE,
    FUNCTION,
    RETURN,
    ENUM,
    IF,
    ELIF,
    ELSE,
    FOR,

    // Grouping * Operators
    BINARY_OPERATOR,
    BINARY_OPERATOR_EQUALS,
    INCREMENT,
    DECREMENT,
    EQUALS,
    OPEN_PAREN, // (
    CLOSE_PAREN, // )
    OPEN_BRACE, // {
    CLOSE_BRACE, // }
    OPEN_BRACKET, // [
    CLOSE_BRACKET, //]
    COMMA,
    COLON,
    PERIOD,
    EXCLAIMATION_POINT,
    EOF, // Signified the end of file
}
