package test.java.frontend.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import main.java.com.acacia.ast.ConditionalStatement;
import main.java.com.acacia.ast.ForStatement;
import main.java.com.acacia.ast.FunctionDeclaration;
import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.Program;
import main.java.com.acacia.ast.StatementBlock;
import main.java.com.acacia.ast.VariableDeclaration;
import main.java.com.acacia.ast.expressions.AssignmentExpression;
import main.java.com.acacia.ast.expressions.BinaryExpression;
import main.java.com.acacia.ast.expressions.CallExpression;
import main.java.com.acacia.ast.expressions.MemberExpression;
import main.java.com.acacia.ast.expressions.NegationExpression;
import main.java.com.acacia.ast.expressions.ReturnExpression;
import main.java.com.acacia.ast.literals.BooleanLiteral;
import main.java.com.acacia.ast.literals.Identifier;
import main.java.com.acacia.ast.literals.NumericLiteral;
import main.java.com.acacia.ast.literals.ObjectLiteral;
import main.java.com.acacia.ast.literals.Property;
import main.java.com.acacia.ast.literals.StringLiteral;
import main.java.com.acacia.frontend.parser.Parser;
import main.java.com.acacia.frontend.token.Token;
import main.java.com.acacia.frontend.token.TokenType;

public class ParserTest {
    private Parser parser;

    @Before
    public void setUp() {
        parser = new Parser();
    }

    private void testProduceAST(Token[] tokens, Program expectedProgram) throws Exception {
        final Queue<Token> queueTokens = new LinkedList<Token>();

        for (final Token t : tokens) {
            queueTokens.offer(t);
        }

        // Add EOF just for convenience.
        queueTokens.offer(new Token(TokenType.EOF));

        assertEquals(expectedProgram, parser.produceAST(queueTokens));
    }

