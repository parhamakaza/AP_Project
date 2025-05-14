package controler;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.*;
import view.Menu;
import view.Setting;

import java.text.NumberFormat;
import java.util.*;

public class LevelsController {

    public static  boolean airyaman = false;
    public static  boolean atar = false;
    public static  boolean anahita = false;

    public static AudioClip connectionPlayer = mediaPlayerMaker("/resources/connection.mp3") ;
    public static AudioClip collisionPlayer = mediaPlayerMaker("/resources/packet_damage.mp3") ;


    public static Level lvl;
    public static boolean paused;
    static Set<String> currentCollisions = new HashSet<>();


    public static AudioClip mediaPlayerMaker(String s){
        String musicUrl = Setting.class
                .getResource(s)
                .toExternalForm();
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
        LevelsController.paused = true;
        for (Packet i : lvl.packets){
            i.timeline.pause();

        }
        try {
            lvl.comps.getFirst().tl.pause();

        }catch (Exception e){

        }

    }

    public static void resumelvl(Level lvl){
        LevelsController.paused = false;
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



    public static void lvlIsOver(boolean a){
        Label label = new Label();
        LevelsController.pauseLvl(LevelsController.lvl);
        Stage lvlOverStage = new Stage();
        lvlOverStage.initModality(Modality.APPLICATION_MODAL); ;// blocks input to other windows
        lvlOverStage.setWidth(800);
        lvlOverStage.setHeight(500);
        lvlOverStage.setResizable(false);
        Button menuButton = Buttons.makeButton("Menu",200,100,500,400);
        if(a){

            label.setText("Level Accomplished");
            label.setTextFill(Color.DARKCYAN);                  // Text color
            label.setFont(Font.font("Arial", 24));              // Font and size
            label.setStyle("-fx-background-color: cyan;" +      // Background
                    "-fx-padding: 10px 20px;" +          // Padding
                    "-fx-background-radius: 12;" +       // Rounded corners
                    "-fx-border-radius: 12;" +
                    "-fx-border-color: darkcyan;" +      // Border
                    "-fx-border-width: 2px;" +
                    "-fx-font-weight: bold;");

        }else{
            label.setText("Game Over");
            label.setTextFill(Color.RED);                      // Text color
            label.setFont(Font.font("Impact", 48));           // Bold dramatic font
            label.setStyle("-fx-background-color: black;" +   // Background
                    "-fx-padding: 20px;" +              // Padding
                    "-fx-background-radius: 15;" +
                    "-fx-border-radius: 15;" +
                    "-fx-border-color: darkred;" +
                    "-fx-border-width: 3px;");

        }
        label.setLayoutX(300);
        label.setLayoutY(100);

        Pane root = new Pane(menuButton , label);
        root.setStyle("-fx-background-color: #0d1b2a;");
        Scene scene = new Scene(root);
        Buttons.styler1(menuButton);
        lvlOverStage.setScene(scene);

        Platform.runLater(() -> lvlOverStage.showAndWait());

        menuButton.setOnAction(e -> {
            lvlOverStage.close();
            Menu.menuConfig();
        });

        lvlOverStage.setOnCloseRequest(e ->{
            lvlOverStage.close();
            Menu.menuConfig();
        });


    }




    public static void checkForCollison(){
        Timeline tl = new Timeline();
        ArrayList<Packet> packets = LevelsController.lvl.packets;
        KeyFrame kf = new KeyFrame((Duration.millis(1)),event -> {
            if (!paused){
                checkPacketLost();


            if (!airyaman) {
                int n = packets.size();
                if (n > 1) {
                    for (int i = 0; i < n; i++) {
                        for (int j = i + 1; j < n; j++) {
                            Packet p1 = packets.getFirst();
                            Packet p2 = packets.getLast();

                            try {
                                p1 = packets.get(i);
                                p2 = packets.get(j);
                            } catch (Exception e) {

                            }

                            String id1 = String.valueOf(p1.id);
                            String id2 = String.valueOf(p2.id);
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
                                    if(!atar){
                                        explosion(collisionX, collisionY, p1, p2);
                                    }
                                }
                            } else {
                                currentCollisions.remove(key);
                            }
                        }
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

    public static void  explosion(double explosionX , double explosionY , Packet p1, Packet p2) {
        double explosionRadius = 200;
        for(Packet i : lvl.packets) {
            if (i != p1 && i != p2) {
                double packetCenterX = -50;
                double packetCenterY = -50;
                if (i instanceof SquarePacket) {
                    Rectangle square = (Rectangle) i.shape;
                    packetCenterX = square.getX() + square.getWidth() / 2;
                    packetCenterY = square.getY() + square.getHeight() / 2;

                }
                if (i instanceof TrianglePacket) {
                    // + 10 is cus the tri angle height and width
                    packetCenterX = i.shape.getLayoutX() + 10;
                    packetCenterY = i.shape.getLayoutY() + 10;

                }
                double dxExplosion = packetCenterX - explosionX;
                double dyExplosion = packetCenterY - explosionY;
                double distanceToExplosion = Math.sqrt(dxExplosion * dxExplosion + dyExplosion * dyExplosion);


                if (distanceToExplosion <= explosionRadius) {
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
    public static boolean compIsReady(Computer comp){
        for(Port i : comp.ports){
            if (i.wire == null){
                return false;
            }
        }
       return true;


    }

    public static void startTimer() {
        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.seconds(1), e -> {
            if(!paused){
                lvl.time++;
                lvl.currentTimeLabel.setText("Time: " + lvl.time + "s");
                if (lvl.time >= 20) {
                    timeline.stop();
                    lvlIsOver(true);
                }
            }
        });
        timeline.getKeyFrames().add(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public static void checkPacketLost(){
       double packetloss =(double) lvl.lostPackets/ lvl.generatedPackets;
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
       percentFormat.setMaximumFractionDigits(1);
        lvl.packetLossLabel.setText("Packet loss :" + percentFormat.format(packetloss));
        if(packetloss > 0.5){
            pauseLvl(lvl);
            lvlIsOver(false);
        }

    }


}
