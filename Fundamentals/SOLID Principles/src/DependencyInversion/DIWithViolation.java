package DependencyInversion;

class WiredKeyboards{
    public void connect(){
        System.out.println("Wired Keyboard connected");
    }
}

class Computers {
    private WiredKeyboards keyboards=new WiredKeyboards();
    public void start(){
        keyboards.connect();
    }
}
public class DIWithViolation {
    public static void main(String[] args) {
        Computers computer=new Computers();
        computer.start();
    }
}
