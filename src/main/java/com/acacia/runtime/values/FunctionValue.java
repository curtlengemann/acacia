package main.java.com.acacia.runtime.values;

import java.util.List;

import main.java.com.acacia.ast.StatementBlock;
import main.java.com.acacia.runtime.IEnvironment;

/**
 * Runtime value that denotes a user defined function.
 */
public class FunctionValue implements IRuntimeValue {
    private List<String> parameters;
    private StatementBlock body;
    private IEnvironment declarationEnvironment;

    public FunctionValue(List<String> parameters, StatementBlock body, IEnvironment declarationEnvironment) {
        this.parameters = parameters;
        this.body = body;
        this.declarationEnvironment = declarationEnvironment;
    }

    public IEnvironment getDeclarationEnvironment() {
        return this.declarationEnvironment;
    }

    public List<String> getParameters() {
        return this.parameters;
    }

    public StatementBlock getBody() {
        return this.body;
    }

    @Override
    public ValueType getRuntimeType() {
        return ValueType.FUNCTION;
    }

    @Override
    public String getStringValue() {
        return "FUNCTION";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        FunctionValue other = (FunctionValue) o;

        if (!(this.body == null ? other.body == null : this.body.equals(other.body))) return false;
        if (!(this.parameters == null ? other.parameters == null : this.parameters.equals(other.parameters))) return false;
        return this.declarationEnvironment == null ? other.declarationEnvironment == null : this.declarationEnvironment.equals(other.declarationEnvironment);
    }

    @Override
    public int hashCode() {
        int result = this.body == null ? 0 : this.body.hashCode();
        result = 31 * result + (this.parameters == null ? 0 : this.parameters.hashCode());
        return this.declarationEnvironment == null ? result : 31 * result + this.declarationEnvironment.hashCode();
    }
}
