package test.java.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.java.com.acacia.runtime.Environment;
import main.java.com.acacia.runtime.values.NullValue;
import main.java.com.acacia.runtime.values.NumberValue;

public class EnvironmentTest {
    private Environment parentEnvironment;


    private void assertScopeSetup() throws Exception {
        assertEquals(new NullValue(), parentEnvironment.lookupVar("null"));
        assertNotNull(parentEnvironment.lookupVar("print"));
        assertNotNull(parentEnvironment.lookupVar("len"));
        assertNotNull(parentEnvironment.lookupVar("charAt"));
    }

    @Before
    public void setUp() throws Exception {
        parentEnvironment = new Environment(null);
        assertScopeSetup();
        parentEnvironment.declareVar("x", new NumberValue(Float.valueOf(100)), false);
        parentEnvironment.declareVar("y", new NumberValue(Float.valueOf(50)), true);
    }

    @Test
    public void testEnvironment_declareVariable() throws Exception {
        parentEnvironment.declareVar("newvar", new NullValue(), true);
        assertEquals(new NullValue(), parentEnvironment.getVariable("newvar"));
        assertTrue(parentEnvironment.hasConstant("newvar"));
        assertEquals(new NullValue(), parentEnvironment.lookupVar("newvar"));
    }

    @Test
    public void testEnvironment_redeclareVariable() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> parentEnvironment.declareVar("x", null, false));
        assertEquals("Cannot redeclare variable: x", e.getMessage());
    }

    @Test
    public void testEnvironment_lookupUnkownVariable() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> parentEnvironment.lookupVar("unknown"));
        assertEquals("Cannot resolve variable: unknown. It doesn't exist.", e.getMessage());
    }

    @Test
    public void testEnvironment_assignVariable() throws Exception {
        parentEnvironment.assignVar("x", new NullValue());
        assertEquals(new NullValue(), parentEnvironment.lookupVar("x"));
    }

    @Test
    public void testEnvironment_assignUnknownVariable() throws Exception {
        parentEnvironment.assignVar("z", new NullValue());
        assertEquals(new NullValue(), parentEnvironment.lookupVar("z"));
    }

    @Test
    public void testEnvironment_reassignConstantVariable() throws Exception {
        final Exception e = assertThrows(Exception.class, () -> parentEnvironment.assignVar("y", null));
        assertEquals("Cannot reassign constant variable: y", e.getMessage());
    }

    @Test
    public void testEnvironment_child_declareSameVariable() throws Exception {
        final Environment childEnvironment = new Environment(parentEnvironment);
        final NumberValue num = new NumberValue(Float.valueOf(2));
        childEnvironment.declareVar("x", num, false);
        assertEquals(num, childEnvironment.lookupVar("x"));
    }

    @Test
    public void testEnvironment_child_assignParentVariable() throws Exception {
        final Environment childEnvironment = new Environment(parentEnvironment);
        final NumberValue num = new NumberValue(Float.valueOf(2));
        childEnvironment.assignVar("x", num);
        assertEquals(num, childEnvironment.lookupVar("x"));
    }

     @Test
    public void testEnvironment_equals_self() throws Exception {
        final Environment testEnvironment = new Environment(null);
        assertTrue(testEnvironment.equals(testEnvironment));
    }

    @Test
    public void testEnvironment_equals_symmetrical() throws Exception {
        final Environment testEnvironment = new Environment(parentEnvironment);
        assertTrue(new Environment(parentEnvironment).equals(testEnvironment));
        assertTrue(testEnvironment.equals(new Environment(parentEnvironment)));
    }

    @Test
    public void testEnvironment_notEquals_null() throws Exception {
        final Environment testEnvironment = new Environment(null);
        assertFalse(testEnvironment.equals(null));
    }

    @Test
    public void testEnvironment_notEquals_changedParent() throws Exception {
        final Environment testEnvironment = new Environment(parentEnvironment);
        assertFalse(testEnvironment.equals(new Environment(null)));
    }
    
    @Test
    public void testEnvironment_hashcode_equals_self() throws Exception {
        final Environment testEnvironment = new Environment(null);
        assertEquals(testEnvironment.hashCode(), testEnvironment.hashCode());
    }

    @Test
    public void testEnvironment_hashcode_equals() throws Exception {
        final Environment testEnvironment = new Environment(parentEnvironment);
        assertEquals(new Environment(parentEnvironment).hashCode(), testEnvironment.hashCode());
    }

    @Test
    public void testEnvironment_hashcode_notEquals_changedParent() throws Exception {
        final Environment testEnvironment = new Environment(parentEnvironment);
        assertNotEquals(new Environment(null).hashCode(), testEnvironment.hashCode());
    }
}
