package WithoutBuilder;

public class Computer {
    private final String HDD;
    private final String RAM;
    private final String graphicsCard;
    private final String bluetooth;

    public Computer(String HDD, String RAM, String graphicsCard, String bluetooth) {
        this.HDD = HDD;
        this.RAM = RAM;
        this.graphicsCard = graphicsCard;
        this.bluetooth = bluetooth;
    }

    public Computer(String HDD, String RAM) {
        this.HDD = HDD;
        this.RAM = RAM;
        this.graphicsCard = null;
        this.bluetooth = null;
    }

    @Override
    public String toString() {
        return "Computer [HDD=" + HDD +
                ", RAM=" + RAM +
                ", GraphicsCardEnabled=" + graphicsCard +
                ", BluetoothEnabled=" + bluetooth + "]";
    }
}

class Main {
    public static void main(String[] args) {
        Computer computer = new Computer("1 TB", "16 GB", "ROG", "SONY");
        System.out.println(computer);
        Computer computer2 = new Computer("1 TB", "16 GB", null, null);

        Computer computer3 = new Computer("1 TB", "16 GB");
        System.out.println(computer2);
        System.out.println(computer3);
    }
}
