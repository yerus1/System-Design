package model;

import enums.VehicleSize;

public abstract class Vehicle {
    private String vehicleNumber;
    private VehicleSize vehicleSize;

    public Vehicle(String vehicleNumber, VehicleSize vehicleSize) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleSize = vehicleSize;
    }

    public String getVehicleNumber(){
        return vehicleNumber;
    }

    public VehicleSize getVehicleSize(){
        return vehicleSize;
    }
}
