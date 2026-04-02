package WithStatePattern;

public class Main {
    public static void main(String[] args) {
        ATMMachine atmMachine=new ATMMachine(1000);
        atmMachine.insertCard();
        atmMachine.enterPin(1234);
        atmMachine.withDraw(1001);
        atmMachine.ejectCard();
    }
}
