package model.computer;

public class VPN  extends Computer{
    public VPN(double x, double y, String id) {
        super(x, y ,id);
        this.computerType = ComputerTypes.VPN;
    }
}
