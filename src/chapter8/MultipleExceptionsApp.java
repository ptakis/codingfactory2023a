package chapter8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MultipleExceptionsApp {
    public static void main(String[] args) {
        File file = new File("C:\\tmp\\cf.txt");
        char ch;

        // exoyme 2 pithana Exception
        // 1.FileNotFindException (tyxainei na einai ypoklasi tou IOException)
        // 2.IOException
        try (Scanner in = new Scanner(file)) {
            ch = (char) System.in.read();
        } catch (IOException e) {
            // e.printStackTrace();
            // de mas dinei ti dinatotita na dinoyme kala minimata gia ta exception
            System.out.println("...");
        }

//        try (Scanner in = new Scanner(file)) {
//            ch = (char) System.in.read();
//        } catch (IOException e1) {
//            System.out.println("Read Error");
//        } catch (FileNotFoundException e2) {
//            System.out.println("File not found Error");
//        }

        // prepei na pame apo to eidiko pros to pio geniko! (Eidiko -> Geniko)
        try (Scanner in = new Scanner(file)) {
            ch = (char) System.in.read();
        } catch (FileNotFoundException e2) {
            System.out.println("File not found Error");
        } catch (IOException e1) {
            System.out.println("Read Error");
        } catch (Exception e3) {
            System.out.println("Error");
        }

    }
}
