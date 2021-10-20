import adt.Matrix;
import approach.NormalRunner;
import approach.PoolRunner;
import internal.Internal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static final int LINES_1 = 10;
    private static final int COLUMNS_1 = 10;
    private static final int LINES_2 = 10;
    private static final int COLUMNS_2 = 10;

    private static final int NUMBER_OF_THREADS = 1;

    private static final String APPROACH = "Normal";
    private static final String TASK_TYPE = "ROW";

    public static void main(String[] args) throws InterruptedException {
        runTests();
        //float time = runMain(LINES_1, LINES_2, COLUMNS_1, COLUMNS_2, NUMBER_OF_THREADS, APPROACH, TASK_TYPE, false);
        //System.out.println("\n Finished in" + time + " seconds");
    }

    private static float runMain(int LINES_1, int LINES_2, int COLUMNS_1, int COLUMNS_2, int NUMBER_OF_THREADS,
                                 String APPROACH, String TASK_TYPE, boolean isTest) throws InterruptedException {
        Matrix matrix_1 = new Matrix(LINES_1, COLUMNS_1, true);
        Matrix matrix_2 = new Matrix(LINES_2, COLUMNS_2, true);

        if (!isTest) {
            System.out.println(matrix_1);
            System.out.println("----------");
            System.out.println(matrix_2);
        }

        Matrix result = new Matrix(LINES_1, COLUMNS_2, false);

        float start =  System.nanoTime() / 1000000;

        if (APPROACH.equals("Pool")) {
            PoolRunner.run(matrix_1, matrix_2, result, NUMBER_OF_THREADS, TASK_TYPE, isTest);
        } else {
            NormalRunner.run(matrix_1, matrix_2, result, NUMBER_OF_THREADS, TASK_TYPE, isTest);
        }
        float end = System.nanoTime() / 1000000;
        return (end - start) / 1000;
    }

    private static void runTests() {
        ArrayList<Integer> dimensions = new ArrayList<>(Arrays.asList(3, 4, 5, 10, 50, 100, 500, 1000, 2500, 5000));
        ArrayList<Integer> threads = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 10, 15));
        ArrayList<String> approaches = new ArrayList<>(Arrays.asList("Normal", "Pool"));
        ArrayList<String> taskTypes = new ArrayList<>(Arrays.asList("ROW", "COLUMN", "KTH"));
        ArrayList<String> headers = new ArrayList<>(Arrays.asList("Dimension", "Nb. of Threads", "Approach", "Task Type", "Time"));


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
                for (Integer thread: threads) {
                    for (String approach: approaches) {
                        for (String taskType: taskTypes) {
                            System.out.println("New test " + dimension + "x" + dimension + " " + thread + " " + approach + " " + taskType);
                            float time = runMain(dimension, dimension, dimension, dimension, thread, approach,
                                    taskType, true);
                            stringBuilder.append(dimension).append("x")
                                    .append(dimension).append(",")
                                    .append(thread).append(",")
                                    .append(approach).append(",")
                                    .append(taskType).append(",")
                                    .append(time).append("\n");
                            outputfile.write(String.valueOf(stringBuilder));
                            stringBuilder.setLength(0);
                        }
                    }
                }
            }
            outputfile.write(String.valueOf(stringBuilder));

            outputfile.flush();
            outputfile.close();
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
