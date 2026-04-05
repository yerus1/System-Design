package strategy.parking;

import enums.VehicleSize;
import model.ParkingFloor;
import model.ParkingSpot;
import model.Vehicle;

import java.util.List;

public class NearestFirstStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            for(ParkingSpot spot: floor.getSpots()){
                if(spot.canFitVehicle(vehicle)){
                    return spot;
                }
            }
        }
        return null;
    }
}
