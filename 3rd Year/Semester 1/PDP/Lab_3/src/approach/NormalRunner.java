package approach;

import adt.Matrix;
import internal.Internal;

import java.util.ArrayList;
import java.util.List;

public class NormalRunner {
    public static void run(Matrix matrix_1, Matrix matrix_2, Matrix result, int nbOfThreads, String taskType, boolean isTest) {
        List<Thread> threadsList = new ArrayList<>();

        for (int i = 0; i < nbOfThreads; i++)
            threadsList.add(Internal.initThread(i, matrix_1, matrix_2, result, nbOfThreads, taskType));

        for (Thread thread : threadsList) {
            thread.start();
        }
        for (Thread thread : threadsList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!isTest) {
            System.out.println("Result:");
            System.out.println(result);
        }
    }
}
