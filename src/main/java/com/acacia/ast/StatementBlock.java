package main.java.com.acacia.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of statements.
 */
public class StatementBlock implements IStatement {
    private List<IStatement> statements;

    public StatementBlock() {
        this.statements = new ArrayList<IStatement>();
    }

    public void add(IStatement statement) {
        this.statements.add(statement);
    }
    
    public List<IStatement> getStatements() {
        return this.statements;
    }

    @Override
    public NodeType getType() {
        return NodeType.STATEMENT_BLOCK;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{" + getType() + ": \n");
        sb.append("\t[Statements: \n");
        for (int i = 0; i < statements.size(); i++) {
            sb.append("\t\t{\n");
            sb.append("\t\t\t"+statements.get(i).toString());
            sb.append("\n\t\t},\n");
        }
        sb.append("\n\t], \n},");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        StatementBlock other = (StatementBlock) o;

        return this.statements.equals(other.statements);
    }

    @Override
    public int hashCode() {
        return this.statements.hashCode();
    }
}
