package chapter8;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TryWithResourcesApp {
    public static void main(String[] args) {

        int num;

        try (Scanner in = new Scanner(System.in)) {
            num = in.nextInt();
            System.out.println(num);
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

        // Java JDK >= 9
        // in2 has to be final or exectively final
        Scanner in2 = new Scanner(System.in);

        try (in2) {
            num = in2.nextInt();
            System.out.println(num);
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

    }
}
