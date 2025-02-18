package test.java.ast.expressions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.expressions.NegationExpression;
import main.java.com.acacia.ast.literals.Identifier;
import main.java.com.acacia.ast.literals.NullLiteral;

public class NegationExpressionTest {
        
    @Test
    public void testNegationExpression_getType() {
        final NegationExpression testNegationExpression = new NegationExpression(null);
        assertEquals(NodeType.NEGATION_EXPRESSION, testNegationExpression.getType());
    }

    @Test
    public void testNegationExpression_getValue_null() {
        final NegationExpression testNegationExpression = new NegationExpression(null);
        assertNull(testNegationExpression.getValue());
    }

    @Test
    public void testNegationExpression_getValue() {
        final NegationExpression testNegationExpression = new NegationExpression(new NullLiteral());
        assertEquals(new NullLiteral(), testNegationExpression.getValue());
    }

    @Test
    public void testNegationExpression_equals_self() {
        final NegationExpression testNegationExpression = new NegationExpression(null);
        assertTrue(testNegationExpression.equals(testNegationExpression));
    }

    @Test
    public void testNegationExpression_equals_symmetrical() {
        final NegationExpression testNegationExpression = new NegationExpression(null);
        assertTrue(testNegationExpression.equals(new NegationExpression(null)));
        assertTrue(new NegationExpression(null).equals(testNegationExpression));
    }

    @Test
    public void testNegationExpression_equals_values() {
        final NegationExpression testNegationExpression = new NegationExpression(new NullLiteral());
        assertTrue(testNegationExpression.equals(new NegationExpression(new NullLiteral())));
    }

    @Test
    public void testNegationExpression_notEquals_null() {
        final NegationExpression testNegationExpression = new NegationExpression(null);
        assertFalse(testNegationExpression.equals(null));
    }

    @Test
    public void testNegationExpression_notEquals_changedValue() {
        final NegationExpression testNegationExpression = new NegationExpression(new NullLiteral());
        assertFalse(testNegationExpression.equals(new NegationExpression(new Identifier(null))));
    }

    @Test
    public void testNegationExpression_hashcode_equals_self() {
        final NegationExpression testNegationExpression = new NegationExpression(new NullLiteral());
        assertEquals(testNegationExpression.hashCode(), testNegationExpression.hashCode());
    }

    @Test
    public void testNegationExpression_hashcode_equals_nullValue() {
        final NegationExpression testNegationExpression = new NegationExpression(null);
        assertEquals(new NegationExpression(null).hashCode(), testNegationExpression.hashCode());
    }

    @Test
    public void testNegationExpression_hashcode_equals_values() {
        final NegationExpression testNegationExpression = new NegationExpression(new NullLiteral());
        assertEquals(new NegationExpression(new NullLiteral()).hashCode(), testNegationExpression.hashCode());
    }

    @Test
    public void testNegationExpression_hashcode_notEquals_changedValue() {
        final NegationExpression testNegationExpression = new NegationExpression(new NullLiteral());
        assertNotEquals(new NegationExpression(new Identifier(null)).hashCode(), testNegationExpression.hashCode());
    }
}
