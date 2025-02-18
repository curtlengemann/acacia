package test.java.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;

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
import main.java.com.acacia.ast.literals.NullLiteral;
import main.java.com.acacia.ast.literals.NumericLiteral;
import main.java.com.acacia.ast.literals.ObjectLiteral;
import main.java.com.acacia.ast.literals.Property;
import main.java.com.acacia.ast.literals.StringLiteral;
import main.java.com.acacia.runtime.Environment;
import main.java.com.acacia.runtime.Interpreter;
import main.java.com.acacia.runtime.values.BooleanValue;
import main.java.com.acacia.runtime.values.FunctionValue;
import main.java.com.acacia.runtime.values.NullValue;
import main.java.com.acacia.runtime.values.NumberValue;
import main.java.com.acacia.runtime.values.ObjectValue;
import main.java.com.acacia.runtime.values.StringValue;

public class InterpreterTest {
    private Interpreter interpreter;
    private Environment parentEnvironment;
    private Identifier x;
    private NumberValue xValue;

    @Before
    public void setUp() throws Exception {
        interpreter = new Interpreter();
        parentEnvironment = new Environment(null);
        x = new Identifier("x");
        xValue = new NumberValue(Float.valueOf(100));
        parentEnvironment.declareVar(x.getValue(), xValue, false);
    }

    @Test
    public void testInterpreter_numericLiteral() throws Exception {
        assertEquals(new NumberValue(Float.valueOf(2)), interpreter.evaluate(new NumericLiteral(Float.valueOf(2)), null));
    }

    @Test
    public void testInterpreter_stringLiteral() throws Exception {
        assertEquals(new StringValue("hello world"), interpreter.evaluate(new StringLiteral("hello world"), null));
    }

