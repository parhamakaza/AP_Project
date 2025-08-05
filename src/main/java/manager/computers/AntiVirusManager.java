package manager.computers;

import controller.ComponentsController;
import javafx.animation.KeyFrame;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import manager.GameManager;
import model.computer.Computer;
import model.packet.Packet;

import static controller.ComponentsController.TheComponentsController;
import static model.packet.Packet.SIZE;

public class AntiVirusManager extends ComputerManager {
    private static final double RADIUS = 250;

    public AntiVirusManager(Computer computer) {
        super(computer);
        detectTrozhan();
    }


    private void detectTrozhan() {
        double centerX = computer.getCenterX();
        double centerY = computer.getCenterY();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event -> {
            for (Packet packet : GameManager.lvl.packets) {
                double packetCenterX = packet.x;
                double packetCenterY = packet.y + SIZE / 2;
                double distance = distance(centerX - packetCenterX, centerY - packetCenterY);

                if (distance <= RADIUS && packet.isTrozhan()) {
                    killTrozhan(packet);


                }

            }
        });
        timeline.getKeyFrames().add(keyFrame);

    }


    private void killTrozhan(Packet packet) {
        packet.setTrozhan(false);
        TheComponentsController.getView(packet).getShape().setFill(Color.rgb(255, 255, 0, 0.2));
        disableComputer();
    }

    private double distance(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

}
