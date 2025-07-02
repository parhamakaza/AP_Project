package controller;

import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.*;
import view.PacketView;

public class SystemController {


    public static KeyFrame startPacketTransfer(Gsystem gsystem){

        KeyFrame kf =new KeyFrame(Duration.millis(100), e -> {
        transferPacket(gsystem);
        });
        return kf;

    }

    public static void transferPacket(Gsystem gsystem){
        if(!gsystem.packets.isEmpty()) {
            Packet packet = gsystem.packets.getFirst();
            if (gsystem.packets.size() > 5) {
                gsystem.packets.remove(packet);
                PacketContoller.killPacket(packet);
                return;
            }

            if (packet instanceof SquarePacket) {
                for (Port i : gsystem.ports) {
                    if ((i instanceof SquarePort) && (i.portType.equals(PortType.OUTPUT)) && (i.wire.avaible)) {
                        PacketView.sendPacket(i,packet);
                        gsystem.packets.remove(packet);
                        return;
                    }
                }
                for (Port i : gsystem.ports) {
                    if (i.portType.equals(PortType.OUTPUT) && (i.wire.avaible)) {
                        PacketView.sendPacket(i,packet);
                        gsystem.packets.remove(packet);

                        return;
                    }
                }
            }

            if (packet instanceof TrianglePacket) {
                for (Port i : gsystem.ports) {
                    if ((i instanceof TrianglePort) && i.portType.equals(PortType.OUTPUT) && (i.wire.avaible)) {
                        gsystem.packets.remove(packet);


                        PacketView.sendPacket(i, packet);
                        return;
                    }
                }

                for (Port i : gsystem.ports) {
                    if (i.portType.equals(PortType.OUTPUT) && (i.wire.avaible)) {
                        gsystem.packets.remove(packet);
                        PacketView.sendPacket(i, packet);
                        return;
                    }
                }

            }

        }

    }

}
