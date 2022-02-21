package domain;

public class Task implements Runnable {
    private final int start;
    private final int end;
    private final Polynomial a;
    private final Polynomial b;
    private final Polynomial result;

    public Task(int start, int end, Polynomial a, Polynomial b, Polynomial result) {
        this.start = start;
        this.end = end;
        this.a = a;
        this.b = b;
        this.result = result;
    }

    @Override
    public void run() {
        for (int index = start; index < end; index++) {
            if (index > result.getCoefficients().size()) {
                return;
            }
            //find all the pairs that we add to obtain the value of a result coefficient
            for (int j = 0; j <= index; j++) {
                if (j < a.getSize() && (index - j) < b.getSize()) {
                    int value = a.getCoefficients().get(j) * b.getCoefficients().get(index - j);
                    result.getCoefficients().set(index, result.getCoefficients().get(index) + value);
                }
            }
        }
    }
}
