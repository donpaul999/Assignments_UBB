package Model;

public class Fish implements IEnt {
    private int age;
    private String colour;

    public Fish(String colour, int age) {
        this.age = age;
        this.colour = colour;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return "Colour: " + this.getColour() + ", Age: " + this.getAge();
    }
}
