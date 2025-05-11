package model;

import controler.LevelsController;
import controler.ServerControler;
import controler.SystemController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SquarePacket extends Packet {
    public int health = 2;


    public SquarePacket (Port sPort , Pane root){
        LevelsController.lvl.packets.add(this);
        this.root = root;
        this.sPort=sPort;
        this.wire = sPort.wire;
        this.ePort = sPort.wire.ePort;
    }



    public synchronized void movePacket(Pane root){
        this.root = root;

        double x1 = this.sPort.x;
        double y1 = this.sPort.y;
        double x2 = this.ePort.x;
        double y2 = this.ePort.y;

        this.sPort.wire.avaible = false;


        Rectangle square = new Rectangle(20, 20);
        square.setFill(Color.GREEN);
        square.setX(x1);
        square.setY(y1);

        this.shape = square;

        Platform.runLater(() -> {

            root.getChildren().add(square);

        });

        // Direction vector
        double dx = x2 - x1;
        double dy = y2 - y1;
        double distance = Math.sqrt(dx * dx + dy * dy);
        // Normalize direction
        double unitX = dx / distance;
        double unitY = dy / distance;

        // Frame timing
        double frameDuration = 16; // milliseconds (~60 FPS)
        double frameDurationSeconds = frameDuration / 1000.0;

        // Distance to move each frame
        double speed = 100;
        if(sPort instanceof TrianglePort){

            speed = speed * 2;

        }
        double movePerFrame = speed * frameDurationSeconds;

        // Position tracker
        final double[] currentX = {x1};
        final double[] currentY = {y1};

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(frameDuration), event -> {
            this.sPort.wire.avaible = false;

            // Move
            currentX[0] += unitX * movePerFrame;
            currentY[0] += unitY * movePerFrame;

            // Update position
            square.setX(currentX[0]);
            square.setY(currentY[0]);
            this.x = currentX[0];
            this.y = currentY[0];


            // Check if reached or passed target
            double traveled = Math.sqrt((currentX[0] - x1) * (currentX[0] - x1) + (currentY[0] - y1) * (currentY[0] - y1));
            if (traveled >= distance) {
                // Snap to final position
                square.setX(x2);
                square.setY(y2);
                this.sPort.wire.avaible = true;

                timeline.stop();
                root.getChildren().remove(square);
                sPort.wire.avaible = true;

                try {
                    ((Gsystem) ePort.system).transferPacket(this); // Assuming `square` is the packet
                } catch (Exception e) {
                    ServerControler.takePacket((Server) ePort.system, this, LevelsController.lvl);
                }



            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        this.timeline = timeline;




    }



}
