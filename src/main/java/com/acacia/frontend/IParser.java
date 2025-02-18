package main.java.com.acacia.frontend;

import java.util.Queue;

import main.java.com.acacia.ast.Program;
import main.java.com.acacia.frontend.token.Token;

/**
 * Produces a valid AST from a list of token data structures.
 */
public interface IParser {
    /**
     * Returns a program that can be interpreted by generating an AST
     * from a list of token data structures.
     * 
     * @param tokens An ordered list of lexed input
     * @return A valid AST in the form of a program
     * @throws Exception If the list of tokens cannot be properly parsed
     */
    public Program produceAST(Queue<Token> tokens) throws Exception;
}
