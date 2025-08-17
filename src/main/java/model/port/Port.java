package model.port;


import model.Type;
import model.wire.Wire;
import model.computer.Computer;

import java.io.Serializable;

public abstract class Port implements Serializable {
    public static final double SIZE = 20;
    public transient Computer computer;
    public final String computerID;
    public final String portID;
    public PortType portType;
    protected Type type;
    public double x;
    public double y;
    public int portNum;
    public Wire wire;

    public Type getType() {
        return type;
    }

    public Port(PortType portType, Computer computer, int portNum , String portID) {
        this.portID = portID;
        this.portType = portType;
        this.computer = computer;
        this.portNum = portNum;
        computerID = computer.getId();
        computer.ports.add(this);

    }






}

