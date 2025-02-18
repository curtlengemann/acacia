package main.java.com.acacia.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * The defining of a function.
 */
public class FunctionDeclaration implements IStatement {
    private List<String> parameters;
    private String name;
    private StatementBlock body;

    public FunctionDeclaration(String name) {
        this.name = name;
        this.body = new StatementBlock();
        this.parameters = new ArrayList<String>();
    }

    public void addStatementToBody(IStatement statement) {
        body.add(statement);
    }

    public void addParameter(String parameter) {
        parameters.add(parameter);
    }

    public String getName() {
        return this.name;
    }

    public List<String> getParameters() {
        return this.parameters;
    }

    public StatementBlock getBody() {
        return this.body;
    }

    @Override
    public NodeType getType() {
        return NodeType.FUNCTION_DECLARATION;
    }

    @Override
    public String toString() {
        final String name = this.name == null ? "null" : this.name;
        return "Type: " + getType() + ", [name: " + name + ", params: " + this.parameters.toString() + ", body: " + this.body.toString() + ",],"; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        FunctionDeclaration other = (FunctionDeclaration) o;

        if (!this.parameters.equals(other.parameters)) return false;
        if (!this.body.equals(other.body)) return false;
        return this.name == null ? other.name == null : this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        int result = this.name == null ? 0 : this.name.hashCode();
        result = 31 * result + this.parameters.hashCode();
        return 31 * result + this.body.hashCode();
    }
}
