package test.java.ast.literals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.literals.StringLiteral;

public class StringLiteralTest {

    @Test
    public void testStringLiteral_getType() {
        final StringLiteral testStringLiteral = new StringLiteral(null);
        assertEquals(NodeType.STRING_LITERAL, testStringLiteral.getType());
    }

    @Test
    public void testStringLiteral_getValue_null() {
        final StringLiteral testStringLiteral = new StringLiteral(null);
        assertNull(testStringLiteral.getValue());
    }

    @Test
    public void testStringLiteral_getValue() {
        final StringLiteral testStringLiteral = new StringLiteral("asdf");
        assertEquals("asdf", testStringLiteral.getValue());
    }

    @Test
    public void testStringLiteral_equals_self() {
        final StringLiteral testStringLiteral = new StringLiteral("asdf");
        assertTrue(testStringLiteral.equals(testStringLiteral));
    }

    @Test
    public void testStringLiteral_equals_symmetrical() {
        final StringLiteral testStringLiteral = new StringLiteral("asdf");
        assertTrue(new StringLiteral("asdf").equals(testStringLiteral));
        assertTrue(testStringLiteral.equals(new StringLiteral("asdf")));
    }

    @Test
    public void testStringLiteral_equals_nullValue() {
        final StringLiteral testStringLiteral = new StringLiteral(null);
        assertTrue(testStringLiteral.equals(new StringLiteral(null)));
    }

    @Test
    public void testStringLiteral_notEquals_null() {
        final StringLiteral testStringLiteral = new StringLiteral(null);
        assertFalse(testStringLiteral.equals(null));
    }

    @Test
    public void testStringLiteral_notEquals_nullValue() {
        final StringLiteral testStringLiteral = new StringLiteral(null);
        assertFalse(testStringLiteral.equals(new StringLiteral("asdf")));
    }

    @Test
    public void testStringLiteral_notEquals_changedValue() {
        final StringLiteral testStringLiteral = new StringLiteral("asdf");
        assertFalse(testStringLiteral.equals(new StringLiteral("ASDF")));
    }

        @Test
    public void testStringLiteral_hashcode_equals_self() {
        final StringLiteral testStringLiteral = new StringLiteral(null);
        assertEquals(testStringLiteral.hashCode(), testStringLiteral.hashCode());
    }

    @Test
    public void testStringLiteral_hashcode_equals_noValue() {
        final StringLiteral testStringLiteral = new StringLiteral(null);
        assertEquals(new StringLiteral(null).hashCode(), testStringLiteral.hashCode());
    }

    @Test
    public void testStringLiteral_hashcode_equals_value() {
        final StringLiteral testStringLiteral = new StringLiteral("asdf");
        assertEquals(new StringLiteral("asdf").hashCode(), testStringLiteral.hashCode());
    }

    @Test
    public void testStringLiteral_hashcode_notEquals_nullValue() {
        final StringLiteral testStringLiteral = new StringLiteral("asdf");
        assertNotEquals(new StringLiteral(null).hashCode(), testStringLiteral.hashCode());
    }

    @Test
    public void testStringLiteral_hashcode_notEquals_changedValue() {
        final StringLiteral testStringLiteral = new StringLiteral("asdf");
        assertNotEquals(new StringLiteral("ASDF").hashCode(), testStringLiteral.hashCode());
    }
}
