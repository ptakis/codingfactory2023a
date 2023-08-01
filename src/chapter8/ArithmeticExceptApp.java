package chapter8;

import java.util.Scanner;

public class ArithmeticExceptApp {

    public static void main(String[] args) {
        int num1 = 5;
        int num2 = 0;
        int result;
        Scanner in = new Scanner(System.in);

        System.out.println("Please insert 2 ints");
        num1 = in.nextInt();
        num2 = in.nextInt();

        // edw exei noima
        try {
            result = num1 / num2;
        } catch (ArithmeticException e) {
            System.out.println("Please ...");
        }

        // enallaktika
        if (num2 == 0) {
            System.out.println("Please...");
            System.exit(1);
        }

        result = num1 / num2;

        /* Δεν κάνω try ... catch σε αυτές τις περιπτώσεις
        try
        {
            result = num1 / num2;
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        */
    }
}
