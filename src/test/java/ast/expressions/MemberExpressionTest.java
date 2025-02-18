package test.java.ast.expressions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.expressions.MemberExpression;
import main.java.com.acacia.ast.literals.Identifier;
import main.java.com.acacia.ast.literals.NullLiteral;

public class MemberExpressionTest {

    @Test
    public void testMemberExpression_getType() {
        final MemberExpression testMemberExpression = new MemberExpression(null, null, false);
        assertEquals(NodeType.MEMBER_EXPRESSION, testMemberExpression.getType());
    }

    @Test
    public void testMemberExpression_getObject_null() {
        final MemberExpression testMemberExpression = new MemberExpression(null, null, false);
        assertNull(testMemberExpression.getObject());
    }

    @Test
    public void testMemberExpression_getObject() {
        final MemberExpression testMemberExpression = new MemberExpression(new NullLiteral(), null, false);
        assertEquals(new NullLiteral(), testMemberExpression.getObject());
    }

    @Test
    public void testMemberExpression_getProperty_null() {
        final MemberExpression testMemberExpression = new MemberExpression(null, null, false);
        assertNull(testMemberExpression.getProperty());
    }

    @Test
    public void testMemberExpression_getProperty() {
        final MemberExpression testMemberExpression = new MemberExpression(null, new NullLiteral(), false);
        assertEquals(new NullLiteral(), testMemberExpression.getProperty());
    }

    @Test
    public void testMemberExpression_isComputed_false() {
        final MemberExpression testMemberExpression = new MemberExpression(null, null, false);
        assertFalse(testMemberExpression.isComputed());
    }

    @Test
    public void testMemberExpression_isComputed_true() {
        final MemberExpression testMemberExpression = new MemberExpression(null, null, true);
        assertTrue(testMemberExpression.isComputed());
    }

    @Test
    public void testMemberExpression_equals_self() {
        final MemberExpression testMemberExpression = new MemberExpression(null, null, false);
        assertTrue(testMemberExpression.equals(testMemberExpression));
    }

    @Test
    public void testMemberExpression_equals_symmetrical() {
        final MemberExpression testMemberExpression = new MemberExpression(null, null, false);
        assertTrue(testMemberExpression.equals(new MemberExpression(null, null, false)));
        assertTrue(new MemberExpression(null, null, false).equals(testMemberExpression));
    }

    @Test
    public void testMemberExpression_equals_values() {
        final MemberExpression testMemberExpression = new MemberExpression(new NullLiteral(), new NullLiteral(), true);
        assertTrue(testMemberExpression.equals(new MemberExpression(new NullLiteral(), new NullLiteral(), true)));
    }

    @Test
    public void testMemberExpression_notEquals_null() {
        final MemberExpression testMemberExpression = new MemberExpression(new NullLiteral(), new NullLiteral(), true);
        assertFalse(testMemberExpression.equals(null));
    }

    @Test
    public void testMemberExpression_notEquals_changedObject() {
        final MemberExpression testMemberExpression = new MemberExpression(new NullLiteral(), null, false);
        assertFalse(testMemberExpression.equals(new MemberExpression(new Identifier(null), null, false)));
    }

    @Test
    public void testMemberExpression_notEquals_changedProperty() {
        final MemberExpression testMemberExpression = new MemberExpression(null, new NullLiteral(), false);
        assertFalse(testMemberExpression.equals(new MemberExpression(null, new Identifier(null), false)));
    }

    @Test
    public void testMemberExpression_notEquals_changedComputed() {
        final MemberExpression testMemberExpression = new MemberExpression(new NullLiteral(), new NullLiteral(), false);
        assertFalse(testMemberExpression.equals(new MemberExpression(new NullLiteral(), new NullLiteral(), true)));
    }

    @Test
    public void testMemberExpression_hashcode_equals_self() {
        final MemberExpression testMemberExpression = new MemberExpression(new NullLiteral(), new NullLiteral(), true);
        assertEquals(testMemberExpression.hashCode(), testMemberExpression.hashCode());
    }

    @Test
    public void testMemberExpression_hashcode_equals_defaultValues() {
        final MemberExpression testMemberExpression = new MemberExpression(null, null, false);
        assertEquals(new MemberExpression(null, null, false).hashCode(), testMemberExpression.hashCode());
    }

    @Test
    public void testMemberExpression_hashcode_equals_setValues() {
        final MemberExpression testMemberExpression = new MemberExpression(new NullLiteral(), new NullLiteral(), true);
        assertEquals(new MemberExpression(new NullLiteral(), new NullLiteral(), true).hashCode(), testMemberExpression.hashCode());
    }

    @Test
    public void testMemberExpression_hashcode_notEquals_changedObject() {
        final MemberExpression testMemberExpression = new MemberExpression(new NullLiteral(), new NullLiteral(), true);
        assertNotEquals(new MemberExpression(new Identifier(null), new NullLiteral(), true).hashCode(), testMemberExpression.hashCode());
    }

    @Test
    public void testMemberExpression_hashcode_notEquals_changedProperty() {
        final MemberExpression testMemberExpression = new MemberExpression(new NullLiteral(), new NullLiteral(), true);
        assertNotEquals(new MemberExpression(new NullLiteral(), new Identifier(null), true).hashCode(), testMemberExpression.hashCode());
    }

    @Test
    public void testMemberExpression_hashcode_notEquals_changedComputed() {
        final MemberExpression testMemberExpression = new MemberExpression(new NullLiteral(), new NullLiteral(), true);
        assertNotEquals(new MemberExpression(new NullLiteral(), new NullLiteral(), false).hashCode(), testMemberExpression.hashCode());
    }
}
