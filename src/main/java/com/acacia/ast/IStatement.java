package main.java.com.acacia.ast;

/**
 * A node that doesn't result in a value at runtime.
 * 
 * This node will contain one or more expressions internally.
 */
public abstract interface IStatement {
    /**
     * Returns the type of this node.
     * 
     * @return The type of this node
     */
    public NodeType getType();
}
