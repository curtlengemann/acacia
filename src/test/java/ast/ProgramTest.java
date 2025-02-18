package test.java.ast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.Program;
import main.java.com.acacia.ast.StatementBlock;
import main.java.com.acacia.ast.literals.NullLiteral;
import main.java.com.acacia.ast.literals.NumericLiteral;

public class ProgramTest {
    
    @Test
    public void testProgram_getType() {
        final Program testProgram = new Program();
        assertEquals(NodeType.PROGRAM, testProgram.getType());
    }

    @Test
    public void testProgram_statements() {
        final Program testProgram = new Program();
        testProgram.addStatement(new NullLiteral());
        testProgram.addStatement(new NullLiteral());
        final StatementBlock expected = new StatementBlock();
        expected.add(new NullLiteral());
        expected.add(new NullLiteral());
        assertEquals(expected, testProgram.getStatementBlock());
    }

    @Test
    public void testProgram_equals_self() {
        final Program testProgram = new Program();
        assertTrue(testProgram.equals(testProgram));
    }

    @Test
    public void testProgram_equals_symmetrical() {
        final Program testProgram = new Program();
        assertTrue(testProgram.equals(new Program()));
        assertTrue(new Program().equals(testProgram));
    }

    @Test
    public void testProgram_equals_statements() {
        final Program testProgram = new Program();
        testProgram.addStatement(new NullLiteral());
        testProgram.addStatement(new NullLiteral());

        final Program testProgram2 = new Program();
        testProgram2.addStatement(new NullLiteral());
        testProgram2.addStatement(new NullLiteral());
        assertTrue(testProgram.equals(testProgram2));
    }

    @Test
    public void testProgram_notEquals_null() {
        final Program testProgram = new Program();
        assertFalse(testProgram.equals(null));
    }

    @Test
    public void testProgram_notEquals_changedNumberOfStatements() {
        final Program testProgram = new Program();
        testProgram.addStatement(new NullLiteral());
        testProgram.addStatement(new NullLiteral());

        final Program testProgram2 = new Program();
        testProgram2.addStatement(new NullLiteral());
        assertFalse(testProgram.equals(testProgram2));
    }

    
    @Test
    public void testProgram_notEquals_changedStatements() {
        final Program testProgram = new Program();
        testProgram.addStatement(new NullLiteral());

        final Program testProgram2 = new Program();
        testProgram2.addStatement(new NumericLiteral(null));
        assertFalse(testProgram.equals(testProgram2));
    }

    @Test
    public void testProgram_hashcode_equals_self() {
        final Program testProgram = new Program();
        assertEquals(testProgram.hashCode(), testProgram.hashCode());
    }

    @Test
    public void testProgram_hashcode_equals_noStatements() {
        final Program testProgram = new Program();
        assertEquals(new Program().hashCode(), testProgram.hashCode());
    }

    @Test
    public void testProgram_hashcode_equals_statements() {
        final Program testProgram = new Program();
        testProgram.addStatement(new NullLiteral());
        testProgram.addStatement(new NullLiteral());

        final Program testProgram2 = new Program();
        testProgram2.addStatement(new NullLiteral());
        testProgram2.addStatement(new NullLiteral());
        assertEquals(testProgram.hashCode(), testProgram2.hashCode());
    }

    @Test
    public void testProgram_hashcode_notEquals_changedNumberOfStatements() {
        final Program testProgram = new Program();
        testProgram.addStatement(new NullLiteral());

        final Program testProgram2 = new Program();
        testProgram2.addStatement(new NullLiteral());
        testProgram2.addStatement(new NullLiteral());
        assertNotEquals(testProgram.hashCode(), testProgram2.hashCode());
    }

    @Test
    public void testProgram_hashcode_notEquals_changedStatements() {
        final Program testProgram = new Program();
        testProgram.addStatement(new NullLiteral());

        final Program testProgram2 = new Program();
        testProgram2.addStatement(new NumericLiteral(null));
        assertNotEquals(testProgram.hashCode(), testProgram2.hashCode());
    }
}
