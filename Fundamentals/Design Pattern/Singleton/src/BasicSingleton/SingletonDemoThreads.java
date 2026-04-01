package BasicSingleton;

public class SingletonDemoThreads {
    public static void main(String[] args) {
        Runnable task=()->{
            Singleton singleton=Singleton.getInstance();
            System.out.println(Thread.currentThread().getName()+"->"+singleton.hashCode());
        };
        Thread thread1=new Thread(task,"thread-1");
        Thread thread2=new Thread(task,"thread-2");

        thread1.start();
        thread2.start();
    }
}
