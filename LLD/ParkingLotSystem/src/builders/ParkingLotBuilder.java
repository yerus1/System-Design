package builders;

import enums.VehicleSize;
import model.ParkingFloor;
import service.ParkingLotSystem;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotBuilder {
    private ParkingFloor currentFloor;
    private List<ParkingFloor> floors=new ArrayList<>();

    public ParkingLotBuilder addFloor(int floorNumber){
        currentFloor=new ParkingFloor(floorNumber);
        floors.add(currentFloor);
        return this;
    }

    public ParkingLotBuilder addSpot(String spotID, VehicleSize vehicleSize){
        if(currentFloor==null) throw new IllegalStateException("Call addFloor() before addSpot()");
        currentFloor.addSpot(spotID,vehicleSize);
        return this;
    }

    public List<ParkingFloor> build(){
        return new ArrayList<>(floors);
    }

    public void buildAndAddTo(ParkingLotSystem system) {
        build().forEach(system::addFloor);
    }
}
