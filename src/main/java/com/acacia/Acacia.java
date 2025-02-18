package main.java.com.acacia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.Scanner;

import main.java.com.acacia.ast.Program;
import main.java.com.acacia.frontend.ILexer;
import main.java.com.acacia.frontend.IParser;
import main.java.com.acacia.frontend.lexer.Lexer;
import main.java.com.acacia.frontend.parser.Parser;
import main.java.com.acacia.frontend.token.Token;
import main.java.com.acacia.runtime.Environment;
import main.java.com.acacia.runtime.IEnvironment;
import main.java.com.acacia.runtime.IInterpreter;
import main.java.com.acacia.runtime.Interpreter;

/**
 * The acacia runtime environment. This class takes care of reading in commands and doing the appropriate actions.
 */
public class Acacia {

    /**
     * Starts the runtime environment through initalizing a lexer, parser and interpreter and running a continuous loop until exit.
     */
    public void startRuntimeEnvironment() {
        Environment env;
        try {
            env = new Environment(null);
        } catch (Exception e) {
            System.err.println("Cannot initalize runtime environment");
            return;
        }
        
        run(new Lexer(), new Parser(), new Interpreter(), env);
    }

    private static void run(ILexer lexer, IParser parser, IInterpreter interpreter, IEnvironment environment) {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("\nAcacia Runtime Environment v1.0: ");
        System.out.print("\n> ");

        String nextLine;
        while (!(nextLine = scanner.nextLine()).equals("exit")) {
            final boolean didProcess = processCommand(nextLine, lexer, parser, interpreter, environment);
            if (didProcess) {
                System.out.print("> ");
                continue;
            }
            
            try {
                final Queue<Token> tokens = lexer.tokenize(nextLine);
                final Program program = parser.produceAST(tokens);
                interpreter.evaluate(program, environment);
                System.out.print("> ");
            } catch (Exception e) {
                System.err.print("ERROR: Cannot process line : " + nextLine + "\n" + e.getMessage());
                System.out.print("\n> ");
            }
            
        }
        scanner.close();
    }

    private static boolean processCommand(String nextLine, ILexer lexer, IParser parser, IInterpreter interpreter, IEnvironment environment) {
        final String[] splitLine = nextLine.split(" ");
        if (splitLine.length <= 0) {
            // Empty line
            return false;
        }
        
        if (!splitLine[0].equals("acacia") || splitLine.length == 1) {
            // All acacia commands start with acacia and are more than 1 word.
            return false;
        }

        if (splitLine[1].equals("help")) {
            return printHelp();
        }

        if (splitLine.length == 3 && splitLine[1].equals("run")) {
            // Process acacia code.
            return processFileCommand(splitLine[2], lexer, parser, interpreter, environment);
        }
        
        // No command found
        return false;
    }

    private static boolean printHelp() {
        System.out.println("Type 'acacia run <path to file>' to run an acacia program. (Uses the same enviroment every run)");
        System.out.println("Type valid acacia code to execute on the fly.");
        System.out.println("Type 'exit' to quit.");
        return true;
    }

    private static boolean processFileCommand(String filePath, ILexer lexer, IParser parser, IInterpreter interpreter, IEnvironment environment) {
        try {
            final File file = new File(filePath);
            final Scanner scanner = new Scanner(file).useDelimiter("\\Z");
            final Queue<Token> tokens = lexer.tokenize(scanner.next());
            final Program program = parser.produceAST(tokens);
            interpreter.evaluate(program, environment);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Invalid file path. Acacia Runtime cannot find file: " + filePath);
        } catch (Exception e) {
            System.err.println("ERROR: Exception while running acacia program: " + e.getMessage());
        }
        return true;
    }
}
