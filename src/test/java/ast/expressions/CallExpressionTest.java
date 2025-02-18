package test.java.ast.expressions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.expressions.CallExpression;
import main.java.com.acacia.ast.literals.Identifier;
import main.java.com.acacia.ast.literals.NullLiteral;

public class CallExpressionTest {

    @Test
    public void testCallExpression_getType() {
        final CallExpression testCallExpression = new CallExpression(null, null);
        assertEquals(NodeType.CALL_EXPRESSION, testCallExpression.getType());
    }

    @Test
    public void testCallExpression_getCaller_null() {
        final CallExpression testCallExpression = new CallExpression(null, null);
        assertNull(testCallExpression.getCaller());
    }

    @Test
    public void testCallExpression_getCaller() {
        final CallExpression testCallExpression = new CallExpression(new NullLiteral(), null);
        assertEquals(new NullLiteral(), testCallExpression.getCaller());
    }

    @Test
    public void testCallExpression_getArgumentsIterator_null() {
        final CallExpression testCallExpression = new CallExpression(new NullLiteral(), null);
        assertNull(testCallExpression.getArgumentsIterator());
    }

    @Test
    public void testCallExpression_getArgumentsIterator() {
        final ArrayList<IExpression> args = new ArrayList<IExpression>();
        args.add(new NullLiteral());
        args.add(new NullLiteral());
        final CallExpression testCallExpression = new CallExpression(null, args);
        final Iterator<IExpression> iter = testCallExpression.getArgumentsIterator();

        int count = 0;
        while(iter.hasNext()) {
            assertEquals(new NullLiteral(), iter.next());
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    public void testCallExpression_equals_self() {
        final CallExpression testCallExpression = new CallExpression(null, null);
        assertTrue(testCallExpression.equals(testCallExpression));
    }

    @Test
    public void testCallExpression_equals_symmetrical() {
        final CallExpression testCallExpression = new CallExpression(null, null);
        assertTrue(testCallExpression.equals(new CallExpression(null, null)));
        assertTrue(new CallExpression(null, null).equals(testCallExpression));
    }

    @Test
    public void testCallExpression_equals_values() {
        final ArrayList<IExpression> arguments = new ArrayList<IExpression>();
        arguments.add(new NullLiteral());
        final CallExpression testCallExpression = new CallExpression(new NullLiteral(), arguments);
        assertTrue(testCallExpression.equals(new CallExpression(new NullLiteral(), arguments)));
    }

    @Test
    public void testCallExpression_notEquals_null() {
        final CallExpression testCallExpression = new CallExpression(null, null);
        assertFalse(testCallExpression.equals(null));
    }

    @Test
    public void testCallExpression_notEquals_changedCaller() {
        final CallExpression testCallExpression = new CallExpression(new NullLiteral(), null);
        assertFalse(testCallExpression.equals(new CallExpression(new Identifier(null), null)));
    }

    @Test
    public void testCallExpression_notEquals_changedArguments() {
        final ArrayList<IExpression> arguments = new ArrayList<IExpression>();
        arguments.add(new NullLiteral());
        final CallExpression testCallExpression = new CallExpression(null, arguments);
        assertFalse(testCallExpression.equals(new CallExpression(null, new ArrayList<IExpression>())));
    }

    @Test
    public void testCallExpression_hashcode_equals_self() {
        final CallExpression testCallExpression = new CallExpression(new NullLiteral(), null);
        assertEquals(testCallExpression.hashCode(), testCallExpression.hashCode());
    }

    @Test
    public void testCallExpression_hashcode_equals_nullValues() {
        final CallExpression testCallExpression = new CallExpression(null, null);
        assertEquals(new CallExpression(null, null).hashCode(), testCallExpression.hashCode());
    }

    @Test
    public void testCallExpression_hashcode_equals_values() {
        final ArrayList<IExpression> arguments = new ArrayList<IExpression>();
        arguments.add(new NullLiteral());
        final CallExpression testCallExpression = new CallExpression(new NullLiteral(), arguments);
        assertEquals(new CallExpression(new NullLiteral(), arguments).hashCode(), testCallExpression.hashCode());
    }

    @Test
    public void testCallExpression_hashcode_notEquals_changedCaller() {
        final CallExpression testCallExpression = new CallExpression(new NullLiteral(), null);
        assertNotEquals(new CallExpression(new Identifier(null), null).hashCode(), testCallExpression.hashCode());
    }

    @Test
    public void testCallExpression_hashcode_notEquals_changedArguments() {
        final ArrayList<IExpression> arguments = new ArrayList<IExpression>();
        arguments.add(new NullLiteral());
        final CallExpression testCallExpression = new CallExpression(null, arguments);
        assertNotEquals(new CallExpression(null, new ArrayList<IExpression>()).hashCode(), testCallExpression.hashCode());
    }
}
