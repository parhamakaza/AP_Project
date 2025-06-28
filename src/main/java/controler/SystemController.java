package controler;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.*;



import static model.Computer.*;

public class SystemController {

    //private final SystemManager systems = new SystemManager();
    public static void drawServers(Pane root, double x, double y,  Computer system){

        Rectangle module = new Rectangle(WIDTH , HEIGHT);
        module.setX(0);
        module.setY(0);
        module.setLayoutX(x);
        module.setLayoutY(y);
        system.x = x;
        system.y = y;
        for(Port i : system.ports){
            if(i instanceof SquarePort){
                PortsController.drawSquarePort( (SquarePort) i , root);
            }else if(i instanceof TrianglePort){
                PortsController.drawTrianglePort((TrianglePort) i , root);
            }

        }

        module.setArcWidth(10);   // rounded corners
        module.setArcHeight(10);

        module.setFill(Color.web("#6e6e6e"));   // mid‑gray fill
        module.setStroke(Color.web("#4a4a4a")); // slightly darker border
        module.setStrokeWidth(2);

        // optional: subtle glow/drop‑shadow
        system.shape=module;
        DropShadow glow = new DropShadow(10, Color.web("#00ffff"));
        glow.setSpread(0.2);
        module.setEffect(glow);

        root.getChildren().add(module);
        module.toBack();


    }
    public static void startPacketTransfer(Gsystem gsystem){
        Timeline tl = new Timeline();
        KeyFrame kf =new KeyFrame(Duration.millis(100), e -> {
        transferPacket(gsystem);
        });
        tl.getKeyFrames().add(kf);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
        gsystem.tl =tl;
    }

    public static void transferPacket(Gsystem gsystem){
        if(!gsystem.packets.isEmpty()) {
            Packet packet = gsystem.packets.getFirst();
            if (gsystem.packets.size() > 5) {
                gsystem.packets.remove(packet);
                LevelsController.killPacket(packet);
                return;
            }

            if (packet instanceof SquarePacket) {
                for (Port i : gsystem.ports) {
                    if ((i instanceof SquarePort) && (i.portType.equals(PortType.OUTPUT)) && (i.wire.avaible)) {
                        packet.sendPacket(i, gsystem.root);
                        gsystem.packets.remove(packet);
                        return;
                    }
                }
                for (Port i : gsystem.ports) {
                    if (i.portType.equals(PortType.OUTPUT) && (i.wire.avaible)) {
                        packet.sendPacket(i, gsystem.root);
                        gsystem.packets.remove(packet);

                        return;
                    }
                }
            }

            if (packet instanceof TrianglePacket) {
                for (Port i : gsystem.ports) {
                    if ((i instanceof TrianglePort) && i.portType.equals(PortType.OUTPUT) && (i.wire.avaible)) {
                        gsystem.packets.remove(packet);


                        packet.sendPacket(i, gsystem.root);
                        return;
                    }
                }

                for (Port i : gsystem.ports) {
                    if (i.portType.equals(PortType.OUTPUT) && (i.wire.avaible)) {
                        gsystem.packets.remove(packet);
                        packet.sendPacket(i, gsystem.root);
                        return;
                    }
                }

            }

        }

    }

}
