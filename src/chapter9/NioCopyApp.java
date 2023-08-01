package chapter9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Παρατηρούμε ότι το while που είχαμε πριν τώρα μετατρέπεται σε
 * Functional programming (κοίτα μέσα στην try σε σχέση π.χ. με το CopyRawDataApp).
 *
 * Το nio έχει γίνει Update στο JDK 7. (δεν χρησιμοποιείται και πολύ από το Community της Java)
 * Η nio έχει όλο το αρχείο μέσα σε έναν Buffer! (κάνει κάποια βελτιστοποίηση)
 */
public class NioCopyApp {
    public static void main(String[] args) {

        byte[] videoBytes;
        Path sourcePath = Paths.get("C:/tmp/sample.mp4");
        Path targetPath = Paths.get("C:/tmp/nio_sample.mp4");

        try {
            videoBytes = Files.readAllBytes(sourcePath);
            Files.write(targetPath, videoBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
