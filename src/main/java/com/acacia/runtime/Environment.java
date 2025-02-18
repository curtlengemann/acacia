package main.java.com.acacia.runtime;

import java.util.HashMap;
import java.util.HashSet;

import main.java.com.acacia.runtime.values.IRuntimeValue;
import main.java.com.acacia.runtime.values.NativeFunctionValue;
import main.java.com.acacia.runtime.values.NullValue;
import main.java.com.acacia.runtime.values.NumberValue;
import main.java.com.acacia.runtime.values.StringValue;
import main.java.com.acacia.runtime.values.ValueType;

public class Environment implements IEnvironment {
    private IEnvironment parent;
    private HashMap<String, IRuntimeValue> variables;
    private HashSet<String> constants;

    public Environment(IEnvironment parent) throws Exception {
        this.parent = parent;
        this.variables = new HashMap<String, IRuntimeValue>();
        this.constants = new HashSet<String>();
        setupScope();
    }

    private void setupScope() throws Exception {
        declareVar("null", new NullValue(), true);
        declareVar("print", new NativeFunctionValue(args -> {
            StringBuilder sb = new StringBuilder();
            for (final IRuntimeValue arg : args) {
                sb.append(arg.getStringValue() + " ");
            }
            System.out.println(sb.toString());
            return new NullValue();
        }), true);
        declareVar("len", new NativeFunctionValue(args -> {
            if(args.size() != 1 || args.get(0).getRuntimeType() != ValueType.STRING) {
                System.err.println("len() must only be used on a single string parameter");
                return new NullValue();
            }

            final StringValue s = (StringValue) args.get(0);
            final String value = s.getValue();

            if (value == null) {
                System.err.println("value of string in len() must not be null");
                return new NullValue();
            }

            return new NumberValue(Float.valueOf(value.length()));
        }), true);
        declareVar("charAt", new NativeFunctionValue(args -> {
            if(args.size() != 2 || 
                args.get(0).getRuntimeType() != ValueType.STRING ||
                args.get(1).getRuntimeType() != ValueType.NUMBER) {
                System.err.println("charAt() must have a string parameter first and a number parameter second");
                return new NullValue();
            }

            final StringValue s = (StringValue) args.get(0);
            final String str = s.getValue();

            final NumberValue n = (NumberValue) args.get(1);
            final Float num = n.getValue();

            if (str == null || num == null) {
                System.err.println("values of params in charAt() must not be null");
                return new NullValue();
            }

            final int numInt = num.intValue();

            if (numInt > str.length() - 1) {
                System.err.println("index: " + numInt + " out of range for word: " + s.getStringValue());
                return new NullValue();
            }
            

            final char c = str.charAt(num.intValue());

            return new StringValue(Character.toString(c));
        }), true);
    }

    @Override
    public boolean hasConstant(String variableName) {
        return this.constants.contains(variableName);
    }

    @Override
    public void putVariable(String variableName, IRuntimeValue value) {
        this.variables.put(variableName, value);
    }

    @Override
    public IRuntimeValue getVariable(String variableName) {
        return this.variables.get(variableName);
    }

    @Override
    public IRuntimeValue declareVar(String variableName, IRuntimeValue value, boolean constant) throws Exception {
        if (this.variables.containsKey(variableName)) {
            throw new Exception("Cannot redeclare variable: " + variableName);
        }

        this.variables.put(variableName, value);
        if (constant) {
            this.constants.add(variableName);
        }

        return value;
    }

    @Override
    public IRuntimeValue assignVar(String variableName, IRuntimeValue value) throws Exception {
        final IEnvironment env = resolveEnvironment(variableName);
        if (env == null) {
            return declareVar(variableName, value, false);
        }

        if (env.hasConstant(variableName)) {
            throw new Exception("Cannot reassign constant variable: " + variableName);
        }
        
        env.putVariable(variableName, value);
        return value;
    }

    @Override
    public IRuntimeValue lookupVar(String variableName) throws Exception {
        final IEnvironment env = resolveEnvironment(variableName);
        if (env == null) {
            throw new Exception("Cannot resolve variable: " + variableName + ". It doesn't exist.");
        }
        return env.getVariable(variableName);
    }

    @Override
    public IEnvironment resolveEnvironment(String variableName) {
        if (this.variables.containsKey(variableName)) {
            return this;
        }

        if (this.parent == null) {
            return null;
        }

        return this.parent.resolveEnvironment(variableName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Environment other = (Environment) o;

        if (!this.constants.equals(other.constants)) return false;
        if (!this.variables.equals(other.variables)) return false;
        return this.parent == null ? other.parent == null : this.parent.equals(other.parent);
    }

    @Override
    public int hashCode() {
        int result = this.constants.hashCode();
        result = 31 * result + this.variables.hashCode();
        return this.parent == null ? result : 31 * result + this.parent.hashCode();
    }
}
