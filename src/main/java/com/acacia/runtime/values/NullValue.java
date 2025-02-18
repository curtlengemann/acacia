package main.java.com.acacia.runtime.values;

/**
 * Runtime value that denotes the absence of a value.
 */
public class NullValue implements IRuntimeValue {

    @Override
    public ValueType getRuntimeType() {
        return ValueType.NULL;
    }

    @Override
    public String getStringValue() {
        return "null";
    }

    @Override
    public String toString() {
        return "Runtime Type: " + getRuntimeType() + ", Value: " + getStringValue(); 
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof NullValue;
    }

    @Override
    public int hashCode() {
        return NullValue.class.hashCode();
    }
}
