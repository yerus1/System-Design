public class main {
    public static void main(String[] args) {
        Game gc1=new Game();
        Game gc2=new Game();
        System.out.println(gc1.hashCode());
        System.out.println(gc2.hashCode());
        System.out.println(gc1==gc2);
    }
}

class Game{
    public Game(){
        System.out.println("Hello Game!");
    }
}
