package main.java.com.acacia.ast.expressions;

import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;

/**
 * The accessing of a property from an object.
 * 
 * The object and property may be complex expressions.
 */
public class MemberExpression implements IExpression {
    private IExpression object;
    private IExpression property;
    private boolean computed;

    public MemberExpression(IExpression object, IExpression property, boolean computed) {
        this.object = object;
        this.property = property;
        this.computed = computed;
    }
    
    @Override
    public NodeType getType() {
        return NodeType.MEMBER_EXPRESSION;
    }

    public IExpression getObject() {
        return this.object;
    }

    public IExpression getProperty() {
        return this.property;
    }

    public boolean isComputed() {
        return this.computed;
    }

    @Override
    public String toString() {
        final String object = this.object == null ? "null" : this.object.toString();
        final String property = this.property == null ? "null" : this.property.toString();
        return "Type: " + getType() + ", [Object: " + object + ", Property: " + property + ", Computed: " + computed + "]"; 
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        MemberExpression other = (MemberExpression) o;

        if (!(this.object == null ? other.object == null : this.object.equals(other.object))) return false;
        if (!(this.property == null ? other.property == null : this.property.equals(other.property))) return false;
        return this.computed == other.computed;
    }

    @Override
    public int hashCode() {
        int result = this.object == null ? 0 : this.object.hashCode();
        result = 31 * result + (this.property == null ? 0 : this.property.hashCode());
        return 31 * result + (this.computed ? 0 : 1);
    }
}
