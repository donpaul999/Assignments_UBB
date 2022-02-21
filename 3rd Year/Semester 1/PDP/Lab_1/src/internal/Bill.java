package internal;

import java.util.HashMap;
import java.util.Map;

public class Bill {
    private Map<Product, Integer> products; // Integer represents sold quantity.
    private int totalPrice;

    public Bill(){
        this.products = new HashMap<>();
        this.totalPrice = 0;
    };

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
