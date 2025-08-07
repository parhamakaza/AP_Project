package model.computer;

public class Distributor extends Computer{
    public Distributor(double x, double y, String id) {
        super(x, y,id);
        this.computerType = ComputerTypes.DISTRIBUTOR;
    }
}
