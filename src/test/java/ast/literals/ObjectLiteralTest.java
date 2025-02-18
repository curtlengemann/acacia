package test.java.ast.literals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.literals.ObjectLiteral;
import main.java.com.acacia.ast.literals.Property;

public class ObjectLiteralTest {
    
    @Test
    public void testObjectLiteral_getType() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);
        assertEquals(NodeType.OBJECT_LITERAL, testObjectLiteral.getType());
    }

    @Test
    public void testObjectLiteral_properties() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);

        final Property property1 = new Property("key");
        final Property property2 = new Property("key2");

        testObjectLiteral.addProperty(property1);
        testObjectLiteral.addProperty(property2);

        final List<Property> properties = testObjectLiteral.getProperties();
        assertEquals(2, properties.size());
        assertEquals(property1, properties.get(0));
        assertEquals(property2, properties.get(1));
    }

    @Test
    public void testObjectLiteral_isEnum() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(true);
        assertTrue(testObjectLiteral.isEnum());
    }

    @Test
    public void testObjectLiteral_equals_self() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);
        assertTrue(testObjectLiteral.equals(testObjectLiteral));
    }

    @Test
    public void testObjectLiteral_equals_symmetrical() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(true);
        assertTrue(testObjectLiteral.equals(new ObjectLiteral(true)));
        assertTrue(new ObjectLiteral(true).equals(testObjectLiteral));
    }

    @Test
    public void testObjectLiteral_equals_properties() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);
        testObjectLiteral.addProperty(new Property(null));
        testObjectLiteral.addProperty(new Property("key"));

        final ObjectLiteral testObjectLiteral2 = new ObjectLiteral(false);
        testObjectLiteral2.addProperty(new Property(null));
        testObjectLiteral2.addProperty(new Property("key"));
        assertTrue(testObjectLiteral.equals(testObjectLiteral2));
    }

    @Test
    public void testObjectLiteral_notEquals_null() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);
        assertFalse(testObjectLiteral.equals(null));
    }

    @Test
    public void testObjectLiteral_notEquals_changedNumberOfProperties() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);
        testObjectLiteral.addProperty(new Property(null));

        final ObjectLiteral testObjectLiteral2 = new ObjectLiteral(false);
        assertFalse(testObjectLiteral.equals(testObjectLiteral2));
    }

    
    @Test
    public void testObjectLiteral_notEquals_changedProperties() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);
        testObjectLiteral.addProperty(new Property(null));

        final ObjectLiteral testObjectLiteral2 = new ObjectLiteral(false);
        testObjectLiteral2.addProperty(new Property("key"));
        assertFalse(testObjectLiteral.equals(testObjectLiteral2));
    }

    @Test
    public void testObjectLiteral_notEquals_changedIsEnum() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);
        assertFalse(testObjectLiteral.equals(new ObjectLiteral(true)));
    }

    @Test
    public void testObjectLiteral_hashcode_equals_self() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);
        assertEquals(testObjectLiteral.hashCode(), testObjectLiteral.hashCode());
    }

    @Test
    public void testObjectLiteral_hashcode_equals_noProperties() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(true);
        assertEquals(new ObjectLiteral(true).hashCode(), testObjectLiteral.hashCode());
    }

    @Test
    public void testObjectLiteral_hashcode_equals_properties() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);
        testObjectLiteral.addProperty(new Property(null));
        testObjectLiteral.addProperty(new Property("key"));

        final ObjectLiteral testObjectLiteral2 = new ObjectLiteral(false);
        testObjectLiteral2.addProperty(new Property(null));
        testObjectLiteral2.addProperty(new Property("key"));
        assertEquals(testObjectLiteral.hashCode(), testObjectLiteral2.hashCode());
    }

    @Test
    public void testObjectLiteral_hashcode_notEquals_changedNumberOfProperties() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);

        final ObjectLiteral testObjectLiteral2 = new ObjectLiteral(false);
        testObjectLiteral2.addProperty(new Property(null));
        assertNotEquals(testObjectLiteral.hashCode(), testObjectLiteral2.hashCode());
    }

    @Test
    public void testObjectLiteral_hashcode_notEquals_changedIsEnum() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);
        assertNotEquals(testObjectLiteral.hashCode(), new ObjectLiteral(true).hashCode());
    }

    @Test
    public void testObjectLiteral_hashcode_notEquals_changedProperties() {
        final ObjectLiteral testObjectLiteral = new ObjectLiteral(false);
        testObjectLiteral.addProperty(new Property(null));

        final ObjectLiteral testObjectLiteral2 = new ObjectLiteral(false);
        testObjectLiteral2.addProperty(new Property("key"));
        assertNotEquals(testObjectLiteral.hashCode(), testObjectLiteral2.hashCode());
    }
}
