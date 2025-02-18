package main.java.com.acacia.ast.expressions;

import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;

/**
 * An operation with two sides seperated by an operator.
 * The sides can be any expression.
 */
public class BinaryExpression implements IExpression {
    private IExpression left;
    private IExpression right;
    private String operator;

    public BinaryExpression(IExpression left, IExpression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public NodeType getType() {
        return NodeType.BINARY_EXPRESSION;
    }

    public IExpression getLeft() {
        return this.left;
    }

    public IExpression getRight() {
        return this.right;
    }

    public String getOperator() {
        return this.operator;
    }

    @Override
    public String toString() {
        final String left = this.left == null ? "null" : this.left.toString();
        final String operator = this.operator == null ? "null" : this.operator;
        final String right = this.right == null ? "null" : this.right.toString();
        return "Type: " + getType() + ", [Left: " + left + " " + operator + " " + right + "],"; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        BinaryExpression other = (BinaryExpression) o;

        if (!(this.left == null ? other.left == null : this.left.equals(other.left))) return false;
        if (!(this.right == null ? other.right == null : this.right.equals(other.right))) return false;
        return this.operator == null ? other.operator == null : this.operator.equals(other.operator);
    }

    @Override
    public int hashCode() {
        int result = this.left == null ? 0 : this.left.hashCode();
        result = 31 * result + (this.right == null ? 0 : this.right.hashCode());
        return 31 * result + (this.operator == null ? 0 : this.operator.hashCode());
    }
}
