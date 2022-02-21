import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Informer {
    private Integer value = null;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public Informer() {
    }

    public void notify(Integer value) {
        lock.lock();

        try {
            while (this.value != null) {
                condition.await();
            }

            this.value = value;

            System.out.println("Value informed: " + value);

            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.unlock();
    }

    public Integer waitForData() {
        lock.lock();

        try {
            while (this.value == null) {
                condition.await();
            }

            var value = this.value;
            this.value = null;

            System.out.println("Value received: " + value);

            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.unlock();
        return value;
    }


}
