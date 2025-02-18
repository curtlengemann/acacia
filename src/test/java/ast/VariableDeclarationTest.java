package test.java.ast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.VariableDeclaration;
import main.java.com.acacia.ast.literals.NullLiteral;
import main.java.com.acacia.ast.literals.NumericLiteral;

public class VariableDeclarationTest {

    @Test
    public void testVariableDeclaration_getType() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "myVar");
        assertEquals(NodeType.VAR_DECLARATION, testVariableDeclaration.getType());
    }

    @Test
    public void testVariableDeclaration_isConstant() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "myVar");
        assertTrue(testVariableDeclaration.isConstant());
    }

    @Test
    public void testVariableDeclaration_getIdentifier() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "myVar");
        assertEquals("myVar", testVariableDeclaration.getIdentifier());
    }

    @Test
    public void testVariableDeclaration_noExpression() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "myVar");
        assertFalse(testVariableDeclaration.hasExpression());
        assertNull(testVariableDeclaration.getExpression());
    }

    @Test
    public void testVariableDeclaration_expression() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar", new NullLiteral());
        assertTrue(testVariableDeclaration.hasExpression());
        assertEquals(new NullLiteral(), testVariableDeclaration.getExpression());
    }

    @Test
    public void testVariableDeclaration_equals_self() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar");
        assertTrue(testVariableDeclaration.equals(testVariableDeclaration));
    }

    @Test
    public void testVariableDeclaration_equals_symmetrical() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar");
        assertTrue(new VariableDeclaration(true, "newVar").equals(testVariableDeclaration));
        assertTrue(testVariableDeclaration.equals(new VariableDeclaration(true, "newVar")));
    }

    @Test
    public void testVariableDeclaration_equals_expression() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar", new NullLiteral());
        assertTrue(testVariableDeclaration.equals(new VariableDeclaration(true, "newVar", new NullLiteral())));
    }

    @Test
    public void testVariableDeclaration_notEquals_null() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar");
        assertFalse(testVariableDeclaration.equals(null));
    }

    @Test
    public void testVariableDeclaration_notEquals_changedConstant() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar");
        assertFalse(testVariableDeclaration.equals(new VariableDeclaration(false, "newVar")));
    }

    @Test
    public void testVariableDeclaration_notEquals_changedIdentifier() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar");
        assertFalse(testVariableDeclaration.equals(new VariableDeclaration(true, "random")));
    }

    @Test
    public void testVariableDeclaration_notEquals_nullExpression() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar", new NullLiteral());
        assertFalse(testVariableDeclaration.equals(new VariableDeclaration(true, "newVar")));
    }

    @Test
    public void testVariableDeclaration_notEquals_changedExpression() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar", new NullLiteral());
        assertFalse(testVariableDeclaration.equals(new VariableDeclaration(true, "newVar", new NumericLiteral(null))));
    }
    
    @Test
    public void testVariableDeclaration_hashcode_equals_self() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar");
        assertEquals(testVariableDeclaration.hashCode(), testVariableDeclaration.hashCode());
    }

    @Test
    public void testVariableDeclaration_hashcode_equals_noExpression() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar");
        assertEquals(new VariableDeclaration(true, "newVar").hashCode(), testVariableDeclaration.hashCode());
    }

    @Test
    public void testVariableDeclaration_hashcode_equals_expression() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar", new NullLiteral());
        assertEquals(new VariableDeclaration(true, "newVar", new NullLiteral()).hashCode(), testVariableDeclaration.hashCode());
    }

    @Test
    public void testVariableDeclaration_hashcode_notEquals_changedConstant() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar");
        assertNotEquals(new VariableDeclaration(false, "newVar").hashCode(), testVariableDeclaration.hashCode());
    }

    @Test
    public void testVariableDeclaration_hashcode_notEquals_changedIdentifier() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar");
        assertNotEquals(new VariableDeclaration(true, "random").hashCode(), testVariableDeclaration.hashCode());
    }

    @Test
    public void testVariableDeclaration_hashcode_notEquals_nullExpression() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar");
        assertNotEquals(new VariableDeclaration(true, "newVar", new NullLiteral()).hashCode(), testVariableDeclaration.hashCode());
    }

    @Test
    public void testVariableDeclaration_hashcode_notEquals_changedExpression() {
        final VariableDeclaration testVariableDeclaration = new VariableDeclaration(true, "newVar", new NullLiteral());
        assertNotEquals(new VariableDeclaration(true, "newVar", new NumericLiteral(null)).hashCode(), testVariableDeclaration.hashCode());
    }
}
