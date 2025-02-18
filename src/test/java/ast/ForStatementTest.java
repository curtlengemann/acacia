package test.java.ast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.com.acacia.ast.ForStatement;
import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.StatementBlock;
import main.java.com.acacia.ast.literals.NullLiteral;

public class ForStatementTest {

    @Test
    public void testForStatement_getType() {
        final ForStatement testForStatement = new ForStatement(null, null);
        assertEquals(NodeType.FOR, testForStatement.getType());
    }

    @Test
    public void testForStatement_getCondition_null() {
        final ForStatement testForStatement = new ForStatement(null, null);
        assertNull(testForStatement.getCondition());
    }

    @Test
    public void testForStatement_getCondition() {
        final ForStatement testForStatement = new ForStatement(new NullLiteral(), null);
        assertEquals(new NullLiteral(), testForStatement.getCondition());
    }

    @Test
    public void testForStatement_getStatementBlock_null() {
        final ForStatement testForStatement = new ForStatement(null, null);
        assertNull(testForStatement.getStatementBlock());
    }

    @Test
    public void testForStatement_getStatementBlock() {
        final ForStatement testForStatement = new ForStatement(null, new StatementBlock());
        assertEquals(new StatementBlock(), testForStatement.getStatementBlock());
    }

    @Test
    public void testForStatement_equals_self() {
        final ForStatement testForStatement = new ForStatement(null, null);
        assertTrue(testForStatement.equals(testForStatement));
    }

    @Test
    public void testForStatement_equals_symmetrical() {
        final ForStatement testForStatement = new ForStatement(new NullLiteral(), new StatementBlock());
        assertTrue(new ForStatement(new NullLiteral(), new StatementBlock()).equals(testForStatement));
        assertTrue(testForStatement.equals(new ForStatement(new NullLiteral(), new StatementBlock())));
    }

    @Test
    public void testForStatement_notEquals_null() {
        final ForStatement testForStatement = new ForStatement(null, null);
        assertFalse(testForStatement.equals(null));
    }

    @Test
    public void testForStatement_notEquals_changedCondition() {
        final ForStatement testForStatement = new ForStatement(null, null);
        assertFalse(testForStatement.equals(new ForStatement(new NullLiteral(), null)));
    }

    @Test
    public void testForStatement_notEquals_changedStatementBlock() {
        final ForStatement testForStatement = new ForStatement(null, null);
        assertFalse(testForStatement.equals(new ForStatement(null, new StatementBlock())));
    }
    
    @Test
    public void testForStatement_hashcode_equals_self() {
        final ForStatement testForStatement = new ForStatement(null, null);
        assertEquals(testForStatement.hashCode(), testForStatement.hashCode());
    }

    @Test
    public void testForStatement_hashcode_equals_condition() {
        final ForStatement testForStatement = new ForStatement(new NullLiteral(), null);
        assertEquals(new ForStatement(new NullLiteral(), null).hashCode(), testForStatement.hashCode());
    }

    @Test
    public void testForStatement_hashcode_equals_statementBlock() {
        final ForStatement testForStatement = new ForStatement(null, new StatementBlock());
        assertEquals(new ForStatement(null, new StatementBlock()).hashCode(), testForStatement.hashCode());
    }

    @Test
    public void testForStatement_hashcode_notEquals_changedCondition() {
        final ForStatement testForStatement = new ForStatement(null, null);
        assertNotEquals(new ForStatement(new NullLiteral(), null).hashCode(), testForStatement.hashCode());
    }

    @Test
    public void testForStatement_hashcode_notEquals_changedStatementBlock() {
        final ForStatement testForStatement = new ForStatement(null, null);
        assertNotEquals(new ForStatement(null, new StatementBlock()).hashCode(), testForStatement.hashCode());
    }
}
