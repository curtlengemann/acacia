package main.java.com.acacia.runtime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.com.acacia.ast.ConditionalStatement;
import main.java.com.acacia.ast.ForStatement;
import main.java.com.acacia.ast.FunctionDeclaration;
import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.IStatement;
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
import main.java.com.acacia.runtime.values.BooleanValue;
import main.java.com.acacia.runtime.values.FunctionValue;
import main.java.com.acacia.runtime.values.IRuntimeValue;
import main.java.com.acacia.runtime.values.NativeFunctionValue;
import main.java.com.acacia.runtime.values.NullValue;
import main.java.com.acacia.runtime.values.NumberValue;
import main.java.com.acacia.runtime.values.ObjectValue;
import main.java.com.acacia.runtime.values.StringValue;
import main.java.com.acacia.runtime.values.ValueType;

public class Interpreter implements IInterpreter {
    // A tracker of how deep we are in the current call stack.
    private int currentDepth = 0;

    @Override
    public IRuntimeValue evaluate(IStatement statement, IEnvironment environment) throws Exception {
        switch (statement.getType()) {
            case NUMERIC_LITERAL:
                return new NumberValue(((NumericLiteral) statement).getValue());
            case BOOLEAN_LITERAL:
                return new BooleanValue(((BooleanLiteral)statement).getValue());
            case STRING_LITERAL:
                return new StringValue(((StringLiteral) statement).getValue());
            case BINARY_EXPRESSION:
                return evaluateBinaryExpression((BinaryExpression) statement, environment);
            case NEGATION_EXPRESSION:
                return evaluateNegationExpression((NegationExpression) statement, environment);
            case IDENTIFIER:
                return evaluateIdentifier((Identifier) statement, environment);
            case VAR_DECLARATION:
                return evaluateVariableDeclaration((VariableDeclaration) statement, environment);
            case FUNCTION_DECLARATION:
                return evaluateFunctionDeclaration((FunctionDeclaration) statement, environment);
            case CONDITONAL:
                return evaluateConditionalStatement((ConditionalStatement) statement, environment);
            case FOR:
                return evaluateForStatement((ForStatement) statement, environment);
            case ASSIGNMENT_EXPRESSION:
                return evaluateAssignmentExpression((AssignmentExpression) statement, environment);
            case OBJECT_LITERAL:
                return evaluateObjectLiteral((ObjectLiteral) statement, environment);
            case MEMBER_EXPRESSION:
                 return evaluateMemberExpression((MemberExpression) statement, environment);
            case CALL_EXPRESSION:
                return evaluateCallExpression((CallExpression)statement, environment);
            case STATEMENT_BLOCK:
                return evaluateStatementBlock((StatementBlock) statement, environment);
            case PROGRAM:
                return evaluateProgram((Program) statement, environment);
            default:
                throw new Exception("Unrecognized statement in interpreter. Cannot interpret " + statement.getType() + "\n" + statement.toString());
        }
    }

    private IRuntimeValue evaluateBinaryExpression(BinaryExpression expression, IEnvironment environment) throws Exception {
        final IRuntimeValue lhs = evaluate(expression.getLeft(), environment);
        final IRuntimeValue rhs = evaluate(expression.getRight(), environment);

        if (lhs.getRuntimeType() == ValueType.NULL || rhs.getRuntimeType() == ValueType.NULL) {
            // Anything operated on by null is null. i.e. null + 1 = null
            return new NullValue();
        }

        if (lhs.getRuntimeType() == ValueType.STRING || rhs.getRuntimeType() == ValueType.STRING) {
            return evaluateStringBinaryExpression(lhs.getStringValue(), rhs.getStringValue(), expression.getOperator());
        }

        if (lhs.getRuntimeType() == ValueType.NUMBER && rhs.getRuntimeType() == ValueType.NUMBER) {
            return evaluateNumericBinaryExpression((NumberValue) lhs, (NumberValue) rhs, expression.getOperator());
        }

        if (lhs.getRuntimeType() == ValueType.BOOLEAN && rhs.getRuntimeType() == ValueType.BOOLEAN) {
            return evaluateBooleanBinaryExpression((BooleanValue) lhs, (BooleanValue) rhs, expression.getOperator());
        }

        throw new Exception("Invalid binary expression. Cannot evaluate expression for " + lhs.getRuntimeType() + " and " + rhs.getRuntimeType());
    }

