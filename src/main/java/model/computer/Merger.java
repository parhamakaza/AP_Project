package model.computer;

public class Merger extends Computer{
    public Merger(double x, double y, String id) {
        super(x, y,id);
        this.computerType = ComputerTypes.MERGER;

    }
}
