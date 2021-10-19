import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private static final int MAX_CAPACITY = 1;
    private final Queue<Integer> queue = new LinkedList();

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void put(Integer value) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == MAX_CAPACITY) {
                System.out.println(Thread.currentThread() + ": queue is full, waiting...");
                condition.await();
            }
            queue.add(value);
            System.out.println(Thread.currentThread() + " added " + value + " into the queue");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public int get() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println(Thread.currentThread() + ": buffer is empty, waiting");
                condition.await();
            }

            Integer value = queue.poll();
            if (value != null) {
                System.out.println(Thread.currentThread() + " consumed " + value + " into the queue");
                condition.signal();
            }
            return value;
        } finally {
            lock.unlock();
        }
    }
}
