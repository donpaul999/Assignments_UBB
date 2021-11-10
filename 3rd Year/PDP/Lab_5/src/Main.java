import domain.Polynomial;
import domain.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // runTests();

        Polynomial a = new Polynomial(5);
        Polynomial b = new Polynomial(5);

        System.out.println("A:" + a);
        System.out.println("B:" + b);

        long startTime = System.currentTimeMillis();
        // Polynomial result = Product.simpleSequential(a, b);
        // Polynomial result = Product.simpleParallelized(a, b, 10);
        // Polynomial result = Product.karatsubaSequential(a, b);
        Polynomial result = Product.karatsubaParallelized(a, b, 10, 0, 4);
        long endTime = System.currentTimeMillis();
        System.out.println("Result: " + result);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");

    }

    private static void runTests() {
        ArrayList<Integer> dimensions = new ArrayList<>(Arrays.asList(5, 10, 50, 100, 500, 1000, 2500, 5000, 10000));
        ArrayList<Integer> threads = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 10, 15, 30));
        ArrayList<String> headers = new ArrayList<>(Arrays.asList("Polynomial order", "Approach", "Nb. of Threads", "Time (ms)"));
        long startTime, endTime;

        File file = new File("experiments.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            StringBuilder stringBuilder = new StringBuilder();

            for (String header : headers) {
                stringBuilder.append(header).append(",");
            }
            stringBuilder.setLength(stringBuilder.length() - 1);
            stringBuilder.append("\n");

            for (Integer dimension: dimensions) {
                Polynomial a = new Polynomial(dimension);
                Polynomial b = new Polynomial(dimension);

                System.out.println("New test " + dimension);

                startTime = System.currentTimeMillis();
                Product.simpleSequential(a, b);
                endTime = System.currentTimeMillis();
                stringBuilder.append(dimension).append(",")
                        .append("Simple Sequential").append(",")
                        .append('-').append(",")
                        .append((endTime - startTime)).append("\n");

                startTime = System.currentTimeMillis();
                Product.karatsubaSequential(a, b);
                endTime = System.currentTimeMillis();
                stringBuilder.append(dimension).append(",")
                        .append("Karatsuba Sequential").append(",")
                        .append('-').append(",")
                        .append((endTime - startTime)).append("\n");

                for (Integer thread: threads) {
                    startTime = System.currentTimeMillis();
                    Product.simpleParallelized(a, b, thread);
                    endTime = System.currentTimeMillis();
                    stringBuilder.append(dimension).append(",")
                            .append("Simple Parallelized").append(",")
                            .append(thread).append(",")
                            .append((endTime - startTime)).append("\n");

                    startTime = System.currentTimeMillis();
                    Product.karatsubaParallelized(a, b, thread, 0, 4);
                    endTime = System.currentTimeMillis();
                    stringBuilder.append(dimension).append(",")
                            .append("Karatsuba Parallelized").append(",")
                            .append(thread).append(",")
                            .append((endTime - startTime)).append("\n");
                }
            }
            outputfile.write(String.valueOf(stringBuilder));

            outputfile.flush();
            outputfile.close();
        }
        catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
