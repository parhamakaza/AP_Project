package manager;

import controller.ComponentsController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import manager.packets.PacketManager;
import model.Level;
import model.Type;
import model.packet.Packet;
import service.AudioManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static controller.ComponentsController.TheComponentsController;
import static controller.PacketContoller.killPacket;
import static manager.LevelManager.theLevelManager;
import static manager.ShopManager.*;

public class CollisonManager {
    private static final double EXPLOSION_RADIUS = 100;
    private static final double EXPLOSION_FORCE = 10;
    Set<String> currentCollisions = new HashSet<>();
    private Timeline timeline = new Timeline();

    public  CollisonManager(Level level) {

        timeline.setCycleCount(Animation.INDEFINITE);
        List<Packet> packets = level.packets;
        KeyFrame keyFrame = new KeyFrame((Duration.millis(16)), event -> {

            if (!airyaman && !theLevelManager.paused) {

                // 1. Create a copy of the list to iterate over
                ArrayList<Packet> packetsThisFrame = new ArrayList<>(packets);
                int n = packetsThisFrame.size();

                    for (int i = 0; i < n; i++) {
                        for (int j = i + 1; j < n; j++) {

                            // 2. Get packets from the copy
                            Packet p1 = packetsThisFrame.get(i);
                            Packet p2 = packetsThisFrame.get(j);
                            if(p1.insideSystem || p2.insideSystem){
                                continue;
                            }
                            if(!(packets.contains(p1) && packets.contains(p2))){
                                continue;
                            }


                            String id1 = String.valueOf(p1.id);
                            String id2 = String.valueOf(p2.id);
                            String key = (id1.compareTo(id2) < 0) ? id1 + "-" + id2 : id2 + "-" + id1;

                            boolean isColliding = false;
                            try {


                                Shape intersect = Shape.intersect(TheComponentsController.packetViewMap.get(p1).getShape(), TheComponentsController.packetViewMap.get(p2).getShape());
                                isColliding = !intersect.getBoundsInLocal().isEmpty();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (isColliding ) {
                                if (!currentCollisions.contains(key)) {
                                   currentCollisions.add(key);

                                    Shape intersect = Shape.intersect(TheComponentsController.packetViewMap.get(p1).getShape(), TheComponentsController.packetViewMap.get(p2).getShape());
                                    Bounds collisionBounds = intersect.getBoundsInLocal();
                                    double collisionX = collisionBounds.getMinX() + collisionBounds.getWidth() / 2;
                                    double collisionY = collisionBounds.getMinY() + collisionBounds.getHeight() / 2;
                                    collision(p1, p2);
                                    if (!ShopManager.atar) {
                                        //explosion(collisionX, collisionY);
                                    }
                                }
                            } else {
                             currentCollisions.remove(key);
                            }
                        }
                    }
                }



        });
        timeline.getKeyFrames().add(keyFrame);
    }

    private void collision(Packet packet1, Packet packet2) {

        AudioManager.playCollison();
        if(packet1.getType() == Type.MATIC){
            PacketManager.changeDirection(packet1);
        }
        if(packet2.getType() == Type.MATIC){
            PacketManager.changeDirection(packet2);
        }

        packet1.health = packet1.health - 1;
        packet2.health = packet2.health - 1;

        if (packet1.health == 0) {
           killPacket(packet1);
        }

        if (packet2.health == 0) {
            killPacket(packet2);
        }
    }
    private void explosion(double explosionX , double explosionY){

            for(Packet packet : GameManager.lvl.packets){

                double deltaX = packet.x - explosionX;
                double deltaY = packet.y - explosionY;
                double distanceSq = (deltaX * deltaX) + (deltaY * deltaY);
                double radiusSq = EXPLOSION_RADIUS * EXPLOSION_RADIUS;
                if (distanceSq <= radiusSq) {
                    double distance = Math.sqrt(distanceSq);
                    if (distance > 0) {
                        // Normalize the direction vector (make its length 1)
                        double normalizedX = deltaX / distance;
                        double normalizedY = deltaY / distance;

                        // Calculate force falloff: 1.0 at center, 0.0 at the edge.
                        // This makes the explosion weaker the farther the packet is from the center.
                        double forceFalloff = 1.0 - (distance / EXPLOSION_RADIUS);

                        // Calculate the final deflection vector
                        double finalDeflectionX =  normalizedX * EXPLOSION_FORCE * forceFalloff;
                        double finalDeflectionY =  normalizedY * EXPLOSION_FORCE * forceFalloff;
                        smoothDeflecting(finalDeflectionX,finalDeflectionY,packet);


                    } else {

                        packet.deflectedX = 0;
                        packet.deflectedY = 1 * EXPLOSION_FORCE;
                    }


                }





            }

    }

    /*private static void smoothDeflecting(double toDeflectX, double toDeflectY , Packet packet){
        final double startX = packet.deflectedX;
        final double startY = packet.deflectedY;

        // 2. Calculate the final target values.
        final double endX = startX + toDeflectX;
        final double endY = startY + toDeflectY;
        // from 0.0 (start) to 1.0 (end) over the duration.
        DoubleProperty progress = new SimpleDoubleProperty(0.0);

        // 4. Add a listener to this progress property. This listener will be
        // called on every single frame of the animation.
        progress.addListener((observable, oldValue, newValue) -> {
            // 'newValue' is the current progress percentage (a value from 0.0 to 1.0).
            double fraction = newValue.doubleValue();

            // 5. Manually calculate the new deflected values using linear interpolation.
            // This is the core of the manual approach.
            double newDeflectedX = startX + (endX - startX) * fraction;
            double newDeflectedY = startY + (endY - startY) * fraction;

            // 6. Update the packet's primitive double fields directly.
            packet.deflectedX = newDeflectedX;
            packet.deflectedY = newDeflectedY;
        });

        // 7. Create a Timeline to drive the animation.
        Timeline timeline = new Timeline();

        // 8. Define that the 'progress' property should go to 1.0 over 500ms.
        KeyValue keyValue = new KeyValue(progress, 1.0);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        checkToKill(packet);





    }*/
    private static void smoothDeflecting(double toDeflectX, double toDeflectY , Packet packet){


        double whatToAddX = toDeflectX / 100.0;
        double whatToAddY = toDeflectY / 100.0;


        Timeline timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(1), e -> {
            packet.deflectedX += whatToAddX;
            packet.deflectedY += whatToAddY;
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(100); // Run 100 times

        // Set the onFinished handler correctly
        // This will now run only ONCE, after all 100 cycles are complete.
        timeline.setOnFinished(event -> {

            checkToKill(packet);
        });

        // Play the single timeline
        timeline.play();





    }
    public void play(){
        timeline.play();
    }
    public void pause(){
        timeline.pause();
    }
    public void stop(){
        timeline.stop();
    }
    private static EventHandler<ActionEvent> checkToKill(Packet packet){

        if(Math.abs(packet.deflectedY) >= 10 || Math.abs(packet.deflectedX) >=10){
            killPacket(packet);
        }
        return null;
    }


}
