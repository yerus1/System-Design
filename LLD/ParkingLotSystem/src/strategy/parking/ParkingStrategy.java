package strategy.parking;

import model.ParkingFloor;
import model.ParkingSpot;
import model.Vehicle;

import java.util.List;

public interface ParkingStrategy {
    ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}
