package service;

import model.ParkingTicket;
import strategy.payment.PaymentStrategy;

public class PaymentService {
    private PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean pay(double fee, ParkingTicket ticket){
        boolean success=strategy.pay(fee, ticket);
        if(!success) System.out.println("Payment failed for ticket: " + ticket.getTicketID());
        return true;
    }
}
