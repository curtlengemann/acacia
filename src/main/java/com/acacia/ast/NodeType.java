package main.java.com.acacia.ast;

/**
 * Different types of nodes in the AST.
 */
public enum NodeType {
    // Statements
    PROGRAM,
    VAR_DECLARATION,
    FUNCTION_DECLARATION,
    CONDITONAL,
    FOR,
    STATEMENT_BLOCK,
    
    // Expressions
    BINARY_EXPRESSION,
    ASSIGNMENT_EXPRESSION,
    MEMBER_EXPRESSION,
    CALL_EXPRESSION,
    NEGATION_EXPRESSION,
    RETURN_EXPRESSION,

    // Literals
    NUMERIC_LITERAL,
    STRING_LITERAL,
    IDENTIFIER,
    NULL_LITERAL,
    OBJECT_LITERAL,
    PROPERTY,
    BOOLEAN_LITERAL,
}
