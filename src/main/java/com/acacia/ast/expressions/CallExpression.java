package main.java.com.acacia.ast.expressions;

import java.util.Iterator;
import java.util.List;

import main.java.com.acacia.ast.IExpression;
import main.java.com.acacia.ast.NodeType;

/**
 * The invocation of a function.
 * The function may be called from a complex expression.
 */
public class CallExpression implements IExpression {
    private IExpression caller;
    private List<IExpression> arguments;

    public CallExpression(IExpression caller, List<IExpression> arguments) {
        this.caller = caller;
        this.arguments = arguments;
    }

    public IExpression getCaller() {
        return this.caller;
    }

    public Iterator<IExpression> getArgumentsIterator() {
        return this.arguments == null ? null : this.arguments.iterator();
    }
    
    @Override
    public NodeType getType() {
        return NodeType.CALL_EXPRESSION;
    }

    @Override
    public String toString() {
        final String caller = this.caller == null ? "null" : this.caller.toString();
        final String arguments = this.arguments == null ? "null" : this.arguments.toString();
        return "Type: " + getType() + ", [Caller: " + caller + ", Arguments: " + arguments + "]"; 
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        CallExpression other = (CallExpression) o;

        if (!(this.caller == null ? other.caller == null : this.caller.equals(other.caller))) return false;
        return this.arguments == null ? other.arguments == null : this.arguments.equals(other.arguments);
    }

    @Override
    public int hashCode() {
        int result = this.caller == null ? 0 : this.caller.hashCode();
        return 31 * result + (this.arguments == null ? 0 : this.arguments.hashCode());
    }
}
