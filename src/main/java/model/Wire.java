package model;

import controller.LevelsController;
import controller.WireController;

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

            this.length = WireController.lengthcounter(this);
            if(this.length >  LevelsController.lvl.wireLength){
                sPort.wire = null;
                ePort.wire = null;
                throw new Exception("not enougth length");
            }

            LevelsController.lvl.wireLength= (LevelsController.lvl.wireLength - this.length);
        }else{
            this.ePort=null;
            this.sPort =null;

            throw new Exception("wires ports type mismatch");

        }
        if(sPort.getClass().getSimpleName().equals("TrianglePort")){
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
