package Model;

public class Book implements IEnt {
    private int density, volume;
    private int numberOfPages;
    public Book(int density, int volume, int numberOfPages) {
        this.density = volume;
        this.numberOfPages = numberOfPages;
    }

    @Override
    public int getWeight() {
        return this.density * this.volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Weight: " + this.getWeight() + ", Nb of pg: " + this.numberOfPages;
    }
}
