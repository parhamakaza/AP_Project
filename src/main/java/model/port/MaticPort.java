package model.port;

import model.Type;
import model.computer.Computer;

public class MaticPort extends Port{
    public MaticPort(PortType portType, Computer computer, int num, String portId) {
        super(portType, computer, num , portId);
        this.type = Type.MATIC;
    }
}