    private IRuntimeValue evaluateStringBinaryExpression(String lhs, String rhs, String operator) throws Exception {
        switch (operator) {
            case "+":
                return new StringValue(lhs + rhs);
            case "-":
                return new StringValue(lhs.replace(rhs, ""));
            case ">":
                return new BooleanValue(lhs.compareTo(rhs) > 0);
            case "<":
                return new BooleanValue(lhs.compareTo(rhs) < 0);
            case ">=":
                return new BooleanValue(lhs.compareTo(rhs) >= 0);
            case "<=":
                return new BooleanValue(lhs.compareTo(rhs) <= 0);
            case "==":
                return new BooleanValue(lhs.equals(rhs));
            case "!=":
                return new BooleanValue(!lhs.equals(rhs)); 
            default:
                throw new Exception("Unrecognized binary operator for string binary operation: " + operator);
        }
    }

    private IRuntimeValue evaluateNumericBinaryExpression(NumberValue lhs, NumberValue rhs, String operator) throws Exception {
        switch (operator) {
            case "+":
                return new NumberValue(lhs.getValue() + rhs.getValue());
            case "-":
                return new NumberValue(lhs.getValue() - rhs.getValue());
            case "*":
                return new NumberValue(lhs.getValue() * rhs.getValue());
            case "/":
                if (rhs.getValue() == 0) {
                    throw new Exception("Division by 0 error. The right hand side of this binary expression cannot be 0.");
                }
                return new NumberValue(lhs.getValue() / rhs.getValue());
            case "%":
                if (rhs.getValue() == 0) {
                    throw new Exception("Division by 0 error. The right hand side of this binary expression cannot be 0.");
                }
                return new NumberValue(lhs.getValue() % rhs.getValue());
            case "^":
                return new NumberValue(Float.valueOf((float) Math.pow(lhs.getValue(), rhs.getValue())));
            case ">":
                return new BooleanValue(lhs.getValue() > rhs.getValue());
            case "<":
                return new BooleanValue(lhs.getValue() < rhs.getValue());
            case ">=":
                return new BooleanValue(lhs.getValue() >= rhs.getValue());
            case "<=":
                return new BooleanValue(lhs.getValue() <= rhs.getValue());
            case "==":
                return new BooleanValue(lhs.getValue().equals(rhs.getValue()));
            case "!=":
                return new BooleanValue(!lhs.getValue().equals(rhs.getValue())); 
            default:
                throw new Exception("Unrecognized binary operator for numeric binary operation: " + operator);
        }
    }

    private IRuntimeValue evaluateBooleanBinaryExpression(BooleanValue lhs, BooleanValue rhs, String operator) throws Exception {
        switch (operator) {
            case "|":
                return new BooleanValue(lhs.getValue() || rhs.getValue());
            case "&":
                return new BooleanValue(lhs.getValue() && rhs.getValue());
            case "==":
                return new BooleanValue(lhs.getValue() == rhs.getValue());
            case "!=":
                return new BooleanValue(lhs.getValue() != rhs.getValue());    
            default:
                throw new Exception("Unrecognized binary operator for boolean binary operation: " + operator);
        }
    }

    private IRuntimeValue evaluateNegationExpression(NegationExpression negationExpression, IEnvironment environment) throws Exception {
        final IRuntimeValue value = evaluate(negationExpression.getValue(), environment);
        if (value.getRuntimeType() != ValueType.BOOLEAN) {
            throw new Exception("Invalid negation expression. Can only negate booleans.");
        }
        final BooleanValue bool = (BooleanValue) value;
        return new BooleanValue(!bool.getValue());
    }

    private IRuntimeValue evaluateIdentifier(Identifier identifier, IEnvironment environment) throws Exception {
        return environment.lookupVar(identifier.getValue());
    }

    private IRuntimeValue evaluateVariableDeclaration(VariableDeclaration varDeclaration, IEnvironment environment) throws Exception {
        final IRuntimeValue value = varDeclaration.hasExpression() ? evaluate(varDeclaration.getExpression(), environment) : new NullValue();
        return environment.declareVar(varDeclaration.getIdentifier(), value, varDeclaration.isConstant());
    }

