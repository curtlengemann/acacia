package test.java.ast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.StatementBlock;
import main.java.com.acacia.ast.FunctionDeclaration;
import main.java.com.acacia.ast.literals.Identifier;
import main.java.com.acacia.ast.literals.NullLiteral;

public class FunctionDeclarationTest {
    
    @Test
    public void testFunctionDeclaration_getType() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration(null);
        assertEquals(NodeType.FUNCTION_DECLARATION, testFunctionDeclaration.getType());
    }

    @Test
    public void testFunctionDeclaration_getName_null() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration(null);
        assertNull(testFunctionDeclaration.getName());
    }

    @Test
    public void testFunctionDeclaration_getName() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        assertEquals("asdf", testFunctionDeclaration.getName());
    }

    @Test
    public void testFunctionDeclaration_body_empty() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration(null);
        assertEquals(0, testFunctionDeclaration.getBody().getStatements().size());
    }

    @Test
    public void testFunctionDeclaration_body() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration(null);
        testFunctionDeclaration.addStatementToBody(new NullLiteral());
        testFunctionDeclaration.addStatementToBody(new Identifier(null));
        final StatementBlock expectedBody = new StatementBlock();
        expectedBody.add(new NullLiteral());
        expectedBody.add(new Identifier(null));
        assertEquals(expectedBody, testFunctionDeclaration.getBody());
    }

    @Test
    public void testFunctionDeclaration_parameters_empty() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration(null);
        assertEquals(0, testFunctionDeclaration.getParameters().size());
    }

    @Test
    public void testFunctionDeclaration_parameters() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration(null);
        testFunctionDeclaration.addParameter("x");
        testFunctionDeclaration.addParameter("y");
        final List<String> parameters = testFunctionDeclaration.getParameters();
        assertEquals(2, parameters.size());
        assertEquals("x", parameters.get(0));
        assertEquals("y", parameters.get(1));
    }

    @Test
    public void testFunctionDeclaration_equals_self() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration(null);
        assertTrue(testFunctionDeclaration.equals(testFunctionDeclaration));
    }

    @Test
    public void testFunctionDeclaration_equals_symmetrical() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        assertTrue(new FunctionDeclaration("asdf").equals(testFunctionDeclaration));
        assertTrue(testFunctionDeclaration.equals(new FunctionDeclaration("asdf")));
    }

    @Test
    public void testFunctionDeclaration_equals_body() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        testFunctionDeclaration.addStatementToBody(new NullLiteral());
        final FunctionDeclaration testFunctionDeclaration2 = new FunctionDeclaration("asdf");
        testFunctionDeclaration2.addStatementToBody(new NullLiteral());
        assertTrue(testFunctionDeclaration.equals(testFunctionDeclaration2));
    }

    @Test
    public void testFunctionDeclaration_equals_parameters() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        testFunctionDeclaration.addParameter("x");
        final FunctionDeclaration testFunctionDeclaration2 = new FunctionDeclaration("asdf");
        testFunctionDeclaration2.addParameter("x");
        assertTrue(testFunctionDeclaration.equals(testFunctionDeclaration2));
    }

    @Test
    public void testFunctionDeclaration_notEquals_null() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration(null);
        assertFalse(testFunctionDeclaration.equals(null));
    }

    @Test
    public void testFunctionDeclaration_notEquals_changedName() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        assertFalse(testFunctionDeclaration.equals(new FunctionDeclaration("ASDF")));
    }

    @Test
    public void testFunctionDeclaration_notEquals_changedBody() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        testFunctionDeclaration.addStatementToBody(new NullLiteral());
        final FunctionDeclaration testFunctionDeclaration2 = new FunctionDeclaration("asdf");
        assertFalse(testFunctionDeclaration.equals(testFunctionDeclaration2));
    }

    @Test
    public void testFunctionDeclaration_notEquals_changedParameters() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        testFunctionDeclaration.addParameter("x");
        final FunctionDeclaration testFunctionDeclaration2 = new FunctionDeclaration("asdf");
        assertFalse(testFunctionDeclaration.equals(testFunctionDeclaration2));
    }
    
    @Test
    public void testFunctionDeclaration_hashcode_equals_self() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration(null);
        assertEquals(testFunctionDeclaration.hashCode(), testFunctionDeclaration.hashCode());
    }

    @Test
    public void testFunctionDeclaration_hashcode_equals_name() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        assertEquals(new FunctionDeclaration("asdf").hashCode(), testFunctionDeclaration.hashCode());
    }

    @Test
    public void testFunctionDeclaration_hashcode_equals_body() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        testFunctionDeclaration.addStatementToBody(new NullLiteral());
        final FunctionDeclaration testFunctionDeclaration2 = new FunctionDeclaration("asdf");
        testFunctionDeclaration2.addStatementToBody(new NullLiteral());
        assertEquals(testFunctionDeclaration2.hashCode(), testFunctionDeclaration.hashCode());
    }

    @Test
    public void testFunctionDeclaration_hashcode_equals_parameters() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        testFunctionDeclaration.addParameter("x");
        final FunctionDeclaration testFunctionDeclaration2 = new FunctionDeclaration("asdf");
        testFunctionDeclaration2.addParameter("x");
        assertEquals(testFunctionDeclaration2.hashCode(), testFunctionDeclaration.hashCode());
    }

    @Test
    public void testFunctionDeclaration_hashcode_notEquals_changedName() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        assertNotEquals(new FunctionDeclaration("ASDF").hashCode(), testFunctionDeclaration.hashCode());
    }

    @Test
    public void testFunctionDeclaration_hashcode_notEquals_changedBody() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        testFunctionDeclaration.addStatementToBody(new NullLiteral());
        final FunctionDeclaration testFunctionDeclaration2 = new FunctionDeclaration("asdf");
        assertNotEquals(testFunctionDeclaration2.hashCode(), testFunctionDeclaration.hashCode());
    }

    @Test
    public void testFunctionDeclaration_hashcode_notEquals_changedParameters() {
        final FunctionDeclaration testFunctionDeclaration = new FunctionDeclaration("asdf");
        testFunctionDeclaration.addParameter("x");
        final FunctionDeclaration testFunctionDeclaration2 = new FunctionDeclaration("asdf");
        assertNotEquals(testFunctionDeclaration2.hashCode(), testFunctionDeclaration.hashCode());
    }
}
