package BasicSingleton;


class Singleton{
    private Singleton(){
        System.out.println("Hello Single");
    }
    private static Singleton instance;
    public static Singleton getInstance(){
        if(instance==null){
            instance=new Singleton();
        }
        return instance;
    }
}
public class SingletonDemo {
    public static void main(String[] args) {
        Singleton id1= Singleton.getInstance();
        Singleton id2= Singleton.getInstance();
        System.out.println(id1==id2);
    }
}
