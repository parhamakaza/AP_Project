package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.*;

import static view.PacketView.movePacket;

public  class ServerControler {

    public static void takePacket(Packet packet , Level lvl){
        if(packet instanceof TrianglePacket){
            lvl.coins = lvl.coins + 2;
        }

        if(packet instanceof SquarePacket){
            lvl.coins= lvl.coins + 1;
        }

        lvl.packets.remove(packet);
    }

    public  static KeyFrame makePacket1(Server server) {
        int n = server.ports.size();
        final int[] i = {0};


        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1 / LevelsController.gameSpeed), e -> {
            Port port = server.ports.get(i[0]);

            if (port instanceof SquarePort && port.wire.avaible) {
                SquarePacket sq = new SquarePacket(port);
                PacketContoller.makePacket(sq);
                movePacket(sq);
                LevelsController.lvl.generatedPackets++;
            }
            if (port instanceof TrianglePort && port.wire.avaible) {
                TrianglePacket tri = new TrianglePacket(port);
                PacketContoller.makePacket(tri);
                movePacket(tri);
                LevelsController.lvl.generatedPackets++;
            }

            i[0]++;
            i[0] = i[0] % n;
        });


        return keyFrame;


    }
    public  static KeyFrame makePacket2(Server server) {
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1  / LevelsController.gameSpeed), e -> {

            for(Port port : server.ports) {
                if (port instanceof SquarePort && port.wire.avaible ) {
                    SquarePacket sq = new SquarePacket(port);
                    PacketContoller.makePacket(sq);
                    movePacket(sq);
                    LevelsController.lvl.generatedPackets++;
                }
                if (port instanceof TrianglePort && port.wire.avaible ) {
                    TrianglePacket tri = new TrianglePacket(port);
                    PacketContoller.makePacket(tri);
                    movePacket(tri);
                    LevelsController.lvl.generatedPackets++;
                }
            }
        });
        return keyFrame;
    }



}
