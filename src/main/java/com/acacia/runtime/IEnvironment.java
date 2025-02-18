package main.java.com.acacia.runtime;

import main.java.com.acacia.runtime.values.IRuntimeValue;

/**
 * Representation of a set of variables and constants within a given hierarchy.
 */
public interface IEnvironment {
    /**
     * Returns if a given variable is a constant variable in this environment.
     * 
     * @param variableName The name of the variable to check
     * @return true if the variable is a constant, false otherwise
     */
    public boolean hasConstant(String variableName);

    /**
     * Adds or overrides a variable with the given variable name in this environment.
     * The value is associated with this variable name.
     * 
     * @param variableName The name of the variable to be added or overridden
     * @param value The value to associate with the given variable name
     */
    public void putVariable(String variableName, IRuntimeValue value);

    /**
     * Returns the stored runtime value for the given variable in this environment.
     * 
     * @param variableName The name of the variable to get the value for
     * @return The runtime value associated with the variable name or null if the variable name doesn't exist
     */
    public IRuntimeValue getVariable(String variableName);

    /**
     * Initializes a new variable with the given variable name and value.
     * 
     * @param variableName The name of the variable to create
     * @param value The initial value of the new variable
     * @param constant If this variable's value can be changed
     * @return The initial value of the new variable
     * @throws Exception If the variable has been declared before
     */
    public IRuntimeValue declareVar(String variableName, IRuntimeValue value, boolean constant) throws Exception;

    /**
     * Assigns a new value to a variable with the given variable name.
     * 
     * If the variable name cannot be found in this environment, parent environments will be searched and updated. The
     * first environment to contain the variable name will get the new value.
     * 
     * If this environment and no parent environment has a variable with the given variable name, a new variable will be
     * initialized in this environment with the variable name and value.
     * 
     * @param variableName The name of the variable to assign the value to
     * @param value The value to assign
     * @return The assigned value
     * @throws Exception If the variable is a constant and cannot be reassigned
     */
    public IRuntimeValue assignVar(String variableName, IRuntimeValue value) throws Exception;

    /**
     * Gets the stored runtime value for a variable with the given variable name using the environment hierarchy.
     * 
     * This environment is checked first. If not found, then its parent is checked.
     * 
     * @param variableName The name of the variable to get the value for
     * @return The runtime value associated with that variable name
     * @throws Exception If the variable name does not exist in this or any parent environment
     */
    public IRuntimeValue lookupVar(String variableName) throws Exception;

    /**
     * Returns the environment that contains the specified variable name or null if it doesn't exist using the
     * environment hierarchy.
     * 
     * @param variableName The name of the variable to get the environment for
     * @return The environment containing the variable name or null if it doesn't exist
     */
    public IEnvironment resolveEnvironment(String variableName);
}
