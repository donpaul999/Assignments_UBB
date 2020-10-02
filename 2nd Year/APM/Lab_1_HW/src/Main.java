public class Main {
    public static void main(String args[]) {
        Vehicle a = new Vehicle(100, 100);
        Car b = new Car();
        RoadCar c = new RoadCar();
        a.getCategory();

        a.setSpeed(100);
        b.setSpeed(100);

        System.out.println(b.getSpeed());
        b.getSound();

        b.setWeight(150);
        System.out.println(b.getWeight());

        c.getSound();
        c.getCategory();
    }
}
