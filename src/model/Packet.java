package model;

import controler.LevelsController;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public abstract class Packet {
    public static int theID = 0;
    public int id;
    public Wire wire;
    public Port sPort;
    public Port ePort;
    public Pane root;
    double x;
    double y;
    public Shape shape;
    public Timeline timeline;
    public Timeline timeline2;

    public int health;
    public double uniitX;
    public double uniitY;
    public double[] unitX =  {0};
    public double[] unitY =  {0};


    abstract void movePacket(Pane root);

    public void sendPacket(Port sPort , Pane root){
        if(sPort.portType.equals(PortType.OUTPUT)) {
            this.sPort = sPort;
            this.wire = sPort.wire;
            this.ePort = sPort.wire.ePort;
            this.movePacket(root);
        }
    }
    public void resetPacketmovment(){
        if(this.timeline != null){
            this.timeline = null;
            movePacket(LevelsController.lvl.root);
        }

    }

}
