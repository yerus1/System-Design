package States;

import Models.Product;
import Models.Rack;
import Services.VendingMachine;

public class DispenseState implements State {
    @Override
    public void insertMoney(VendingMachine vendingMachine, Double amount) {
        System.out.println("[DispenseState] Can't insert money while dispensing");
    }

    @Override
    public void selectProduct(VendingMachine vendingMachine, Integer rackNumber) {
        System.out.println("[DispenseState] Can't select product while dispensing");
    }

    public void dipense(VendingMachine vendingMachine, Rack rack) {
        Product product = rack.getProduct();
        System.out.println("[DispenseState] Dispensing: " + product.getProductName());
        rack.dispenseOne();
        Double paid = vendingMachine.getCurrentAmount();
        Double change = paid - product.getProductPrice();
        if (change > 0) {
            System.out.println("[DispenseState] Change: Rs " + change);
        }
    }
}
