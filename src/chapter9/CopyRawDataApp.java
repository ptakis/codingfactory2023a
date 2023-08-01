package chapter9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyRawDataApp {
    public static void main(String[] args) {

        int b = 0;
        int count = 0;
        byte[] buffer = new byte[1024];
        long start;
        long end;
        double elapsed;

        try (FileInputStream in = new FileInputStream("C:/tmp/sample.mp4");
             FileOutputStream out = new FileOutputStream("C:/tmp/video_out.mp4")) {

            // oso den exei ftasei sto telos tou arxeioy
//            while ((b = in.read()) != -1) { // H read() dinei IOException.
//                out.write(b);
//                count++;
//            }

            start = System.currentTimeMillis();
            while ((b = in.read(buffer)) != -1) { // H read() dinei IOException.
                out.write(buffer, 0, b);
                count = count + b;
            }
            end = System.currentTimeMillis();
            elapsed = (end - start) / 1000.0;
            System.out.println("Elapsed time: " + elapsed + " secs");
            System.out.printf("%f KB", count / 1024.0);

        } catch (IOException e) {
            // e.printStackTrace();
            // metatrepw ena checked exception se unchecked
            throw new RuntimeException();
        }
    }


}
