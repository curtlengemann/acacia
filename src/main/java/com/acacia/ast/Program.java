package main.java.com.acacia.ast;

/**
 * A block which contains zero or more statements.
 * 
 * Only one program is supported per file.
 */
public class Program implements IStatement {
    private StatementBlock statements;
    
    public Program() {
        statements = new StatementBlock();
    }

    @Override
    public NodeType getType() {
        return NodeType.PROGRAM;
    }

    public void addStatement(IStatement stmt) {
        statements.add(stmt);
    }

    public StatementBlock getStatementBlock() {
        return statements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{" + getType() + ": \n");
        sb.append(statements.toString());
        sb.append("\n}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Program other = (Program) o;

        return this.statements.equals(other.statements);
    }
    
    @Override
    public int hashCode() {
        return this.statements.hashCode();
    }
}
