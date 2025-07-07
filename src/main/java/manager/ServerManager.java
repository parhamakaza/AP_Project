package manager;

import controller.PacketContoller;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.*;
import model.computer.Server;
import model.computer.Transformer;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;
import model.port.Port;
import model.port.PortType;
import model.port.SquarePort;
import model.port.TrianglePort;

import static manager.LevelManager.lvl;
import static view.PacketView.movePacket;

public  class ServerManager {

    public static void takePacket(Packet packet){
        if(packet instanceof TrianglePacket){
            lvl.coins = lvl.coins + 2;
        }

        if(packet instanceof SquarePacket){
            lvl.coins= lvl.coins + 1;
        }

        lvl.packets.remove(packet);
    }


    public  static KeyFrame makePacket2(Server server) {
       return new KeyFrame(Duration.seconds(1  / LevelManager.gameSpeed), e -> {

           for (Port port : server.ports) {
               if (port.wire.avaible && port.portType == PortType.OUTPUT) {

                   if (port instanceof SquarePort ) {
                       SquarePacket sq = new SquarePacket(port);
                       PacketContoller.makePacket(sq);
                       movePacket(sq);
                       lvl.generatedPackets++;
                   }
                   if (port instanceof TrianglePort) {
                       TrianglePacket tri = new TrianglePacket(port);
                       PacketContoller.makePacket(tri);
                       movePacket(tri);
                       lvl.generatedPackets++;
                   }
               }
           }
       });

    }



}
