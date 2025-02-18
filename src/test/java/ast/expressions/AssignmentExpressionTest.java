package test.java.ast.expressions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.expressions.AssignmentExpression;
import main.java.com.acacia.ast.literals.Identifier;
import main.java.com.acacia.ast.literals.NullLiteral;

public class AssignmentExpressionTest {
        
    @Test
    public void testAssignmentExpression_getType() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(null, null);
        assertEquals(NodeType.ASSIGNMENT_EXPRESSION, testAssignmentExpression.getType());
    }

    @Test
    public void testAssignmentExpression_getAssignee_null() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(null, null);
        assertNull(testAssignmentExpression.getAssignee());
    }

    @Test
    public void testAssignmentExpression_getAssignee() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(new NullLiteral(), null);
        assertEquals(new NullLiteral(), testAssignmentExpression.getAssignee());
    }

    @Test
    public void testAssignmentExpression_getValue_null() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(null, null);
        assertNull(testAssignmentExpression.getValue());
    }

    @Test
    public void testAssignmentExpression_getValue() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(null, new NullLiteral());
        assertEquals(new NullLiteral(), testAssignmentExpression.getValue());
    }

    @Test
    public void testAssignmentExpression_equals_self() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(null, null);
        assertTrue(testAssignmentExpression.equals(testAssignmentExpression));
    }

    @Test
    public void testAssignmentExpression_equals_symmetrical() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(null, null);
        assertTrue(testAssignmentExpression.equals(new AssignmentExpression(null, null)));
        assertTrue(new AssignmentExpression(null, null).equals(testAssignmentExpression));
    }

    @Test
    public void testAssignmentExpression_equals_values() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(new NullLiteral(), new NullLiteral());
        assertTrue(testAssignmentExpression.equals(new AssignmentExpression(new NullLiteral(), new NullLiteral())));
    }

    @Test
    public void testAssignmentExpression_notEquals_null() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(null, null);
        assertFalse(testAssignmentExpression.equals(null));
    }

    @Test
    public void testAssignmentExpression_notEquals_changedAssignee() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(new NullLiteral(), null);
        assertFalse(testAssignmentExpression.equals(new AssignmentExpression(new Identifier(null), null)));
    }

    @Test
    public void testAssignmentExpression_notEquals_changedValue() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(null, new NullLiteral());
        assertFalse(testAssignmentExpression.equals(new AssignmentExpression(null, new Identifier(null))));
    }

    @Test
    public void testAssignmentExpression_hashcode_equals_self() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(new NullLiteral(), new NullLiteral());
        assertEquals(testAssignmentExpression.hashCode(), testAssignmentExpression.hashCode());
    }

    @Test
    public void testAssignmentExpression_hashcode_equals_nullValues() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(null, null);
        assertEquals(new AssignmentExpression(null, null).hashCode(), testAssignmentExpression.hashCode());
    }

    @Test
    public void testAssignmentExpression_hashcode_equals_values() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(new NullLiteral(), new NullLiteral());
        assertEquals(new AssignmentExpression(new NullLiteral(), new NullLiteral()).hashCode(), testAssignmentExpression.hashCode());
    }

    @Test
    public void testAssignmentExpression_hashcode_notEquals_changedAssignee() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(new NullLiteral(), new NullLiteral());
        assertNotEquals(new AssignmentExpression(new Identifier(null), new NullLiteral()).hashCode(), testAssignmentExpression.hashCode());
    }

    @Test
    public void testAssignmentExpression_hashcode_notEquals_changedValue() {
        final AssignmentExpression testAssignmentExpression = new AssignmentExpression(new NullLiteral(), new NullLiteral());
        assertNotEquals(new AssignmentExpression(new NullLiteral(), new Identifier(null)).hashCode(), testAssignmentExpression.hashCode());
    }
}
