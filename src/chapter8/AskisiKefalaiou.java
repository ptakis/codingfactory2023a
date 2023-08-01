package chapter8;

import java.util.Scanner;

public class AskisiKefalaiou {
    public static void main(String[] args) {
        // try catch gia to IllegalArgumentException
        int choice = -1;

        do {
            showMenu();

            try {
                choice = getChoice();
                if (choice == -1) continue;
                printChoice(choice);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (choice != 5);

    }

    /**
     * This method just prints the MENU
     */
    public static void showMenu() {
        System.out.println("PLease insert your choice (1 to 5).");
        System.out.println("1. Choice 1");
        System.out.println("2. Choice 2");
        System.out.println("3. Choice 3");
        System.out.println("4. Choice 4");
        System.out.println("5. Exit");
    }

    /**
     * This method returns the choice of the user.
     * If choice is bad (not 1 to 5) then returns -1.
     * @return the choice of the user.
     */
    public static int getChoice() {
        Scanner in = new Scanner(System.in);
        int num = -1;

        if (in.hasNextInt()) {
            num = in.nextInt();
        } else {
            System.out.println("Bad choice. You have to give a number between 1 and 5.");
        }

        return num;
    }

    /**
     * This method prints the choice of the user.
     * @param choice : gets and int and prints the choice for the user selection.
     * @throws IllegalArgumentException : if choice is not between 1 and 5 throws IllegalArgumentException.
     */
    public static void printChoice(int choice) throws IllegalArgumentException{
        try {
            switch (choice) {
                case 1:
                    System.out.println("Choice 1 selected.");
                    break;
                case 2:
                    System.out.println("Choice 2 selected.");
                    break;
                case 3:
                    System.out.println("Choice 3 selected.");
                    break;
                case 4:
                    System.out.println("Choice 4 selected.");
                    break;
                case 5:
                    System.out.println("Exit. Goodbye.");
                    break;
                default:
                    throw new IllegalArgumentException("Bad number choice. Numbers have to be among 1 and 5.");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
