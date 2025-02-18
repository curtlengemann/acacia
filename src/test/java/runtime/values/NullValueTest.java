package test.java.runtime.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.com.acacia.runtime.values.NullValue;
import main.java.com.acacia.runtime.values.ValueType;

public class NullValueTest {
    private NullValue testNullValue;

    @Before
    public void setUp() {
        testNullValue = new NullValue();
    }

    @Test
    public void testNullValue_getRuntimeType() {
        assertEquals(ValueType.NULL, testNullValue.getRuntimeType());
    }

    @Test
    public void testNullValue_getStringValue() {
        assertEquals("null", testNullValue.getStringValue());
    }

    @Test
    public void testNullValue_equals_self() {
        assertTrue(testNullValue.equals(testNullValue));
    }

    @Test
    public void testNullValue_equals_symmetrical() {
        assertTrue(testNullValue.equals(new NullValue()));
        assertTrue(new NullValue().equals(testNullValue));
    }

    @Test
    public void testNullValue_hashcode_equals_self() {
        assertEquals(testNullValue.hashCode(), testNullValue.hashCode());
    }

    @Test
    public void testNullValue_hashcode_equals() {
        assertEquals(new NullValue().hashCode(), testNullValue.hashCode());
    }
}
