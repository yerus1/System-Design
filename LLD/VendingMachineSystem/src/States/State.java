package States;

import Services.VendingMachine;

public interface State {
    void insertMoney(VendingMachine vendingMachine,Double amount);
    void selectProduct(VendingMachine vendingMachine,Integer rackNumber);
}
