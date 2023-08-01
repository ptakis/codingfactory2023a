package chapter8;

import java.util.Scanner;

/**
 * Displays a menu until the user inputs 5 for exit.
 * If the choice is out of range (i.e. greater than 5 or less than 1)
 * it displays an error message. The program uses methods to display
 * the menu, get the user's choice, and print the choice.
 *
 * @author Charalampos Kontos
 */
public class MenuCharalamposKontos {
    static final int INPUT = 1;
    static final int ERASE = 2;
    static final int UPDATE = 3;
    static final int SEARCH = 4;
    static final int EXIT = 5;

    public static void main(String[] args) {
        int keybrdInput = 0;

        do {
            displayMenu();
            keybrdInput = getChoice();

            try {
                printChoice(keybrdInput);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        } while (keybrdInput != EXIT);
    }

    /**
     * Displays the menu of choices to the user.
     */
    private static void displayMenu() {
        System.out.println("Choice Menu");
        System.out.println("1. Input");
        System.out.println("2. Erase");
        System.out.println("3. Update");
        System.out.println("4. Search");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Reads the user's choice and returns it as an integer.
     * Checks if the user has entered an integer using hasNextInt() method.
     *
     * @return  user's choice as an integer.
     */
    private static int getChoice() {
        Scanner in = new Scanner(System.in);
        int choice;

        while (!in.hasNextInt()) {
            in.nextLine();
            System.out.println("Your choice is not a number. Please enter a choice from the options 1 - 5.");
            displayMenu();
        }
        choice = in.nextInt();

        if (choice == EXIT)
            in.close();

        return choice;
    }

    /**
     * Prints the user's choice based on the integer provided.
     * If the choice is not between the predefined constants INPUT and EXIT,
     * an IllegalArgumentException is thrown and its stack trace is printed.
     *
     * @param   choice the user's choice as an integer
     * @throws  IllegalArgumentException if the choice is not within the range
     *          defined by INPUT and EXIT.
     * The exception is caught, its stack trace is printed, and then it is rethrown.
     */
    private static void printChoice(int choice) {
        try {
            if (choice < INPUT || choice > EXIT) {
                throw new IllegalArgumentException("Your choice is not valid. Please enter a choice from the options 1 - 5.");
            }

            switch (choice) {
                case INPUT:
                    System.out.println("You chose input.");
                    break;
                case ERASE:
                    System.out.println("You chose erase.");
                    break;
                case UPDATE:
                    System.out.println("You chose update.");
                    break;
                case SEARCH:
                    System.out.println("You chose search.");
                    break;
                case EXIT:
                    System.out.println("Exiting the menu.");
                    break;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
    }
}

