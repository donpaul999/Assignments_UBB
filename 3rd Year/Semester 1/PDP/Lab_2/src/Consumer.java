public class Consumer extends Thread{
    public int result = 0;
    public Buffer buffer;
    public int length;

    public Consumer(Buffer buffer, int length) {
        super("Consumer");
        this.buffer = buffer;
        this.length = length;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.length; i++) {
            try {
                result += buffer.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer: Result is now " + result);
        }
        System.out.println("Consumer: Final result is: " + result);
    }
}
