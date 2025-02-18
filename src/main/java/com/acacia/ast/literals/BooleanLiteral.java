package main.java.com.acacia.ast.literals;

import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;

/**
 * Represents a logical state.
 */
public class BooleanLiteral implements IExpression {
    private boolean value;

    public BooleanLiteral(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public NodeType getType() {
        return NodeType.BOOLEAN_LITERAL;
    }

    @Override
    public String toString() {
        return "Type: " + getType() + ", Value: " + value; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        BooleanLiteral other = (BooleanLiteral) o;

        return this.value == other.value;
    }

    @Override
    public int hashCode() {
        return this.value ? 1 : 0;
    }
}
