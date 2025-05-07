package model;

import controler.LevelsController;
import controler.ServerControler;
import controler.SystemController;
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
        this.root = root;
        this.sPort=sPort;
        this.wire = sPort.wire;
        this.ePort = sPort.wire.ePort;
    }

    public void sendPacket(Port sPort , Pane root){
        this.root = root;
        if(sPort.portType.equals(PortType.OUTPUT)) {
            this.sPort = sPort;
            this.wire = sPort.wire;
            this.ePort = sPort.wire.ePort;
            this.movePacket(root);
        }
    }

    public void movePacket(Pane root){
        this.root = root;

        double x1 = this.sPort.x;
        double y1 = this.sPort.y;
        double x2 = this.ePort.x;
        double y2 = this.ePort.y;
        this.sPort.wire.avaible=false;


        Rectangle square = new Rectangle(20, 20);
        this.shape = square;
        square.setFill(Color.GREEN);
        square.setX(x1);
        square.setY(y1);
        Platform.runLater(() -> {
            root.getChildren().add(square);
        });
        final double totalTime = 2000; // 2 seconds
        final double[] elapsed = {0};
        Timeline timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(20), event -> {
            if(LevelsController.paused == false) {
                double t = elapsed[0] / totalTime;
                if (t > 1) t = 1;

                double x = x1 + t * (x2 - x1);
                double y = y1 + t * (y2 - y1);

                square.setX(x);
                square.setY(y);
                this.x = x;
                this.y = y;


                if (t >= 1) {
                    timeline.stop();
                    root.getChildren().remove(square);
                    this.sPort.wire.avaible = true;

                    try {
                        ((Gsystem) this.ePort.system).transferPacket(this);
                    } catch (Exception e) {
                        ServerControler.takePacket(((Server) this.ePort.system), this, LevelsController.lvl);

                    }


                }

                elapsed[0] += 16;
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


    }



}
