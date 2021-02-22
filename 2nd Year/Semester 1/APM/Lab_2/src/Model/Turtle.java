package Model;

public class Turtle implements IEnt {
    private int age;
    private String name;

    public Turtle(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Age: " + this.getAge();
    }
}
