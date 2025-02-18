package main.java.com.acacia.frontend.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import main.java.com.acacia.ast.FunctionDeclaration;
import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.IStatement;
import main.java.com.acacia.ast.ConditionalStatement;
import main.java.com.acacia.ast.ForStatement;
import main.java.com.acacia.ast.NodeType;
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
import main.java.com.acacia.frontend.IParser;
import main.java.com.acacia.frontend.token.Token;
import main.java.com.acacia.frontend.token.TokenType;

public class Parser implements IParser {
    private Queue<Token> tokens;

    @Override
    public Program produceAST(Queue<Token> tokens) throws Exception {
        this.tokens = tokens;
        final Program program = new Program();

        // Parse all of the source code
        while(hasNextToken()) {
            program.addStatement(parseStatement());
        }

        return program;
    }

    private boolean hasNextToken() throws Exception {
        if (this.tokens == null || this.tokens.isEmpty()) {
            throw new Exception("Unexpectedly ran out of tokens. No EOF found.");
        }
        return this.tokens.peek().getTokenType() != TokenType.EOF;
    }

    private Token next() throws Exception {
        if (this.tokens.isEmpty()) {
            throw new Exception("Unable to retrieve next token");
        }
        return this.tokens.poll();
    }

    private Token nextWithAssert(TokenType expectedTokenType, String errMessage) throws Exception {
        final Token nextToken = next();
        if (nextToken.getTokenType() != expectedTokenType) {
            throw new Exception(errMessage);
        }
        return nextToken;
    }

    private Token current() throws Exception {
        if (this.tokens.isEmpty()) {
            throw new Exception("Unable to retrieve current token");
        }
        return this.tokens.peek();
    }

    private IStatement parseStatement() throws Exception {
        switch (current().getTokenType()) {
            case VAR:
            case FINAL:
                return parseVariableDeclaration();
            case FUNCTION:
                return parseFunctionDeclaration();
            case IF:
                return parseConditionalStatement();
            case FOR:
                return parseForStatement();
            default:
                return parseExpression();
        }
    }

    private IStatement parseVariableDeclaration() throws Exception {
        final boolean isConstant = next().getTokenType() == TokenType.FINAL;
        final Token identifier = nextWithAssert(TokenType.IDENTIFIER, "Expected identifier after variable declaration keyword.");

        if (!hasNextToken()) {
            if (isConstant) {
                throw new Exception("Must assign value to constant variables.");
            }
            return new VariableDeclaration(isConstant, identifier.getValue());
        }

        nextWithAssert(TokenType.EQUALS, "Expected equals token after identifier in variable declaration.");

        final IExpression expression = parseExpression();
        return new VariableDeclaration(isConstant, identifier.getValue(), expression);
    }

    private IStatement parseFunctionDeclaration() throws Exception {
        // Eat the fn keyword
        next();

        final Token name = nextWithAssert(TokenType.IDENTIFIER, "Expected function to have declared name.");
        final FunctionDeclaration functionDeclaration = new FunctionDeclaration(name.getValue());

        final List<IExpression> args = parseArguments();
        for (final IExpression arg : args) {
            if (arg.getType() != NodeType.IDENTIFIER) {
                throw new Exception("Expected function parameter list to only contain identifiers.");
            }
            functionDeclaration.addParameter(((Identifier)arg).getValue());
        }

        nextWithAssert(TokenType.OPEN_BRACE, "Function declaration should contain open brace.");

        while(hasNextToken() && current().getTokenType() != TokenType.CLOSE_BRACE) {
            functionDeclaration.addStatementToBody(parseStatement());
        }

        nextWithAssert(TokenType.CLOSE_BRACE, "Function declaration should end with close brace.");
        return functionDeclaration;
    }

    private IStatement parseConditionalStatement() throws Exception {
        final ConditionalStatement conditional = new ConditionalStatement();

        TokenType curTokenType = current().getTokenType();
        while(hasNextToken() && (curTokenType == TokenType.IF || curTokenType == TokenType.ELIF || curTokenType == TokenType.ELSE)) {
            // Eat the keyword
            Token keyword = next();
    
            // The else block should always be taken if reached.
            final IExpression condition = parseCondition(keyword.getTokenType() == TokenType.ELSE, "Expected closing parenthesis after condition in conditional statement");
            final StatementBlock statementBlock = parseStatementBlock(
                "Expected open brace after condition in conditional statement.", 
                "Conditional statement should end in close brace."
                );

            conditional.addConditionAndStatementBlock(condition, statementBlock);
            curTokenType = current().getTokenType();
        }

        return conditional;
    }

    private IStatement parseForStatement() throws Exception {
        // Eat the for keyword
        next();

       final IExpression condition = parseCondition(false, "Expected closing parenthesis after condition in for statement.");
       final StatementBlock statementBlock = parseStatementBlock(
        "Expected open brace after condition in for statement.", 
        "For statement should end in close brace."
        );

        return new ForStatement(condition, statementBlock);
    }

