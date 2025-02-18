package test.java.ast.literals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.literals.NumericLiteral;

public class NumericLiteralTest {
    
    @Test
    public void testNumericLiteral_getType() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(null);
        assertEquals(NodeType.NUMERIC_LITERAL, testNumericLiteral.getType());
    }

    @Test
    public void testNumericLiteral_getValue_null() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(null);
        assertNull(testNumericLiteral.getValue());
    }

    @Test
    public void testNumericLiteral_getValue() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(Float.valueOf(2));
        assertEquals(Float.valueOf(2), testNumericLiteral.getValue());
    }

    @Test
    public void testNumericLiteral_equals_self() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(Float.valueOf(2));
        assertTrue(testNumericLiteral.equals(testNumericLiteral));
    }

    @Test
    public void testNumericLiteral_equals_symmetrical() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(Float.valueOf(2));
        assertTrue(new NumericLiteral(Float.valueOf(2)).equals(testNumericLiteral));
        assertTrue(testNumericLiteral.equals(new NumericLiteral(Float.valueOf(2))));
    }

    @Test
    public void testNumericLiteral_equals_nullValue() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(null);
        assertTrue(testNumericLiteral.equals(new NumericLiteral(null)));
    }

    @Test
    public void testNumericLiteral_notEquals_null() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(null);
        assertFalse(testNumericLiteral.equals(null));
    }

    @Test
    public void testNumericLiteral_notEquals_nullValue() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(null);
        assertFalse(testNumericLiteral.equals(new NumericLiteral(Float.valueOf(2))));
    }

    @Test
    public void testNumericLiteral_notEquals_changedValue() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(Float.valueOf(2));
        assertFalse(testNumericLiteral.equals(new NumericLiteral(Float.valueOf(3))));
    }

    @Test
    public void testNumericLiteral_hashcode_equals_self() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(null);
        assertEquals(testNumericLiteral.hashCode(), testNumericLiteral.hashCode());
    }

    @Test
    public void testNumericLiteral_hashcode_equals_noValue() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(null);
        assertEquals(new NumericLiteral(null).hashCode(), testNumericLiteral.hashCode());
    }

    @Test
    public void testNumericLiteral_hashcode_equals_value() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(Float.valueOf(2));
        assertEquals(new NumericLiteral(Float.valueOf(2)).hashCode(), testNumericLiteral.hashCode());
    }

    @Test
    public void testNumericLiteral_hashcode_notEquals_nullValue() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(Float.valueOf(2));
        assertNotEquals(new NumericLiteral(null).hashCode(), testNumericLiteral.hashCode());
    }

    @Test
    public void testNumericLiteral_hashcode_notEquals_changedValue() {
        final NumericLiteral testNumericLiteral = new NumericLiteral(Float.valueOf(2));
        assertNotEquals(new NumericLiteral(Float.valueOf(3)).hashCode(), testNumericLiteral.hashCode());
    }
}
