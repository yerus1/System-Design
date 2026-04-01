package EnumSingleton;

public enum Singleton {
    INSTANCE;
    public void message(){
        System.out.println("Singleton using ENUM!");
    }
}

class Main{
    public static void main(String[] args) {
        Singleton.INSTANCE.message();
    }
}
