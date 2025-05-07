package controler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.Gsystem;
import model.Level;
import model.Packet;
import model.Server;

import java.util.Timer;
import java.util.TimerTask;

public class LevelsController {
    public static Level lvl;
    public static boolean paused;
    public static void start(Server server){


        LevelsController.paused=false;

        /*Timeline tl = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(3000), event -> {
            ServerControler.makePacket(server);
        });

        tl.getKeyFrames().add(keyFrame);
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

        server.tl = tl;*/

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ServerControler.makePacket(server);
            }
        }, 0, 3000);

    }
    public static void pauseLvl(Level lvl){
        for (Packet i : lvl.packets){
            i.timeline.stop();
        }
    }
    public static void resumelvl(Level lvl){
        for (Packet i : lvl.packets){
            i.timeline.play();
        }
    }

    public static void recivelvl(Level lvl){
        LevelsController.lvl = lvl;
    }



}
