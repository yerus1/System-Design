import Enums.ProductType;
import Models.Product;
import Services.VendingMachine;

public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine=VendingMachine.getInstance();

        Product biscuits=new Product(1,"Good-Day",10.0, ProductType.BISCUITS);
        Product chocolate=new Product(2,"Dairy-Milk",100.0,ProductType.CHOCOLATE);
        Product chips=new Product(3,"Lays",10.0,ProductType.CHIPS);

        vendingMachine.loadProduct(1,biscuits,3);
        vendingMachine.loadProduct(2,chocolate,2);
        vendingMachine.loadProduct(3,chips,2);

        vendingMachine.displayProducts();

        System.out.println("****************************************");
        // Customer: Exact Payment (Equal to product price)
        vendingMachine.insertMoney(5.0);
        vendingMachine.selectProduct(3);
        System.out.println("****************************************");

        System.out.println("****************************************");
        // Customer: Depletes rack & retry (Until No quantity left)
        vendingMachine.insertMoney(10.0);
        vendingMachine.selectProduct(1);
        vendingMachine.insertMoney(20.0);
        vendingMachine.selectProduct(1);
        vendingMachine.insertMoney(20.0);
        vendingMachine.selectProduct(1);
        vendingMachine.insertMoney(10.0);
        vendingMachine.selectProduct(1);
        System.out.println("****************************************");

        // Operator: Restock (Increase the quantity of the product in the rack)
        vendingMachine.reloadProduct(1, 10);

        System.out.println("****************************************");
        // Customer: Tries after restock (After restock, the quantity of the product in the rack is increased)
        vendingMachine.insertMoney(10.0);
        vendingMachine.selectProduct(1);
        System.out.println("****************************************");
    }
}
