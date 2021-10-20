package approach;

import adt.Matrix;
import internal.Internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PoolRunner {
    public static void run(Matrix matrix_1, Matrix matrix_2, Matrix result, int nbOfThreads,
                           String taskType, boolean isTest) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(nbOfThreads);

        for (int i = 0; i < nbOfThreads; i++)
            service.submit(Internal.initThread(i, matrix_1, matrix_2, result, nbOfThreads, taskType));

        service.shutdown();
        if (!service.awaitTermination(300, TimeUnit.SECONDS)) {
            service.shutdownNow();
        }

        if (!isTest) {
            System.out.println("Result:");
            System.out.println(result);
        }
    }
}
