package internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class Service implements Runnable{
    private List<Bill> allSales;
    private int totalProfit;
    private Inventory inventory;
    private List<String> productNames;
    private final ReadWriteLock verifyLock;
    private final ReentrantLock mutex = new ReentrantLock();

    public Service(Inventory inventory, ReadWriteLock verifyLock) {
        this.allSales = new ArrayList<>();
        this.totalProfit = 0;

        this.verifyLock = verifyLock;
        this.inventory = inventory;
        this.productNames = new ArrayList<String>(inventory.getProducts().keySet());
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        sellProducts();
    }

    public int getTotalProfit() {
        return totalProfit;
    }

    public int getTotalQuantity() {
        return inventory.getTotalQuantity();
    }

    public int getPresentQuantity(){
        return this.inventory.getProducts().values().stream().map(Product::getQuantity).reduce(0, Integer::sum);
    }

    public int getBilledProfit(){
        return this.allSales.stream().map(Bill::getTotalPrice).reduce(0, Integer::sum);
    }

    public int getBilledQuantity(){
        return this.allSales.stream().map(bill -> bill.getProducts().values().stream().reduce(0, Integer::sum))
                .reduce(0, Integer::sum);
    }

    private void sellProducts(){
        verifyLock.readLock().lock();
        mutex.lock();
        Bill bill = new Bill();
        Random r = new Random();

        while(bill.getProducts().size() < 10){
            Product product = this.inventory.getProducts().get(productNames.get(r.nextInt(productNames.size())));
            int quantity = r.nextInt(10);
            if(product != null && !bill.getProducts().containsKey(product) && product.getQuantity() - quantity >= 0){
                bill.getProducts().put(product, quantity);
                this.inventory.sellProduct(product.getName(), quantity);
                bill.setTotalPrice(bill.getTotalPrice() +  product.getPrice() * quantity);
            }
        }
        this.totalProfit += bill.getTotalPrice();
        this.allSales.add(bill);

        mutex.unlock();
        verifyLock.readLock().unlock();
    }
}
