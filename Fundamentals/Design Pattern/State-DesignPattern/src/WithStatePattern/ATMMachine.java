package WithStatePattern;

public class ATMMachine {
    private int balance;
    private ATMState currentState;

    public ATMMachine(int balance) {
        this.balance = balance;
        this.currentState = new IdleState();
    }

    public void setState(ATMState state) {
        this.currentState = state;
    }

    public int getBalance(){
        return balance;
    }

    public void setBalance(int balance){
        this.balance=balance;
    }

    public void insertCard(){
        currentState.insertCard(this);
    }

    public void enterPin(int pin){
        currentState.enterPin(this,pin);
    }

    public void withDraw(int amount){
        currentState.withdrawCash(this,amount);
    }

    public void ejectCard(){
        currentState.ejectCard(this);
    }
}
