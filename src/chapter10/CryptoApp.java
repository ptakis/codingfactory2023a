package chapter10;

import java.util.ArrayList;

public class CryptoApp {

    public static void main(String[] args) {
        final int KEY = 300;
        String s = "Coding#";
        String encrypted = encrypt(s, KEY).toString();

        System.out.println("encrypted: " + encrypted);

        String decrypted = decrypt(encrypt(s, KEY), KEY).toString();

        System.out.println("decrypted: " + decrypted);

    }

    public static ArrayList<Integer> encrypt(String s, int key) {
        ArrayList<Integer> encrypted = new ArrayList<>();
        char ch;
        int i;

        int prev = cipher(s.charAt(0), -1, key);
        encrypted.add(prev);

        i = 1;

        while ((ch = s.charAt(i)) != '#') {
            encrypted.add(cipher(ch, prev, key));
            prev = cipher(ch, prev, key);
            i++;
        }
        encrypted.add(-1);

        return encrypted;
    }

    public static ArrayList<Character> decrypt(ArrayList<Integer> encrypted, int key) {
        ArrayList<Character> decrypted = new ArrayList<>();
        int token;
        int i;
        int previousToken;

        previousToken = decipher(encrypted.get(0), -1, key);
        decrypted.add((char) previousToken);

        i = 1;
        while ((token = encrypted.get(i)) != -1) {
            decrypted.add(decipher(token, previousToken, key));
            previousToken = token;
            i++;
        }
        return decrypted;
    }

    public static int cipher(char ch, int prev, int key) {
        if (prev == -1) {
            return ch;
        }
        return (ch + prev) % key;
    }

    public static char decipher(int cipher, int prev, int key) {
        if (prev == -1) return (char) cipher;
        return (char) ((cipher - prev + key) % key);
    }


}
