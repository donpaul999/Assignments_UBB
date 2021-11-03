package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Product {
    public static Polynomial simpleSequential(Polynomial a, Polynomial b) {
        int sizeOfResultCoefficientList = a.getOrder() + b.getOrder() + 1;
        List<Integer> coefficients = new ArrayList<>();
        for (int i = 0; i < sizeOfResultCoefficientList; i++) {
            coefficients.add(0);
        }
        for (int i = 0; i < a.getSize(); i++) {
            for (int j = 0; j < b.getSize(); j++) {
                int index = i + j;
                int value = a.getCoefficients().get(i) * b.getCoefficients().get(j);
                coefficients.set(index, coefficients.get(index) + value);
            }
        }
        return new Polynomial(coefficients);
    }

    public static Polynomial simpleParallelized(Polynomial a, Polynomial b, int nbOfThreads) throws
            InterruptedException {
        int sizeOfResultCoefficientList = a.getOrder() + b.getOrder() + 1;
        List<Integer> coefficients = new ArrayList<>(Collections.nCopies(sizeOfResultCoefficientList, 0));;
        Polynomial result = new Polynomial(coefficients);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nbOfThreads);
        int step = result.getSize() / nbOfThreads;
        if (step == 0) {
            step = 1;
        }

        int end;
        for (int i = 0; i < result.getSize(); i += step) {
            end = i + step;
            Task task = new Task(i, end, a, b, result);
            executor.execute(task);
        }

        executor.shutdown();
        executor.awaitTermination(120, TimeUnit.SECONDS);

        return result;
    }

    public static Polynomial karatsubaSequential(Polynomial a, Polynomial b) {
        if (a.getOrder() < 2 || b.getOrder() < 2) {
            return simpleSequential(a, b);
        }

        int len = Math.max(a.getOrder(), b.getOrder()) / 2;
        Polynomial lowA = new Polynomial(a.getCoefficients().subList(0, len));
        Polynomial highA = new Polynomial(a.getCoefficients().subList(len, a.getSize()));
        Polynomial lowB = new Polynomial(b.getCoefficients().subList(0, len));
        Polynomial highB = new Polynomial(b.getCoefficients().subList(len, b.getSize()));

        Polynomial z1 = karatsubaSequential(lowA, lowB);
        Polynomial z2 = karatsubaSequential(add(lowA, highA), add(lowB, highB));
        Polynomial z3 = karatsubaSequential(highA, highB);

        //calculate the final result
        Polynomial r1 = addZeros(z3, 2 * len);
        Polynomial r2 = addZeros(subtract(subtract(z2, z3), z1), len);
        return add(add(r1, r2), z1);
    }

    private static Polynomial addZeros(Polynomial a, int lastPos) {
        List<Integer> coefficients = IntStream.range(0, lastPos).mapToObj(i -> 0).collect(Collectors.toList());
        coefficients.addAll(a.getCoefficients());
        return new Polynomial(coefficients);
    }

    private static Polynomial add(Polynomial a, Polynomial b) {
        int maxOrder = Math.max(a.getOrder(), b.getOrder());
        int minOrder = Math.min(a.getOrder(), b.getOrder());
        List<Integer> coefficients = new ArrayList<>(maxOrder + 1);

        for (int i = 0; i <= minOrder; i++) {
            coefficients.add(a.getCoefficients().get(i) + b.getCoefficients().get(i));
        }

        addRemainingCoefficients(a, b, coefficients);

        return new Polynomial(coefficients);
    }

    private static void addRemainingCoefficients(Polynomial a, Polynomial b, List<Integer> coefficients) {
        int maxOrder = Math.max(a.getOrder(), b.getOrder());
        int minOrder = Math.min(a.getOrder(), b.getOrder());

        if (minOrder == maxOrder)
            return;

        if (maxOrder == a.getOrder()) {
            for (int i = minOrder + 1; i <= maxOrder; i++) {
                coefficients.add(a.getCoefficients().get(i));
            }
        } else {
            for (int i = minOrder + 1; i <= maxOrder; i++) {
                coefficients.add(b.getCoefficients().get(i));
            }
        }
    }

    public static Polynomial subtract(Polynomial a, Polynomial b) {
        int minDegree = Math.min(a.getOrder(), b.getOrder());
        int maxDegree = Math.max(a.getOrder(), b.getOrder());
        List<Integer> coefficients = new ArrayList<>(maxDegree + 1);

        for (int i = 0; i <= minDegree; i++) {
            coefficients.add(a.getCoefficients().get(i) - b.getCoefficients().get(i));
        }

        addRemainingCoefficients(a, b, coefficients);

        int i = coefficients.size() - 1;
        while (coefficients.get(i) == 0 && i > 0) {
            coefficients.remove(i);
            i--;
        }

        return new Polynomial(coefficients);
    }

    public static Polynomial karatsubaParallelized(Polynomial a, Polynomial b, int nbOfThreads, int currentDepth, int maxDepth) throws ExecutionException, InterruptedException {
        if (currentDepth > maxDepth) {
            return karatsubaSequential(a, b);
        }

        if (a.getOrder() < 2 || b.getOrder() < 2) {
            return simpleSequential(a, b);
        }

        int len = Math.max(a.getOrder(), b.getOrder()) / 2;
        Polynomial lowA = new Polynomial(a.getCoefficients().subList(0, len));
        Polynomial highA = new Polynomial(a.getCoefficients().subList(len, a.getSize()));
        Polynomial lowB = new Polynomial(b.getCoefficients().subList(0, len));
        Polynomial highB = new Polynomial(b.getCoefficients().subList(len, b.getSize()));

        ExecutorService executor = Executors.newFixedThreadPool(nbOfThreads);
        Future<Polynomial> f1 = executor.submit(() -> karatsubaParallelized(lowA, lowB, nbOfThreads, currentDepth + 1, maxDepth));
        Future<Polynomial> f2 = executor.submit(() -> karatsubaParallelized(add(lowA, highA), add(lowB, highB), nbOfThreads, currentDepth + 1, maxDepth));
        Future<Polynomial> f3 = executor.submit(() -> karatsubaParallelized(highA, highB, nbOfThreads, currentDepth + 1, maxDepth));

        executor.shutdown();

        Polynomial z1 = f1.get();
        Polynomial z2 = f2.get();
        Polynomial z3 = f3.get();

        executor.awaitTermination(120, TimeUnit.SECONDS);

        //calculate the final result
        Polynomial r1 = addZeros(z3, 2 * len);
        Polynomial r2 = addZeros(subtract(subtract(z2, z3), z1), len);
        return add(add(r1, r2), z1);
    }
}
