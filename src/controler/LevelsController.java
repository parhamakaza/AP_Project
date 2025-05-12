package controler;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import model.*;
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
                        Packet p1 = packets.get(i);
                        Packet p2 = packets.get(j);

                        String id1 = p1.toString();
                        String id2 = p2.toString();
                        String key = (id1.compareTo(id2) < 0) ? id1 + "-" + id2 : id2 + "-" + id1;

                        boolean isColliding = false;
                        try {
                            Shape intersect = Shape.intersect(p1.shape, p2.shape);
                            isColliding = !intersect.getBoundsInLocal().isEmpty();
                        } catch (Exception e) {
                            e.printStackTrace(); // Always log exceptions in dev
                        }

                        if (isColliding) {
                            if (!currentCollisions.contains(key)) {
                                currentCollisions.add(key);

                                Shape intersect = Shape.intersect(p1.shape, p2.shape);
                                Bounds collisionBounds = intersect.getBoundsInLocal();
                                double collisionX = collisionBounds.getMinX() + collisionBounds.getWidth() / 2;
                                double collisionY = collisionBounds.getMinY() + collisionBounds.getHeight() / 2;

                                collison(p1, p2);
                                explosion(collisionX, collisionY);
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
        System.out.println( packet1.health + " - " +  packet2.health );
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

    public static  void  explosion(double explosionX , double explosionY){
        double explosionRadius = 500;
        for(Packet i : lvl.packets){
            double packetCenterX = -50;
            double packetCenterY = -50;
            if( i instanceof SquarePacket){
                Rectangle square = (Rectangle) i.shape;
                packetCenterX =  square.getX()+ square.getWidth()/2;
                packetCenterY =  square.getY() + square.getHeight() /2;



            }
            if( i instanceof TrianglePacket){
                // + 10 is cus the tri angle height and width
                packetCenterX =  i.shape.getLayoutX() + 10;
                packetCenterY =  i.shape.getLayoutY() + 10;

            }
            double dxExplosion = packetCenterX - explosionX;
            double dyExplosion = packetCenterY - explosionY;
            double distanceToExplosion = Math.sqrt(dxExplosion * dxExplosion + dyExplosion * dyExplosion);


            if (distanceToExplosion <= explosionRadius){
                double deflectX = dxExplosion / distanceToExplosion;
                double deflectY = dyExplosion / distanceToExplosion;
                double blendFactor = 0.25;
                i.unitX[0] = (1 - blendFactor) * i.unitX[0] + blendFactor * deflectX;
                i.unitY[0] = (1 - blendFactor) * i.unitY[0] + blendFactor * deflectY;

                double magnitude = Math.sqrt(i.unitX[0] * i.unitX[0] + i.unitY[0] * i.unitY[0]);
                i.unitX[0] /= magnitude;
                i.unitY[0] /= magnitude;



            }





        }





    }





}
