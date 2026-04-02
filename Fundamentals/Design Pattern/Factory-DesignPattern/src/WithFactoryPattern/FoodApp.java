package WithFactoryPattern;

interface Food {
    String Prepare();
}

class Pizza implements Food {
    public String Prepare() {
        return "Preparing Pizza";
    }
}

class Burger implements Food {
    public String Prepare() {
        return "Preparing Burger";
    }
}

class FoodFactory {
    public static Food getFood(String order) {
        Food food;
        if (order.equals("Pizza")) {
            food = new Pizza();
        } else if (order.equals("Burger")) {
            food = new Burger();
        } else {
            throw new IllegalArgumentException("We don't serve that!");
        }
        return food;
    }
}

public class FoodApp {
    public static void main(String[] args) {
        Food food = FoodFactory.getFood("Pizza");
        System.out.println(food.Prepare());
    }
}
