package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class TrianglePacket extends Packet {
    public Wire wire;
    public Port sPort;
    public Port ePort;
    double x;
    double y;
    public Computer comp;
    public TrianglePacket(Computer computer){
        this.wire = wire;
        this.sPort= wire.sPort;
        this.ePort = wire.ePort;
    }

    public TrianglePacket (Port sPort){
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
        System.out.println("this is s Port: " + this.sPort);
        System.out.println("this is sending computer" + this.sPort.system);
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
        root.getChildren().add(triangle);

        final double totalTime = 2000; // milliseconds
        final double[] elapsed = {0};

        Timeline timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(20), event -> {

            double t = elapsed[0] / totalTime;
            if (t > 1) t = 1;

            // Linear interpolation between P1 and P2
            double x = x1 + t * (x2 - x1);
            double y = y1 + t * (y2 - y1);


            triangle.setLayoutX(x);
            triangle.setLayoutY(y);

            if (t >= 1) {

                timeline.stop();
                root.getChildren().remove(triangle);

                try{
                    ((Gsystem)this.ePort.system).transferPacket(this);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }

            elapsed[0] += 16;
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();





    }


}
