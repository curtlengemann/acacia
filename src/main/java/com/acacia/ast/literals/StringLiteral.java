package main.java.com.acacia.ast.literals;

import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;

/**
 * Represents a combination of characters.
 */
public class StringLiteral implements IExpression {
    private String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    @Override
    public NodeType getType() {
       return NodeType.STRING_LITERAL;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        final String value = this.value == null ? "null" : this.value;
        return "Type: " + getType() + ", Value: " + value; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        StringLiteral other = (StringLiteral) o;

        return this.value == null ? other.value == null : this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return this.value == null ? 0 : this.value.hashCode();
    }
}
