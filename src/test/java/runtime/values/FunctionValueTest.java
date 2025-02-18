package test.java.runtime.values;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import main.java.com.acacia.runtime.Environment;
import main.java.com.acacia.runtime.values.FunctionValue;
import main.java.com.acacia.runtime.values.ValueType;
import main.java.com.acacia.ast.StatementBlock;
import main.java.com.acacia.ast.literals.NullLiteral;

public class FunctionValueTest {

    @Test
    public void testFunctionValue_getType() {
        final FunctionValue testFunctionValue = new FunctionValue(null, null, null);
        assertEquals(ValueType.FUNCTION, testFunctionValue.getRuntimeType());
    }

    @Test
    public void testFunctionValue_getDeclarationEnvironment_null() {
        final FunctionValue testFunctionValue = new FunctionValue(null, null, null);
        assertNull(testFunctionValue.getDeclarationEnvironment());
    }

    @Test
    public void testFunctionValue_getDeclarationEnvironment() throws Exception {
        final FunctionValue testFunctionValue = new FunctionValue(null, null, new Environment(null));
        assertEquals(new Environment(null), testFunctionValue.getDeclarationEnvironment());
    }

    @Test
    public void testFunctionValue_getParameters() throws Exception {
        final ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("x");
        parameters.add("y");
        final FunctionValue testFunctionValue = new FunctionValue(parameters, null, null);
        assertEquals(parameters, testFunctionValue.getParameters());
    }

    @Test
    public void testFunctionValue_getBodyIterator() throws Exception {
        final StatementBlock body = new StatementBlock();
        body.add(new NullLiteral());
        body.add(new NullLiteral());
        final FunctionValue testFunctionValue = new FunctionValue(null, body, null);
        assertEquals(body, testFunctionValue.getBody());
    }

    @Test
    public void testFunctionValue_equals_self() {
        final FunctionValue testFunctionValue = new FunctionValue(null, null, null);
        assertTrue(testFunctionValue.equals(testFunctionValue));
    }

    @Test
    public void testFunctionValue_equals_symmetrical() {
        final FunctionValue testFunctionValue = new FunctionValue(null, null, null);
        assertTrue(new FunctionValue(null, null, null).equals(testFunctionValue));
        assertTrue(testFunctionValue.equals(new FunctionValue(null, null, null)));
    }

    @Test
    public void testFunctionValue_equals_environment() throws Exception {
        final FunctionValue testFunctionValue = new FunctionValue(null, null, new Environment(null));
        assertTrue(testFunctionValue.equals(new FunctionValue(null, null, new Environment(null))));
    }

    @Test
    public void testFunctionValue_equals_body() {
        final StatementBlock body = new StatementBlock();
        body.add(new NullLiteral());
        body.add(new NullLiteral());
        final FunctionValue testFunctionValue = new FunctionValue(null, body, null);
        assertTrue(testFunctionValue.equals(new FunctionValue(null, body, null)));
    }

    @Test
    public void testFunctionValue_equals_parameters() {
        final ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("x");
        parameters.add("y");
        final FunctionValue testFunctionValue = new FunctionValue(parameters, null, null);
        assertTrue(testFunctionValue.equals(new FunctionValue(parameters, null, null)));
    }

    @Test
    public void testFunctionValue_notEquals_null() {
        final FunctionValue testFunctionValue = new FunctionValue(null, null, null);
        assertFalse(testFunctionValue.equals(null));
    }

    @Test
    public void testFunctionValue_notEquals_changedEnvironment() throws Exception {
        final FunctionValue testFunctionValue = new FunctionValue(null, null, new Environment(null));
        assertFalse(testFunctionValue.equals(new FunctionValue(null, null, new Environment(new Environment(null)))));
    }

    @Test
    public void testFunctionValue_notEquals_changedBody() {
        final StatementBlock body = new StatementBlock();
        body.add(new NullLiteral());
        body.add(new NullLiteral());
        final FunctionValue testFunctionValue = new FunctionValue(null, body, null);
        assertFalse(testFunctionValue.equals(new FunctionValue(null, new StatementBlock(), null)));
    }

    @Test
    public void testFunctionValue_notEquals_changedParameters() {
        final ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("x");
        parameters.add("y");
        final FunctionValue testFunctionValue = new FunctionValue(parameters, null, null);
        assertFalse(testFunctionValue.equals(new FunctionValue(new ArrayList<String>(), null, null)));
    }
    
    @Test
    public void testFunctionValue_hashcode_equals_self() {
        final FunctionValue testFunctionValue = new FunctionValue(null, null, null);
        assertEquals(testFunctionValue.hashCode(), testFunctionValue.hashCode());
    }

    @Test
    public void testFunctionValue_hashcode_equals_environment() throws Exception {
        final FunctionValue testFunctionValue = new FunctionValue(null, null, new Environment(null));
        assertEquals(new FunctionValue(null, null, new Environment(null)).hashCode(), testFunctionValue.hashCode());
    }

    @Test
    public void testFunctionValue_hashcode_equals_body() {
        final StatementBlock body = new StatementBlock();
        body.add(new NullLiteral());
        body.add(new NullLiteral());
        final FunctionValue testFunctionValue = new FunctionValue(null, body, null);
        assertEquals(new FunctionValue(null, body, null).hashCode(), testFunctionValue.hashCode());
    }

    @Test
    public void testFunctionValue_hashcode_equals_parameters() {
        final ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("x");
        parameters.add("y");
        final FunctionValue testFunctionValue = new FunctionValue(parameters, null, null);
        assertEquals(new FunctionValue(parameters, null, null).hashCode(), testFunctionValue.hashCode());
    }

    @Test
    public void testFunctionValue_hashcode_notEquals_changedEnvironemnt() throws Exception {
        final FunctionValue testFunctionValue = new FunctionValue(null, null, new Environment(null));
        assertNotEquals(new FunctionValue(null, null, new Environment(new Environment(null))).hashCode(), testFunctionValue.hashCode());
    }

    @Test
    public void testFunctionValue_hashcode_notEquals_changedBody() {
        final StatementBlock body = new StatementBlock();
        body.add(new NullLiteral());
        body.add(new NullLiteral());
        final FunctionValue testFunctionValue = new FunctionValue(null, body, null);
        assertNotEquals(new FunctionValue(null, new StatementBlock(), null).hashCode(), testFunctionValue.hashCode());
    }

    @Test
    public void testFunctionValue_hashcode_notEquals_changedParameters() {
        final ArrayList<String> parameters = new ArrayList<String>();
        parameters.add("x");
        parameters.add("y");
        final FunctionValue testFunctionValue = new FunctionValue(parameters, null, null);
        assertNotEquals(new FunctionValue(new ArrayList<String>(), null, null).hashCode(), testFunctionValue.hashCode());
    }
}