    private IExpression parseCondition(boolean useBooleanLiteral, String missingCloseParenErrorMessage) throws Exception {
        boolean hasOpenParen = false;
        if (current().getTokenType() == TokenType.OPEN_PAREN) {
            // Eat the parenthesis.
            next();
            hasOpenParen = true;
        }

        final IExpression condition = useBooleanLiteral ? new BooleanLiteral(true) : parseRelationalExpression();

        if (hasOpenParen) {
            nextWithAssert(TokenType.CLOSE_PAREN, missingCloseParenErrorMessage);
        }

        return condition;
    }

    private StatementBlock parseStatementBlock(String missingOpenBraceErrorMessage, String missingCloseBraceErrorMessage) throws Exception {
        nextWithAssert(TokenType.OPEN_BRACE, missingOpenBraceErrorMessage);

        final StatementBlock statementBlock = new StatementBlock();
        while (hasNextToken() && current().getTokenType() != TokenType.CLOSE_BRACE) {
            statementBlock.add(parseStatement());
        }

        nextWithAssert(TokenType.CLOSE_BRACE, missingCloseBraceErrorMessage);
        return statementBlock;
    }

    // Orders of precidence
    // 1. Primary expression
    // 2. Member expression
    // 3. Call expression
    // 4. Relational
    // 5. Multiplicative
    // 6. Additive
    // 7. Object Expression
    // 8. Assignment Expression
    private IExpression parseExpression() throws Exception {
        return parseAssignmentExpression();
    }

    private IExpression parseAssignmentExpression() throws Exception {
        final IExpression left = parseObjectExpression();


        final TokenType currentTokenType = current().getTokenType();
        if (currentTokenType == TokenType.EQUALS) {
            // Eat the equals token.
            next();
            // Allow for chaining of assignment expressions. i.e. a = b = 2
            final IExpression value  = parseAssignmentExpression();
            return new AssignmentExpression(left, value);
        }

        if (currentTokenType == TokenType.INCREMENT) {
            // Eat the increment token.
            next();
            return new AssignmentExpression(left, new BinaryExpression(left, new NumericLiteral(Float.valueOf(1)), "+"));
        }

        if (currentTokenType == TokenType.DECREMENT) {
            // Eat the decrement token.
            next();
            return new AssignmentExpression(left, new BinaryExpression(left, new NumericLiteral(Float.valueOf(1)), "-"));
        }

        if (currentTokenType == TokenType.BINARY_OPERATOR_EQUALS) {
            final String operator = current().getValue();
            // Eat the binary operator equals token.
            next();
            final IExpression right = parseExpression();
            return new AssignmentExpression(left, new BinaryExpression(left, right, operator));
        }

        return left;
    }

    private IExpression parseObjectExpression() throws Exception {
        TokenType currentTokenType = current().getTokenType();
        if (currentTokenType != TokenType.OPEN_BRACE && currentTokenType != TokenType.ENUM) {
            // Not an object.
            return parseAdditiveExpression();
        }

        boolean isEnum = false;
        if (currentTokenType == TokenType.ENUM) {
            // Eat enum keyword
            next();
            isEnum = true;
        }
        // Eat open brace.
        next();

        final ObjectLiteral objectLiteral = new ObjectLiteral(isEnum);

        while(hasNextToken() && current().getTokenType() != TokenType.CLOSE_BRACE) {
            final Token key = nextWithAssert(TokenType.IDENTIFIER, "Expected key for object.");

            currentTokenType = current().getTokenType();
            // Handle shorthand {key,} or {key}
            if (currentTokenType == TokenType.COMMA || currentTokenType == TokenType.CLOSE_BRACE || isEnum) {
                // Eat the comma, if there is one.
                if (currentTokenType == TokenType.COMMA) next();
                objectLiteral.addProperty(new Property(key.getValue()));
                continue; 
            }

            // Handle {key: value} or {key: value,}
            nextWithAssert(TokenType.COLON, "Object expression should hava a colon following the key to declare a value.");
            final IExpression value = parseExpression();

            objectLiteral.addProperty(new Property(key.getValue(), value));

            // Eat the comma if there is one.
            if (current().getTokenType() == TokenType.COMMA) next();
        }

        nextWithAssert(TokenType.CLOSE_BRACE, "Object expression missing a closing brace.");
        return objectLiteral;

    }

    private IExpression parseAdditiveExpression() throws Exception {
        IExpression left = parseMultiplicativeExpression();

        String curValue = current().getValue();

        // Check if our current token is an additive operator
        while (current().getTokenType() == TokenType.BINARY_OPERATOR && (curValue.equals("+") || curValue.equals("-"))) {
            // Eat the operator token to advance to the right expression
            final String operator = next().getValue();
            final IExpression right = parseMultiplicativeExpression();
            left = new BinaryExpression(left, right, operator);
            curValue = current().getValue();
        }

        return left;
    }

