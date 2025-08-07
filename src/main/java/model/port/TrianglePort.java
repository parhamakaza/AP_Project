package model.port;

import model.Type;
import model.computer.Computer;

public class TrianglePort extends Port {

    public TrianglePort(PortType portType, Computer computer, int num , String portId) {
        super(portType, computer, num, portId);
        this.type = Type.TRIANGLE;

    }


}