    private IRuntimeValue evaluateFunctionDeclaration(FunctionDeclaration functionDeclaration, IEnvironment environment) throws Exception {
        final FunctionValue func = new FunctionValue(functionDeclaration.getParameters(), functionDeclaration.getBody(), environment);
        return environment.declareVar(functionDeclaration.getName(), func, false);
    }

    private IRuntimeValue evaluateConditionalStatement(ConditionalStatement conditionalStatement, IEnvironment environment) throws Exception {
        final List<IExpression> conditions = conditionalStatement.getConditions();
        final List<StatementBlock> statementBlocks = conditionalStatement.getStatements();

        if (conditions.size() != statementBlocks.size()) {
            throw new Exception("Must have same number of conditions as statement blocks in conditional statement.");
        }

        for (int i = 0; i < conditions.size(); i++) {
            final IRuntimeValue evaluatedCondition = evaluate(conditions.get(i), environment);
            if (evaluatedCondition.getRuntimeType() != ValueType.BOOLEAN) {
                throw new Exception("Evaluted condition in conditional statement is not BOOLEAN type.");
            }

            BooleanValue evaluatedBooleanValue = (BooleanValue) evaluatedCondition;
            if (evaluatedBooleanValue.getValue()) {
                final IEnvironment scope = new Environment(environment);
                return evaluate(statementBlocks.get(i), scope);
            }
        }
        // No conditions were satisfied.
        return new NullValue();
    }

    private IRuntimeValue evaluateForStatement(ForStatement forStatement, IEnvironment environment) throws Exception {
        final IExpression condition = forStatement.getCondition();
        final StatementBlock statementBlock = forStatement.getStatementBlock();

        IRuntimeValue lastEvaluatedValue = new NullValue();

        final int startingDepth = currentDepth;
        // Verify we haven't hit a return in our loop that causes us to stop execution of the loop.
        while (!haveReturned(startingDepth)) {
            final IRuntimeValue evaluatedCondition = evaluate(condition, environment);
            if (evaluatedCondition.getRuntimeType() != ValueType.BOOLEAN) {
                throw new Exception("Evaluted condition in for statement is not BOOLEAN type.");
            }

            BooleanValue evaluatedBooleanValue = (BooleanValue) evaluatedCondition;
            if (!evaluatedBooleanValue.getValue()) {
                break;
            }
            
            final IEnvironment scope = new Environment(environment);
            lastEvaluatedValue = evaluate(statementBlock, scope);
        }

        return lastEvaluatedValue;
    }

    private IRuntimeValue evaluateAssignmentExpression(AssignmentExpression assignmentExpression, IEnvironment environment) throws Exception {
        final IExpression assignee = assignmentExpression.getAssignee();
        switch (assignee.getType()) {
            case IDENTIFIER:
                final Identifier identifier = (Identifier) assignee;
                return environment.assignVar(identifier.getValue(), evaluate(assignmentExpression.getValue(), environment));
            case MEMBER_EXPRESSION:
                final MemberExpression memberExpression = (MemberExpression) assignee;
                final ObjectValue objectValue = getMemberExpressionObjectValue(memberExpression, environment);
                if (objectValue.isEnum()) {
                    throw new Exception("Invalid assignment. Cannot assign to enum value.");
                }
                final String propertyKey = getMemberExpressionPropertyKey(memberExpression.getProperty(), environment, memberExpression.isComputed());
                return objectValue.addProperty(propertyKey, evaluate(assignmentExpression.getValue(), environment));
            default:
                throw new Exception("Invalid assignment. Must assign to identifier or member expression");
        }
    }

    private IRuntimeValue evaluateObjectLiteral(ObjectLiteral object, IEnvironment environment) throws Exception {
        final boolean isEnum = object.isEnum();
        final ObjectValue objectValue = new ObjectValue(isEnum);
        int i = 0;
        for (final Property p : object.getProperties()) {
            final String key = p.getKey();
            final IExpression value = p.getValue();
            if (isEnum) {
                objectValue.addProperty(key, new NumberValue(Float.valueOf(i++)));
                continue;
            }
            final IRuntimeValue runtimeValue = value == null ? environment.lookupVar(key) : evaluate(value, environment);
            objectValue.addProperty(key, runtimeValue);
        }
        return objectValue;
    }

