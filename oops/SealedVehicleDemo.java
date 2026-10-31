sealed class Vehicle permits Car, Bike {
    public void start() {
        System.out.println("Vehicle started");
    }
}

final class Car extends Vehicle {
    @Override
    public void start() {
        System.out.println("Car started");
    }
}

final class Bike extends Vehicle {
    @Override
    public void start() {
        System.out.println("Bike started");
    }
}

public class SealedVehicleDemo {
    public static void main(String[] args) {
        Vehicle car = new Car();
        Vehicle bike = new Bike();

        car.start();
        bike.start();
    }
}
