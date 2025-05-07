package model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public abstract class Packet {
    public Wire wire;
    public Port sPort;
    public Port ePort;
    public Pane root;
    double x;
    double y;
    public Shape shape;

    abstract void movePacket(Pane root);

    public void sendPacket(Port sPort , Pane root){
        if(sPort.portType.equals(PortType.OUTPUT)) {
            this.sPort = sPort;
            this.wire = sPort.wire;
            this.ePort = sPort.wire.ePort;
            this.movePacket(root);
        }
    }

}
