package EfficientThreadSafeSingleton;

public class Singleton {
    private static volatile Singleton instance;
    private Singleton(){}
    public static Singleton getInstance(){
       Singleton localInstance=instance;
       if(localInstance==null){
           synchronized (Singleton.class){
               if(localInstance==null){
                   localInstance=new Singleton();
               }
               instance=localInstance;
           }
       }
       return instance;
    }
}

class Main{
    public static void main(String[] args) {
        Runnable task=()->{
            Singleton singleton=Singleton.getInstance();
            System.out.println(Thread.currentThread().getName()+"->"+singleton.hashCode());
        };
        Thread thread1=new Thread(task,"Thread1");
        Thread thread2=new Thread(task,"Thread2");

        thread1.start();
        thread2.start();
        System.out.println(thread1==thread2);
    }
}
