package test.java.ast.literals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.literals.NullLiteral;

public class NullLiteralTest {
    private NullLiteral testNullLiteral;

    @Before
    public void setUp() {
        testNullLiteral = new NullLiteral();
    }
    
    @Test
    public void testNullLiteral_getType() {
        assertEquals(NodeType.NULL_LITERAL, testNullLiteral.getType());
    }

    @Test
    public void testNullLiteral_equals_self() {
        assertTrue(testNullLiteral.equals(testNullLiteral));
    }

    @Test
    public void testNullLiteral_equals_symmetrical() {
        assertTrue(testNullLiteral.equals(new NullLiteral()));
        assertTrue(new NullLiteral().equals(testNullLiteral));
    }

    @Test
    public void testNullLiteral_hashcode_equals_self() {
        assertEquals(testNullLiteral.hashCode(), testNullLiteral.hashCode());
    }

    @Test
    public void testNullLiteral_hashcode_equals() {
        assertEquals(new NullLiteral().hashCode(), testNullLiteral.hashCode());
    }
}
