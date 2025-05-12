package controler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.*;

public  class ServerControler {
    public static void takePacket(Server server, Packet packet , Level lvl){
        if(packet instanceof TrianglePacket){
            lvl.coins.set(lvl.coins.get() + 2);
        }

        if(packet instanceof SquarePacket){
            lvl.coins.set(lvl.coins.get() + 1);
        }
        lvl.packets.remove(packet);
        packet = null;
    }
    public synchronized static void makePacket1(Server server) {
        int n = server.ports.size();
        final int[] i = {0};
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            Port port = server.ports.get(i[0]);
            if(port instanceof SquarePort && port.wire.avaible == true){
                SquarePacket sq = new SquarePacket(port , LevelsController.lvl.root);
                sq.movePacket(LevelsController.lvl.root);
                LevelsController.lvl.generatedPackets++;
            }
            if(port instanceof TrianglePort && port.wire.avaible == true){
                TrianglePacket tri = new TrianglePacket(port , LevelsController.lvl.root);
                tri.movePacket(LevelsController.lvl.root);
                LevelsController.lvl.generatedPackets++;
            }

            i[0]++;
            i[0] = i[0] % n;

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
        server.tl = timeline;

    }
    public synchronized static void makePacket2(Server server) {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            for(Port port : server.ports) {
                if (port instanceof SquarePort && port.wire.avaible == true) {
                    SquarePacket sq = new SquarePacket(port, LevelsController.lvl.root);
                    sq.movePacket(LevelsController.lvl.root);
                    LevelsController.lvl.generatedPackets++;
                }
                if (port instanceof TrianglePort && port.wire.avaible == true) {
                    TrianglePacket tri = new TrianglePacket(port, LevelsController.lvl.root);
                    tri.movePacket(LevelsController.lvl.root);
                    LevelsController.lvl.generatedPackets++;
                }
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
        server.tl = timeline;

    }


}
