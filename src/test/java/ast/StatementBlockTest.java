package test.java.ast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import main.java.com.acacia.ast.StatementBlock;
import main.java.com.acacia.ast.IStatement;
import main.java.com.acacia.ast.NodeType;
import main.java.com.acacia.ast.literals.Identifier;
import main.java.com.acacia.ast.literals.NullLiteral;

public class StatementBlockTest {
    
    @Test
    public void testStatementBlock_getType() {
        final StatementBlock testStatementBlock = new StatementBlock();
        assertEquals(NodeType.STATEMENT_BLOCK, testStatementBlock.getType());
    }

    @Test
    public void testStatementBlock_addAndGetStatements() {
        final StatementBlock testStatementBlock = new StatementBlock();
        testStatementBlock.add(new NullLiteral());
        testStatementBlock.add(new Identifier("asdf"));
        
        final List<IStatement> statements = testStatementBlock.getStatements();
        assertEquals(2, statements.size());
        assertEquals(new NullLiteral(), statements.get(0));
        assertEquals(new Identifier("asdf"), statements.get(1));
    }

    @Test
    public void testStatementBlock_equals_self() {
        final StatementBlock testStatementBlock = new StatementBlock();
        assertTrue(testStatementBlock.equals(testStatementBlock));
    }

    @Test
    public void testStatementBlock_equals_symmetrical() {
        final StatementBlock testStatementBlock = new StatementBlock();
        assertTrue(new StatementBlock().equals(testStatementBlock));
        assertTrue(testStatementBlock.equals(new StatementBlock()));
    }

    @Test
    public void testStatementBlock_equals_withStatements() {
        final StatementBlock testStatementBlock = new StatementBlock();
        testStatementBlock.add(new NullLiteral());
        testStatementBlock.add(new Identifier("asdf"));
        final StatementBlock testStatementBlock2 = new StatementBlock();
        testStatementBlock2.add(new NullLiteral());
        testStatementBlock2.add(new Identifier("asdf"));
        assertTrue(testStatementBlock.equals(testStatementBlock2));
    }

    @Test
    public void testStatementBlock_notEquals_null() {
        final StatementBlock testStatementBlock = new StatementBlock();
        assertFalse(testStatementBlock.equals(null));
    }

    @Test
    public void testStatementBlock_notEquals_changedStatements() {
        final StatementBlock testStatementBlock = new StatementBlock();
        testStatementBlock.add(new NullLiteral());
        testStatementBlock.add(new Identifier("asdf"));
        assertFalse(testStatementBlock.equals(new StatementBlock()));
    }

    @Test
    public void testStatementBlock_hashcode_equals_self() {
        final StatementBlock testStatementBlock = new StatementBlock();
        assertEquals(testStatementBlock.hashCode(), testStatementBlock.hashCode());
    }

    @Test
    public void testStatementBlock_hashcode_equals_withStatements() {
        final StatementBlock testStatementBlock = new StatementBlock();
        testStatementBlock.add(new NullLiteral());
        testStatementBlock.add(new Identifier("asdf"));
        final StatementBlock testStatementBlock2 = new StatementBlock();
        testStatementBlock2.add(new NullLiteral());
        testStatementBlock2.add(new Identifier("asdf"));
        assertEquals(testStatementBlock2.hashCode(), testStatementBlock.hashCode());
    }

    @Test
    public void testStatementBlock_hashcode_notEquals_changedStatements() {
        final StatementBlock testStatementBlock = new StatementBlock();
        testStatementBlock.add(new NullLiteral());
        testStatementBlock.add(new Identifier("asdf"));
        assertNotEquals(new StatementBlock().hashCode(), testStatementBlock.hashCode());
    }
}
