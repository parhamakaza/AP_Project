package model.port;

import model.Type;
import model.computer.Computer;

public class SquarePort extends Port {
    public SquarePort(PortType portType, Computer computer, int num, String portId) {
        super(portType, computer, num, portId);
        this.type = Type.SQUARE;

    }
}
