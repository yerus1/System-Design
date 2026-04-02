package WithStatePattern;

public class CashDispensedState implements ATMState{
    @Override
    public void insertCard(ATMMachine atm) {
        System.out.println("Transaction in progress");
    }

    @Override
    public void enterPin(ATMMachine atm, int pin) {
        System.out.println("Transaction in progress");
    }

    @Override
    public void withdrawCash(ATMMachine atm, int amount) {
        System.out.println("Transaction in completed");
    }

    @Override
    public void ejectCard(ATMMachine atm) {
        System.out.println("please take your card");
        atm.setState(new IdleState());
    }
}
