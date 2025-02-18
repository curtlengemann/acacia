package main.java.com.acacia.runtime.values;

/**
 * Runtime value that holds a collection of characters.
 */
public class StringValue implements IRuntimeValue {
    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public ValueType getRuntimeType() {
        return ValueType.STRING;
    }

    @Override
    public String getStringValue() {
       return this.value == null ? "null" : this.value.toString();
    }

    public String getValue() {
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

        StringValue other = (StringValue) o;

        return this.value == null ? other.value == null : this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return this.value == null ? 0 : this.value.hashCode();
    }
}
