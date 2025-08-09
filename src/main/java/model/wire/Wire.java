package model.wire;

import manager.LevelManager;
import model.Type;
import model.port.*;

import java.io.Serializable;

public class Wire implements Serializable {
    public Type type;
    public transient Port sPort;
    public transient Port ePort;
    public String sPortId;
    public String ePortId;
    public double startX;
    public double startY;
    public double endX;
    public double endY;
    public double firstControlX;
    public double firstControlY;
    public double secondControlX;
    public double secondControlY;
    public double thirdControlX;
    public double thirdControlY;

    public boolean avaible = true;
    public double length;
    private boolean curved = false;
    private int massivePassed = 0;

    public int getMassivePassed() {
        return massivePassed;
    }

    public void increaseMassivePassed(){
        massivePassed++;
    }

    public Wire(Port sPort, Port ePort) throws Exception {
        if (checkPorts(sPort, ePort)) {
            this.sPort = sPort;
            this.ePort = ePort;
            this.sPortId = sPort.portID;
            this.ePortId = ePort.portID;


            this.startX = sPort.centerX();
            this.startY = sPort.centerY();
            this.endX = ePort.centerX();
            this.endY = ePort.centerY();


            if (this.length > LevelManager.lvl.wireLength) {
                throw new Exception("not enougth length");
            }

            sPort.wire = this;
            ePort.wire = this;

            LevelManager.lvl.wireLength = (LevelManager.lvl.wireLength - this.length);
        } else {
            throw new Exception("wires ports type mismatch");

        }
        this.type = sPort.getType();
    }

    public boolean isCurved() {
        return curved;
    }

    public static boolean checkPorts(Port p1, Port p2) {
        if(!(p1.wire == null && p2.wire == null)){
            return false;
        }
        boolean a = p1.getClass() == p2.getClass();
        boolean b = !p1.computer.equals(p2.computer);
        boolean c = p1.portType.equals(PortType.OUTPUT);
        boolean d = p2.portType.equals(PortType.INPUT);

        return  b && c && d;

    }

    public void setCurved(){
        curved = true;
    }
}
