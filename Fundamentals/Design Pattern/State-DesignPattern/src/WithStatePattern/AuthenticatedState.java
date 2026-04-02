package WithStatePattern;

public class AuthenticatedState implements ATMState{
    @Override
    public void insertCard(ATMMachine atm) {
        System.out.println("Card already inserted");
    }

    @Override
    public void enterPin(ATMMachine atm, int pin) {
        System.out.println("Already authenticated");
    }

    @Override
    public void withdrawCash(ATMMachine atm, int amount) {
        if (amount<=atm.getBalance()) {
            System.out.println("Dispensing cash: " + amount);
            atm.setBalance(atm.getBalance()-amount);
            atm.setState(new CashDispensedState());
        }else{
            System.out.println("Insufficient balance");
        }
    }

    @Override
    public void ejectCard(ATMMachine atm) {
        System.out.println("Card ejected");
        atm.setState(new IdleState());
    }
}
