package WithStatePattern;

public class HasCardState implements ATMState{
    @Override
    public void insertCard(ATMMachine atm) {
        System.out.println("Card already inserted");
    }

    @Override
    public void enterPin(ATMMachine atm, int pin) {
        if (pin==1234) {
            System.out.println("PIN correct");
            atm.setState(new AuthenticatedState());
        }else{
            System.out.println("PIN incorrect");
            atm.setState(new IdleState());
        }
    }

    @Override
    public void withdrawCash(ATMMachine atm, int amount) {
        System.out.println("Enter PIN first");
    }

    @Override
    public void ejectCard(ATMMachine atm) {
        System.out.println("Card ejected");
        atm.setState(new IdleState());
    }
}
