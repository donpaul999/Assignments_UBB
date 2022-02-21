package internal;

import java.util.TimerTask;
import java.util.concurrent.locks.ReadWriteLock;

public class Verifier extends TimerTask {
    private Service service;
    private final ReadWriteLock verifyLock;

    public Verifier(Service service, ReadWriteLock verifyLock) {
        this.service = service;
        this.verifyLock = verifyLock;
    }

    @Override
    public void run() {
        verifyLock.writeLock().lock();
        System.out.println("--------------------------------");
        System.out.println("Check started!");

        int presentQuantity = this.service.getPresentQuantity();
        int leftQuantity = this.service.getTotalQuantity() - this.service.getBilledQuantity();
        if (leftQuantity != presentQuantity) {
            System.err.println("CHECK failed!");
        }
        System.out.println("leftQuantity = " + leftQuantity + " --- presentQuantity = " + presentQuantity);

        int billedSales = this.service.getBilledProfit();
        int totalProfit = this.service.getTotalProfit();
        if (billedSales != totalProfit) {
            System.err.println("CHECK failed!");
        }
        System.out.println("billedSales = " + billedSales + " --- totalProfit = " + totalProfit);
        System.out.println("Check finished!");
        verifyLock.writeLock().unlock();
    }
}
