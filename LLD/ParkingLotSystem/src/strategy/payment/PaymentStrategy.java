package strategy.payment;

import model.ParkingTicket;

public interface PaymentStrategy {
    boolean pay(double fee, ParkingTicket ticket);
}
