import internal.Product;
import internal.Transaction;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
 1000 products

 5 threads ~ 0.016 - 0.032 s
 500 threads ~ 0.128 - 0.3 s
 1000 threads ~ 0.288 - 0.4 s
 */

public class Main {
    private static final int NUMBER_OF_THREADS = 5;
    private static final int NUMBER_OF_PRODUCTS = 1000;

    private static int expectedSum = 0;
    private static List<Product> products = new ArrayList<>();
    private static ConcurrentHashMap<Product, Integer> inventory = new ConcurrentHashMap<>();
    private static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        generateProducts();
        importProducts();

        float start =  System.nanoTime() / 1000000;
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            Transaction t = new Transaction(inventory, "T" + i);
            int product = new Random().nextInt(10);
            for (int j = 0; j < product; j++) {
                int quantity = new Random().nextInt(10);
                int productIndex = new Random().nextInt(products.size());
                if (quantity != 0) {
                    t.sellProduct(products.get(productIndex), quantity);
                }
            }
            transactions.add(t);
        }

        List<Thread> threads = new ArrayList<>();
        transactions.forEach(t -> threads.add(new Thread(t)));

        for (Thread thread : threads){
            thread.start();
        }

        for (Thread thread : threads){
            try {
                thread.join();
                check();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        check();
        float end = System.nanoTime() / 1000000;
        System.out.println("\n Finished in" + (end - start) / 1000 + " seconds");
    }

    private static void check() {
        System.out.println("Checking the stock");
        int profitSum = 0;
        for (Transaction transaction : transactions) {
            profitSum += transaction.getProfit();
        }

        if (profitSum + transactions.get(0).getInventoryPriceSum() == expectedSum) {
            System.out.println("Verification successful!");
        }
        else {
            System.out.println("Stock verification failed!");
        }
    }

    private static void generateProducts() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"));
            for (int i = 1; i <= NUMBER_OF_PRODUCTS; ++i) {
                Random r = new Random();
                int quantity = r.nextInt(100);
                int price = r.nextInt(1000);

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
                Product product = new Product(sc.next(), sc.nextInt());
                int quantity = sc.nextInt();
                products.add(product);
                expectedSum += product.getPrice() * quantity;
                if (inventory != null && inventory.containsKey(product)) {
                    inventory.replace(product, inventory.get(product) + quantity);
                } else {
                    inventory.put(product, quantity);
                }
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