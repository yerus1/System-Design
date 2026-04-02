package InterfaceSegregation;

interface Workers {
    void work();
    void eat();
}

class Humans implements Workers {
    public void work() {
        System.out.println("Human Working...");
    }
    public void eat() {
        System.out.println("Humans Eating...");
    }
}

class Robots implements Workers {
    @Override
    public void work() {
        System.out.println("Robot Working...");
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robots do not eat!");
    }
}
public class ISWithViolation {
    public static void main(String[] args) {
        Humans humans=new Humans();
        Robots robots=new Robots();

        humans.work();
        robots.work();

        humans.eat();
        robots.eat();
    }
}
