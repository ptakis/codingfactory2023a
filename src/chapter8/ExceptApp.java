package chapter8;

import java.io.IOException;

/**
 * Demonstrates the usage of Exceptions
 */
public class ExceptApp {

    public static void main(String[] args) {

        try {
            int ch = System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int ch2;

        try {
            ch2 = getNextChar();
            System.out.println(ch2);
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Error in I/O");
        }
    }

    public static int getNextChar() throws IOException {
        int ch = ' ';

        try {
            ch = System.in.read();

        } catch (IOException e) {
            // 1. Rollback
            // 2. Logging
            // 3. Rethrow
            e.printStackTrace();
            throw e;
        }
        return ch;
    }

}
