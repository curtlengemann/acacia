package main.java.com.acacia.ast.expressions;

import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;

/**
 * The opposite of a logical expression.
 */
public class NegationExpression implements IExpression {
    private IExpression value;

    public NegationExpression(IExpression value) {
        this.value = value;
    }

    @Override
    public NodeType getType() {
        return NodeType.NEGATION_EXPRESSION;
    }

    public IExpression getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        final String value = this.value == null ? "null" : this.value.toString();
        return "Type: " + getType() + ", [Value: " + value + "]"; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        NegationExpression other = (NegationExpression) o;

        return this.value == null ? other.value == null : this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return this.value == null ? 0 : this.value.hashCode();
    }
}
