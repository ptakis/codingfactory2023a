package chapter9;

import java.io.*;

public class CopyRawDataApp2 {
    public static void main(String[] args) {

        int b = 0;
        int count = 0;
        byte[] buffer = new byte[1024];
        long start;
        long end;
        double elapsed;

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:/tmp/sample.mp4"));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:/tmp/video_out2.mp4"))) {

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
