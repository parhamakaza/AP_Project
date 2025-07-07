package view;

import controller.*;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import manager.PacketAnimatorManager;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;
import model.port.Port;
import model.port.PortType;
import service.SceneManager;

import java.util.HashSet;
import java.util.Set;

import static controller.PacketContoller.packetMap;

public class PacketView implements Drawable{
    public static  final double FRAME_DURATION = 16;
    public static Set<Timeline> packetTimelines = new HashSet<>();
    private Packet packet;
    private Shape shape;

    public Timeline getTimeline() {
        return timeline;
    }

    public static Set<Timeline> getPacketTimelines() {
        return packetTimelines;
    }

    Timeline timeline = new Timeline();



    public static  void sendPacket(Port sPort , Packet packet){
        System.out.println("salammmm");
        if(sPort.portType.equals(PortType.OUTPUT)) {
            packet.sPort = sPort;
            packet.wire = sPort.wire;
            packet.ePort = sPort.wire.ePort;
            PacketAnimatorManager packetAnimatorManager = new PacketAnimatorManager(packet,WireController.wireMap.get(sPort.wire).getCurves(),100);
            packetAnimatorManager.start();
        }
    }

    public Shape getShape() {
        return shape;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    @Override
    public Shape draw(){
        Packet packet1 = this.packet;
        if(packet1 instanceof SquarePacket){
            this.shape = Drawable.createRectangleAsPolygon();
        }else if(packet1 instanceof TrianglePacket){
            this.shape = Drawable.createTriangleAsPolygon();
        }
        shape.setLayoutX(packet1.x);
        shape.setLayoutY(packet1.y);
        ((Pane)SceneManager.getPrimaryStage().getScene().getRoot()).getChildren().add(shape);
        return shape;
    }

    public PacketView(Packet packet){
        this.packet = packet;
        this.draw();
    }




    public static void movePacket(Packet packet){
        PacketAnimatorManager packetAnimatorManager = new PacketAnimatorManager(packet,WireController.wireMap.get(packet.sPort.wire).getCurves(),100);
        packetAnimatorManager.start();
    }

}
