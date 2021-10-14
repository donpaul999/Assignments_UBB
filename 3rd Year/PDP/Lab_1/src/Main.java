import internal.Product;
import internal.Inventory;
import internal.Service;
import internal.Verifier;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    private static final int NUMBER_OF_THREADS = 1000;
    private static final int NUMBER_OF_PRODUCTS = 10000;

    private static ArrayList<Product> products = new ArrayList<>();
    private static ReadWriteLock verifyLock;

    public static void main(String[] args) {
        generateProducts();
        importProducts();

        verifyLock = new ReentrantReadWriteLock();

        Inventory inventory = new Inventory(NUMBER_OF_PRODUCTS, products);
        Service service = new Service(inventory, verifyLock);

        Timer timer = new Timer();
        Verifier verifier = new Verifier(service, verifyLock);
        timer.schedule(verifier, 10, 10);

        float start =  System.nanoTime() / 1000000;

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            Thread t = new Thread(service);
            threads.add(t);
            t.start();
        }

        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        timer.cancel();
        timer.purge();
        verifier.run();

        float end = System.nanoTime() / 1000000;
        System.out.println("\n Finished in" + (end - start) / 1000 + " seconds");
    }

    private static void generateProducts() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"));
            for (int i = 1; i <= NUMBER_OF_PRODUCTS; ++i) {
                Random r = new Random();
                int quantity = r.nextInt(100);
                int price = r.nextInt(10);

                String s = getAlphaNumericString(10 ) + " " + price +" " +  quantity + '\n';
                writer.write(s);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void importProducts() {
        File file = new File("input.txt");
        try {
            Scanner sc = new Scanner(file);
            for (int i = 1; i <= NUMBER_OF_PRODUCTS; ++i) {
                Product product = new Product(sc.next(), sc.nextInt(), sc.nextInt());
                products.add(product);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getAlphaNumericString(int n)
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}