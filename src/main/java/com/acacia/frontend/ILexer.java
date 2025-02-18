package main.java.com.acacia.frontend;

import java.util.Queue;

import main.java.com.acacia.frontend.token.Token;

/**
 * Produces tokens by splitting up the source code.
 */
public interface ILexer {
    /**
     * Iterates through all space delineated words in the source code and 
     * creates tokens from them. Tokens are created based off the content
     * in the word.
     * 
     * @param sourceCode The user supplied acacia code
     * @return A list of lexed tokens
     * @throws Exception If unexpected words appear in the source code
     */
    public Queue<Token> tokenize(String sourceCode) throws Exception;
}
