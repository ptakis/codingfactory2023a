package chapter9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Prints with PrintStream.
 */
public class PrintStreamApp {
    public static void main(String[] args) {
        try (PrintStream ps = new PrintStream("C:/tmp/f2023.txt")) {
            ps.println("Hello Coding Factory");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (PrintStream ps = new PrintStream("C:/tmp/f2023_1.txt", StandardCharsets.UTF_8)) {
            ps.println("Hello Coding Factory (UTF-8) -> άρα μπορεί να γράφει εκτός των άλλων και ελληνικά");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}