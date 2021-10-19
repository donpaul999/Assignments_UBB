import java.util.ArrayList;

public class Producer extends Thread{
    public int length;
    public Buffer buffer;
    public ArrayList<Integer> arrayList_1;
    public ArrayList<Integer> arrayList_2;

    public Producer(Buffer buffer, ArrayList<Integer> arrayList_1, ArrayList<Integer> arrayList_2) {
        super("Producer");
        this.buffer = buffer;
        this.arrayList_1 = arrayList_1;
        this.arrayList_2 = arrayList_2;
        this.length = arrayList_1.size();
    }

    @Override
    public void run() {
        for (int i = 0; i < length; i++){
            System.out.println("Producer: Sending " + arrayList_1.get(i) + " * " + arrayList_2.get(i) + " = " + arrayList_1.get(i) * arrayList_2.get(i));
            try {
                buffer.put(arrayList_1.get(i) * arrayList_2.get(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
