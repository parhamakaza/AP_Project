package model;

import controler.ServerControler;
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
        this.root = root;
        this.sPort=sPort;
        this.wire = sPort.wire;
        this.ePort = sPort.wire.ePort;
    }



    public void movePacket(Pane root){
        this.root=root;
        double x1 = this.sPort.x;
        double y1 = this.sPort.y;
        double x2 = this.ePort.x;
        double y2 = this.ePort.y;

        Polygon triangle = new Polygon();
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
        Timeline timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(20), event -> {

            double t = elapsed[0] / totalTime;
            if (t > 1) t = 1;

            // Linear interpolation between P1 and P2
            double x = x1 + t * (x2 - x1);
            double y = y1 + t * (y2 - y1);


            triangle.setTranslateX(x);
            triangle.setTranslateY(y);
            this.x=x;
            this.y=y;
            this.sPort.wire.avaible=false;
            if (t >= 1) {

                timeline.stop();
                root.getChildren().remove(triangle);
                this.sPort.wire.avaible=true;

                try{
                    ((Gsystem)this.ePort.system).transferPacket(this);
                }
                catch (Exception e){
                    ServerControler.takePacket(((Server)this.ePort.system), this);
                }

            }

            elapsed[0] += 16;
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();





    }


}
