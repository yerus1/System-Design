package BillPugh;

public class Singleton {
    private Singleton(){
        System.out.println("Singleton instance created!");
    }

    private static class SingletonHelper{
        private static final Singleton instance=new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHelper.instance;
    }
}

class Main{
    public static void main(String[] args) {
        Singleton id1=Singleton.getInstance();
        Singleton id2=Singleton.getInstance();
        System.out.println(id1==id2);

    }
}
