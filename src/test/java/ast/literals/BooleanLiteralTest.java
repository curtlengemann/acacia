package test.java.ast.literals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.literals.BooleanLiteral;

public class BooleanLiteralTest {
        
    @Test
    public void testBooleanLiteral_getType() {
        final BooleanLiteral testBooleanLiteral = new BooleanLiteral(false);
        assertEquals(NodeType.BOOLEAN_LITERAL, testBooleanLiteral.getType());
    }

    @Test
    public void testBooleanLiteral_getValue() {
        final BooleanLiteral testBooleanLiteral = new BooleanLiteral(true);
        assertTrue(testBooleanLiteral.getValue());
    }

    @Test
    public void testBooleanLiteral_equals_self() {
        final BooleanLiteral testBooleanLiteral = new BooleanLiteral(false);
        assertTrue(testBooleanLiteral.equals(testBooleanLiteral));
    }

    @Test
    public void testBooleanLiteral_equals_symmetrical() {
        final BooleanLiteral testBooleanLiteral = new BooleanLiteral(true);
        assertTrue(new BooleanLiteral(true).equals(testBooleanLiteral));
        assertTrue(testBooleanLiteral.equals(new BooleanLiteral(true)));
    }

    @Test
    public void testBooleanLiteral_notEquals_null() {
        final BooleanLiteral testBooleanLiteral = new BooleanLiteral(false);
        assertFalse(testBooleanLiteral.equals(null));
    }

    @Test
    public void testBooleanLiteral_notEquals_changedValue() {
        final BooleanLiteral testBooleanLiteral = new BooleanLiteral(false);
        assertFalse(testBooleanLiteral.equals(new BooleanLiteral(true)));
    }

        @Test
    public void testBooleanLiteral_hashcode_equals_self() {
        final BooleanLiteral testBooleanLiteral = new BooleanLiteral(false);
        assertEquals(testBooleanLiteral.hashCode(), testBooleanLiteral.hashCode());
    }

    @Test
    public void testBooleanLiteral_hashcode_equals() {
        final BooleanLiteral testBooleanLiteral = new BooleanLiteral(true);
        assertEquals(new BooleanLiteral(true).hashCode(), testBooleanLiteral.hashCode());
    }

    @Test
    public void testBooleanLiteral_hashcode_notEquals_changedValue() {
        final BooleanLiteral testBooleanLiteral = new BooleanLiteral(true);
        assertNotEquals(new BooleanLiteral(false).hashCode(), testBooleanLiteral.hashCode());
    }
}
