package model;

import controler.LevelsController;
import controler.ServerControler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.sql.SQLOutput;

public class TrianglePacket extends Packet {
    public int health = 3;
    public TrianglePacket(Computer computer){
        this.wire = wire;
        this.sPort= wire.sPort;
        this.ePort = wire.ePort;
    }

    public TrianglePacket (Port sPort , Pane root){
        LevelsController.lvl.packets.add(this);
        this.root = root;
        this.sPort=sPort;
        this.wire = sPort.wire;
        this.ePort = sPort.wire.ePort;
    }



    public synchronized void movePacket(Pane root){
        this.root=root;
        double x1 = this.sPort.x;
        double y1 = this.sPort.y;
        double x2 = this.ePort.x;
        double y2 = this.ePort.y;

        Polygon triangle = new Polygon();
        this.shape = triangle;
        triangle.getPoints().addAll(
                0.0, 0.0,    // Peak at (0,0)
                10.0,20.0,// Bottom right point (slanted)
                -10.0,20.0       // Bottom left point
        );
        triangle.setFill(Color.YELLOW);

        final double totalTime = 2000; // milliseconds
        final double[] elapsed = {0};

        Platform.runLater(() -> {
            root.getChildren().add(triangle);
        });


        // Direction vector
        double dx = x2 - x1;
        double dy = y2 - y1;
        double distance = Math.sqrt(dx * dx + dy * dy);
        this.sPort.wire.avaible = false;

        // Normalize direction
        double unitX = dx / distance;
        double unitY = dy / distance;

        // Frame timing
        double frameDuration = 16; // milliseconds (~60 FPS)
        double frameDurationSeconds = frameDuration / 1000.0;

        // Distance to move each frame
        final double[] speed = {90.0};


        // Position tracker
        final double[] currentX = {x1};
        final double[] currentY = {y1};

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(frameDuration), event -> {
            this.sPort.wire.avaible = false;

            double movePerFrame = speed[0] * frameDurationSeconds;
            // Move
            currentX[0] += unitX * movePerFrame;
            currentY[0] += unitY * movePerFrame;
            if(sPort instanceof SquarePort){
                speed[0] = speed[0] + 5;

            }
            // Update position
            triangle.setLayoutX(currentX[0]);
            triangle.setLayoutY(currentY[0]);
            this.x = currentX[0];
            this.y = currentY[0];


            // Check if reached or passed target
            double traveled = Math.sqrt((currentX[0] - x1) * (currentX[0] - x1) + (currentY[0] - y1) * (currentY[0] - y1));
            if (traveled >= distance) {
                // Snap to final position
                triangle.setLayoutX(x2);
                triangle.setLayoutY(y2);
                this.sPort.wire.avaible = true;



                timeline.stop();
                root.getChildren().remove(triangle);
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
