package model;

import javafx.scene.shape.Line;

import java.lang.System;

public class Wire {
    public WireType type;
    public Port sPort;
    public Port ePort;
    public double startX;
    public double startY;
    public double endX;
    public double endY;
    public boolean avaible = true;
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


        }else{
            this.ePort=null;
            this.sPort=null;

            throw new Exception("wires ports type mismatch");

        }
        if(sPort.getClass().getSimpleName().equals("TrianglePort")){
            this.type = WireType.TRIANGLE;
        }else {
            this.type = WireType.SQUARE;
        }
    }

    private boolean checkPorts(Port p1, Port p2){

        boolean a = sPort.getClass().getSimpleName().equals(ePort.getClass().getSimpleName());
        boolean b = !sPort.system.equals(ePort.system);
        boolean c = sPort.portType.equals(PortType.OUTPUT);
        boolean d = ePort.portType.equals(PortType.INPUT);

        return a && b && c && d;

    }

}
