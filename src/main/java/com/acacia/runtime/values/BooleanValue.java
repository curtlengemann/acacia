package main.java.com.acacia.runtime.values;

/**
 * Runtime value that denotes a boolean logical state.
 */
public class BooleanValue implements IRuntimeValue {
    private boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public ValueType getRuntimeType() {
        return ValueType.BOOLEAN;
    }

    @Override
    public String getStringValue() {
        return this.value ? "true" : "false";
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Runtime Type: " + getRuntimeType() + ", Value: " + getStringValue(); 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        BooleanValue other = (BooleanValue) o;

        return this.value == other.value;
    }

    @Override
    public int hashCode() {
        return this.value ? 1 : 0;
    }
}
