package main.java.com.acacia.ast;

/**
 * The defining of a variable.
 */
public class VariableDeclaration implements IStatement {
    private boolean constant;
    private String identifier;
    private IExpression expression;

    public VariableDeclaration(boolean constant, String identifier) {
        this.constant = constant;
        this.identifier = identifier;
        this.expression = null;
    }

    public VariableDeclaration(boolean constant, String identifier, IExpression expression) {
        this.constant = constant;
        this.identifier = identifier;
        this.expression = expression;
    }

    public boolean isConstant() {
        return this.constant;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public boolean hasExpression() {
        return this.expression != null;
    }

    public IExpression getExpression() {
        return this.expression;
    }

    @Override
    public NodeType getType() {
        return NodeType.VAR_DECLARATION;
    }

    @Override
    public String toString() {
        final String identifier = this.identifier == null ? "null" : this.identifier;
        final String expression = this.expression == null ? "null" : this.expression.toString();
        return "Type: " + getType() + ", [const: " + this.constant + ", identifier: " + identifier + ", expression: [" + expression + "],],"; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        VariableDeclaration other = (VariableDeclaration) o;

        if (this.constant != other.constant) return false;
        if (!(this.identifier == null ? other.identifier == null : this.identifier.equals(other.identifier))) return false;
        return this.expression == null ? other.expression == null : this.expression.equals(other.expression);
    }

    @Override
    public int hashCode() {
        int result = this.constant ? 1 : 0;
        result = 31 * result + this.identifier.hashCode();
        return 31 * result + (this.expression == null ? 0 : this.expression.hashCode());
    }
}