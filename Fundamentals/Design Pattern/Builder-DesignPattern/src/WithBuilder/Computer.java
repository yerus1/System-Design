package WithBuilder;

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
        return "Computer [HDD=" + HDD +
                ", RAM=" + RAM +
                ", GraphicsCardEnabled=" + graphicsCard +
                ", BluetoothEnabled=" + bluetooth + "]";
    }

    public static class ComputerBuilder {
        private final String HDD;
        private final String RAM;
        private String graphicsCard;
        private String bluetooth;

        public ComputerBuilder(String HDD, String RAM) {
            this.HDD = HDD;
            this.RAM = RAM;
        }

        public ComputerBuilder enablegraphicsCard(String graphicsCard){
            this.graphicsCard=graphicsCard;
            return this;
        }

        public  ComputerBuilder enableBluetooth(String bluetooth){
            this.bluetooth=bluetooth;
            return this;
        }

        public Computer build(){
            return new Computer(HDD,RAM,graphicsCard,bluetooth);
        }
    }
}

class main{
    public static void main(String[] args) {
        Computer gaming=new Computer.ComputerBuilder("1 TB", "16 GB")
                .enableBluetooth("Sony")
                .enablegraphicsCard("ROG")
                .build();
        System.out.println(gaming);
        Computer Entry=new Computer.ComputerBuilder("1 TB", "16 GB")
                .enablegraphicsCard("ROG")
                .build();
        System.out.println(Entry);
        Computer office=new Computer.ComputerBuilder("1 TB", "16 GB")
                .build();
        System.out.println(office);


    }
}
