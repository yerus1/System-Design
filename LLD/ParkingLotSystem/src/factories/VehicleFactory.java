package factories;

import enums.VehicleSize;
import model.Bike;
import model.Car;
import model.Truck;
import model.Vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(String vehicleNumber, VehicleSize vehicleSize){
        return switch (vehicleSize){
            case SMALL -> new Bike(vehicleNumber,vehicleSize);
            case MEDIUM -> new Car(vehicleNumber,vehicleSize);
            case LARGE -> new Truck(vehicleNumber,vehicleSize);
            default -> throw new IllegalArgumentException("Unknown Vehicle: " + vehicleSize);
        };
    }
}
