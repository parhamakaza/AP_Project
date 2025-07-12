package model.port;

import model.Type;
import model.computer.Computer;

public class MaticPort extends Port{
    public MaticPort(PortType portType, Computer computer, int num) {
        super(portType, computer, num);
        this.type = Type.MATIC;
    }
}
