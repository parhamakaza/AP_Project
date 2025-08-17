package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import manager.CollisonManager;
import manager.packets.AccelerationDamper;
import model.packet.Packet;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static controller.ComponentsController.TheComponentsController;
import static manager.ComponentsManager.TheComponentsManager;
import static manager.LevelManager.lvl;
import static manager.LevelManager.theLevelManager;

public class Shop implements Serializable {
    Set<String> currentPacketInEliphas = new HashSet<>();

    public static Point2D aergiaPoint;
    public static Point2D eliphasPoint;


    private boolean atar = false;
    private double atarStartTime = -1;

    private boolean airyaman = false;
    private double airyamanStartTime = -1;

    private boolean anahita = false;
    private double anahitaStartTime = -1;

    private boolean aergia = false;
    private double aergiaStartTime = -1;
    private transient Timeline aergiaTimeLine;

    private boolean sisyphus = false;
    private double sisyphusStartTime = -1;

    private boolean eliphas = false;
    private double eliphasStartTime = -1;
    private transient Timeline eliphasTimeLine;


    public void atar() {
        atar = true;
        atarStartTime = lvl.getTime();
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), e -> {
            if (lvl.getTime() - atarStartTime >= 10) {
                atar = false;
            }
            timeline.stop();
        });
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);

    }

    public void airyaman() {
        airyaman = true;
        airyamanStartTime = lvl.getTime();
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), e -> {
            if (lvl.getTime() - airyamanStartTime >= 5) {
                airyaman = false;
                timeline.stop();
            }
        });
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }


    public void anahita() {
        for (Packet packet : lvl.packets) {
            packet.setNoise(0);
        }
    }

    public void aergia() {
        aergia = true;
        System.out.println("aergia is on");
        if(aergiaTimeLine != null){
            aergiaTimeLine.stop();
        }
            aergiaTimeLine = new Timeline();

    }

    public void doAergia(Point2D point2D) {
        aergiaPoint = point2D;
        System.out.println("aergia");
        aergia = false;
        aergiaStartTime = lvl.getTime();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(16), e -> {
            if(lvl.getTime() - aergiaStartTime >= 15){
                aergiaTimeLine.stop();
            }
            // Inside your KeyFrame's lambda
            for (Packet packet : lvl.packets) {
                Shape packetShape = TheComponentsController.getView(packet).getShape();

                // Convert the mouse click point into the packet's own coordinate system
                Point2D localPoint = packetShape.sceneToLocal(point2D);

                // Now, use the converted point for the containment check
                if (packetShape.contains(localPoint)) {
                    try {
                        AccelerationDamper accelerationDamper = (AccelerationDamper) TheComponentsManager.getManager(packet);
                        accelerationDamper.turnAccelerationOff();

                    } catch (ClassCastException exception) {

                    }
                }

            }
        });
        aergiaTimeLine.getKeyFrames().add(keyFrame);
        aergiaTimeLine.setCycleCount(Animation.INDEFINITE);
        aergiaTimeLine.play();


    }

    public void sisyphus() {
        sisyphus = true;

    }

    public void eliphas() {
        eliphas = true;
        if(eliphasTimeLine != null){
            eliphasTimeLine.stop();
        }
            eliphasTimeLine = new Timeline();

    }


        public void doEliphas(Point2D point2D) {
            eliphasPoint = point2D;
            aergia = false;
            eliphasStartTime = lvl.getTime();
            KeyFrame keyFrame = new KeyFrame(Duration.millis(16), e -> {
                if(lvl.getTime() - eliphasStartTime >= 20){
                    eliphasTimeLine.stop();
                }
                for (Packet packet : lvl.packets) {
                    Shape packetShape = TheComponentsController.getView(packet).getShape();
                    Point2D localPoint = packetShape.sceneToLocal(eliphasPoint);
                    boolean isInside = packetShape.contains(localPoint);
                    String key = String.valueOf(packet.id);

                    // ✅ CORRECTED LOGIC:
                    // Check if the packet is inside AND we haven't already started an animation for it.
                    if (isInside && !currentPacketInEliphas.contains(key)) {
                        // Only start the animation if there is a deflection to correct.
                        if (packet.deflectedX != 0 || packet.deflectedY != 0) {
                            // 1. Add the packet's key to the set to mark it as "being processed".
                            currentPacketInEliphas.add(key);

                            // 2. Start the animation ONCE.
                            CollisonManager.smoothDeflecting(-packet.deflectedX, -packet.deflectedY, packet);
                        }
                    }
                    // If the mouse is NO LONGER over the packet, remove it from the set
                    // so it can be selected again later.
                    else if (!isInside) {
                        currentPacketInEliphas.remove(key);
                    }
                }
            });
            eliphasTimeLine.getKeyFrames().add(keyFrame);
            eliphasTimeLine.setCycleCount(Animation.INDEFINITE);
            eliphasTimeLine.play();
        }


    private void makeDeflectZero(Packet packet){
        packet.deflectedX = 0;
        packet.deflectedY = 0;
    }



    public void setAtar(boolean atar) {
        this.atar = atar;
    }

    public void setAiryaman(boolean airyaman) {
        this.airyaman = airyaman;
    }

    public void setAnahita(boolean anahita) {
        this.anahita = anahita;
    }

    public void setAergia(boolean aergia) {
        this.aergia = aergia;
    }

    public void setSisyphus(boolean sisyphus) {
        this.sisyphus = sisyphus;
    }

    public void setEliphas(boolean eliphas) {
        this.eliphas = eliphas;
    }


    public boolean isAtar() {
        return atar;
    }

    public boolean isAiryaman() {
        return airyaman;
    }

    public boolean isAnahita() {
        return anahita;
    }

    public boolean isAergia() {
        return aergia;
    }

    public boolean isSisyphus() {
        return sisyphus;
    }

    public boolean isEliphas() {
        return eliphas;
    }

}
