package chapter10;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Στην εφαρμογή μας το ρόλο του Client θα τον παίξει η main().
 */
public class MobileContactsApp {

    final static String[][] contacts = new String[500][3];
    static int pivot = -1; // arxika deixnei eksw apo to orio tou pinaka (deixnei stin trexousa thesi).
    final static Path path = Paths.get("C:/tmp/log-mobile.txt");
    final static Scanner in = new Scanner(System.in);


    public static void main(String[] args) {
        boolean quit = false;
        String s;
        int choice;
        String phoneNumber;

        do {
            printMenu();
            s = getChoice();

            if (s.matches("[Qq]")) {
                quit = true;
            } else {
                try {
                    choice = Integer.parseInt(s);

                    if (!isValid(choice)) {
                        throw new IllegalArgumentException("Error - Choice between 1 - 5");
                    }

                    switch (choice) {
                        case 1:
                            printContactMenu();
                            insertController(getFirstName(), getLastName(), getPhoneNumber());
                            System.out.println("Επιτυχής εισαγωγή");
                            break;
                        case 2:
                            phoneNumber = getPhoneNumber();
                            deleteController(phoneNumber);
                            System.out.println("Επιτυχής διαγραφή");
                            break;
                        case 3:
                            phoneNumber = getPhoneNumber();
                            printContactMenu();
                            updateController(phoneNumber, getFirstName(), getLastName(), getPhoneNumber());
                            System.out.println("Επιτυχής ενημέρωση");
                            break;
                        case 4:
                            phoneNumber = getPhoneNumber();
                            String[] contact = getOneContactController(phoneNumber);
                            printContact(contact);
                            break;
                        case 5:
                            String[][] allContacts = getAllContactsController();
                            printAllContacts(allContacts);
                            break;
                        default:
                            throw new IllegalArgumentException("Bad choice");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!quit);
    }

    public static void printContact(String[] contacts) {
        for (String s: contacts) {
            System.out.println(s + " ");
        }
    }

    public static void printAllContacts(String[][] allContacts) {
        for (String[] contact : allContacts) {
            // System.out.printf("%s\t%s\t%s\n", contact[0], contact[1], contact[2]);
            printContact(contact);
        }
    }

    public static boolean isValid(int choice) {
        return (choice >= 1 && choice <= 5);
    }

    public static void printMenu() {
        System.out.println("Επιλέξτε ένα από τα παρακάτω");
        System.out.println("1. Εισαγωγή επαφής");
        System.out.println("2. Διαγραφή επαφής");
        System.out.println("3. Ενημέρωση επαφής");
        System.out.println("4. Αναζήτηση επαφής");
        System.out.println("5. Εκτύπωση όλων ων επαφών");
        System.out.println("Q/q. Έξοδος");
    }

    public static String getChoice() {
        System.out.println("Εισάγετε επιλογή");
        return in.nextLine().trim();
    }

    public static void printContactMenu() {
        System.out.println("Εισάγετε Όνομα, Επώνυμο και Τηλέφωνο");
    }

    public static String getFirstName() {
        System.out.println("Εισάγετε όνομα");
        return in.nextLine().trim();
    }

    public static String getLastName() {
        System.out.println("Εισάγετε επίθετο");
        return in.nextLine().trim();
    }

    public static String getPhoneNumber() {
        System.out.println("Εισάγετε τηλέφωνο");
        return in.nextLine().trim();
    }

    /*
     * Controllers
     *
     * O ρόλος του Controller είναι να ενημερώσει τον
     * Client (ο οποίος του ζητάει) αν συνέβη κάτι σωστά ή λάθος.
     * Ο Controller κανονικά δεν κάνει throw...
     *
     * Ο Controller:
     * - Ελέγχει τα data που λαμβάνει.
     * - Καλεί το Service Layer.
     * - Κατ' εξοχήν έχει try...catch γιατί από τα Services του έρχονται όλα τα λογικά λάθη.
     * - Ο ίδιος ο Controller ελέγχει και κάνει throw new Exception() γιατί θα μπορούσε και από το Service να έρθει κάτι
     *   το οποίο να σημαίνει ότι πρέπει να δοθεί ένα Exception π.χ. Να επιστραφεί ένα κενό collection.
     */

    public static void insertController(String firstname, String lastname, String phoneNumber) {
        try {
            // validation of the data
            if (firstname == null || lastname == null || phoneNumber == null) {
                throw new IllegalArgumentException("Nulls are not allowed");
            }

            if (firstname.length() < 2 || firstname.length() > 50) {
                throw new IllegalArgumentException("Firstname is not valid");
            }

            if (lastname.length() < 2 || lastname.length() > 50) {
                throw new IllegalArgumentException("Lastname is not valid");
            }

            if (phoneNumber.length() < 2 || phoneNumber.length() > 50) {
                throw new IllegalArgumentException("Phone number is not valid");
            }

            // Service call
            insertContactService(firstname.trim(), lastname.trim(), phoneNumber.trim());

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void updateController(String oldPhoneNumber, String firstname, String lastname, String newPhoneNumber) {
        try {
            // validation of the data
            if (oldPhoneNumber == null || firstname == null || lastname == null || newPhoneNumber == null) {
                throw new IllegalArgumentException("Nulls are not allowed");
            }

            if (oldPhoneNumber.length() < 2 || oldPhoneNumber.length() > 50) {
                throw new IllegalArgumentException("Old Phone Number is not valid");
            }

            if (firstname.length() < 2 || firstname.length() > 50) {
                throw new IllegalArgumentException("Firstname is not valid");
            }

            if (lastname.length() < 2 || lastname.length() > 50) {
                throw new IllegalArgumentException("Lastname is not valid");
            }

            if (newPhoneNumber.length() < 2 || newPhoneNumber.length() > 50) {
                throw new IllegalArgumentException("New Phone Number is not valid");
            }

            // Service call
            updateContactService(oldPhoneNumber.trim(), firstname.trim(), lastname.trim(), newPhoneNumber.trim());

        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            throw e;
        }
    }

    public static String[] deleteController(String phoneNumber) {
        try {
            if (phoneNumber == null) {
                throw new IllegalArgumentException("Nulls are not allowed");
            }

            if (phoneNumber.length() < 2 || phoneNumber.length() > 50) {
                throw new IllegalArgumentException("Phone number is not valid");
            }

            return  deleteContactService(phoneNumber);

        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            throw e;
        }
    }

    public static String[] getOneContactController(String phoneNumber) {
        try {
            if (phoneNumber == null) {
                throw new IllegalArgumentException("Nulls are not allowed");
            }

            if (phoneNumber.length() < 2 || phoneNumber.length() > 50) {
                throw new IllegalArgumentException("Phone number is not valid");
            }

            return  getOneContactService(phoneNumber);

        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            throw e;
        }
    }

    public static String[][] getAllContactsController() {
        try {
            return getAllContactsService();
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            throw e;
        }
    }

    /*
     * Service Layer
     *
     * Το Service Layer καλεί το CRUD.
     * Στο Service Layer εκφράζουμε και λογική με Exception.
     * Το Exception αυτό θα μεταβιβαστεί μετά στο layer που θα καλέσει το Service (θα μεταβιβαστεί προς τα πάνω - θα το κάνουμε rethrow).
     *
     * Το Service:
     * - Παρέχει την υπηρεσία.
     * - Κάνει throw τα λογικά λάθη (αν υπάρχουν).
     * - Το Service Layer (πρέπει να κάνει) μαζί με το CRUD Layer κάνει το logging.
     *
     * O Controller συνήθως δε κάνει logging.
     */

    public static String[] getOneContactService(String phoneNumber) {
        try {
            String[] contact = getContactByPhoneNumber(phoneNumber);
            if (contact.length == 0) {
                throw new IllegalArgumentException("Contact not found");
            }
            return contact;
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    public static String[][] getAllContactsService() {
        try {
            String[][] contactsList = getALlContacts();
            if (contactsList.length == 0) {
                throw new IllegalArgumentException("List is empty");
            }
            return contactsList;
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    public static void insertContactService(String firstname, String lastname, String phoneNumber) {
        try {
            if (!insert(firstname, lastname, phoneNumber)) {
                throw new IllegalArgumentException("Error in insert");
            }
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    public static void updateContactService(String oldPhoneNumber, String firstname, String lastname, String newPhoneNumber) {
        try {
            if (!update(oldPhoneNumber, firstname, lastname, newPhoneNumber)) {
                throw new IllegalArgumentException("Error in update");
            }
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    public static String[] deleteContactService(String phoneNumber) {
        String[] contact;
        try {
            contact = delete(phoneNumber);
            if (contact.length == 0) {
                throw new IllegalArgumentException("Error in delete");
            }
            return contact;
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }


    /*
     *  CRUD Services that are provided to Service Layer.
     */

    public static int getIndexByPhoneNumber(String phoneNumber) {
        for (int i = 0; i <= pivot; i++) {
            if (contacts[i][2].equals(phoneNumber)) {
                return i;
            }
        }
        return -1; // if not found
    }

    public static boolean insert(String firstname, String lastname, String phoneNumber) {

        if (isFull(contacts)) {
            return false;
        }
        // Σημαίνει οτι βρέθηκε ο phoneNumber
        if (getIndexByPhoneNumber(phoneNumber) != -1) {
            return false;
        }

        pivot++;
        contacts[pivot][0] = firstname;
        contacts[pivot][1] = lastname;
        contacts[pivot][2] = phoneNumber;
        return true;
    }

    public static boolean update(String oldPhoneNumber, String firstname, String lastname, String newPhoneNumber) {
        int positionToUpdate = getIndexByPhoneNumber(oldPhoneNumber);

        // Αν δε βρέθηκε το αριθμός
        if (positionToUpdate == -1) {
            return false;
        }

        contacts[positionToUpdate][0] = firstname;
        contacts[positionToUpdate][1] = lastname;
        contacts[positionToUpdate][2] = newPhoneNumber;
        return true;
    }

    public static String[] delete(String phoneNumber) {
        int positionToDelete = getIndexByPhoneNumber(phoneNumber);
        String[] contact = new String[3];

        if (positionToDelete == -1) {
            return new String[] {};
        }


        for (int i = 0; i < contact.length; i++) {
            contact[i] = contacts[positionToDelete][i];
        }
        // Εναλλακτικά (αντί για την παραπάνω for)
        // System.arraycopy(contact[positionToDelete], 0, contact, 0, contact.length);

        if (!(positionToDelete == contacts.length - 1)) {
            System.arraycopy(contacts, positionToDelete + 1, contacts, positionToDelete, pivot - positionToDelete);
        }
        pivot--;

        return contact;
    }

    public static String[] getContactByPhoneNumber(String phoneNumber) {
        int positionToReturn = getIndexByPhoneNumber(phoneNumber);

        if (positionToReturn == -1) {
            return new String[] {};
        }

        return contacts[positionToReturn];
    }

    public static String[][] getALlContacts() {
        return Arrays.copyOf(contacts, pivot + 1);
    }

    public static boolean isFull(String[][] arr) {
        return pivot == arr.length - 1;
    }

    /**
     * Custom logger
     * Varargs : String[] message (Μπορώ να περάσω μέσα 0, 1 ή περισσότερα String)
     * Είναι πιο ευέλικτη μορφή από το να περνούσα ένα πίνακα.
     * @param e
     * @param message
     */
    public static void log(Exception e, String... message) {
        try (PrintStream ps = new PrintStream(new FileOutputStream(path.toFile(), true))) {
            ps.println(LocalDateTime.now() + "\n" + e);
            ps.printf("%s", (message.length == 1) ? message[0] : "");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
