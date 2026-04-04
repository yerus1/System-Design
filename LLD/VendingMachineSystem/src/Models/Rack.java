package Models;

public class Rack {
    private final Integer rackNumber;
    private final Integer maxCapacity;
    private Product product;
    private Integer quantity;

    public Rack(Integer rackNumber, Integer maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("MaxCapacity must be greater than 0");
        }
        this.rackNumber = rackNumber;
        this.maxCapacity = maxCapacity;
        this.quantity = 0;
    }

    public void loadProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public boolean reStock(Integer restockQuantity) {
        if (restockQuantity + quantity > maxCapacity) {
            return false;
        }
        this.quantity = quantity + restockQuantity;
        return true;
    }

    public void dispenseOne() {
        if (quantity != null || quantity > 0) {
            quantity--;
        }
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getRackNumber() {
        return rackNumber;
    }
}
