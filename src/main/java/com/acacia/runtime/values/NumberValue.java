package main.java.com.acacia.runtime.values;

/**
 * Runtime value that contains a floating point representation of a number.
 */
public class NumberValue implements IRuntimeValue {
    private Float value;

    public NumberValue(Float value) {
        this.value = value;
    }
    @Override
    public ValueType getRuntimeType() {
        return ValueType.NUMBER;
    }

    @Override
    public String getStringValue() {
        if (this.value == null) return "null";
        if (this.value % 1 == 0) {
            return Integer.toString(this.value.intValue());
        }
        return this.value.toString();
    }

    public Float getValue() {
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

        NumberValue other = (NumberValue) o;

        return this.value == null ? other.value == null : this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return this.value == null ? 0 : this.value.hashCode();
    }
}
