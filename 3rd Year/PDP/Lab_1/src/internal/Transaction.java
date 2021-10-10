package internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Transaction implements Runnable{
    public String name;
    private int profit = 0;
    private ConcurrentHashMap<Product, Integer> inventory;
    private Lock _mutex = new ReentrantLock();

    public Transaction(ConcurrentHashMap<Product, Integer> inventory, String name) {
        this.inventory = inventory;
        this.name = name;
    }

    @Override
    public void run() {
    }

    public void sellProduct(Product product, int quantity) {
        if (!inventory.containsKey(product) || inventory.get(product) - quantity < 0)
        return;
        inventory.replace(product, inventory.get(product) - quantity);
        profit = profit + quantity * product.getPrice();
    }

    public int getProfit() {
        return profit;
    }

    public int getInventoryPriceSum() {
        int totalPrice = 0;
        for (Product product : inventory.keySet()){
            totalPrice += inventory.getOrDefault(product, 0) * product.getPrice();
        }
        return totalPrice;
    }
}
