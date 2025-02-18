package test.java.runtime.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.runtime.values.NumberValue;
import main.java.com.acacia.runtime.values.ValueType;

public class NumberValueTest {
        
    @Test
    public void testNumberValue_getRuntimeType() {
        final NumberValue testNumberValue = new NumberValue(null);
        assertEquals(ValueType.NUMBER, testNumberValue.getRuntimeType());
    }

    @Test
    public void testNumberValue_getStringValue_null() {
        final NumberValue testNumberValue = new NumberValue(null);
        assertEquals("null", testNumberValue.getStringValue());
    }

    @Test
    public void testNumberValue_getStringValue() {
        final NumberValue testNumberValue = new NumberValue(Float.valueOf(4));
        assertEquals("4", testNumberValue.getStringValue());
    }

    @Test
    public void testNumberValue_getStringValue_withDecimal() {
        final NumberValue testNumberValue = new NumberValue(Float.valueOf("4.125"));
        assertEquals("4.125", testNumberValue.getStringValue());
    }

    @Test
    public void testNumberValue_getValue_null() {
        final NumberValue testNumberValue = new NumberValue(null);
        assertNull(testNumberValue.getValue());
    }

    @Test
    public void testNumberValue_getValue() {
        final NumberValue testNumberValue = new NumberValue(Float.valueOf(2));
        assertEquals(Float.valueOf(2), testNumberValue.getValue());
    }

    @Test
    public void testNumberValue_equals_self() {
        final NumberValue testNumberValue = new NumberValue(Float.valueOf(2));
        assertTrue(testNumberValue.equals(testNumberValue));
    }

    @Test
    public void testNumberValue_equals_symmetrical() {
        final NumberValue testNumberValue = new NumberValue(Float.valueOf(2));
        assertTrue(new NumberValue(Float.valueOf(2)).equals(testNumberValue));
        assertTrue(testNumberValue.equals(new NumberValue(Float.valueOf(2))));
    }

    @Test
    public void testNumberValue_equals_nullValue() {
        final NumberValue testNumberValue = new NumberValue(null);
        assertTrue(testNumberValue.equals(new NumberValue(null)));
    }

    @Test
    public void testNumberValue_notEquals_null() {
        final NumberValue testNumberValue = new NumberValue(null);
        assertFalse(testNumberValue.equals(null));
    }

    @Test
    public void testNumberValue_notEquals_nullValue() {
        final NumberValue testNumberValue = new NumberValue(null);
        assertFalse(testNumberValue.equals(new NumberValue(Float.valueOf(2))));
    }

    @Test
    public void testNumberValue_notEquals_changedValue() {
        final NumberValue testNumberValue = new NumberValue(Float.valueOf(2));
        assertFalse(testNumberValue.equals(new NumberValue(Float.valueOf(3))));
    }

    @Test
    public void testNumberValue_hashcode_equals_self() {
        final NumberValue testNumberValue = new NumberValue(null);
        assertEquals(testNumberValue.hashCode(), testNumberValue.hashCode());
    }

    @Test
    public void testNumberValue_hashcode_equals_noValue() {
        final NumberValue testNumberValue = new NumberValue(null);
        assertEquals(new NumberValue(null).hashCode(), testNumberValue.hashCode());
    }

    @Test
    public void testNumberValue_hashcode_equals_value() {
        final NumberValue testNumberValue = new NumberValue(Float.valueOf(2));
        assertEquals(new NumberValue(Float.valueOf(2)).hashCode(), testNumberValue.hashCode());
    }

    @Test
    public void testNumberValue_hashcode_notEquals_nullValue() {
        final NumberValue testNumberValue = new NumberValue(Float.valueOf(2));
        assertNotEquals(new NumberValue(null).hashCode(), testNumberValue.hashCode());
    }

    @Test
    public void testNumberValue_hashcode_notEquals_changedValue() {
        final NumberValue testNumberValue = new NumberValue(Float.valueOf(2));
        assertNotEquals(new NumberValue(Float.valueOf(3)).hashCode(), testNumberValue.hashCode());
    }
}
