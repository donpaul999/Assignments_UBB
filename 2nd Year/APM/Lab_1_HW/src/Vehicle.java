public class Vehicle {
    public static String category = "Vehicle";
    private int speed;
    private int weight;

    public Vehicle(int givenSpeed, int givenWeight) {
        speed = givenSpeed;
        weight = givenWeight;
    }

    public Vehicle() {
        speed = 0;
        weight = 0;
    }

    public void setSpeed(int givenSpeed) {
        speed = givenSpeed;
    }

    public void setWeight(int givenWeight) {
        weight = givenWeight;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWeight() {
        try {
            if(weight > 100)
                throw new Exception("WEIGHT TOO BIG");
        }
        catch (Exception e){
            System.out.println(e);
        }
        return weight;
    }

    public void getSound() {
        System.out.println("BumBum");
    }

    public static void getCategory() {
        System.out.println(category);
    }

}
