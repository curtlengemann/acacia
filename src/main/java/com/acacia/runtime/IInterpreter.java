package main.java.com.acacia.runtime;

import main.java.com.acacia.ast.IStatement;
import main.java.com.acacia.runtime.values.IRuntimeValue;

/**
 * Representation of an interpreter for the acacia language. Instructions can be run without compilation.
 */
public interface IInterpreter {
    /**
     * Evaluates a single statement and returns the runtime value after interpretation. The environment is updated accordingly.
     * 
     * @param statement The statement to evaluate
     * @param environment The current environment the statement resides in
     * @return A runtime value representation of the statement after evaluation
     * @throws Exception For an invalid statement or an unrecognized statement
     */
    public IRuntimeValue evaluate(IStatement statement, IEnvironment environment) throws Exception;
}
