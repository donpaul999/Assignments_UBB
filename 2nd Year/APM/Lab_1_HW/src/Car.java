public class Car extends Vehicle {

    public Car(int givenSpeed, int givenWeight) {
        super(givenSpeed, givenWeight);
    }

    public Car() {
        super();
    }

    @Override
    public void getSound() {
        System.out.println("Whoosh");
    }
}
