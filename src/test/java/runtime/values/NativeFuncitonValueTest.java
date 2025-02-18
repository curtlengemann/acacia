package test.java.runtime.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.function.Function;

import org.junit.Test;

import main.java.com.acacia.runtime.values.IRuntimeValue;
import main.java.com.acacia.runtime.values.NativeFunctionValue;
import main.java.com.acacia.runtime.values.NullValue;
import main.java.com.acacia.runtime.values.StringValue;
import main.java.com.acacia.runtime.values.ValueType;

public class NativeFuncitonValueTest {
    
    @Test
    public void testNativeFunctionValue_getRuntimeType() {
        final NativeFunctionValue testNativeFunctionValue = new NativeFunctionValue(null);
        assertEquals(ValueType.NATIVE_FUNCTION, testNativeFunctionValue.getRuntimeType());
    }

    @Test
    public void testNativeFunctionValue_evaluate_null() {
        final NativeFunctionValue testNativeFunctionValue = new NativeFunctionValue(null);
        assertEquals(new NullValue(), testNativeFunctionValue.evaluate(null));
    }

    
    @Test
    public void testNativeFunctionValue_evaluate() {
        final NativeFunctionValue testNativeFunctionValue = new NativeFunctionValue(args -> {
            return new StringValue("asdf");
        });
        assertEquals(new StringValue("asdf"), testNativeFunctionValue.evaluate(null));
    }


    @Test
    public void testNativeFunctionValue_equals_self() {
        final NativeFunctionValue testNativeFunctionValue = new NativeFunctionValue(null);
        assertTrue(testNativeFunctionValue.equals(testNativeFunctionValue));
    }

    @Test
    public void testNativeFunctionValue_equals_symmetrical() {
        final Function<List<IRuntimeValue>, IRuntimeValue> testFunc = args -> {
            return new NullValue();
        };
        final NativeFunctionValue testNativeFunctionValue = new NativeFunctionValue(testFunc);
        assertTrue(new NativeFunctionValue(testFunc).equals(testNativeFunctionValue));
        assertTrue(testNativeFunctionValue.equals(new NativeFunctionValue(testFunc)));
    }

    @Test
    public void testNativeFunctionValue_notEquals_null() {
        final NativeFunctionValue testNativeFunctionValue = new NativeFunctionValue(null);
        assertFalse(testNativeFunctionValue.equals(null));
    }

    @Test
    public void testNativeFunctionValue_notEquals_changedValue() {
        final Function<List<IRuntimeValue>, IRuntimeValue> testFunc1 = args -> {
            return new NullValue();
        };
        final Function<List<IRuntimeValue>, IRuntimeValue> testFunc2 = args -> {
            return new NullValue();
        };
        final NativeFunctionValue testNativeFunctionValue = new NativeFunctionValue(testFunc1);
        assertFalse(testNativeFunctionValue.equals(new NativeFunctionValue(testFunc2)));
    }

        @Test
    public void testNativeFunctionValue_hashcode_equals_self() {
        final NativeFunctionValue testNativeFunctionValue = new NativeFunctionValue(null);
        assertEquals(testNativeFunctionValue.hashCode(), testNativeFunctionValue.hashCode());
    }

    @Test
    public void testNativeFunctionValue_hashcode_equals() {
        final Function<List<IRuntimeValue>, IRuntimeValue> testFunc = args -> {
            return new NullValue();
        };
        final NativeFunctionValue testNativeFunctionValue = new NativeFunctionValue(testFunc);
        assertEquals(new NativeFunctionValue(testFunc).hashCode(), testNativeFunctionValue.hashCode());
    }

    @Test
    public void testNativeFunctionValue_hashcode_notEquals_changedValue() {
        final Function<List<IRuntimeValue>, IRuntimeValue> testFunc1 = args -> {
            return new NullValue();
        };
        final Function<List<IRuntimeValue>, IRuntimeValue> testFunc2 = args -> {
            return new NullValue();
        };
        final NativeFunctionValue testNativeFunctionValue = new NativeFunctionValue(testFunc1);
        assertNotEquals(new NativeFunctionValue(testFunc2).hashCode(), testNativeFunctionValue.hashCode());
    }
}
