package InterfaceSegregation;

interface Worker {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Worker, Eatable {
    public void work() {
        System.out.println("Human Working...");
    }

    public void eat() {
        System.out.println("Human Eating...");
    }
}

class Robot implements Worker {
    public void work() {
        System.out.println("Robot Working...");
    }
}

public class ISWithOutViolation {
    public static void main(String[] args) {
        Human human=new Human();
        human.work();
        human.eat();

        Robot robot=new Robot();
        robot.work();
    }
}
