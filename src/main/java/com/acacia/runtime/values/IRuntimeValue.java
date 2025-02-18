package main.java.com.acacia.runtime.values;

/**
 * A value that is determined or calculated during the execution of a program.
 */
public interface IRuntimeValue {
    /**
     * Returns the type of runtime value this is.
     * 
     * @return The runtime value type
     */
    public ValueType getRuntimeType();

    /**
     * Returns a string value representation of this runtime value.
     * 
     * @return A string representing the runtime value
     */
    public String getStringValue();
}
