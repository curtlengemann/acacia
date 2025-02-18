package main.java.com.acacia.runtime.values;

import java.util.HashMap;

/**
 * Runtime value that contains keys mapped to other runtime values.
 */
public class ObjectValue implements IRuntimeValue {
    private HashMap<String, IRuntimeValue> properties;
    private boolean isEnum;

    public ObjectValue(boolean isEnum) {
        this.isEnum = isEnum;
        this.properties = new HashMap<String, IRuntimeValue>();
    }

    @Override
    public ValueType getRuntimeType() {
        return ValueType.OBJECT;
    }

    @Override
    public String getStringValue() {
        return isEnum ? "ENUM" : "OBJECT";
    }

    public IRuntimeValue addProperty(String key, IRuntimeValue value) {
        this.properties.put(key, value);
        return value;
    }

    public IRuntimeValue getProperty(String key) {
        return this.properties.get(key);
    }

    public boolean isEnum() {
        return this.isEnum;
    }

    @Override
    public String toString() {
        return "Runtime Type: " + getRuntimeType() + ", isEnum: " + this.isEnum + ", Value: " + this.properties.toString(); 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        ObjectValue other = (ObjectValue) o;

        if (this.isEnum != other.isEnum) return false;
        return this.properties.equals(other.properties);
    }

    @Override
    public int hashCode() {
        int result = this.isEnum ? 1 : 0;
        return result * 31 + this.properties.hashCode();
    }
}
