package LiskovSubstitution;

abstract class Birds {
    abstract void makeSound();
}

interface FlgBird {
    void fly();
}

class Peacock extends Birds implements FlgBird{
    public void makeSound() {
        System.out.println("Peacock can makeSound");
    }

    public void fly() {
        System.out.println("Peacock can fly!");
    }
}

class Ostrich extends Birds{
    public void makeSound() {
        System.out.println("Ostrich can makeSound");
    }
}

public class LSWithOutViolation {
    public static void main(String[] args) {
        Birds peacock=new Peacock();
        Birds ostrich=new Ostrich();

        peacock.makeSound();
        ostrich.makeSound();

        FlgBird flgBird=new Peacock();
        flgBird.fly();
    }
}
