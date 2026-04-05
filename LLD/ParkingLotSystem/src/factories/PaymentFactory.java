package factories;

import enums.PaymentMode;
import strategy.payment.CardPaymentStrategy;
import strategy.payment.CashPaymentStrategy;
import strategy.payment.PaymentStrategy;

public class PaymentFactory {
    public static PaymentStrategy get(PaymentMode mode) {
        if (mode == null) throw new IllegalArgumentException("PaymentMode cannot be null");
        return switch (mode) {
            case CASH -> new CashPaymentStrategy();
            case CARD -> new CardPaymentStrategy();
            default -> throw new IllegalArgumentException("Unknown PaymentMode: " + mode);
        };
    }
}
