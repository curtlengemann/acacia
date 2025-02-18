package test.java.ast.expressions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.expressions.ReturnExpression;
import main.java.com.acacia.ast.literals.Identifier;
import main.java.com.acacia.ast.literals.NullLiteral;

public class ReturnExpressionTest {
    
    @Test
    public void testReturnExpression_getType() {
        final ReturnExpression testReturnExpression = new ReturnExpression(null);
        assertEquals(NodeType.RETURN_EXPRESSION, testReturnExpression.getType());
    }

    @Test
    public void testReturnExpression_getValue_null() {
        final ReturnExpression testReturnExpression = new ReturnExpression(null);
        assertNull(testReturnExpression.getValue());
    }

    @Test
    public void testReturnExpression_getValue() {
        final ReturnExpression testReturnExpression = new ReturnExpression(new NullLiteral());
        assertEquals(new NullLiteral(), testReturnExpression.getValue());
    }

    @Test
    public void testReturnExpression_equals_self() {
        final ReturnExpression testReturnExpression = new ReturnExpression(null);
        assertTrue(testReturnExpression.equals(testReturnExpression));
    }

    @Test
    public void testReturnExpression_equals_symmetrical() {
        final ReturnExpression testReturnExpression = new ReturnExpression(null);
        assertTrue(testReturnExpression.equals(new ReturnExpression(null)));
        assertTrue(new ReturnExpression(null).equals(testReturnExpression));
    }

    @Test
    public void testReturnExpression_equals_values() {
        final ReturnExpression testReturnExpression = new ReturnExpression(new NullLiteral());
        assertTrue(testReturnExpression.equals(new ReturnExpression(new NullLiteral())));
    }

    @Test
    public void testReturnExpression_notEquals_null() {
        final ReturnExpression testReturnExpression = new ReturnExpression(null);
        assertFalse(testReturnExpression.equals(null));
    }

    @Test
    public void testReturnExpression_notEquals_changedValue() {
        final ReturnExpression testReturnExpression = new ReturnExpression(new NullLiteral());
        assertFalse(testReturnExpression.equals(new ReturnExpression(new Identifier(null))));
    }

    @Test
    public void testReturnExpression_hashcode_equals_self() {
        final ReturnExpression testReturnExpression = new ReturnExpression(new NullLiteral());
        assertEquals(testReturnExpression.hashCode(), testReturnExpression.hashCode());
    }

    @Test
    public void testReturnExpression_hashcode_equals_nullValue() {
        final ReturnExpression testReturnExpression = new ReturnExpression(null);
        assertEquals(new ReturnExpression(null).hashCode(), testReturnExpression.hashCode());
    }

    @Test
    public void testReturnExpression_hashcode_equals_values() {
        final ReturnExpression testReturnExpression = new ReturnExpression(new NullLiteral());
        assertEquals(new ReturnExpression(new NullLiteral()).hashCode(), testReturnExpression.hashCode());
    }

    @Test
    public void testReturnExpression_hashcode_notEquals_changedValue() {
        final ReturnExpression testReturnExpression = new ReturnExpression(new NullLiteral());
        assertNotEquals(new ReturnExpression(new Identifier(null)).hashCode(), testReturnExpression.hashCode());
    }
}
