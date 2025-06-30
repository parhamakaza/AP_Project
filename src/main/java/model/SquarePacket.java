package model;

import controller.LevelsController;
import controller.ServerControler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class SquarePacket extends Packet {
    private Rectangle square;
    private double[] currentX;
    private double[] currentY;
    private double x1, y1, x2, y2, distance;
    private double frameDuration = 16;




    public SquarePacket (Port sPort , Pane root){
        theID++;
        this.id= theID;
        LevelsController.lvl.packets.add(this);
        this.root = root;
        this.sPort=sPort;
        this.wire = sPort.wire;
        this.ePort = sPort.wire.ePort;
        this.health = 2;
    }

    public void startTimeline2(){
        if (timeline2 != null) {
            timeline2.stop();
        }

        double intervalMillis = 500 / LevelsController.gameSpeed;  // Adjust with game speed

        timeline2 = new Timeline();
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(intervalMillis), e -> {
            this.unitX[0] = uniitX;
            this.unitY[0] = uniitY;
        });

        timeline2.getKeyFrames().add(keyFrame2);
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();
    }


    public synchronized void movePacket(Pane root) {
        this.root = root;

        // Initialize start and end positions
        x1 = this.sPort.x;
        y1 = this.sPort.y;
        x2 = this.ePort.x;
        y2 = this.ePort.y;

        this.sPort.wire.avaible = false;

        square = new Rectangle(20, 20);
        square.setFill(Color.GREEN);
        square.setX(x1);
        square.setY(y1);
        this.shape = square;
        if (root == null) {
            System.err.println("Root is null!");
        } else {
            Platform.runLater(() -> root.getChildren().add(square));
        }

        // Vector setup
        double dx = x2 - x1;
        double dy = y2 - y1;
        distance = Math.sqrt(dx * dx + dy * dy);

        double unitX = dx / distance;
        this.uniitX = unitX;
        this.unitX[0] = unitX;
        double unitY = dy / distance;
        this.uniitY = unitY;
        this.unitY[0] = unitY;


        currentX = new double[]{x1};
        currentY = new double[]{y1};

        startTimeline2();
        buildAndStartTimeline();  // <--- extract logic to a new method
    }

    public void buildAndStartTimeline() {
        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(frameDuration) , event -> {
            if (!LevelsController.paused) {
                sPort.wire.avaible = false;

                // Calculate speed with multiplier
                double currentSpeed = 100;
                if (sPort instanceof TrianglePort) {
                    currentSpeed *= 2;
                }
                double movePerFrame = currentSpeed * (frameDuration / 1000.0)* LevelsController.gameSpeed ;

                // Move
                currentX[0] += unitX[0] * movePerFrame;
                currentY[0] += unitY[0] * movePerFrame;

                square.setX(currentX[0]);
                square.setY(currentY[0]);
                this.x = currentX[0];
                this.y = currentY[0];

                double traveled = Math.sqrt((currentX[0] - x1) * (currentX[0] - x1) + (currentY[0] - y1) * (currentY[0] - y1));
                Shape intersection = Shape.intersect(square, sPort.wire.line);

                if (traveled >= distance) {
                    square.setX(x2);
                    square.setY(y2);
                    sPort.wire.avaible = true;
                    timeline.stop();
                    root.getChildren().remove(square);
                    timeline2.stop();




                    try {
                        ((Gsystem) this.ePort.system).packets.add(this);
                    } catch (ClassCastException e) {
                        ServerControler.takePacket((Server) this.ePort.system, this, LevelsController.lvl);
                    }
                    return;
                }

                if (!(intersection.getBoundsInLocal().getWidth() > 0 && intersection.getBoundsInLocal().getHeight() > 0)) {
                    System.out.println("kill squarePacket");
                    LevelsController.killPacket(this);
                }
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }






}
