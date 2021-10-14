package internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory{
    private Map<String, Product> products = new HashMap<>();
    private int totalQuantity = 0;

    public Inventory(int productsNumber, ArrayList<Product> productsList) {
        for (int i = 0; i < productsNumber; i++) {
            products.put(productsList.get(i).getName(), productsList.get(i));
            this.totalQuantity += productsList.get(i).getQuantity();
        }
    }

    public void sellProduct(String name, int quantity){
        Product product = this.products.get(name);
        if(product != null){
            product.setQuantity(product.getQuantity() - quantity);
        }
    }

    public Map<String, Product> getProducts(){
        return this.products;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }
}
