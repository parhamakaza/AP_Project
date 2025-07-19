package model.wire;

import manager.GameManager;
import manager.WireManager;
import model.Type;
import model.port.*;

public class Wire {
    public Type type;
    public Port sPort;
    public Port ePort;
    public double startX;
    public double startY;
    public double endX;
    public double endY;
    public boolean avaible = true;
    public double length;

    public Wire(Port sPort, Port ePort) throws Exception {
        if (checkPorts(sPort, ePort)) {
            this.sPort = sPort;
            this.ePort = ePort;


            this.startX = sPort.centerX();
            this.startY = sPort.centerY();
            this.endX = ePort.centerX();
            this.endY = ePort.centerY();


            this.length = WireManager.lengthcounter(this);
            if (this.length > GameManager.lvl.wireLength) {
                throw new Exception("not enougth length");
            }

            sPort.wire = this;
            ePort.wire = this;

            GameManager.lvl.wireLength = (GameManager.lvl.wireLength - this.length);
        } else {
            throw new Exception("wires ports type mismatch");

        }
        this.type = sPort.getType();
    }

    public static boolean checkPorts(Port p1, Port p2) {
        boolean a = p1.getClass() == p2.getClass();
        boolean b = !p1.computer.equals(p2.computer);
        boolean c = p1.portType.equals(PortType.OUTPUT);
        boolean d = p2.portType.equals(PortType.INPUT);

        return a && b && c && d;

    }
}
