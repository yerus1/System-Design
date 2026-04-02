package WithoutFactoryPattern;

interface Food {
    String prepare();
}

class Pizza implements Food {
    public String prepare() {
        return "Preparing Pizza";
    }
}

class Burger implements Food {
    public String prepare() {
        return "Preparing Burger";
    }
}

public class FoodApp {
    public static void main(String[] args) {
        String order = "Burger";
        Food food;
        if (order.equals("Burger")) {
            food = new Burger();
        } else if (order.equals("Pizza")) {
            food = new Pizza();
        } else {
            throw new IllegalArgumentException("We don't serve that!");
        }
        System.out.println(food.prepare());
    }
}
