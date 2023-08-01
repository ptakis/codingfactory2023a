package chapter8;

public class NullPointerExceptApp {
    public static void main(String[] args) {
        String s = null;

        if (s!= null)
            if (s.equals("Coding")) {
                System.out.println("bingo!");
            } else {
                System.out.println("not equals");
            }
        else {
            System.out.println("s is null");
        }
    }
}
