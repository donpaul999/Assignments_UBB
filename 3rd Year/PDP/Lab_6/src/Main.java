import domain.DirectedGraph;
import domain.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        runTests();
//        DirectedGraph graph = new DirectedGraph(10);
//        long startTime = System.currentTimeMillis();
//        findCycle(graph, 10);
//        long endTime = System.currentTimeMillis();
//        System.out.println(graph);
//        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    private static void runTests() {
        ArrayList<Integer> dimensions = new ArrayList<>(Arrays.asList(5, 10, 50, 100, 500, 1000, 2500, 5000, 10000));
        ArrayList<Integer> threads = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 10, 15, 30));
        ArrayList<String> headers = new ArrayList<>(Arrays.asList("Nb. of vertices", "Nb. of Threads", "Time (ms)"));
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
                DirectedGraph graph = new DirectedGraph(dimension);

                System.out.println("New test " + dimension);

                for (Integer thread: threads) {
                    System.out.println("New thread " + thread);
                    startTime = System.currentTimeMillis();
                    findCycle(graph, thread);
                    endTime = System.currentTimeMillis();
                    stringBuilder.append(dimension).append(",")
                            .append(thread).append(",")
                            .append((endTime - startTime)).append("\n");
                }
            }
            outputfile.write(String.valueOf(stringBuilder));

            outputfile.flush();
            outputfile.close();
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }


    private static void findCycle(DirectedGraph graph, int nbOfThreads) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(nbOfThreads);

        List<Integer> result = new ArrayList<>(graph.size());

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        for (int i = 0; i < graph.size(); i++){ //check from each node
            executor.submit(new Task(graph, i, result, atomicBoolean));
        }

        executor.shutdown();

        executor.awaitTermination(120, TimeUnit.SECONDS);
    }
}