    private IRuntimeValue evaluateMemberExpression(MemberExpression memberExpression, IEnvironment environment) throws Exception {
        final ObjectValue objValue = getMemberExpressionObjectValue(memberExpression, environment);
        final String property = getMemberExpressionPropertyKey(memberExpression.getProperty(), environment, memberExpression.isComputed());
        return objValue.getProperty(property);
    }

    private ObjectValue getMemberExpressionObjectValue(MemberExpression memberExpression, IEnvironment environment) throws Exception {
        final IRuntimeValue objectValue = evaluate(memberExpression.getObject(), environment);
        if (objectValue.getRuntimeType() != ValueType.OBJECT) {
            throw new Exception("Invalid member expression. The object of the member expression must be an object literal.");
        }

        return (ObjectValue) objectValue;
    }

    private String getMemberExpressionPropertyKey(IExpression propertyExpression, IEnvironment environment, boolean isComputed) throws Exception {
        if (propertyExpression == null) {
            throw new Exception("Invalid member expression. Must have property.");
        }

        if (!isComputed) {
            if (propertyExpression.getType() != NodeType.IDENTIFIER) {
                throw new Exception("Invalid property of non-computed member expression. Needs to have identifier property.");
            }
            return ((Identifier)propertyExpression).getValue();
        }

        final IRuntimeValue propertyValue = evaluate(propertyExpression, environment);
        if (propertyValue.getRuntimeType() != ValueType.STRING) {
            throw new Exception("Invalid property of computed member expression. Needs to be of type string.");
        }

        return ((StringValue)propertyValue).getValue();
    }

    private IRuntimeValue evaluateCallExpression(CallExpression callExpression, IEnvironment environment) throws Exception {
        final ArrayList<IRuntimeValue> args = new ArrayList<IRuntimeValue>();
        final Iterator<IExpression> argsIterator = callExpression.getArgumentsIterator();
        while (argsIterator != null && argsIterator.hasNext()) {
            args.add(evaluate(argsIterator.next(), environment));
        }

        final IRuntimeValue function = evaluate(callExpression.getCaller(), environment);

        if (function.getRuntimeType() == ValueType.NATIVE_FUNCTION) {
            return ((NativeFunctionValue)function).evaluate(args);
        }

        if (function.getRuntimeType() == ValueType.FUNCTION) {
            final FunctionValue functionValue = (FunctionValue) function;
            final IEnvironment scope = new Environment(functionValue.getDeclarationEnvironment());
            final List<String> parameters = functionValue.getParameters();

            if (parameters.size() != args.size()) {
                throw new Exception("Different number of arguments than declared. Has " + args.size() + ", expected " + parameters.size());
            }

            for (int i = 0; i < parameters.size(); i++) {
                scope.declareVar(parameters.get(i), args.get(i), false);
            }

            currentDepth++;
            return evaluate(functionValue.getBody(), scope);
        }

        throw new Exception("Must call function in call expression. Tried to call: " + function.getRuntimeType());
    }

    private IRuntimeValue evaluateStatementBlock(StatementBlock statementBlock, IEnvironment environment) throws Exception {
        IRuntimeValue lastEvaluatedValue = new NullValue();
        final int startingDepth = currentDepth;

        final List<IStatement> statements = statementBlock.getStatements();

        for(IStatement s : statements) {
            if (haveReturned(startingDepth)) {
                // No need to process more statemtents as we have hit a return in this method.
                // return lastEvaluatedValue;
                return lastEvaluatedValue;
            }

            if (s.getType() == NodeType.RETURN_EXPRESSION) {
                // Handle return statements. If the return has a value, return that. Otherwise just return what we previously evaluated.
                final ReturnExpression returnExpression = (ReturnExpression) s;
                if (returnExpression.hasValue()) {
                    final IRuntimeValue evaluatedReturnExpression = evaluate(returnExpression.getValue(), environment);
                    currentDepth--;
                    return evaluatedReturnExpression;
                }
                currentDepth--;
                return lastEvaluatedValue;
            }
            lastEvaluatedValue = evaluate(s, environment);
        }
        return lastEvaluatedValue;
    }

    private IRuntimeValue evaluateProgram(Program program, IEnvironment environment) throws Exception {
        return evaluate(program.getStatementBlock(), environment);
    }

    private boolean haveReturned(int startingDepth) {
        return startingDepth > currentDepth;
    }
}