    private IExpression parseMultiplicativeExpression() throws Exception {
        IExpression left = parseRelationalExpression();

        String curValue = current().getValue();

        // Check if our current token is an multiplicative operator
        while (current().getTokenType() == TokenType.BINARY_OPERATOR && (curValue.equals("*") || curValue.equals("/") || curValue.equals("%") || curValue.equals("^"))) {
            // Eat the operator token to advance to the right expression
            final String operator = next().getValue();
            final IExpression right = parseRelationalExpression();
            left = new BinaryExpression(left, right, operator);
            curValue = current().getValue();
        }

        return left;
    }

    private IExpression parseRelationalExpression() throws Exception {
        IExpression left = parseCallAndMemberExpressions();

        String curValue = current().getValue();

        // Check if our current token is an relational operator
        while (current().getTokenType() == TokenType.BINARY_OPERATOR && (curValue.equals("|") || curValue.equals("&") || curValue.equals(">") || curValue.equals("<") ||
        curValue.equals("<=") || curValue.equals(">=") || curValue.equals("==") || curValue.equals("!="))) {
            // Eat the operator token to advance to the right expression
            final String operator = next().getValue();
            final IExpression right = parseCallAndMemberExpressions();
            left = new BinaryExpression(left, right, operator);
            curValue = current().getValue();
        }

        return left;
    }

    private IExpression parseCallAndMemberExpressions() throws Exception {
        final IExpression member = parseMemberExpression();

        if (current().getTokenType() == TokenType.OPEN_PAREN) {
            return parseCallExpression(member);
        }

        return member;
    }

    private IExpression parseMemberExpression() throws Exception {
        IExpression object = this.parsePrimaryExpression();

        while(hasNextToken() && isMemberExpressionOperator()) {
            final Token operator = next();

            // Handle non-computed member expressions (i.e. x.member)
            if (operator.getTokenType() == TokenType.PERIOD) {
                final IExpression property = parsePrimaryExpression();
                if (property.getType() != NodeType.IDENTIFIER) {
                    throw new Exception("Expected identifier after period in member expression");
                }
                object = new MemberExpression(object, property, false);
                continue;
            }
            // Handle computed member expressions (i.e. x[member])
            object = new MemberExpression(object, parseExpression(), true);
            nextWithAssert(TokenType.CLOSE_BRACKET, "Missing closing bracket for computed value");
        }

        return object;
    }

    private boolean isMemberExpressionOperator() throws Exception {
        final TokenType currentTokenType = current().getTokenType();
        return currentTokenType == TokenType.PERIOD || currentTokenType == TokenType.OPEN_BRACKET;
    }

    
    private IExpression parsePrimaryExpression() throws Exception {
        final Token nextToken = next();

        switch (nextToken.getTokenType()) {
            case IDENTIFIER:
                return new Identifier(nextToken.getValue());
            case NUMBER:
                final String value = nextToken.getValue();
                try {
                    return new NumericLiteral(Float.valueOf(value).floatValue());
                } catch (Exception e) {
                    throw new Exception("Malformed number. " + value + " cannot be parsed.");
                }
            case STRING:
                return new StringLiteral(nextToken.getValue());
            case OPEN_PAREN:
                final IExpression contents = parseExpression();
                // Eat the close paren
                nextWithAssert(TokenType.CLOSE_PAREN, "Unexpected token. Expected closing parenthesis.");
                return contents;
            case TRUE:
            case FALSE:
                return new BooleanLiteral(nextToken.getTokenType() == TokenType.TRUE);
            case EXCLAIMATION_POINT:
                return new NegationExpression(parseCallAndMemberExpressions());
            case RETURN:
                if (current().getTokenType() == TokenType.CLOSE_BRACE) {
                    // Account for empty returns at the end of functions, if/else, etc.
                    return new ReturnExpression(null);
                }
                return new ReturnExpression(parseExpression());    
            default:
                throw new Exception("Unexpected token during parsing. Token type " + nextToken.getTokenType() + " is not a primary expression.");    
        }
    }

    private IExpression parseCallExpression(IExpression caller) throws Exception {
        final CallExpression callExpression = new CallExpression(caller, parseArguments());

        // Handle a case where this call expression is calling something else. i.e. x()()
        if (current().getTokenType() == TokenType.OPEN_PAREN) {
            return parseCallExpression(callExpression);
        }

        return callExpression;
    }

    private List<IExpression> parseArguments() throws Exception {
        nextWithAssert(TokenType.OPEN_PAREN, "Arguments list should start with an open parenthesis.");

        if (current().getTokenType() == TokenType.CLOSE_PAREN) {
            // Eat the closing parenthesis.
            next();
            return new ArrayList<IExpression>();
        }

        final ArrayList<IExpression> arguments = new ArrayList<IExpression>();
        arguments.add(parseAssignmentExpression());
        while(hasNextToken() && current().getTokenType() == TokenType.COMMA) {
            // Eat the comma.
            next();
            arguments.add(parseAssignmentExpression());
        }

        nextWithAssert(TokenType.CLOSE_PAREN, "Arguments should end with a closing parenthesis.");

        return arguments;
    }
}
