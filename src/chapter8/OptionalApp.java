package chapter8;

import java.util.Optional;

public class OptionalApp {

    public static void main(String[] args) {
//        String s = null;
//        String str = getCopy(s);
//
//        if ((str !=null) && (str.equals("AUEB"))) {
//            System.out.println(str);
//        }

        String s = "AUEB";
        String copied = null;

        Optional<String> str = getStrCopy(s);

        // Αν έχει κάτι μέσα (δεν είναι null)
        if (str.isPresent()) {
            copied = str.get();
        }

        System.out.println(copied);

        // method reference (η μοντέρνα εκδοχή των lambda expression)
        str.ifPresent(System.out::println);


    }

    public static String getCopy(String s) {
        return s;
    }

    public static Optional<String> getStrCopy(String s) {
        // Optional.ofNullable: μπορεί να κουβαλάει και null
        // return Optional.ofNullable(s);

        if (s == null) return Optional.empty();
        return Optional.of(s);
    }
}