    @Test
    public void testInterpreter_binaryExpression_null() throws Exception {
        assertEquals(new NullValue(), interpreter.evaluate(
            new BinaryExpression(
                new Identifier("null"),
                new StringLiteral("hello"),
                "+"
                ), parentEnvironment
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_string_additon() throws Exception {
        assertEquals(new StringValue("hellohello"), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("hello"),
                new StringLiteral("hello"),
                "+"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_number_additon() throws Exception {
        assertEquals(new StringValue("hello3"), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("hello"),
                new NumericLiteral(Float.valueOf(3)),
                "+"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_numberWithDecimal_additon() throws Exception {
        assertEquals(new StringValue("hello3.2"), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("hello"),
                new NumericLiteral(Float.valueOf("3.2")),
                "+"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_object_additon() throws Exception {
        assertEquals(new StringValue("helloOBJECT"), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("hello"),
                new ObjectLiteral(false),
                "+"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_enum_addtion() throws Exception {
        assertEquals(new StringValue("helloENUM"), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("hello"),
                new ObjectLiteral(true),
                "+"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_boolean_additon() throws Exception {
        assertEquals(new StringValue("hellotrue"), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("hello"),
                new BooleanLiteral(true),
                "+"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_string_subtraction() throws Exception {
        assertEquals(new StringValue(" world"), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("hello world"),
                new StringLiteral("hello"),
                "-"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_number_subtraction() throws Exception {
        assertEquals(new StringValue("hello world"), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("hello world3"),
                new NumericLiteral(Float.valueOf(3)),
                "-"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_object_subtraction() throws Exception {
        assertEquals(new StringValue("hello world"), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("hello world"),
                new ObjectLiteral(false),
                "-"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_boolean_subtraction() throws Exception {
        assertEquals(new StringValue("hello world"), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("hello worldfalse"),
                new BooleanLiteral(false),
                "-"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_greaterThan() throws Exception {
        assertEquals(new BooleanValue(false), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("a"),
                new StringLiteral("b"),
                ">"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_lessThan() throws Exception {
        assertEquals(new BooleanValue(true), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("a"),
                new StringLiteral("b"),
                "<"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_greaterThanEqual() throws Exception {
        assertEquals(new BooleanValue(false), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("a"),
                new StringLiteral("b"),
                ">="
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_lessThanEqual() throws Exception {
        assertEquals(new BooleanValue(true), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("b"),
                new StringLiteral("b"),
                "<="
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_equal() throws Exception {
        assertEquals(new BooleanValue(true), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("b"),
                new StringLiteral("b"),
                "=="
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_notEqual() throws Exception {
        assertEquals(new BooleanValue(false), interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("b"),
                new StringLiteral("b"),
                "!="
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_string_unrecognizedOperator() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new BinaryExpression(
                new StringLiteral("hello world"),
                new NumericLiteral(Float.valueOf(0)),
                ":"
                ), null
            )
        );
        assertEquals("Unrecognized binary operator for string binary operation: :", e.getMessage());
    }

    @Test
    public void testInterpreter_binaryExpression_boolean_and() throws Exception {
        assertEquals(new BooleanValue(false), interpreter.evaluate(
            new BinaryExpression(
                new BooleanLiteral(true),
                new BooleanLiteral(false),
                "&"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_boolean_or() throws Exception {
        assertEquals(new BooleanValue(true), interpreter.evaluate(
            new BinaryExpression(
                new BooleanLiteral(true),
                new BooleanLiteral(false),
                "|"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_boolean_equals() throws Exception {
        assertEquals(new BooleanValue(false), interpreter.evaluate(
            new BinaryExpression(
                new BooleanLiteral(true),
                new BooleanLiteral(false),
                "=="
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_boolean_notEquals() throws Exception {
        assertEquals(new BooleanValue(true), interpreter.evaluate(
            new BinaryExpression(
                new BooleanLiteral(true),
                new BooleanLiteral(false),
                "!="
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_addition() throws Exception {
        assertEquals(new NumberValue(Float.valueOf(5)), interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(2)),
                new NumericLiteral(Float.valueOf(3)),
                "+"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_subtraction() throws Exception {
        assertEquals(new NumberValue(Float.valueOf(-1)), interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(2)),
                new NumericLiteral(Float.valueOf(3)),
                "-"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_multiplication() throws Exception {
        assertEquals(new NumberValue(Float.valueOf(6)), interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(2)),
                new NumericLiteral(Float.valueOf(3)),
                "*"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_division() throws Exception {
        assertEquals(new NumberValue(Float.valueOf(2)), interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(6)),
                new NumericLiteral(Float.valueOf(3)),
                "/"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_division_byZero() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(6)),
                new NumericLiteral(Float.valueOf(0)),
                "/"
                ), null
            )
        );
        assertEquals("Division by 0 error. The right hand side of this binary expression cannot be 0.", e.getMessage());
    }

    @Test
    public void testInterpreter_binaryExpression_modulo() throws Exception {
        assertEquals(new NumberValue(Float.valueOf(0)), interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(6)),
                new NumericLiteral(Float.valueOf(3)),
                "%"
                ), null
            )
        );
    }

    
    @Test
    public void testInterpreter_binaryExpression_modulo_byZero() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(6)),
                new NumericLiteral(Float.valueOf(0)),
                "%"
                ), null
            )
        );
        assertEquals("Division by 0 error. The right hand side of this binary expression cannot be 0.", e.getMessage());
    }

    @Test
    public void testInterpreter_binaryExpression_power() throws Exception {
        assertEquals(new NumberValue(Float.valueOf(16)), interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(2)),
                new NumericLiteral(Float.valueOf(4)),
                "^"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_greaterThan() throws Exception {
        assertEquals(new BooleanValue(false), interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(2)),
                new NumericLiteral(Float.valueOf(4)),
                ">"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_lessThan() throws Exception {
        assertEquals(new BooleanValue(true), interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(2)),
                new NumericLiteral(Float.valueOf(4)),
                "<"
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_greaterThanEqual() throws Exception {
        assertEquals(new BooleanValue(true), interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(4)),
                new NumericLiteral(Float.valueOf(4)),
                ">="
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_lessThanEqual() throws Exception {
        assertEquals(new BooleanValue(true), interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(4)),
                new NumericLiteral(Float.valueOf(4)),
                "<="
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_equal() throws Exception {
        assertEquals(new BooleanValue(true), interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(4)),
                new NumericLiteral(Float.valueOf(4)),
                "=="
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_notEqual() throws Exception {
        assertEquals(new BooleanValue(false), interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(4)),
                new NumericLiteral(Float.valueOf(4)),
                "!="
                ), null
            )
        );
    }

    @Test
    public void testInterpreter_binaryExpression_unrecognizedOperator() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new BinaryExpression(
                new NumericLiteral(Float.valueOf(6)),
                new NumericLiteral(Float.valueOf(0)),
                ":"
                ), null
            )
        );
        assertEquals("Unrecognized binary operator for numeric binary operation: :", e.getMessage());
    }

    @Test
    public void testInterpreter_binaryExpression_invalidTypes() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new BinaryExpression(
                new ObjectLiteral(false),
                new NumericLiteral(Float.valueOf(0)),
                ":"
                ), null
            )
        );
        assertEquals("Invalid binary expression. Cannot evaluate expression for OBJECT and NUMBER", e.getMessage());
    }

    @Test
    public void testInterpreter_negationExpression() throws Exception {
        parentEnvironment.declareVar("y", new BooleanValue(true), false);
        assertEquals(new BooleanValue(false), interpreter.evaluate(new NegationExpression(new Identifier("y")), parentEnvironment));
    }

    @Test
    public void testInterpreter_negationExpression_invalid() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new NegationExpression(new NumericLiteral(null)), null
        ));
        assertEquals("Invalid negation expression. Can only negate booleans.", e.getMessage());
    }

    @Test
    public void testInterpreter_identifier() throws Exception {
        assertEquals(xValue, interpreter.evaluate(new Identifier("x"), parentEnvironment));
    }

    @Test
    public void testInterpreter_variableDeclaration() throws Exception {
        assertEquals(new NullValue(), interpreter.evaluate(new VariableDeclaration(false, "y"), parentEnvironment));
    }

    @Test
    public void testInterpreter_variableDeclaration_withExpression() throws Exception {
        assertEquals(new NumberValue(Float.valueOf(4)), interpreter.evaluate(
            new VariableDeclaration(false, "y", new NumericLiteral(Float.valueOf(4))), parentEnvironment));
    }

    @Test
    public void testInterpreter_functionDeclaration_withExpression() throws Exception {
        final FunctionDeclaration f = new FunctionDeclaration("a");
        f.addParameter("b");
        f.addParameter("c");
        f.addStatementToBody(new NullLiteral());
        assertEquals(new FunctionValue(f.getParameters(), f.getBody(), parentEnvironment), interpreter.evaluate(f, parentEnvironment));
    }

    @Test
    public void testInterpreter_assignmentExpression() throws Exception {
        assertEquals(new NumberValue(Float.valueOf(2)), interpreter.evaluate(new AssignmentExpression(x,  new NumericLiteral(Float.valueOf(2))), parentEnvironment));
    }

    @Test
    public void testInterpreter_assignmentExpression_member_nonComputed() throws Exception {
        final ObjectValue obj = new ObjectValue(false);
        obj.addProperty(x.getValue(), xValue);
        parentEnvironment.declareVar("obj", obj, false);
        final MemberExpression memberExpression = new MemberExpression(new Identifier("obj"), x, false);
        assertEquals(new NumberValue(Float.valueOf(2)), interpreter.evaluate(new AssignmentExpression(memberExpression, new NumericLiteral(Float.valueOf(2))), parentEnvironment));
        assertEquals(new NumberValue(Float.valueOf(2)), obj.getProperty(x.getValue()));
    }

    @Test
    public void testInterpreter_assignmentExpression_member_computed() throws Exception {
        final ObjectValue obj = new ObjectValue(false);
        obj.addProperty(x.getValue(), xValue);
        parentEnvironment.declareVar("obj", obj, false);
        final MemberExpression memberExpression = new MemberExpression(new Identifier("obj"), new StringLiteral(x.getValue()), true);
        assertEquals(new NumberValue(Float.valueOf(2)), interpreter.evaluate(new AssignmentExpression(memberExpression,  new NumericLiteral(Float.valueOf(2))), parentEnvironment));
        assertEquals(new NumberValue(Float.valueOf(2)), obj.getProperty(x.getValue()));
    }

    @Test
    public void testInterpreter_assignmentExpression_member_nested() throws Exception {
        final ObjectValue internalObj = new ObjectValue(false);
        internalObj.addProperty(x.getValue(), xValue);
        final ObjectValue obj = new ObjectValue(false);
        obj.addProperty("internal", internalObj);
        parentEnvironment.declareVar("obj", obj, false);
        final MemberExpression memberExpression = new MemberExpression(
            new MemberExpression(new Identifier("obj"), new StringLiteral("internal"), true),
            x,
            false
        );
        assertEquals(new StringValue("hello"), interpreter.evaluate(new AssignmentExpression(memberExpression, new StringLiteral("hello")), parentEnvironment));
        assertEquals(new StringValue("hello"), internalObj.getProperty(x.getValue()));
    }

    @Test
    public void testInterpreter_assignmentExpression_invalidAssignee() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new AssignmentExpression(new NullLiteral(),  null), null)
        );
        assertEquals("Invalid assignment. Must assign to identifier or member expression", e.getMessage());
    }

    @Test
    public void testInterpreter_assignmentExpression_invalidAssignee_enum() throws Exception {
        final ObjectValue en = new ObjectValue(true);
        en.addProperty("x", new NumberValue(Float.valueOf(0)));
        parentEnvironment.declareVar("e", en, false);
        final MemberExpression memberExpression = new MemberExpression(new Identifier("e"), new NumericLiteral(Float.valueOf(2)), false);
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new AssignmentExpression(memberExpression,  null), parentEnvironment)
        );
        assertEquals("Invalid assignment. Cannot assign to enum value.", e.getMessage());
    }

    @Test
    public void testInterpreter_objectLiteral_noValue() throws Exception {
        final ObjectLiteral object = new ObjectLiteral(false);
        object.addProperty(new Property(x.getValue()));

        final ObjectValue expected = new ObjectValue(false);
        expected.addProperty(x.getValue(), xValue);
        assertEquals(expected, interpreter.evaluate(object, parentEnvironment));
    }

    @Test
    public void testInterpreter_objectLiteral_noValue_invalidIdentifier() throws Exception {
        final ObjectLiteral object = new ObjectLiteral(false);
        object.addProperty(new Property("random"));
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(object, parentEnvironment));
        assertEquals("Cannot resolve variable: random. It doesn't exist.", e.getMessage());
    }

    
    @Test
    public void testInterpreter_objectLiteral_value() throws Exception {
        final ObjectLiteral object = new ObjectLiteral(false);
        object.addProperty(new Property("asdf", new NumericLiteral(Float.valueOf(2))));

        final ObjectValue expected = new ObjectValue(false);
        expected.addProperty("asdf", new NumberValue(Float.valueOf(2)));
        assertEquals(expected, interpreter.evaluate(object, parentEnvironment));
    }

    @Test
    public void testInterpreter_objectLiteral_enum() throws Exception {
        final ObjectLiteral e = new ObjectLiteral(true);
        e.addProperty(new Property("monday"));

        final ObjectValue expected = new ObjectValue(true);
        expected.addProperty("monday", new NumberValue(Float.valueOf(0)));
        assertEquals(expected, interpreter.evaluate(e, parentEnvironment));
    }

    @Test
    public void testInterpreter_memberExpression_notComputed() throws Exception {
        final ObjectValue object = new ObjectValue(false);
        object.addProperty("asdf", new NumberValue(Float.valueOf(2)));
        parentEnvironment.declareVar("obj", object, false);

        final MemberExpression memberExpression = new MemberExpression(new Identifier("obj"), new Identifier("asdf"), false);
        assertEquals(new NumberValue(Float.valueOf(2)), interpreter.evaluate(memberExpression, parentEnvironment));
    }

    @Test
    public void testInterpreter_memberExpression_computed() throws Exception {
        final ObjectValue object = new ObjectValue(false);
        object.addProperty("asdf", new NumberValue(Float.valueOf(2)));
        parentEnvironment.declareVar("obj", object, false);

        final MemberExpression memberExpression = new MemberExpression(new Identifier("obj"), new StringLiteral("asdf"), true);
        assertEquals(new NumberValue(Float.valueOf(2)), interpreter.evaluate(memberExpression, parentEnvironment));
    }

    
    @Test
    public void testInterpreter_memberExpression_nested() throws Exception {
        final ObjectValue internalObject = new ObjectValue(false);
        internalObject.addProperty("asdf", new NumberValue(Float.valueOf(2)));
        final ObjectValue object = new ObjectValue(false);
        object.addProperty("internal", internalObject);
        parentEnvironment.declareVar("obj", object, false);

        final MemberExpression memberExpression = new MemberExpression(
            new MemberExpression(new Identifier("obj"), new Identifier("internal"), false),
            new StringLiteral("asdf"),
            true
        );
        assertEquals(new NumberValue(Float.valueOf(2)), interpreter.evaluate(memberExpression, parentEnvironment));
    }

        
    @Test
    public void testInterpreter_memberExpression_invalidObject() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new MemberExpression(new NumericLiteral(null),  null, false), null)
        );
        assertEquals("Invalid member expression. The object of the member expression must be an object literal.", e.getMessage());
    }

    @Test
    public void testInterpreter_memberExpression_invalidNullProperty() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new MemberExpression(new ObjectLiteral(false),  null, false), null)
        );
        assertEquals("Invalid member expression. Must have property.", e.getMessage());
    }

    @Test
    public void testInterpreter_memberExpression_notComputed_invalidPropertyType() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new MemberExpression(new ObjectLiteral(false),  new StringLiteral("asdf"), false), null)
        );
        assertEquals("Invalid property of non-computed member expression. Needs to have identifier property.", e.getMessage());
    }

    @Test
    public void testInterpreter_memberExpression_computed_invalidPropertyType() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new MemberExpression(new ObjectLiteral(false),  new NumericLiteral(null), true), null)
        );
        assertEquals("Invalid property of computed member expression. Needs to be of type string.", e.getMessage());
    }

    @Test
    public void testInterpreter_callExpression_nativeFunction() throws Exception {
        final ArrayList<IExpression> args = new ArrayList<IExpression>();
        args.add(x);
        
        final CallExpression callExpression = new CallExpression(new Identifier("print"), args);
        assertEquals(new NullValue(), interpreter.evaluate(callExpression, parentEnvironment));
    }

    @Test
    public void testInterpreter_callExpression_function() throws Exception {
        final ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("y");
        final StatementBlock body = new StatementBlock();
        body.add(new ReturnExpression(new Identifier("y")));
        parentEnvironment.declareVar("myFunc", new FunctionValue(parameters, body, new Environment(parentEnvironment)), false);
        final ArrayList<IExpression> args = new ArrayList<IExpression>();
        args.add(new NumericLiteral(Float.valueOf(2)));
        
        final CallExpression callExpression = new CallExpression(new Identifier("myFunc"), args);
        assertEquals(new NumberValue(Float.valueOf(2)), interpreter.evaluate(callExpression, parentEnvironment));
    }


    @Test
    public void testInterpreter_callExpression_function_invalidParameterLength() throws Exception {
        final ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("y");
        final StatementBlock body = new StatementBlock();
        body.add(new ReturnExpression(new Identifier("y")));
        parentEnvironment.declareVar("myFunc", new FunctionValue(parameters, body, new Environment(parentEnvironment)), false);
        final ArrayList<IExpression> args = new ArrayList<IExpression>();
        args.add(new NumericLiteral(Float.valueOf(2)));
        args.add(new NumericLiteral(Float.valueOf(3)));
        
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new CallExpression(new Identifier("myFunc"), args), parentEnvironment)
        );
        assertEquals("Different number of arguments than declared. Has 2, expected 1", e.getMessage());
    }
    @Test
    public void testInterpreter_callExpression_invalid() throws Exception {        
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(
            new CallExpression(new ObjectLiteral(false),  null), null)
        );
        assertEquals("Must call function in call expression. Tried to call: OBJECT", e.getMessage());
    }

    @Test
    public void testInterpreter_conditionalStatement() throws Exception {
        final ConditionalStatement cs = new ConditionalStatement();
        final StatementBlock s = new StatementBlock();
        s.add(new Identifier("x"));
        cs.addConditionAndStatementBlock(new BooleanLiteral(true), s);
        assertEquals(new NumberValue(Float.valueOf(100)), interpreter.evaluate(cs, parentEnvironment));
    }

    @Test
    public void testInterpreter_conditionalStatement_multipleConditions() throws Exception {
        final ConditionalStatement cs = new ConditionalStatement();
        final StatementBlock s = new StatementBlock();
        s.add(new NullLiteral());
        cs.addConditionAndStatementBlock(new BooleanLiteral(false), s);

        final StatementBlock s2 = new StatementBlock();
        s2.add(new Identifier("x"));
        cs.addConditionAndStatementBlock(new BooleanLiteral(true), s2);
        assertEquals(new NumberValue(Float.valueOf(100)), interpreter.evaluate(cs, parentEnvironment));
    }

    @Test
    public void testInterpreter_conditionalStatement_noSatisfiedCondition() throws Exception {
        final ConditionalStatement cs = new ConditionalStatement();
        final StatementBlock s = new StatementBlock();
        s.add(new Identifier("x"));
        cs.addConditionAndStatementBlock(new BooleanLiteral(false), s);
        cs.addConditionAndStatementBlock(new BooleanLiteral(false), s);
        assertEquals(new NullValue(), interpreter.evaluate(cs, parentEnvironment));
    }

    @Test
    public void testInterpreter_conditionalStatement_nonBooleanCondition() throws Exception { 
        final ConditionalStatement cs = new ConditionalStatement();
        cs.addConditionAndStatementBlock(new NumericLiteral(Float.valueOf(2)), null);  
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(cs, null));
        assertEquals("Evaluted condition in conditional statement is not BOOLEAN type.", e.getMessage());
    }

    @Test
    public void testInterpreter_forStatement() throws Exception {
        final StatementBlock s = new StatementBlock();
        s.add(new AssignmentExpression(x, new BinaryExpression(x, new NumericLiteral(Float.valueOf(1)), "-")));
        final IExpression condition = new BinaryExpression(x, new NumericLiteral(Float.valueOf(90)), ">");
        assertEquals(new NumberValue(Float.valueOf(90)), interpreter.evaluate(new ForStatement(condition, s), parentEnvironment));
    }

    @Test
    public void testInterpreter_forStatement_neverLoop() throws Exception {
        assertEquals(new NullValue(), interpreter.evaluate(new ForStatement(new BooleanLiteral(false), null), parentEnvironment));
    }

    @Test
    public void testInterpreter_forStatement_nonBooleanCondition() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> interpreter.evaluate(new ForStatement(new NumericLiteral(null), null), null));
        assertEquals("Evaluted condition in for statement is not BOOLEAN type.", e.getMessage());
    }

    @Test
    public void testInterpreter_program() throws Exception {
        assertEquals(new NullValue(), interpreter.evaluate(new Program(), parentEnvironment));
    }

    @Test
    public void testInterpreter_program_withStatements() throws Exception {
        final Program program = new Program();
        program.addStatement(new VariableDeclaration(true, "y", new NumericLiteral(Float.valueOf(4))));
        program.addStatement(new BinaryExpression(new Identifier("x"), new Identifier("y"), "+"));
        assertEquals(new NumberValue(Float.valueOf(104)), interpreter.evaluate(program, parentEnvironment));
    }
}
