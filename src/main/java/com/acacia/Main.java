package main.java.com.acacia;

/**
 * A simple class used to kick off the acacia runtime environment.
 */
public class Main {
    public static void main(String[] args) {
        final Acacia acacia = new Acacia();
        acacia.startRuntimeEnvironment();
    }
}
