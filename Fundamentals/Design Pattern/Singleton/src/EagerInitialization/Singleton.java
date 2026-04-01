package EagerInitialization;

public class Singleton {
    private static final Singleton instance=new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){
        return instance;
    }
}

class Main{
    public static void main(String[] args) {
        Singleton id1=Singleton.getInstance();
        Singleton id2=Singleton.getInstance();
        System.out.println(id1==id2);
    }
}
