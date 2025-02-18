package test.java.runtime.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.runtime.values.NullValue;
import main.java.com.acacia.runtime.values.ObjectValue;
import main.java.com.acacia.runtime.values.ValueType;

public class ObjectValueTest {
        
    @Test
    public void testObjectValue_getRuntimeType() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        assertEquals(ValueType.OBJECT, testObjectValue.getRuntimeType());
    }

    @Test
    public void testObjectValue_getStringValue() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        assertEquals("OBJECT", testObjectValue.getStringValue());
    }

    @Test
    public void testObjectValue_properties() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        testObjectValue.addProperty("key", new NullValue());
        assertEquals(new NullValue(), testObjectValue.getProperty("key"));
    }

    @Test
    public void testObjectValue_isEnum() {
        final ObjectValue testObjectValue = new ObjectValue(true);
        assertTrue(testObjectValue.isEnum());
    }

    @Test
    public void testObjectValue_equals_self() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        assertTrue(testObjectValue.equals(testObjectValue));
    }

    @Test
    public void testObjectValue_equals_symmetrical() {
        final ObjectValue testObjectValue = new ObjectValue(true);
        assertTrue(testObjectValue.equals(new ObjectValue(true)));
        assertTrue(new ObjectValue(true).equals(testObjectValue));
    }

    @Test
    public void testObjectValue_equals_properties() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        testObjectValue.addProperty("asdf", null);

        final ObjectValue testObjectValue2 = new ObjectValue(false);
        testObjectValue2.addProperty("asdf", null);
        assertTrue(testObjectValue.equals(testObjectValue2));
    }

    @Test
    public void testObjectValue_notEquals_null() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        assertFalse(testObjectValue.equals(null));
    }

    @Test
    public void testObjectValue_notEquals_changedNumberOfProperties() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        testObjectValue.addProperty("asdf", null);
        assertFalse(testObjectValue.equals(new ObjectValue(false)));
    }

    
    @Test
    public void testObjectValue_notEquals_changedProperties() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        testObjectValue.addProperty("asdf", null);

        final ObjectValue testObjectValue2 = new ObjectValue(false);
        testObjectValue2.addProperty("a", null);
        assertFalse(testObjectValue.equals(testObjectValue2));
    }

    @Test
    public void testObjectValue_notEquals_changedIsEnum() {
        final ObjectValue testObjectValue = new ObjectValue(true);
        assertFalse(testObjectValue.equals(new ObjectValue(false)));
    }

    @Test
    public void testObjectValue_hashcode_equals_self() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        assertEquals(testObjectValue.hashCode(), testObjectValue.hashCode());
    }

    @Test
    public void testObjectValue_hashcode_equals_noProperties() {
        final ObjectValue testObjectValue = new ObjectValue(true);
        assertEquals(new ObjectValue(true).hashCode(), testObjectValue.hashCode());
    }

    @Test
    public void testObjectValue_hashcode_equals_properties() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        testObjectValue.addProperty("asdf", null);

        final ObjectValue testObjectValue2 = new ObjectValue(false);
        testObjectValue2.addProperty("asdf", null);;
        assertEquals(testObjectValue.hashCode(), testObjectValue2.hashCode());
    }

    @Test
    public void testObjectValue_hashcode_notEquals_changedNumberOfProperties() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        testObjectValue.addProperty("asdf", null);
        assertNotEquals(testObjectValue.hashCode(), new ObjectValue(false).hashCode());
    }

    @Test
    public void testObjectValue_hashcode_notEquals_changedIsEnum() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        assertNotEquals(testObjectValue.hashCode(), new ObjectValue(true).hashCode());
    }

    @Test
    public void testObjectValue_hashcode_notEquals_changedProperties() {
        final ObjectValue testObjectValue = new ObjectValue(false);
        testObjectValue.addProperty("asdf", null);

        final ObjectValue testObjectValue2 = new ObjectValue(false);
        testObjectValue2.addProperty("a", null);
        assertNotEquals(testObjectValue.hashCode(), testObjectValue2.hashCode());
    }
}
