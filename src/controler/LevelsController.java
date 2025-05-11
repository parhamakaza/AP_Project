package controler;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.CycleMethod;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import model.Gsystem;
import model.Level;
import model.Packet;
import model.Server;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LevelsController {
    public static Level lvl;
    public static boolean paused;
    public synchronized static void start(Server server){

        LevelsController.paused=false;
        ServerControler.makePacket(server);




    }
    public static void pauseLvl(Level lvl){
        for (Packet i : lvl.packets){
            i.timeline.pause();

        }
        try {
            lvl.comps.getFirst().tl.pause();

        }catch (Exception e){

        }




    }

    public static void resumelvl(Level lvl){
        for (Packet i : lvl.packets){
            i.timeline.play();
        }
        try {
            lvl.comps.getFirst().tl.play();

        }catch (Exception e){

        }




    }

    public static void recivelvl(Level lvl){
        LevelsController.lvl = lvl;
    }

    public static void checkColiison(){
        Timeline tl = new Timeline();
        ArrayList<Packet> packets = LevelsController.lvl.packets;
        KeyFrame kf = new KeyFrame((Duration.millis(1)),event -> {


        int n = packets.size();



        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                Shape intersect = Shape.intersect(packets.get(i).shape, packets.get(j).shape);
                LevelsController.lvl.root.getChildren().add(intersect);
                boolean isColliding = !intersect.getBoundsInLocal().isEmpty();

                if(isColliding){
                    System.out.println(isColliding);
                }




            }
        }


        });
        tl.getKeyFrames().add(kf);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();



    }



}