    @Test
    public void testParser_nullTokens() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> parser.produceAST(null));
        assertEquals("Unexpectedly ran out of tokens. No EOF found.", e.getMessage());
    }

    @Test
    public void testParser_EOFToken() throws Exception {
        testProduceAST(new Token[]{}, new Program());
    }

    @Test
    public void testParser_identifier() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new Identifier("myFunVar"));
        testProduceAST(new Token[]{new Token("myFunVar", TokenType.IDENTIFIER)}, expectedProgram);
    }
 
    @Test
    public void testParser_number() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new NumericLiteral(Float.valueOf(2)));
        testProduceAST(new Token[]{new Token("2", TokenType.NUMBER)}, expectedProgram);
    }

    @Test
    public void testParser_number_malformed() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token("a", TokenType.NUMBER),
        }, null));
        assertEquals("Malformed number. a cannot be parsed.", e.getMessage());
    }

    @Test
    public void testParser_string() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new StringLiteral("fun string"));
        testProduceAST(new Token[]{new Token("fun string", TokenType.STRING)}, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_addition() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new NumericLiteral(Float.valueOf(2)), new NumericLiteral(Float.valueOf(2)), "+"));
        testProduceAST(new Token[]{
            new Token("2", TokenType.NUMBER),
            new Token("+", TokenType.BINARY_OPERATOR),
            new Token("2", TokenType.NUMBER),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_subtraction() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new NumericLiteral(Float.valueOf(2)), new NumericLiteral(Float.valueOf(2)), "-"));
        testProduceAST(new Token[]{
            new Token("2", TokenType.NUMBER),
            new Token("-", TokenType.BINARY_OPERATOR),
            new Token("2", TokenType.NUMBER),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_multiplication() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new NumericLiteral(Float.valueOf(2)), new NumericLiteral(Float.valueOf(2)), "*"));
        testProduceAST(new Token[]{
            new Token("2", TokenType.NUMBER),
            new Token("*", TokenType.BINARY_OPERATOR),
            new Token("2", TokenType.NUMBER),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_division() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new NumericLiteral(Float.valueOf(2)), new NumericLiteral(Float.valueOf(2)), "/"));
        testProduceAST(new Token[]{
            new Token("2", TokenType.NUMBER),
            new Token("/", TokenType.BINARY_OPERATOR),
            new Token("2", TokenType.NUMBER),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_modulo() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new NumericLiteral(Float.valueOf(2)), new NumericLiteral(Float.valueOf(2)), "%"));
        testProduceAST(new Token[]{
            new Token("2", TokenType.NUMBER),
            new Token("%", TokenType.BINARY_OPERATOR),
            new Token("2", TokenType.NUMBER),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_power() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new NumericLiteral(Float.valueOf(2)), new NumericLiteral(Float.valueOf(2)), "^"));
        testProduceAST(new Token[]{
            new Token("2", TokenType.NUMBER),
            new Token("^", TokenType.BINARY_OPERATOR),
            new Token("2", TokenType.NUMBER),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_and() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new BooleanLiteral(false), new BooleanLiteral(true), "&"));
        testProduceAST(new Token[]{
            new Token(TokenType.FALSE),
            new Token("&", TokenType.BINARY_OPERATOR),
            new Token(TokenType.TRUE),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_or() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new BooleanLiteral(false), new BooleanLiteral(true), "|"));
        testProduceAST(new Token[]{
            new Token(TokenType.FALSE),
            new Token("|", TokenType.BINARY_OPERATOR),
            new Token(TokenType.TRUE),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_greaterThan() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new Identifier("a"), new Identifier("b"), ">"));
        testProduceAST(new Token[]{
            new Token("a", TokenType.IDENTIFIER),
            new Token(">", TokenType.BINARY_OPERATOR),
            new Token("b", TokenType.IDENTIFIER),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_lessThan() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new Identifier("a"), new Identifier("b"), "<"));
        testProduceAST(new Token[]{
            new Token("a", TokenType.IDENTIFIER),
            new Token("<", TokenType.BINARY_OPERATOR),
            new Token("b", TokenType.IDENTIFIER),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_greaterThanEqual() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new Identifier("a"), new Identifier("b"), ">="));
        testProduceAST(new Token[]{
            new Token("a", TokenType.IDENTIFIER),
            new Token(">=", TokenType.BINARY_OPERATOR),
            new Token("b", TokenType.IDENTIFIER),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_lessThanEqual() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new Identifier("a"), new Identifier("b"), "<="));
        testProduceAST(new Token[]{
            new Token("a", TokenType.IDENTIFIER),
            new Token("<=", TokenType.BINARY_OPERATOR),
            new Token("b", TokenType.IDENTIFIER),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_equal() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new Identifier("a"), new Identifier("b"), "=="));
        testProduceAST(new Token[]{
            new Token("a", TokenType.IDENTIFIER),
            new Token("==", TokenType.BINARY_OPERATOR),
            new Token("b", TokenType.IDENTIFIER),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_notEqual() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new BinaryExpression(new Identifier("a"), new Identifier("b"), "!="));
        testProduceAST(new Token[]{
            new Token("a", TokenType.IDENTIFIER),
            new Token("!=", TokenType.BINARY_OPERATOR),
            new Token("b", TokenType.IDENTIFIER),
        }, expectedProgram);
    }


    @Test
    public void testParser_binaryExpression_multiplicativeBeforeAdditive() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(2)), 
                new BinaryExpression(new NumericLiteral(Float.valueOf(5)), new NumericLiteral(Float.valueOf(3)), "*"), 
                "-"
                ));
        testProduceAST(new Token[]{
            new Token("2", TokenType.NUMBER),
            new Token("-", TokenType.BINARY_OPERATOR),
            new Token("5", TokenType.NUMBER),
            new Token("*", TokenType.BINARY_OPERATOR),
            new Token("3", TokenType.NUMBER),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_parenthesis() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(2)), 
                new BinaryExpression(new NumericLiteral(Float.valueOf(5)), new NumericLiteral(Float.valueOf(3)), "-"), 
                "*"
                ));
        
        //2*(5-3)
        testProduceAST(new Token[]{
            new Token("2", TokenType.NUMBER),
            new Token("*", TokenType.BINARY_OPERATOR),
            new Token(TokenType.OPEN_PAREN),
            new Token("5", TokenType.NUMBER),
            new Token("-", TokenType.BINARY_OPERATOR),
            new Token("3", TokenType.NUMBER),
            new Token( TokenType.CLOSE_PAREN),
        }, expectedProgram);
    }

    @Test
    public void testParser_binaryExpression_parenthesis_noClosingParen() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.OPEN_PAREN),
            new Token("2", TokenType.NUMBER),
        }, null));
        assertEquals("Unexpected token. Expected closing parenthesis.", e.getMessage());
    }

    @Test
    public void testParser_variableDeclaration_var() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new VariableDeclaration(false, "a"));
        testProduceAST(new Token[]{
            new Token(TokenType.VAR),
            new Token("a", TokenType.IDENTIFIER),
        }, expectedProgram);
    }

    @Test
    public void testParser_variableDeclaration_var_initalized() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new VariableDeclaration(false, "a", new NumericLiteral(Float.valueOf(2))));
        testProduceAST(new Token[]{
            new Token(TokenType.VAR),
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.EQUALS),
            new Token("2", TokenType.NUMBER),
        }, expectedProgram);
    }

    @Test
    public void testParser_variableDeclaration_final() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new VariableDeclaration(true, "a", new NumericLiteral(Float.valueOf(2))));
        testProduceAST(new Token[]{
            new Token(TokenType.FINAL),
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.EQUALS),
            new Token("2", TokenType.NUMBER),
        }, expectedProgram);
    }

    @Test
    public void testParser_variableDeclaration_withoutIdentifier() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.VAR),
            new Token("2", TokenType.NUMBER),
        }, null));
        assertEquals("Expected identifier after variable declaration keyword.", e.getMessage());
    }

    @Test
    public void testParser_variableDeclaration_withoutEquals() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.VAR),
            new Token("a", TokenType.IDENTIFIER),
            new Token("2", TokenType.NUMBER),
        }, null));
        assertEquals("Expected equals token after identifier in variable declaration.", e.getMessage());
    }

    @Test
    public void testParser_variableDeclaration_uninitalizedFinal() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.FINAL),
            new Token("a", TokenType.IDENTIFIER),
        }, null));
        assertEquals("Must assign value to constant variables.", e.getMessage());
    }

    @Test
    public void testParser_functionDeclaration() throws Exception {
        final FunctionDeclaration f = new FunctionDeclaration("a");
        f.addParameter("x");
        f.addParameter("y");
        f.addStatementToBody(new NumericLiteral(Float.valueOf(2)));
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(f);
        testProduceAST(new Token[]{
            new Token(TokenType.FUNCTION),
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_PAREN),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.COMMA),
            new Token("y", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_PAREN),
            new Token(TokenType.OPEN_BRACE),
            new Token("2",TokenType.NUMBER),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_functionDeclaration_withEmptyReturn() throws Exception {
        final FunctionDeclaration f = new FunctionDeclaration("a");
        f.addStatementToBody(new ReturnExpression(null));
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(f);
        testProduceAST(new Token[]{
            new Token(TokenType.FUNCTION),
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_PAREN),
            new Token(TokenType.CLOSE_PAREN),
            new Token(TokenType.OPEN_BRACE),
            new Token(TokenType.RETURN),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_functionDeclaration_withReturnValue() throws Exception {
        final FunctionDeclaration f = new FunctionDeclaration("a");
        f.addStatementToBody(new ReturnExpression(new NumericLiteral(Float.valueOf(2))));
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(f);
        testProduceAST(new Token[]{
            new Token(TokenType.FUNCTION),
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_PAREN),
            new Token(TokenType.CLOSE_PAREN),
            new Token(TokenType.OPEN_BRACE),
            new Token(TokenType.RETURN),
            new Token("2", TokenType.NUMBER),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_functionDeclaration_missingName() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.FUNCTION),
        }, null));
        assertEquals("Expected function to have declared name.", e.getMessage());
    }

    @Test
    public void testParser_functionDeclaration_invalidParameter() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.FUNCTION),
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_PAREN),
            new Token("2", TokenType.NUMBER),
            new Token(TokenType.CLOSE_PAREN),
        }, null));
        assertEquals("Expected function parameter list to only contain identifiers.", e.getMessage());
    }

    @Test
    public void testParser_functionDeclaration_missingOpenBrace() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.FUNCTION),
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_PAREN),
            new Token(TokenType.CLOSE_PAREN),
        }, null));
        assertEquals("Function declaration should contain open brace.", e.getMessage());
    }

    @Test
    public void testParser_functionDeclaration_missingCloseBrace() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.FUNCTION),
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_PAREN),
            new Token(TokenType.CLOSE_PAREN),
            new Token(TokenType.OPEN_BRACE),
        }, null));
        assertEquals("Function declaration should end with close brace.", e.getMessage());
    }

    @Test
    public void testParser_conditionalStatement() throws Exception {
        final ConditionalStatement cs = new ConditionalStatement();
        final StatementBlock s = new StatementBlock();
        s.add(new ReturnExpression(new Identifier("x")));
        cs.addConditionAndStatementBlock(
            new BinaryExpression(new Identifier("x"), new NumericLiteral(Float.valueOf(4)), ">"),
            s
        );
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(cs);
        testProduceAST(new Token[]{
            new Token(TokenType.IF),
            new Token("x", TokenType.IDENTIFIER),
            new Token(">", TokenType.BINARY_OPERATOR),
            new Token("4", TokenType.NUMBER),
            new Token(TokenType.OPEN_BRACE),
            new Token(TokenType.RETURN),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_conditionalStatement_withParenthesis() throws Exception {
        final ConditionalStatement cs = new ConditionalStatement();
        final StatementBlock s = new StatementBlock();
        s.add(new ReturnExpression(new Identifier("x")));
        cs.addConditionAndStatementBlock(
            new BinaryExpression(new Identifier("x"), new NumericLiteral(Float.valueOf(4)), ">"),
            s
        );
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(cs);
        testProduceAST(new Token[]{
            new Token(TokenType.IF),
            new Token(TokenType.OPEN_PAREN),
            new Token("x", TokenType.IDENTIFIER),
            new Token(">", TokenType.BINARY_OPERATOR),
            new Token("4", TokenType.NUMBER),
            new Token(TokenType.CLOSE_PAREN),
            new Token(TokenType.OPEN_BRACE),
            new Token(TokenType.RETURN),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_conditionalStatement_elifAndElse() throws Exception {
        final ConditionalStatement cs = new ConditionalStatement();
        final StatementBlock s = new StatementBlock();
        s.add(new ReturnExpression(new Identifier("x")));
        cs.addConditionAndStatementBlock(
            new Identifier("x"),
            s
        );
        final StatementBlock s2 = new StatementBlock();
        s2.add(new ReturnExpression(new Identifier("y")));
        cs.addConditionAndStatementBlock(
            new Identifier("y"),
            s2
        );
        final StatementBlock s3 = new StatementBlock();
        s3.add(new ReturnExpression(new Identifier("z")));
        cs.addConditionAndStatementBlock(
            new BooleanLiteral(true),
            s3
        );
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(cs);
        testProduceAST(new Token[]{
            new Token(TokenType.IF),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_BRACE),
            new Token(TokenType.RETURN),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_BRACE),
            new Token(TokenType.ELIF),
            new Token("y", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_BRACE),
            new Token(TokenType.RETURN),
            new Token("y", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_BRACE),
            new Token(TokenType.ELSE),
            new Token(TokenType.OPEN_BRACE),
            new Token(TokenType.RETURN),
            new Token("z", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_conditionalStatement_missingCloseParen() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.IF),
            new Token(TokenType.OPEN_PAREN),
            new Token("x", TokenType.IDENTIFIER),
        }, null));
        assertEquals("Expected closing parenthesis after condition in conditional statement", e.getMessage());
    }

    @Test
    public void testParser_conditionalStatement_missingOpenBrace() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.IF),
            new Token("x", TokenType.IDENTIFIER),
        }, null));
        assertEquals("Expected open brace after condition in conditional statement.", e.getMessage());
    }

    @Test
    public void testParser_conditionalStatement_missingCloseBrace() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.IF),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_BRACE),
        }, null));
        assertEquals("Conditional statement should end in close brace.", e.getMessage());
    }

    @Test
    public void testParser_forStatement() throws Exception {
        final StatementBlock s = new StatementBlock();
        s.add(new ReturnExpression(new Identifier("x")));
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new ForStatement(new BinaryExpression(new Identifier("x"), new NumericLiteral(Float.valueOf(4)), ">"), s));
        testProduceAST(new Token[]{
            new Token(TokenType.FOR),
            new Token("x", TokenType.IDENTIFIER),
            new Token(">", TokenType.BINARY_OPERATOR),
            new Token("4", TokenType.NUMBER),
            new Token(TokenType.OPEN_BRACE),
            new Token(TokenType.RETURN),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_forStatement_withParenthesis() throws Exception {
        final StatementBlock s = new StatementBlock();
        s.add(new ReturnExpression(new Identifier("x")));
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new ForStatement(new Identifier("x"), s));
        testProduceAST(new Token[]{
            new Token(TokenType.FOR),
            new Token(TokenType.OPEN_PAREN),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_PAREN),
            new Token(TokenType.OPEN_BRACE),
            new Token(TokenType.RETURN),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    
    @Test
    public void testParser_forStatement_missingCloseParen() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.FOR),
            new Token(TokenType.OPEN_PAREN),
            new Token("x", TokenType.IDENTIFIER),
        }, null));
        assertEquals("Expected closing parenthesis after condition in for statement.", e.getMessage());
    }

    @Test
    public void testParser_forStatement_missingOpenBrace() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.FOR),
            new Token("x", TokenType.IDENTIFIER),
        }, null));
        assertEquals("Expected open brace after condition in for statement.", e.getMessage());
    }

    @Test
    public void testParser_forStatement_missingCloseBrace() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.FOR),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_BRACE),
        }, null));
        assertEquals("For statement should end in close brace.", e.getMessage());
    }

    @Test 
    public void testParser_assignmentExpression() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new AssignmentExpression(
            new Identifier("asdf"), 
            new NumericLiteral(Float.valueOf(2))
        ));
        testProduceAST(new Token[]{
            new Token("asdf", TokenType.IDENTIFIER),
            new Token(TokenType.EQUALS),
            new Token("2", TokenType.NUMBER),
        }, expectedProgram);
    }

    @Test 
    public void testParser_assignmentExpression_chain() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new AssignmentExpression(
            new Identifier("a"), 
            new AssignmentExpression(
                new Identifier("b"),
                new AssignmentExpression(
                    new Identifier("c"),
                    new NumericLiteral(Float.valueOf(2))
                    )
                )
        ));
        testProduceAST(new Token[]{
            new Token("a", TokenType.IDENTIFIER),
            new Token(TokenType.EQUALS),
            new Token("b", TokenType.IDENTIFIER),
            new Token(TokenType.EQUALS),
            new Token("c", TokenType.IDENTIFIER),
            new Token(TokenType.EQUALS),
            new Token("2", TokenType.NUMBER),
        }, expectedProgram);
    }

    @Test 
    public void testParser_assignmentExpression_increment() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new AssignmentExpression(
            new Identifier("asdf"), 
            new BinaryExpression(new Identifier("asdf"), new NumericLiteral(Float.valueOf(1)), "+")
        ));
        testProduceAST(new Token[]{
            new Token("asdf", TokenType.IDENTIFIER),
            new Token(TokenType.INCREMENT),
        }, expectedProgram);
    }

    @Test 
    public void testParser_assignmentExpression_decrement() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new AssignmentExpression(
            new Identifier("asdf"), 
            new BinaryExpression(new Identifier("asdf"), new NumericLiteral(Float.valueOf(1)), "-")
        ));
        testProduceAST(new Token[]{
            new Token("asdf", TokenType.IDENTIFIER),
            new Token(TokenType.DECREMENT),
        }, expectedProgram);
    }

    @Test 
    public void testParser_assignmentExpression_binaryOperatorEquals() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new AssignmentExpression(
            new Identifier("asdf"), 
            new BinaryExpression(new Identifier("asdf"), new NumericLiteral(Float.valueOf(2)), "+")
        ));
        testProduceAST(new Token[]{
            new Token("asdf", TokenType.IDENTIFIER),
            new Token("+",TokenType.BINARY_OPERATOR_EQUALS),
            new Token("2", TokenType.NUMBER),
        }, expectedProgram);
    }

    @Test 
    public void testParser_assignmentExpression_binaryOperatorEquals_chain() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new AssignmentExpression(
            new Identifier("a"), 
            new BinaryExpression(
                new Identifier("a"),
                new AssignmentExpression(
                    new Identifier("b"),
                    new BinaryExpression(
                        new Identifier("b"),
                        new BooleanLiteral(false),
                        "|"
                    )
                ),
                "&"
            )
        ));
        testProduceAST(new Token[]{
            new Token("a", TokenType.IDENTIFIER),
            new Token("&",TokenType.BINARY_OPERATOR_EQUALS),
            new Token("b", TokenType.IDENTIFIER),
            new Token("|",TokenType.BINARY_OPERATOR_EQUALS),
            new Token(TokenType.FALSE),
        }, expectedProgram);
    }

    @Test
    public void testParser_objectExpression() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new ObjectLiteral(false));
        testProduceAST(new Token[]{
            new Token(TokenType.OPEN_BRACE),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_objectExpression_shorthand() throws Exception {
        final Program expectedProgram = new Program();
        final ObjectLiteral obj = new ObjectLiteral(false);
        obj.addProperty(new Property("x"));
        expectedProgram.addStatement(obj);
        testProduceAST(new Token[]{
            new Token(TokenType.OPEN_BRACE),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_objectExpression_shorthandWithComma() throws Exception {
        final Program expectedProgram = new Program();
        final ObjectLiteral obj = new ObjectLiteral(false);
        obj.addProperty(new Property("x"));
        expectedProgram.addStatement(obj);
        testProduceAST(new Token[]{
            new Token(TokenType.OPEN_BRACE),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.COMMA),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_objectExpression_withValue() throws Exception {
        final Program expectedProgram = new Program();
        final ObjectLiteral obj = new ObjectLiteral(false);
        obj.addProperty(new Property("x", new NumericLiteral(Float.valueOf(2))));
        expectedProgram.addStatement(obj);
        testProduceAST(new Token[]{
            new Token(TokenType.OPEN_BRACE),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.COLON),
            new Token("2", TokenType.NUMBER),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_objectExpression_withValueAndComma() throws Exception {
        final Program expectedProgram = new Program();
        final ObjectLiteral obj = new ObjectLiteral(false);
        obj.addProperty(new Property("x", new NumericLiteral(Float.valueOf(2))));
        expectedProgram.addStatement(obj);
        testProduceAST(new Token[]{
            new Token(TokenType.OPEN_BRACE),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.COLON),
            new Token("2", TokenType.NUMBER),
            new Token(TokenType.COMMA),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_objectExpression_multipleProperties() throws Exception {
        final Program expectedProgram = new Program();

        final ObjectLiteral obj = new ObjectLiteral(false);
        obj.addProperty(new Property("x", new NumericLiteral(Float.valueOf(2))));

        final ObjectLiteral internalObj = new ObjectLiteral(false);
        internalObj.addProperty(new Property("y"));
        obj.addProperty(new Property("internal", internalObj));

        expectedProgram.addStatement(obj);
        // {x:2,internal:{y}}
        testProduceAST(new Token[]{
            new Token(TokenType.OPEN_BRACE),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.COLON),
            new Token("2", TokenType.NUMBER),
            new Token(TokenType.COMMA),
            new Token("internal", TokenType.IDENTIFIER),
            new Token(TokenType.COLON),
            new Token(TokenType.OPEN_BRACE),
            new Token("y", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_BRACE),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    
    @Test
    public void testParser_objectExpression_enum() throws Exception {
        final Program expectedProgram = new Program();

        final ObjectLiteral obj = new ObjectLiteral(true);
        obj.addProperty(new Property("x"));
        obj.addProperty(new Property("y"));
        obj.addProperty(new Property("z"));

        expectedProgram.addStatement(obj);
        testProduceAST(new Token[]{
            new Token(TokenType.ENUM),
            new Token(TokenType.OPEN_BRACE),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.COMMA),
            new Token("y", TokenType.IDENTIFIER),
            new Token(TokenType.COMMA),
            new Token("z", TokenType.IDENTIFIER),
            new Token(TokenType.COMMA),
            new Token(TokenType.CLOSE_BRACE),
        }, expectedProgram);
    }

    @Test
    public void testParser_objectExpression_missingIdentifier() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.OPEN_BRACE),
            new Token(TokenType.COLON),
            new Token(TokenType.CLOSE_BRACE),
        }, null));
        assertEquals("Expected key for object.", e.getMessage());
    }

    @Test
    public void testParser_objectExpression_missingColon() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.OPEN_BRACE),
            new Token("x", TokenType.IDENTIFIER),
            new Token("y", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_BRACE),
        }, null));
        assertEquals("Object expression should hava a colon following the key to declare a value.", e.getMessage());
    }

    @Test
    public void testParser_objectExpression_missingClosingBrace() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token(TokenType.OPEN_BRACE),
        }, null));
        assertEquals("Object expression missing a closing brace.", e.getMessage());
    }

    @Test
    public void testParser_memberExpression_notComputed() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new MemberExpression(new Identifier("x") , new Identifier("y"), false));
        testProduceAST(new Token[]{
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.PERIOD),
            new Token("y", TokenType.IDENTIFIER),
        }, expectedProgram);
    }

    @Test
    public void testParser_memberExpression_notComputed_nestedMembers() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new MemberExpression(
            new MemberExpression(new Identifier("x") , new Identifier("y"), false),
            new Identifier("z"),
            false)
        );
        testProduceAST(new Token[]{
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.PERIOD),
            new Token("y", TokenType.IDENTIFIER),
            new Token(TokenType.PERIOD),
            new Token("z", TokenType.IDENTIFIER),
        }, expectedProgram);
    }

    @Test
    public void testParser_memberExpression_computed() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new MemberExpression(new Identifier("x") , new StringLiteral("y"), true));
        testProduceAST(new Token[]{
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_BRACKET),
            new Token("y", TokenType.STRING),
            new Token(TokenType.CLOSE_BRACKET),
        }, expectedProgram);
    }

    @Test
    public void testParser_memberExpression_computed_nestedMembers() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new MemberExpression(
            new MemberExpression(new Identifier("x") , new StringLiteral("y"), true),
            new StringLiteral("z"),
            true)
        );
        testProduceAST(new Token[]{
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_BRACKET),
            new Token("y", TokenType.STRING),
            new Token(TokenType.CLOSE_BRACKET),
            new Token(TokenType.OPEN_BRACKET),
            new Token("z", TokenType.STRING),
            new Token(TokenType.CLOSE_BRACKET),
        }, expectedProgram);
    }

    @Test
    public void testParser_memberExpression_mixed() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new MemberExpression(
            new MemberExpression(new Identifier("x") , new StringLiteral("y"), true),
            new Identifier("z"),
            false)
        );
        testProduceAST(new Token[]{
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_BRACKET),
            new Token("y", TokenType.STRING),
            new Token(TokenType.CLOSE_BRACKET),
            new Token(TokenType.PERIOD),
            new Token("z", TokenType.IDENTIFIER),
        }, expectedProgram);
    }

    @Test
    public void testParser_memberExpression_missingIdentifer() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.PERIOD),
            new Token("y", TokenType.STRING),
        }, null));
        assertEquals("Expected identifier after period in member expression", e.getMessage());
    }

    @Test
    public void testParser_memberExpression_missingClosingBracket() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> testProduceAST(new Token[]{
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_BRACKET),
            new Token("y", TokenType.STRING),
        }, null));
        assertEquals("Missing closing bracket for computed value", e.getMessage());
    }

    @Test
    public void testParser_callExpression() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new CallExpression(new Identifier("x"), new ArrayList<IExpression>()));
        testProduceAST(new Token[]{
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_PAREN),
            new Token(TokenType.CLOSE_PAREN),
        }, expectedProgram);
    }

    @Test
    public void testParser_callExpression_multipleCalls() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new CallExpression(
            new CallExpression(new Identifier("x"), new ArrayList<IExpression>()), 
            new ArrayList<IExpression>()));
        testProduceAST(new Token[]{
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_PAREN),
            new Token(TokenType.CLOSE_PAREN),
            new Token(TokenType.OPEN_PAREN),
            new Token(TokenType.CLOSE_PAREN),
        }, expectedProgram);
    }

    @Test
    public void testParser_callExpression_singleArgument() throws Exception {
        final ArrayList<IExpression> expectedArguments = new ArrayList<IExpression>();
        expectedArguments.add(new Identifier("y"));
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new CallExpression(new Identifier("x"), expectedArguments));
        testProduceAST(new Token[]{
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_PAREN),
            new Token("y", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_PAREN),
        }, expectedProgram);
    }

    @Test
    public void testParser_callExpression_multipleArguments() throws Exception {
        final ArrayList<IExpression> expectedArguments = new ArrayList<IExpression>();
        expectedArguments.add(new Identifier("y"));
        expectedArguments.add(new Identifier("z"));
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new CallExpression(new Identifier("x"), expectedArguments));
        testProduceAST(new Token[]{
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_PAREN),
            new Token("y", TokenType.IDENTIFIER),
            new Token(TokenType.COMMA),
            new Token("z", TokenType.IDENTIFIER),
            new Token(TokenType.CLOSE_PAREN),
        }, expectedProgram);
    }

    @Test
    public void testParser_callExpression_assignmentExpression() throws Exception {
        final ArrayList<IExpression> expectedArguments = new ArrayList<IExpression>();
        expectedArguments.add(new AssignmentExpression(new Identifier("y"), new NumericLiteral(Float.valueOf(2))));
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new CallExpression(new Identifier("x"), expectedArguments));
        testProduceAST(new Token[]{
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_PAREN),
            new Token("y", TokenType.IDENTIFIER),
            new Token(TokenType.EQUALS),
            new Token("2", TokenType.NUMBER),
            new Token(TokenType.CLOSE_PAREN),
        }, expectedProgram);
    }

    @Test
    public void testParser_negationExpression() throws Exception {
        final Program expectedProgram = new Program();
        expectedProgram.addStatement(new NegationExpression(
            new CallExpression(new Identifier("x"), new ArrayList<IExpression>())
        ));
        testProduceAST(new Token[]{
            new Token(TokenType.EXCLAIMATION_POINT),
            new Token("x", TokenType.IDENTIFIER),
            new Token(TokenType.OPEN_PAREN),
            new Token(TokenType.CLOSE_PAREN),
        }, expectedProgram);
    }
}
