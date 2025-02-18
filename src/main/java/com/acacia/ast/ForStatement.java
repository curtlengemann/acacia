package main.java.com.acacia.ast;

/**
 * The defining of a for loop.
 */
public class ForStatement implements IStatement{
    private IExpression condition;
    private StatementBlock statementBlock;


    public ForStatement(IExpression condition, StatementBlock statementBlock) {
        this.condition = condition;
        this.statementBlock = statementBlock;
    }

    @Override
    public NodeType getType() {
        return NodeType.FOR;
    }

    public IExpression getCondition() {
        return this.condition;
    }

    public StatementBlock getStatementBlock() {
        return this.statementBlock;
    }

    @Override
    public String toString() {
        final String conditionString = this.condition == null ? "null" : this.condition.toString();
        final String statementString = this.statementBlock == null ? "null" : this.statementBlock.toString();
        return "Type: " + getType() + ", [condition: " + conditionString + ", statements: " + statementString + "],"; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        ForStatement other = (ForStatement) o;

        if (!(this.condition == null ? other.condition == null : this.condition.equals(other.condition))) return false;
        return this.statementBlock == null ? other.statementBlock == null : this.statementBlock.equals(other.statementBlock);
    }

    @Override
    public int hashCode() {
        int result = this.condition == null ? 0 : this.condition.hashCode();
        return 31 * result + (this.statementBlock == null ? 0 : this.statementBlock.hashCode());
    }
}
