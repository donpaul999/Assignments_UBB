package Model;

public class Cake implements IEnt {
    private int density, volume;

    @Override
    public int getWeight() {
        return this.density * this.volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getDensity() {
        return this.density;
    }

    @Override
    public String toString() {
        return "Density: " + this.getDensity() + ", Weight: " + this.getWeight();
    }
}
