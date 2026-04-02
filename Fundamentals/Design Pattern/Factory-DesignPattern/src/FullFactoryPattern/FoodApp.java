package FullFactoryPattern;

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

abstract class FoodFactory {
    abstract Food createFood();
}

class PizzaFactory extends FoodFactory {
    public Food createFood() {
        return new Pizza();
    }
}

class BurgerFactory extends FoodFactory {
    public Food createFood() {
        return new Burger();
    }
}

public class FoodApp {
    public static void main(String[] args) {
        FoodFactory pizza=new PizzaFactory();
        Food food=pizza.createFood();
        System.out.println(food.Prepare());

        FoodFactory burger=new BurgerFactory();
        Food food1=burger.createFood();
        System.out.println(food1.Prepare());
    }
}
