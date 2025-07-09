package view.packets;

import controller.*;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import manager.packets.PacketManager;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;
import model.port.Port;
import model.port.PortType;
import service.SceneManager;
import view.Drawable;

import java.util.HashSet;
import java.util.Set;

public abstract class PacketView implements Drawable {
    public static  final double FRAME_DURATION = 16;
    public static Set<Timeline> packetTimelines = new HashSet<>();
    protected Packet packet;
    protected Shape shape;


    public static Set<Timeline> getPacketTimelines() {
        return packetTimelines;
    }





    public static void sendPacket(Port sPort , Packet packet){
        sPort.wire.avaible = false;
        packet.insideSystem =false;
        if(sPort.portType.equals(PortType.OUTPUT)) {
            packet.sPort = sPort;
            packet.x = sPort.centerX();
            packet.y = sPort.centerY();
            packet.wire = sPort.wire;
            packet.ePort = sPort.wire.ePort;

            PacketManager packetManager = new PacketManager(packet,WireController.wireMap.get(sPort.wire).getCurves(),100);
            packetManager.start();
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

    public PacketView(Packet packet){
        this.packet = packet;
        this.draw();
    }




    public static void movePacket(Packet packet){
        packet.insideSystem =false;
        PacketManager packetManager = new PacketManager(packet,WireController.wireMap.get(packet.sPort.wire).getCurves(),100);
        packetManager.start();
    }

}
