package ThreadSafeSingleton;

public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

class Main{
    public static void main(String[] args) {
        Runnable task=()->{
            Singleton singleton=Singleton.getInstance();
            System.out.println(Thread.currentThread().getName()+" -> "+singleton.hashCode());
        };

        Thread thread1=new Thread(task,"Thread1");
        Thread thread2=new Thread(task,"Thread2");

        thread1.start();
        thread2.start();
    }
}
