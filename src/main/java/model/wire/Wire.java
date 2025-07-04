package model.wire;

import manager.LevelManager;
import manager.WireManager;
import model.port.Port;
import model.port.PortType;
import model.port.TrianglePort;

public class Wire {
    public WireType type;
    public Port sPort;
    public Port ePort;
    public double startX;
    public double startY;
    public double endX;
    public double endY;
    public boolean avaible = true;
    public double length;
    public int curved = 0;

    public Wire(Port sPort , Port ePort)throws Exception{
        this.sPort = sPort;
        this.ePort = ePort;
        if(checkPorts(sPort,ePort)){



            this.startX = sPort.centerX();
            this.startY = sPort.centerY();

            this.endX = ePort.centerX();
            this.endY = ePort.centerY();

            sPort.wire = this;
            ePort.wire = this;

            this.length = WireManager.lengthcounter(this);
            if(this.length >  LevelManager.lvl.wireLength){
                sPort.wire = null;
                ePort.wire = null;
                throw new Exception("not enougth length");
            }

            LevelManager.lvl.wireLength= (LevelManager.lvl.wireLength - this.length);
        }else{
            this.ePort=null;
            this.sPort =null;

            throw new Exception("wires ports type mismatch");

        }
        if(sPort instanceof TrianglePort){
            this.type = WireType.TRIANGLE;
        }else {
            this.type = WireType.SQUARE;
        }
    }

    public static boolean checkPorts(Port p1, Port p2){

        boolean a = p1.getClass().getSimpleName().equals(p2.getClass().getSimpleName());
        boolean b = !p1.computer.equals(p2.computer);
        boolean c =  p1.portType.equals(PortType.OUTPUT);
        boolean d = p2.portType.equals(PortType.INPUT);

        return a && b && c && d;

    }
}
