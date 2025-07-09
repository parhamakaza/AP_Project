package model.port;

import model.Type;
import model.computer.Computer;

public class SquarePort extends Port {
    public SquarePort(PortType portType, Computer computer, int num) {
        super(portType, computer, num);
        this.type = Type.SQUARE;

    }
}
