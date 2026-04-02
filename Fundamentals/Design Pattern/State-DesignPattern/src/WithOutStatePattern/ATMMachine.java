package WithOutStatePattern;

public class ATMMachine {

    private int balance;
    private String state;

    /// /Idle, Has card, Authenticated, Dispense

    public ATMMachine(int balance) {
        this.balance = balance;
        this.state = "IDLE";
    }

    public void insertCard() {
        if (state.equals("IDLE")) {
            System.out.println("Card inserted");
            state = "Has_card";
        } else {
            System.out.println("Card already inserted");
        }
    }

    public void enterPin(int pin) {
        if (state.equals("Has_card")) {
            if (pin == 1234) {
                System.out.println("PIN correct");
                state = "Authenticated";
            } else {
                System.out.println("PIN incorrect");
                state = "IDLE";
            }
        } else if (state.equals("IDLE")) {
            System.out.println("Insert card");
        } else {
            System.out.println("Already authenticated");
        }
    }

    public void withdrawCash(int amount) {
        if (state.equals("Authenticated")) {
            if (amount <= balance) {
                System.out.println("Dispensing cash: " + amount);
                balance -= amount;
            } else {
                System.out.println("Insufficient balance");
            }
        } else if (state.equals("Has_card")) {
            System.out.println("Enter PIN first");
        } else {
            System.out.println("Insert card first");
        }
    }

    public void ejectCard() {
        if (state.equals("Has_card") || state.equals("Authenticated")) {
            System.out.println("Card ejected");
            state = "IDLE";
        } else {
            System.out.println("No card inserted");
        }
    }
}
