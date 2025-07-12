package model.computer;

public class VPN  extends Computer{
    public VPN(double x, double y) {
        super(x, y);
        this.computerType = ComputerTypes.VPN;
    }
}
