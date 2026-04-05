package model;

import java.util.UUID;

public class ParkingTicket {
    private String ticketID;
    private Vehicle parkedVehicle;
    private ParkingSpot assignedSpot;
    private long startTimeStamp;
    private long endTimeStamp;

    public ParkingTicket(Vehicle parkedVehicle, ParkingSpot assignedSpot) {
        this.parkedVehicle = parkedVehicle;
        this.assignedSpot = assignedSpot;
        this.ticketID = UUID.randomUUID().toString();
        this.startTimeStamp = System.currentTimeMillis();
    }

    public String getTicketID() {
        return ticketID;
    }

    public ParkingSpot getAssignedSpot() {
        return assignedSpot;
    }

    public double getDurationInHours() {
        double endTime = (endTimeStamp == 0) ? System.currentTimeMillis() : endTimeStamp;
        return (double) (endTime - startTimeStamp) / (1000 * 60 * 60);
    }

    public void setEndTimeStamp() {
        endTimeStamp = System.currentTimeMillis();
    }
}
