package main.java.com.acacia.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of multiple expression and statement block parings.
 */
public class ConditionalStatement implements IStatement {
    private List<IExpression> conditions;
    private List<StatementBlock> statementBlocks;

    public ConditionalStatement() {
        conditions = new ArrayList<IExpression>();
        statementBlocks = new ArrayList<StatementBlock>();
    }

    @Override
    public NodeType getType() {
        return NodeType.CONDITONAL;
    }

    public void addConditionAndStatementBlock(IExpression c, StatementBlock statementBlock) {
        conditions.add(c);
        statementBlocks.add(statementBlock);
    }

    public List<IExpression> getConditions() {
        return this.conditions;
    }

    public List<StatementBlock> getStatements() {
        return this.statementBlocks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{" + getType() + ": \n");
        for (int i = 0; i < conditions.size(); i++) {
            sb.append("{\n");
            sb.append("Condition: " + conditions.get(i) + ",\n");
            sb.append(statementBlocks.get(i).toString());
            sb.append("\n},\n");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        ConditionalStatement other = (ConditionalStatement) o;

        if (!this.conditions.equals(other.conditions)) return false;
        return this.statementBlocks.equals(other.statementBlocks);
    }

    @Override
    public int hashCode() {
        int result = this.conditions.hashCode();
        return 31 * result + this.statementBlocks.hashCode();
    }
}
