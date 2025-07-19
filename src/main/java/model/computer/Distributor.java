package model.computer;

public class Distributor extends Computer{
    public Distributor(double x, double y) {
        super(x, y);
        this.computerType = ComputerTypes.DISTRIBUTOR;
    }
}
