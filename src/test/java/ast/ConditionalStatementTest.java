package test.java.ast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import main.java.com.acacia.ast.ConditionalStatement;
import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.StatementBlock;
import main.java.com.acacia.ast.literals.NullLiteral;

public class ConditionalStatementTest {

    @Test
    public void testConditionalStatement_getType() {
        final ConditionalStatement testConditionalStatement = new ConditionalStatement();
        assertEquals(NodeType.CONDITONAL, testConditionalStatement.getType());
    }

    @Test
    public void testConditionalStatement_getConditionsAndStatements() {
        final ConditionalStatement testConditionalStatement = new ConditionalStatement();
        testConditionalStatement.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        testConditionalStatement.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        
        final List<IExpression> conditions = testConditionalStatement.getConditions();
        assertEquals(2, conditions.size());
        assertEquals(new NullLiteral(), conditions.get(0));
        assertEquals(new NullLiteral(), conditions.get(1));

        final List<StatementBlock> statementBlocks = testConditionalStatement.getStatements();
        assertEquals(2, statementBlocks.size());
        assertEquals(new StatementBlock(), statementBlocks.get(0));
        assertEquals(new StatementBlock(), statementBlocks.get(1));
    }

    @Test
    public void testConditionalStatement_equals_self() {
        final ConditionalStatement testConditionalStatement = new ConditionalStatement();
        assertTrue(testConditionalStatement.equals(testConditionalStatement));
    }

    @Test
    public void testConditionalStatement_equals_symmetrical() {
        final ConditionalStatement testConditionalStatement = new ConditionalStatement();
        assertTrue(new ConditionalStatement().equals(testConditionalStatement));
        assertTrue(testConditionalStatement.equals(new ConditionalStatement()));
    }

    @Test
    public void testConditionalStatement_equals_withConditionsAndStatements() {
        final ConditionalStatement testConditionalStatement = new ConditionalStatement();
        testConditionalStatement.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        testConditionalStatement.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        final ConditionalStatement testConditionalStatement2 = new ConditionalStatement();
        testConditionalStatement2.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        testConditionalStatement2.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        assertTrue(testConditionalStatement.equals(testConditionalStatement2));
    }

    @Test
    public void testConditionalStatement_notEquals_null() {
        final ConditionalStatement testConditionalStatement = new ConditionalStatement();
        assertFalse(testConditionalStatement.equals(null));
    }

    @Test
    public void testConditionalStatement_notEquals_changedConditions() {
        final ConditionalStatement testConditionalStatement = new ConditionalStatement();
        testConditionalStatement.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        final ConditionalStatement testConditionalStatement2 = new ConditionalStatement();
        testConditionalStatement2.addConditionAndStatementBlock(null, new StatementBlock());
        assertFalse(testConditionalStatement.equals(testConditionalStatement2));
    }

    @Test
    public void testConditionalStatement_notEquals_changedStatements() {
        final ConditionalStatement testConditionalStatement = new ConditionalStatement();
        testConditionalStatement.addConditionAndStatementBlock(new NullLiteral(), null);
        final ConditionalStatement testConditionalStatement2 = new ConditionalStatement();
        testConditionalStatement2.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        assertFalse(testConditionalStatement.equals(testConditionalStatement2));
    }
    
    @Test
    public void testConditionalStatement_hashcode_equals_self() {
        final ConditionalStatement testConditionalStatement = new ConditionalStatement();
        assertEquals(testConditionalStatement.hashCode(), testConditionalStatement.hashCode());
    }

    @Test
    public void testConditionalStatement_hashcode_equals_withConditionsAndStatements() {
        final ConditionalStatement testConditionalStatement = new ConditionalStatement();
        testConditionalStatement.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        testConditionalStatement.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        final ConditionalStatement testConditionalStatement2 = new ConditionalStatement();
        testConditionalStatement2.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        testConditionalStatement2.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        assertEquals(testConditionalStatement2.hashCode(), testConditionalStatement.hashCode());
    }

    @Test
    public void testConditionalStatement_hashcode_notEquals_changedConditions() {
        final ConditionalStatement testConditionalStatement = new ConditionalStatement();
        testConditionalStatement.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        final ConditionalStatement testConditionalStatement2 = new ConditionalStatement();
        testConditionalStatement2.addConditionAndStatementBlock(null, new StatementBlock());
        assertNotEquals(testConditionalStatement2.hashCode(), testConditionalStatement.hashCode());
    }

    @Test
    public void testConditionalStatement_hashcode_notEquals_changedStatements() {
        final ConditionalStatement testConditionalStatement = new ConditionalStatement();
        testConditionalStatement.addConditionAndStatementBlock(new NullLiteral(), null);
        final ConditionalStatement testConditionalStatement2 = new ConditionalStatement();
        testConditionalStatement2.addConditionAndStatementBlock(new NullLiteral(), new StatementBlock());
        assertNotEquals(testConditionalStatement2.hashCode(), testConditionalStatement.hashCode());
    }
}
