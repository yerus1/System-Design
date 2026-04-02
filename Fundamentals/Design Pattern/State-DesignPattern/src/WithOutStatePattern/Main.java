package WithOutStatePattern;

public class Main {
    public static void main(String[] args) {
        ATMMachine atmMachine=new ATMMachine(1000);
        atmMachine.insertCard();
        atmMachine.enterPin(1234);
        atmMachine.withdrawCash(1000);
        atmMachine.ejectCard();
    }
}
