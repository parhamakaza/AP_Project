package model.port;

import model.Type;
import model.computer.Computer;

public class TrianglePort extends Port {

    public TrianglePort(PortType portType, Computer computer, int num) {
        super(portType, computer, num);
        this.type = Type.TRIANGLE;

    }


}
