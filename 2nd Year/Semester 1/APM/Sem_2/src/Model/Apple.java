package Model;

public class Apple implements IEnt {
    private int density, volume;

    public Apple(int density, int volume) {
        this.density = density;
        this.volume = volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public int getWeight() {
        return density * volume;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public int getDensity() {
        return this.density;
    }

    @Override
    public String toString() {
        return "Density: " + this.getDensity() + ", Weight: " + this.getWeight();
    }
}
