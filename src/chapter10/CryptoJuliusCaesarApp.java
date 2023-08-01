package chapter10;

/**
 * p.x. kryptografw mono ta kefalaia grammata
 */
public class CryptoJuliusCaesarApp {

    public static void main(String[] args) {

    }

    public static String encrypt(String s, int key) {
        StringBuilder encrypted = new StringBuilder();
        char ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (Character.isUpperCase(ch)) encrypted.append(cipher(ch, key));
            else encrypted.append(ch);
        }

        return encrypted.toString();
    }

    public static String decrypt(String s, int key) {
        StringBuilder decrypted = new StringBuilder();
        char ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);

            if (Character.isAlphabetic(ch)) decrypted.append(decipher(ch, key));
            else decrypted.append(ch);
        }

        return decrypted.toString();
    }

    public static char cipher(char ch, int key) {
        int message;
        int c;

        message = ch - 65;

        c = (message + key) % 26;

        return (char) (c + 65);
    }

    public static char decipher(char ch, int key) {
        int message;
        int c;

        c = ch - 65;

        message = ((c - key) + 26) % 26;

        return (char) (message + 65);
    }

}
