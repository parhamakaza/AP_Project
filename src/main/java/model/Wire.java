package model;

import controller.LevelsController;
import javafx.scene.shape.Line;

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
    public Line line;

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

            this.length = lengthcounter(this);
            if(this.length >  LevelsController.lvl.wireLength.get()){
                sPort.wire = null;
                ePort.wire = null;
                throw new Exception("not enougth length");
            }

            LevelsController.lvl.wireLength.set(LevelsController.lvl.wireLength.get() - this.length);
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
    public double lengthcounter(Wire wire){
        double x1 = wire.startX;
        double y1 = wire.startY;
        double x2 = wire.endX;
        double y2 = wire.endY;
        double l = Math.abs(x1 - x2);
        double h = Math.abs(y2 - y1);

        return Math.sqrt((l * l) + (h * h));


    }

    private static boolean checkPorts(Port p1, Port p2){

        boolean a = p1.getClass().getSimpleName().equals(p2.getClass().getSimpleName());
        boolean b = !p1.system.equals(p2.system);
        boolean c =  p1.portType.equals(PortType.OUTPUT);
        boolean d = p2.portType.equals(PortType.INPUT);

        return a && b && c && d;

    }

}
