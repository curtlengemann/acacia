package main.java.com.acacia.ast.literals;

import java.util.ArrayList;
import java.util.List;

import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;

/**
 * Represents a complex list of properties.
 */
public class ObjectLiteral implements IExpression {
    private List<Property> properties;
    private boolean isEnum;

    public ObjectLiteral(boolean isEnum) {
        this.isEnum = isEnum;
        this.properties = new ArrayList<Property>();
    }

    @Override
    public NodeType getType() {
        return NodeType.OBJECT_LITERAL;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public boolean isEnum() {
        return this.isEnum;
    }

    @Override
    public String toString() {
        return "Type: " + getType() + ", isEnum: " + this.isEnum + ", Properties: " + this.properties.toString(); 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        ObjectLiteral other = (ObjectLiteral) o;

        if (this.isEnum != other.isEnum) return false;
        return this.properties.equals(other.properties);
    }

    @Override
    public int hashCode() {
        int result = this.isEnum ? 1 : 0;
        return result * 31 + this.properties.hashCode();
    }
}
