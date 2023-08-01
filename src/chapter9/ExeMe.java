package chapter9;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ExeMe {
    public static void main(String[] args) throws IOException {
        String line;
        String[] records;
        File dir = new File("C:/tmp");
        double averageGrade;

        if (!dir.exists()) {
            if (!dir.mkdir()) {
                System.err.println("Error in make dir");
                System.exit(1);
            }
        }

        try (PrintStream ps = new PrintStream("C:/tmp/primOut.txt", StandardCharsets.UTF_8);
             PrintStream logPs = new PrintStream("C:/tmp/log.txt", StandardCharsets.UTF_8)) {

            try (BufferedReader bf = new BufferedReader(new FileReader("C:/tmp/grades.txt"))) {
                while ((line = bf.readLine()) != null) {
                    records = line.split(" +");

                    averageGrade = getAverage(Integer.parseInt(records[2]), Integer.parseInt(records[3]));
                    for (int i = 0; i < records.length / 2; i++) {
                        ps.print(records[i] + " ");
                    }
                    ps.println(averageGrade);
                }
            } catch (NumberFormatException e) {
                logPs.println(e);
            } catch (RuntimeException e1) {
                logPs.println(e1);
            } catch (IOException e2) {
                logPs.println(e2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double getAverage(int a, int b) throws RuntimeException{
        try {
            if ((a >= 0 && a <= 10) && (b >= 0 && b <= 10)) {
                return (a + b) / 2.0;
            } else {
                throw new RuntimeException("Wrong numbers");
            }
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
