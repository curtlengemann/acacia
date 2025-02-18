package main.java.com.acacia.ast.literals;

import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;

/**
 * Represents the absence of a value.
 */
public class NullLiteral implements IExpression {

    @Override
    public NodeType getType() {
        return NodeType.NULL_LITERAL;
    }

    @Override
    public String toString() {
        return "Type: " + getType() + ", Value: null"; 
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof NullLiteral;
    }

    @Override
    public int hashCode() {
        return NullLiteral.class.hashCode();
    }
}
