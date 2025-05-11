package controler;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.*;
import view.Level1;

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
    public synchronized static void makePacket(Server server) {
        int n = server.ports.size();
        final int[] i = {0};
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            Port port = server.ports.get(i[0]);
            if(port instanceof SquarePort && port.wire.avaible == true){
                SquarePacket sq = new SquarePacket(port , LevelsController.lvl.root);
                sq.movePacket(LevelsController.lvl.root);
            }
            if(port instanceof TrianglePort && port.wire.avaible == true){
                TrianglePacket tri = new TrianglePacket(port , LevelsController.lvl.root);
                tri.movePacket(LevelsController.lvl.root);
            }

            i[0]++;
            i[0] = i[0] % n;

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
        server.tl = timeline;

    }


}
