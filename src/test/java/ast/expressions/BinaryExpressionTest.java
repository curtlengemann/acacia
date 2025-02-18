package test.java.ast.expressions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.expressions.BinaryExpression;
import main.java.com.acacia.ast.literals.Identifier;
import main.java.com.acacia.ast.literals.NullLiteral;

public class BinaryExpressionTest {
    
    @Test
    public void testBinaryExpression_getType() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(null, null, null);
        assertEquals(NodeType.BINARY_EXPRESSION, testBinaryExpression.getType());
    }

    @Test
    public void testBinaryExpression_getLeft_null() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(null, null, null);
        assertNull(testBinaryExpression.getLeft());
    }

    @Test
    public void testBinaryExpression_getLeft() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(new NullLiteral(), null, null);
        assertEquals(new NullLiteral(), testBinaryExpression.getLeft());
    }

    @Test
    public void testBinaryExpression_getRight_null() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(null, null, null);
        assertNull(testBinaryExpression.getRight());
    }

    @Test
    public void testBinaryExpression_getRight() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(null, new NullLiteral(), null);
        assertEquals(new NullLiteral(), testBinaryExpression.getRight());
    }

    @Test
    public void testBinaryExpression_getOperator_null() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(null, null, null);
        assertNull(testBinaryExpression.getOperator());
    }

    @Test
    public void testBinaryExpression_getOperator() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(null, null, "+");
        assertEquals("+", testBinaryExpression.getOperator());
    }

    @Test
    public void testBinaryExpression_equals_self() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(null, null, null);
        assertTrue(testBinaryExpression.equals(testBinaryExpression));
    }

    @Test
    public void testBinaryExpression_equals_symmetrical() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(null, null, null);
        assertTrue(testBinaryExpression.equals(new BinaryExpression(null, null, null)));
        assertTrue(new BinaryExpression(null, null, null).equals(testBinaryExpression));
    }

    @Test
    public void testBinaryExpression_equals_values() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(new NullLiteral(), new NullLiteral(), "+");
        assertTrue(testBinaryExpression.equals(new BinaryExpression(new NullLiteral(), new NullLiteral(), "+")));
    }

    @Test
    public void testBinaryExpression_notEquals_null() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(new NullLiteral(), new NullLiteral(), "+");
        assertFalse(testBinaryExpression.equals(null));
    }

    @Test
    public void testBinaryExpression_notEquals_changedLeft() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(new NullLiteral(), null, null);
        assertFalse(testBinaryExpression.equals(new BinaryExpression(new Identifier(null), null, null)));
    }

    @Test
    public void testBinaryExpression_notEquals_changedRight() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(null, new NullLiteral(), null);
        assertFalse(testBinaryExpression.equals(new BinaryExpression(null, new Identifier(null), null)));
    }

    @Test
    public void testBinaryExpression_notEquals_changedOperator() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(new NullLiteral(), new NullLiteral(), "+");
        assertFalse(testBinaryExpression.equals(new BinaryExpression(new NullLiteral(), new NullLiteral(), "-")));
    }

    @Test
    public void testBinaryExpression_hashcode_equals_self() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(new NullLiteral(), new NullLiteral(), "+");
        assertEquals(testBinaryExpression.hashCode(), testBinaryExpression.hashCode());
    }

    @Test
    public void testBinaryExpression_hashcode_equals_nullValues() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(null, null, null);
        assertEquals(new BinaryExpression(null, null, null).hashCode(), testBinaryExpression.hashCode());
    }

    @Test
    public void testBinaryExpression_hashcode_equals_values() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(new NullLiteral(), new NullLiteral(), "+");
        assertEquals(new BinaryExpression(new NullLiteral(), new NullLiteral(), "+").hashCode(), testBinaryExpression.hashCode());
    }

    @Test
    public void testBinaryExpression_hashcode_notEquals_changedLeft() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(new NullLiteral(), new NullLiteral(), "+");
        assertNotEquals(new BinaryExpression(new Identifier(null), new NullLiteral(), "+").hashCode(), testBinaryExpression.hashCode());
    }

    @Test
    public void testBinaryExpression_hashcode_notEquals_changedRight() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(new NullLiteral(), new NullLiteral(), "+");
        assertNotEquals(new BinaryExpression(new NullLiteral(), new Identifier(null), "+").hashCode(), testBinaryExpression.hashCode());
    }

    @Test
    public void testBinaryExpression_hashcode_notEquals_changedOperator() {
        final BinaryExpression testBinaryExpression = new BinaryExpression(new NullLiteral(), new NullLiteral(), "+");
        assertNotEquals(new BinaryExpression(new NullLiteral(), new NullLiteral(), "-").hashCode(), testBinaryExpression.hashCode());
    }
}
