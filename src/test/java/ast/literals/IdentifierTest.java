package test.java.ast.literals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.literals.Identifier;

public class IdentifierTest {
    
    @Test
    public void testIdentifier_getType() {
        final Identifier testIdentifier = new Identifier(null);
        assertEquals(NodeType.IDENTIFIER, testIdentifier.getType());
    }

    @Test
    public void testIdentifier_getValue_null() {
        final Identifier testIdentifier = new Identifier(null);
        assertNull(testIdentifier.getValue());
    }

    @Test
    public void testIdentifier_getValue() {
        final Identifier testIdentifier = new Identifier("asdf");
        assertEquals("asdf", testIdentifier.getValue());
    }

    @Test
    public void testIdentifier_equals_self() {
        final Identifier testIdentifier = new Identifier("asdf");
        assertTrue(testIdentifier.equals(testIdentifier));
    }

    @Test
    public void testIdentifier_equals_symmetrical() {
        final Identifier testIdentifier = new Identifier("asdf");
        assertTrue(new Identifier("asdf").equals(testIdentifier));
        assertTrue(testIdentifier.equals(new Identifier("asdf")));
    }

    @Test
    public void testIdentifier_equals_nullValue() {
        final Identifier testIdentifier = new Identifier(null);
        assertTrue(testIdentifier.equals(new Identifier(null)));
    }

    @Test
    public void testIdentifier_notEquals_null() {
        final Identifier testIdentifier = new Identifier(null);
        assertFalse(testIdentifier.equals(null));
    }

    @Test
    public void testIdentifier_notEquals_nullValue() {
        final Identifier testIdentifier = new Identifier(null);
        assertFalse(testIdentifier.equals(new Identifier("asdf")));
    }

    @Test
    public void testIdentifier_notEquals_changedValue() {
        final Identifier testIdentifier = new Identifier("asdf");
        assertFalse(testIdentifier.equals(new Identifier("ASDF")));
    }

        @Test
    public void testIdentifier_hashcode_equals_self() {
        final Identifier testIdentifier = new Identifier(null);
        assertEquals(testIdentifier.hashCode(), testIdentifier.hashCode());
    }

    @Test
    public void testIdentifier_hashcode_equals_noValue() {
        final Identifier testIdentifier = new Identifier(null);
        assertEquals(new Identifier(null).hashCode(), testIdentifier.hashCode());
    }

    @Test
    public void testIdentifier_hashcode_equals_value() {
        final Identifier testIdentifier = new Identifier("asdf");
        assertEquals(new Identifier("asdf").hashCode(), testIdentifier.hashCode());
    }

    @Test
    public void testIdentifier_hashcode_notEquals_nullValue() {
        final Identifier testIdentifier = new Identifier("asdf");
        assertNotEquals(new Identifier(null).hashCode(), testIdentifier.hashCode());
    }

    @Test
    public void testIdentifier_hashcode_notEquals_changedValue() {
        final Identifier testIdentifier = new Identifier("asdf");
        assertNotEquals(new Identifier("ASDF").hashCode(), testIdentifier.hashCode());
    }
}
