import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList_1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> arrayList_2 = new ArrayList<>(Arrays.asList(6, 7, 8, 9, 10));

        Buffer buffer = new Buffer();

        Producer producer = new Producer(buffer, arrayList_1, arrayList_2);
        Consumer consumer = new Consumer(buffer, arrayList_1.size());

        producer.start();
        consumer.start();
    }
}
