package main.java.com.acacia.ast.literals;

import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;

/**
 * Represents a key and value pair.
 * 
 * The value may be any complex expression.
 */
public class Property implements IExpression {
    private String key;
    private IExpression value;

    public Property(String key, IExpression value) {
        this.key = key;
        this.value = value;
    }

    public Property(String key) {
        this.key = key;
    }

    @Override
    public NodeType getType() {
        return NodeType.PROPERTY;
    }

    public String getKey() {
        return this.key;
    }

    public IExpression getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        final String key = this.key == null ? "null" : this.key;
        final String value = this.value == null ? "null" : this.value.toString();
        return "Type: " + getType() + ", Key: " + key + ", Value: " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Property other = (Property) o;

        if (!(this.key == null ? other.key == null : this.key.equals(other.key))) return false;
        return this.value == null ? other.value == null : this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        int result = this.key == null ? 0 : this.key.hashCode();
        return 31 * result + (this.value == null ? 0 : this.value.hashCode());
    }
}
