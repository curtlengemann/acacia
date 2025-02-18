package main.java.com.acacia.ast.literals;

import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;

/**
 * Represents a numeric constant.
 */
public class NumericLiteral implements IExpression {
    private Float number;

    public NumericLiteral(Float number) {
        this.number = number;
    }

    public Float getValue() {
        return this.number;
    }

    @Override
    public NodeType getType() {
        return NodeType.NUMERIC_LITERAL;
    }

    @Override
    public String toString() {
        final String number = this.number == null ? "null" : this.number.toString();
        return "Type: " + getType() + ", Value: " + number; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        NumericLiteral other = (NumericLiteral) o;

        return this.number == null ? other.number == null : this.number.equals(other.number);
    }

    @Override
    public int hashCode() {
        return this.number == null ? 0 : this.number.hashCode();
    }
}
