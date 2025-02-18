package test.java.runtime.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.runtime.values.BooleanValue;
import main.java.com.acacia.runtime.values.ValueType;

public class BooleanValueTest {
            
    @Test
    public void testBooleanValue_getRuntimeType() {
        final BooleanValue testBooleanValue = new BooleanValue(false);
        assertEquals(ValueType.BOOLEAN, testBooleanValue.getRuntimeType());
    }

    @Test
    public void testBooleanValue_getValue() {
        final BooleanValue testBooleanValue = new BooleanValue(true);
        assertTrue(testBooleanValue.getValue());
    }

    @Test
    public void testBooleanValue_equals_self() {
        final BooleanValue testBooleanValue = new BooleanValue(false);
        assertTrue(testBooleanValue.equals(testBooleanValue));
    }

    @Test
    public void testBooleanValue_equals_symmetrical() {
        final BooleanValue testBooleanValue = new BooleanValue(true);
        assertTrue(new BooleanValue(true).equals(testBooleanValue));
        assertTrue(testBooleanValue.equals(new BooleanValue(true)));
    }

    @Test
    public void testBooleanValue_notEquals_null() {
        final BooleanValue testBooleanValue = new BooleanValue(false);
        assertFalse(testBooleanValue.equals(null));
    }

    @Test
    public void testBooleanValue_notEquals_changedValue() {
        final BooleanValue testBooleanValue = new BooleanValue(false);
        assertFalse(testBooleanValue.equals(new BooleanValue(true)));
    }

        @Test
    public void testBooleanValue_hashcode_equals_self() {
        final BooleanValue testBooleanValue = new BooleanValue(false);
        assertEquals(testBooleanValue.hashCode(), testBooleanValue.hashCode());
    }

    @Test
    public void testBooleanValue_hashcode_equals() {
        final BooleanValue testBooleanValue = new BooleanValue(true);
        assertEquals(new BooleanValue(true).hashCode(), testBooleanValue.hashCode());
    }

    @Test
    public void testBooleanValue_hashcode_notEquals_changedValue() {
        final BooleanValue testBooleanValue = new BooleanValue(true);
        assertNotEquals(new BooleanValue(false).hashCode(), testBooleanValue.hashCode());
    }
}
