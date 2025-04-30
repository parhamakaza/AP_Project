package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SquarePacket extends Packet {



    public SquarePacket(Wire wire){
        this.wire = wire;
        this.sPort= wire.sPort;
        this.ePort = wire.ePort;

    }

    public SquarePacket (Port sPort){
        this.sPort=sPort;
        this.wire = sPort.wire;
        this.ePort = sPort.wire.ePort;

    }
    public void sendPacket(Port sPort , Pane root){
        if(sPort.portType.equals(PortType.OUTPUT)) {
            this.sPort = sPort;
            this.wire = sPort.wire;
            this.ePort = sPort.wire.ePort;
            this.movePacket(root);
        }
    }

    public void movePacket(Pane root){

        double x1 = this.sPort.x;
        double y1 = this.sPort.y;
        double x2 = this.ePort.x;
        double y2 = this.ePort.y;



        Rectangle square = new Rectangle(20, 20);
        square.setFill(Color.GREEN);
        square.setX(x1);
        square.setY(y1);
        root.getChildren().add(square);
        final double totalTime = 2000; // 2 seconds
        final double[] elapsed = {0};
        Timeline timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(20), event -> {
            double t = elapsed[0] / totalTime;
            if (t > 1) t = 1;

            double x = x1 + t * (x2 - x1);
            double y = y1 + t * (y2 - y1);

            square.setX(x);
            square.setY(y);

            if (t >= 1) {
                timeline.stop();
                root.getChildren().remove(square);
                try{
                    ((Gsystem)this.ePort.system).transferPacket(this);
                }
                catch (Exception e){

                }


            }

            elapsed[0] += 16;
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


    }



}
