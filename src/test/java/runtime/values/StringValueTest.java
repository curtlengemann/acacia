package test.java.runtime.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.runtime.values.StringValue;
import main.java.com.acacia.runtime.values.ValueType;

public class StringValueTest {

    @Test
    public void testStringValue_getRuntimeType() {
        final StringValue testStringValue = new StringValue(null);
        assertEquals(ValueType.STRING, testStringValue.getRuntimeType());
    }

    @Test
    public void testStringValue_getStringValue_null() {
        final StringValue testStringValue = new StringValue(null);
        assertEquals("null", testStringValue.getStringValue());
    }

    @Test
    public void testStringValue_getStringValue() {
        final StringValue testStringValue = new StringValue("asdf");
        assertEquals("asdf", testStringValue.getStringValue());
    }

    @Test
    public void testStringValue_getValue_null() {
        final StringValue testStringValue = new StringValue(null);
        assertNull(testStringValue.getValue());
    }

    @Test
    public void testStringValue_getValue() {
        final StringValue testStringValue = new StringValue("asdf");
        assertEquals("asdf", testStringValue.getValue());
    }

    @Test
    public void testStringValue_equals_self() {
        final StringValue testStringValue = new StringValue("asdf");
        assertTrue(testStringValue.equals(testStringValue));
    }

    @Test
    public void testStringValue_equals_symmetrical() {
        final StringValue testStringValue = new StringValue("asdf");
        assertTrue(new StringValue("asdf").equals(testStringValue));
        assertTrue(testStringValue.equals(new StringValue("asdf")));
    }

    @Test
    public void testStringValue_equals_nullValue() {
        final StringValue testStringValue = new StringValue(null);
        assertTrue(testStringValue.equals(new StringValue(null)));
    }

    @Test
    public void testStringValue_notEquals_null() {
        final StringValue testStringValue = new StringValue(null);
        assertFalse(testStringValue.equals(null));
    }

    @Test
    public void testStringValue_notEquals_nullValue() {
        final StringValue testStringValue = new StringValue(null);
        assertFalse(testStringValue.equals(new StringValue("asdf")));
    }

    @Test
    public void testStringValue_notEquals_changedValue() {
        final StringValue testStringValue = new StringValue("asdf");
        assertFalse(testStringValue.equals(new StringValue("Asdf")));
    }

    @Test
    public void testStringValue_hashcode_equals_self() {
        final StringValue testStringValue = new StringValue(null);
        assertEquals(testStringValue.hashCode(), testStringValue.hashCode());
    }

    @Test
    public void testStringValue_hashcode_equals_noValue() {
        final StringValue testStringValue = new StringValue(null);
        assertEquals(new StringValue(null).hashCode(), testStringValue.hashCode());
    }

    @Test
    public void testStringValue_hashcode_equals_value() {
        final StringValue testStringValue = new StringValue("asdf");
        assertEquals(new StringValue("asdf").hashCode(), testStringValue.hashCode());
    }

    @Test
    public void testStringValue_hashcode_notEquals_nullValue() {
        final StringValue testStringValue = new StringValue("asdf");
        assertNotEquals(new StringValue(null).hashCode(), testStringValue.hashCode());
    }

    @Test
    public void testStringValue_hashcode_notEquals_changedValue() {
        final StringValue testStringValue = new StringValue("asdf");
        assertNotEquals(new StringValue("asdff").hashCode(), testStringValue.hashCode());
    }
}
