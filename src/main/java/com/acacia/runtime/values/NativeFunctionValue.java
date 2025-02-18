package main.java.com.acacia.runtime.values;

import java.util.List;
import java.util.function.Function;

/**
 * Runtime value that denotes a Java defined function.
 */
public class NativeFunctionValue implements IRuntimeValue {
    private Function<List<IRuntimeValue>, IRuntimeValue> nativeFunction;

    public NativeFunctionValue(Function<List<IRuntimeValue>, IRuntimeValue> nativeFunction) {
        this.nativeFunction = nativeFunction;
    }

    public IRuntimeValue evaluate(List<IRuntimeValue> args) {
        return nativeFunction == null ? new NullValue() : nativeFunction.apply(args);
    }

    @Override
    public ValueType getRuntimeType() {
        return ValueType.NATIVE_FUNCTION;
    }

    @Override
    public String getStringValue() {
        return "NATIVE_FUNCTION";
    }
    
    @Override
    public String toString() {
        return "Runtime Type: " + getRuntimeType() + ", Value: " + getStringValue(); 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        NativeFunctionValue other = (NativeFunctionValue) o;

        return this.nativeFunction == null ? other.nativeFunction == null : this.nativeFunction.equals(other.nativeFunction);
    }

    @Override
    public int hashCode() {
        return this.nativeFunction == null ? 0 : this.nativeFunction.hashCode();
    }
}
