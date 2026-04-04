package Services;

import Models.Inventory;
import Models.Product;
import Models.Rack;
import States.DispenseState;
import States.IdleState;
import States.MoneyInsertedState;
import States.State;

public class VendingMachine {
    private final Integer RACK_SLOT_COUNT = 3;
    private final Integer MAX_UNITS_PER_RAC = 20;

    private static volatile VendingMachine instance;
    private static final Object lock=new Object();

    private Double currentAmount;
    private Inventory inventory;
    private IdleState idleState;
    private MoneyInsertedState moneyInsertedState;
    private DispenseState dispenseState;

    private State currentState;

    private VendingMachine() {
        currentAmount = 0.0;
        inventory = new Inventory(RACK_SLOT_COUNT, MAX_UNITS_PER_RAC);
        idleState = new IdleState();
        moneyInsertedState = new MoneyInsertedState();
        dispenseState = new DispenseState();
        currentState = idleState;
    }

    public static VendingMachine getInstance(){
        if (instance==null){
            synchronized (lock){
                if(instance==null){
                    instance=new VendingMachine();
                }
            }
        }
        return instance;
    }

    public void insertMoney(Double amount){
        idleState.insertMoney(this,amount);
    }

    public void selectProduct(Integer rackNumber){
        moneyInsertedState.selectProduct(this,rackNumber);
    }

    public void setState(State state) {
        currentState = state;
    }

    public void loadProduct(Integer rackNumber, Product product, Integer quantity) {
        if (product == null || quantity == null || quantity < 0) {
            System.out.println("Can't load product: need a product and a non-negative quantity.");
            return;
        }

        Rack rack = inventory.getRack(rackNumber);
        if (rack == null) {
            System.out.println("Can't load product. Rack no " + rackNumber + " doesn't exist.");
            return;
        }

        if (quantity > rack.getMaxCapacity()) {
            System.out.println("Can't load product: quantity " + quantity + " exceeds rack "
                    + rackNumber + " max (" + rack.getMaxCapacity() + ").");
            return;
        }

        rack.loadProduct(product, quantity);
    }

    public void reloadProduct(Integer rackNumber, Integer quantity) {
        if (quantity == null || quantity < 0) {
            System.out.println("Can't load product: need a product and a non-negative quantity.");
            return;
        }

        Rack rack = inventory.getRack(rackNumber);
        if (rack == null) {
            System.out.println("Can't load product. Rack no " + rackNumber + " doesn't exist.");
            return;
        }

        if (!rack.reStock(quantity)) {
            System.out.println("Can't restock rack " + rackNumber + ": would exceed max ("
                    + rack.getMaxCapacity() + ").");
            return;
        }

        System.out.println("Restock successful for rack " + rackNumber + " with quantity : " + quantity);
    }
    public void addAmount(Double amount) {
        currentAmount += amount;
    }

    public void refund() {
        System.out.println("Refunding amount: Rs " + currentAmount);
        reset();
    }

    public void reset() {
        setState(idleState);
        currentAmount = 0.0;
    }

    public void beginDispense(Rack rack) {
        dispenseState.dipense(this, rack);
        reset();
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public MoneyInsertedState getMoneyInsertedState() {
        return moneyInsertedState;
    }

    public DispenseState getDispenseState() {
        return dispenseState;
    }

    public void displayProducts () {
        System.out.println("=== Inventory Status ===");
        for (int rackNum = 1; rackNum <= inventory.rackCount() ; rackNum++) {
            Rack rack=inventory.getRack(rackNum);
            Product product=rack.getProduct();
            Integer quantity=rack.getQuantity();
            System.out.printf(
                    "Rack %d: %s (id=%d, type=%s) @ Rs %.2f x%d/%d%n",
                    rackNum,
                    product.getProductName(),
                    product.getProductID(),
                    product.getProductType(),
                    product.getProductPrice(),
                    quantity,
                    rack.getMaxCapacity()
            );
        }
        System.out.println("========================");
    }
}
