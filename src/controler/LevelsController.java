package controler;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import model.Level;
import model.Packet;
import model.Server;
import view.Setting;

import java.util.*;

public class LevelsController {

    public static  boolean airyaman = false;
    public static  boolean atar = false;
    public static  boolean anahita = false;

    public static AudioClip connectionPlayer = mediaPlayerMaker("/resources/connection.mp3") ;
    public static AudioClip collisionPlayer = mediaPlayerMaker("/resources/electric.mp3") ;

    public static Level lvl;
    public static boolean paused;
    static Set<String> currentCollisions = new HashSet<>();

    public static AudioClip mediaPlayerMaker(String s){
        String musicUrl = Setting.class
                .getResource(s)
                .toExternalForm();
        //Media media = new Media(musicUrl);
        AudioClip player = new AudioClip(musicUrl);
        player.setCycleCount(1);
        player.setVolume(0.5);
        return player;

    }
    public synchronized static void start(Server server){

        LevelsController.paused=false;
        ServerControler.makePacket2(server);




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

    public static void checkForCollison(){

        Timeline tl = new Timeline();
        ArrayList<Packet> packets = LevelsController.lvl.packets;
        KeyFrame kf = new KeyFrame((Duration.millis(1)),event -> {
            if(!airyaman) {

                int n = packets.size();
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {

                        boolean isColliding = false;

                        try {
                            Shape intersect = Shape.intersect(packets.get(i).shape, packets.get(j).shape);
                            isColliding = !intersect.getBoundsInLocal().isEmpty();
                        } catch (Exception e) {

                        }

                        //String key = packets.get(i) .toString()+"-"+packets.get(j).toString();

                        String key = "-";

                        if (isColliding) {
                            String id1 = packets.get(i).toString();
                            String id2 = packets.get(j).toString();


                            if (id1.compareTo(id2) < 0) {
                                key = id1 + "-" + id2;
                            } else {
                                key = id2 + "-" + id1;
                            }
                            if (!currentCollisions.contains(key)) {
                                currentCollisions.add(key);
                                collison(packets.get(i), packets.get(j));
                            }
                        } else {
                            currentCollisions.remove(key);

                        }
                    }

                }
            }


        });
        tl.getKeyFrames().add(kf);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();



    }
    public static void collison(Packet packet1 , Packet packet2){
        collisionPlayer.play();
        packet1.health = packet1.health-1;
        packet2.health = packet2.health-1;

        if(packet1.health == 0){
            killPacket(packet1);

        }
        if(packet2.health == 0){
            killPacket(packet2);

        }

    }
    public static void killPacket(Packet packet){
        lvl.lostPackets++;
        LevelsController.lvl.packets.remove(packet);
        packet.root.getChildren().remove(packet.shape);
        packet.timeline.stop();
        packet.wire.avaible = true;

    }



}
