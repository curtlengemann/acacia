package main.java.com.acacia.ast.expressions;

import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;

/**
 * The assignment of one expression to another one.
 * Both expressions may be complex.
 */
public class AssignmentExpression implements IExpression {
    private IExpression assignee;
    private IExpression value;

    public AssignmentExpression(IExpression assignee, IExpression value) {
        this.assignee = assignee;
        this.value = value;
    }

    @Override
    public NodeType getType() {
        return NodeType.ASSIGNMENT_EXPRESSION;
    }

    public IExpression getAssignee() {
        return this.assignee;
    }

    public IExpression getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        final String assignee = this.assignee == null ? "null" : this.assignee.toString();
        final String value = this.value == null ? "null" : this.value.toString();
        return "Type: " + getType() + ", [Assignee: " + assignee + " Value: " + value + "]"; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        AssignmentExpression other = (AssignmentExpression) o;

        if (!(this.assignee == null ? other.assignee == null : this.assignee.equals(other.assignee))) return false;
        return this.value == null ? other.value == null : this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        int result = this.assignee == null ? 0 : this.assignee.hashCode();
        return 31 * result + (this.value == null ? 0 : this.value.hashCode());
    }
}
