package strategy.payment;

import model.ParkingTicket;

public class CashPaymentStrategy implements PaymentStrategy{
    @Override
    public boolean pay(double fee, ParkingTicket ticket) {
        System.out.println("Paid ₹" + fee + " for ticket " + ticket.getTicketID() + " via Cash.");
        return true;
    }
}
