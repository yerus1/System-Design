package strategy.fee;

import model.ParkingTicket;

public class FlatFeeStrategy implements FeeStrategy {
    private final double flatRatePerHour;

    public FlatFeeStrategy(double flatRatePerHour) {
        if(flatRatePerHour<0) throw new IllegalArgumentException("Rate Must be positive");
        this.flatRatePerHour = flatRatePerHour;
    }

    @Override
    public double calculateFee(ParkingTicket ticket) {
        double hour = Math.ceil(ticket.getDurationInHours());
        return flatRatePerHour*hour;
    }
}
