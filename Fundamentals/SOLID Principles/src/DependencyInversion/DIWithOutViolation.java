package DependencyInversion;

interface Keyboard {
    void connect();
}

class WiredKeyBoard implements Keyboard {
    public void connect() {
        System.out.println("Wired Keyboard connected");
    }
}

class WirelessKeyboard implements Keyboard {
    public void connect() {
        System.out.println("Wireless Keyboard connected");
    }
}

class Computer {
    private Keyboard keyboard;

    public Computer(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void start() {
        keyboard.connect();
    }
}

public class DIWithOutViolation {
    public static void main(String[] args) {
        Keyboard wired = new WiredKeyBoard();
        Computer computer = new Computer(wired);
        computer.start();
        Keyboard wireless = new WirelessKeyboard();
        Computer computer1 = new Computer(wireless);
        computer1.start();
    }
}
