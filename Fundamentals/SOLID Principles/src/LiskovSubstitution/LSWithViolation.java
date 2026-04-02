package LiskovSubstitution;

class Bird {
    public void fly() {
        System.out.println("Flying!");
    }
}

class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow can fly!");
    }
}

class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguin can't fly");
    }
}

public class LSWithViolation {
    public static void main(String[] args) {
        Bird sparrow=new Sparrow();
        Bird penguin=new Penguin();

        sparrow.fly();
        penguin.fly();
    }

}
