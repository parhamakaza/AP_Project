package manager;

import controller.PacketContoller;
import javafx.animation.KeyFrame;

import javafx.animation.PauseTransition;
import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import model.packet.Packet;
import service.AudioManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static manager.GameLoopManager.gameLoopManager;

public class CollisonManager {
    static Set<String> currentCollisions = new HashSet<>();


    public static void checkForCollison() {
        ArrayList<Packet> packets = LevelManager.lvl.packets;
        KeyFrame keyFrame = new KeyFrame((Duration.millis(16)), event -> {

            if (!LevelManager.airyaman && !gameLoopManager.paused) {

                // 1. Create a copy of the list to iterate over
                ArrayList<Packet> packetsThisFrame = new ArrayList<>(packets);
                int n = packetsThisFrame.size();

                if (n > 1) {
                    for (int i = 0; i < n; i++) {
                        for (int j = i + 1; j < n; j++) {

                            // 2. Get packets from the copy
                            Packet p1 = packetsThisFrame.get(i);
                            Packet p2 = packetsThisFrame.get(j);


                            String id1 = String.valueOf(p1.id);
                            String id2 = String.valueOf(p2.id);
                            String key = (id1.compareTo(id2) < 0) ? id1 + "-" + id2 : id2 + "-" + id1;

                            boolean isColliding = false;
                            try {
                                Shape intersect = Shape.intersect(PacketContoller.packetMap.get(p1).getShape(), PacketContoller.packetMap.get(p2).getShape());
                                isColliding = !intersect.getBoundsInLocal().isEmpty();
                            } catch (Exception e) {
                                e.printStackTrace(); // Always log exceptions in dev
                            }

                            if (isColliding) {
                                if (!currentCollisions.contains(key)) {
                                   currentCollisions.add(key);

                                    Shape intersect = Shape.intersect(PacketContoller.packetMap.get(p1).getShape(), PacketContoller.packetMap.get(p2).getShape());
                                    Bounds collisionBounds = intersect.getBoundsInLocal();
                                    double collisionX = collisionBounds.getMinX() + collisionBounds.getWidth() / 2;
                                    double collisionY = collisionBounds.getMinY() + collisionBounds.getHeight() / 2;
                                    collison(p1, p2);
                                    if (!LevelManager.atar) {
                                        explosion(collisionX, collisionY, p1, p2);
                                    }
                                }
                            } else {
                             currentCollisions.remove(key);
                            }
                        }
                    }
                }

            }

        });
        gameLoopManager.addKeyFrame(keyFrame);
    }

    public static void collison(Packet packet1, Packet packet2) {
        AudioManager.playCollison();

        packet1.health = packet1.health - 1;
        packet2.health = packet2.health - 1;

        if (packet1.health == 0) {
            PacketContoller.killPacket(packet1);
        }

        if (packet2.health == 0) {
            PacketContoller.killPacket(packet2);
        }

    }

    public static void explosion(double explosionX, double explosionY, Packet p1, Packet p2) {
        double explosionRadius = 200;
        for (Packet i : LevelManager.lvl.packets) {
            if (i != p1 && i != p2) {
                double packetCenterX = PacketContoller.packetMap.get(i).getShape().getLayoutX() + 10;
                double packetCenterY = PacketContoller.packetMap.get(i).getShape().getLayoutY() + 10;

                double dxExplosion = packetCenterX - explosionX;
                double dyExplosion = packetCenterY - explosionY;
                double distanceToExplosion = Math.sqrt(dxExplosion * dxExplosion + dyExplosion * dyExplosion);

                if (distanceToExplosion <= explosionRadius) {

                    final double originalUnitX = i.unitX[0];
                    final double originalUnitY = i.unitY[0];

                    // --- STEP 2: Apply the deflection (your existing code) ---
                    double deflectX = dxExplosion / distanceToExplosion;
                    double deflectY = dyExplosion / distanceToExplosion;
                    double blendFactor = 0.25;
                    i.unitX[0] = (1 - blendFactor) * originalUnitX + blendFactor * deflectX;
                    i.unitY[0] = (1 - blendFactor) * originalUnitY + blendFactor * deflectY;

                    // Normalize the new vector
                    double magnitude = Math.sqrt(i.unitX[0] * i.unitX[0] + i.unitY[0] * i.unitY[0]);
                    if (magnitude > 0) {
                        i.unitX[0] /= magnitude;
                        i.unitY[0] /= magnitude;
                    }


                    PauseTransition resetDelay = new PauseTransition(Duration.millis(300));
                    resetDelay.setOnFinished(event -> {

                        i.unitX[0] = originalUnitX;
                        i.unitY[0] = originalUnitY;
                    });
                    resetDelay.play();
                }
            }
        }
    }
}
