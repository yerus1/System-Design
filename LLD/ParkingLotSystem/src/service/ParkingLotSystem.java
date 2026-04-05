package service;

import builders.ParkingLotBuilder;
import enums.PaymentMode;
import factories.PaymentFactory;
import model.ParkingFloor;
import model.ParkingSpot;
import model.ParkingTicket;
import model.Vehicle;
import strategy.fee.FeeStrategy;
import strategy.fee.FlatFeeStrategy;
import strategy.parking.NearestFirstStrategy;
import strategy.parking.ParkingStrategy;
import strategy.payment.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLotSystem {
    private static volatile ParkingLotSystem instance;
    private static final Object lock = new Object();
    private ConcurrentHashMap<String, ParkingTicket> activeTickets;
    private List<ParkingFloor> floors;
    private FeeStrategy feeStrategy;
    private ParkingStrategy parkingStrategy;

    public ParkingLotSystem() {
        activeTickets = new ConcurrentHashMap<>();
        floors = new ArrayList<>();
        feeStrategy = new FlatFeeStrategy(5.0);
        parkingStrategy = new NearestFirstStrategy();
    }

    public static ParkingLotSystem getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ParkingLotSystem();
                }
            }
        }
        return instance;
    }

    public ParkingLotBuilder createBuilder() {
        return new ParkingLotBuilder();
    }

    public void addFloor(ParkingFloor floor) {
        if (floor == null) throw new IllegalArgumentException("floor cannot be null");
        floors.add(floor);
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        int maxAttempts = floors.stream().mapToInt(f -> f.getSpots().size()).sum();
        for (int i = 0; i < maxAttempts; i++) {

            ParkingSpot spot = parkingStrategy.findSpot(floors, vehicle);
            if (spot == null) {
                System.out.println("No parking spot available to park vehicle " + vehicle.getVehicleNumber());
                break;
            }

            ParkingTicket ticket = spot.parkVehicle(vehicle);
            if (ticket == null) {
                System.out.println(vehicle.getVehicleNumber() + " -> Spot " + spot.getSpotID() + " taken by another vehicle, retrying...");
                continue;
            }

            activeTickets.put(ticket.getTicketID(), ticket);
            System.out.println("Vehicle " + vehicle.getVehicleNumber() + " parked. Ticket: " + ticket.getTicketID());
            return ticket;

        }
        return null;
    }

    public void unParkVehicle(String ticketID, PaymentMode mode) {
        ParkingTicket ticket = activeTickets.get(ticketID);
        if (ticket == null) throw new IllegalArgumentException("Invalid Ticket -> " + ticketID);

        ticket.setEndTimeStamp();

        double fee = feeStrategy.calculateFee(ticket);

        PaymentStrategy paymentStrategy = PaymentFactory.get(mode);
        PaymentService service = new PaymentService(paymentStrategy);
        boolean success = service.pay(fee, ticket);
        if (!success) throw new IllegalArgumentException("Payment Failed Exception ->" + ticketID);

        activeTickets.remove(ticketID);
        ticket.getAssignedSpot().unParkVehicle();
        System.out.println("Vehicle exited. Fee charged: ₹" + fee);
    }

    public void printFloors() {
        System.out.println("***********************************************************************************");
        for (ParkingFloor floor : floors) {
            System.out.println("Floor: " + floor.getFloorNumber());
            for(ParkingSpot spot:floor.getSpots()){
                System.out.println(spot.getSpotID() + " [" + spot.getSpotSize() + "] - " + (spot.isOccupied() ? "Occupied":"Free"));

            }
        }
        System.out.println("***********************************************************************************");
    }


}
