package WithBuilderAndDirector;

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

    @Override
    public String toString() {
        return "Computer [CPU=" + HDD + ", RAM=" + RAM +
                ", GraphicsCardEnabled=" + graphicsCard +
                ", BluetoothEnabled=" + bluetooth + "]";
    }
}

interface ComputerBuilder {
    ComputerBuilder enableGraphicsCard(String graphicsCard);

    ComputerBuilder enableBluetooth(String bluetooth);

    Computer build();
}

class GamingComputerBuilder implements ComputerBuilder {
    private final String HDD;
    private final String RAM;
    private String graphicsCard;
    private String bluetooth;

    public GamingComputerBuilder(String HDD, String RAM) {
        this.HDD = HDD;
        this.RAM = RAM;
    }

    @Override
    public ComputerBuilder enableGraphicsCard(String graphicsCard) {
        this.graphicsCard=graphicsCard;
        return this;
    }

    @Override
    public ComputerBuilder enableBluetooth(String bluetooth) {
        this.bluetooth=bluetooth;
        return this;
    }

    @Override
    public Computer build() {
        return new Computer(HDD,RAM,graphicsCard,bluetooth);
    }
}

class OfficeComputerBuilder implements ComputerBuilder{

    private final String HDD;
    private final String RAM;
    private String graphicsCard;
    private String bluetooth;

    public OfficeComputerBuilder(String HDD, String RAM) {
        this.HDD = HDD;
        this.RAM = RAM;
    }

    @Override
    public ComputerBuilder enableGraphicsCard(String graphicsCard) {
        this.graphicsCard=graphicsCard;
        return this;
    }

    @Override
    public ComputerBuilder enableBluetooth(String bluetooth) {
        this.bluetooth=bluetooth;
        return this;
    }

    @Override
    public Computer build() {
        return new Computer(HDD,RAM,graphicsCard,bluetooth);
    }
}

class ComputerDirector {
    public Computer constructGamingComputer(){
        return new GamingComputerBuilder("1 TB","64 GB")
                .enableBluetooth("Sony")
                .enableGraphicsCard("ROG")
                .build();
    }

    public Computer constructOfficeComputer(){
        return new OfficeComputerBuilder("500 GB","16 GB")
                .enableBluetooth("Sony")
                .build();
    }
}

class Main{
    public static void main(String[] args) {
        ComputerDirector director=new ComputerDirector();
        Computer gaming=director.constructGamingComputer();
        System.out.println(gaming);

        Computer office=director.constructOfficeComputer();
        System.out.println(office);
    }
}