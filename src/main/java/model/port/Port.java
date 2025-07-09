package model.port;


import model.Type;
import model.wire.Wire;
import model.computer.Computer;

public abstract class Port {
    public static final double SIZE = 20;
    public Computer computer;
    public PortType portType;
    public double x;
    public double y;
    public int portNum;
    public Wire wire;
    protected Type type;

    public Type getType() {
        return type;
    }

    public Port(PortType portType, Computer computer, int portNum) {
        this.portType = portType;
        this.computer = computer;
        this.portNum = portNum;
        computer.ports.add(this);

    }

    public double centerX() {
        return (this.x);
    }

    public double centerY() {
        return (this.y) + SIZE / 2;
    }


}

