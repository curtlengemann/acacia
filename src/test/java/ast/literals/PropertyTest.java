package test.java.ast.literals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.literals.Identifier;
import main.java.com.acacia.ast.literals.NullLiteral;
import main.java.com.acacia.ast.literals.Property;

public class PropertyTest {

    @Test
    public void testProperty_getType() {
        final Property testProperty = new Property(null);
        assertEquals(NodeType.PROPERTY, testProperty.getType());
    }

    @Test
    public void testProperty_getKey_null() {
        final Property testProperty = new Property(null);
        assertNull(testProperty.getKey());
    }

    @Test
    public void testProperty_getKey() {
        final Property testProperty = new Property("asdf");
        assertEquals("asdf", testProperty.getKey());
    }

    @Test
    public void testProperty_getValue_null() {
        final Property testProperty = new Property(null, null);
        assertNull(testProperty.getValue());
    }

    @Test
    public void testProperty_getValue() {
        final NullLiteral nullLiteral = new NullLiteral();
        final Property testProperty = new Property(null, nullLiteral);
        assertEquals(nullLiteral, testProperty.getValue());
    }

    @Test
    public void testProperty_equals_self() {
        final Property testProperty = new Property("asdf");
        assertTrue(testProperty.equals(testProperty));
    }

    @Test
    public void testProperty_equals_symmetrical() {
        final Property testProperty = new Property("asdf");
        assertTrue(new Property("asdf").equals(testProperty));
        assertTrue(testProperty.equals(new Property("asdf")));
    }

    @Test
    public void testProperty_equals_keyAndValue() {
        final NullLiteral nullLiteral = new NullLiteral();
        final Property testProperty = new Property("asdf", nullLiteral);
        assertTrue(testProperty.equals(new Property("asdf", nullLiteral)));
    }

    @Test
    public void testProperty_notEquals_null() {
        final Property testProperty = new Property(null);
        assertFalse(testProperty.equals(null));
    }

    @Test
    public void testProperty_notEquals_nullKey() {
        final Property testProperty = new Property(null);
        assertFalse(testProperty.equals(new Property("asdf")));
    }

    @Test
    public void testProperty_notEquals_changedKey() {
        final Property testProperty = new Property("asdf");
        assertFalse(testProperty.equals(new Property("ASDF")));
    }

    @Test
    public void testProperty_notEquals_nullValue() {
        final Property testProperty = new Property(null, null);
        assertFalse(testProperty.equals(new Property(null, new NullLiteral())));
    }

    @Test
    public void testProperty_notEquals_changedValue() {
        final Property testProperty = new Property("asdf", new NullLiteral());
        assertFalse(testProperty.equals(new Property("asdf", new Identifier(null))));
    }

        @Test
    public void testProperty_hashcode_equals_self() {
        final Property testProperty = new Property(null);
        assertEquals(testProperty.hashCode(), testProperty.hashCode());
    }

    @Test
    public void testProperty_hashcode_equals_noKey() {
        final Property testProperty = new Property(null);
        assertEquals(new Property(null).hashCode(), testProperty.hashCode());
    }

    @Test
    public void testProperty_hashcode_equals_key() {
        final Property testProperty = new Property("asdf");
        assertEquals(new Property("asdf").hashCode(), testProperty.hashCode());
    }

    
    @Test
    public void testProperty_hashcode_equals_keyAndValue() {
        final NullLiteral nullLiteral = new NullLiteral();
        final Property testProperty = new Property("asdf", nullLiteral);
        assertEquals(new Property("asdf", nullLiteral).hashCode(), testProperty.hashCode());
    }

    @Test
    public void testProperty_hashcode_notEquals_nullKey() {
        final Property testProperty = new Property("asdf");
        assertNotEquals(new Property(null).hashCode(), testProperty.hashCode());
    }

    @Test
    public void testProperty_hashcode_notEquals_changedKey() {
        final Property testProperty = new Property("asdf");
        assertNotEquals(new Property("ASDF").hashCode(), testProperty.hashCode());
    }

    @Test
    public void testProperty_hashcode_notEquals_nullValue() {
        final Property testProperty = new Property("asdf", null);
        assertNotEquals(new Property("asdf", new NullLiteral()).hashCode(), testProperty.hashCode());
    }

    @Test
    public void testProperty_hashcode_notEquals_changedValue() {
        final Property testProperty = new Property("asdf", new NullLiteral());
        assertNotEquals(new Property("asdf", new Identifier(null)).hashCode(), testProperty.hashCode());
    }
}
