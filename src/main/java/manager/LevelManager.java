package manager;

import controller.ComponentsController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import manager.computers.ComputerManager;
import manager.packets.PacketManager;
import model.Level;
import model.wire.Wire;
import saveAndLoad.Save;
import view.WireView;

import static manager.ComponentsManager.TheComponentsManager;

public class LevelManager {
    public static LevelManager theLevelManager;
    public static Level lvl;

    private Level level;
    private ComponentsController componentsController;
    private ComponentsManager componentsManager;
    private CollisonManager collisonManager;
    private Timeline timeline = new Timeline();

    public boolean paused;

    public LevelManager(Level level){
        theLevelManager = this;
        this.level = level;
        this.componentsController = new ComponentsController();
        this.componentsManager = new ComponentsManager();
        this.collisonManager = new CollisonManager(level);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            level.increaseTime();
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void pauseAndResume(boolean b){
        paused = b;
        if(!b){
            start();
        }else {
            pause();
        }
    }



    public  void start() {
        for (ComputerManager computer : componentsManager.computerManagerMap.values()) {
            computer.timeline.play();
        }
        timeline.play();

        Save.save();

        collisonManager.play();
    }

    public void pause(){
        for (ComputerManager computer : componentsManager.computerManagerMap.values()) {
            computer.timeline.pause();
        }

       collisonManager.pause();
    }

    public void stop(){
        for (ComputerManager computer : componentsManager.computerManagerMap.values()) {
            computer.timeline.stop();
        }

        collisonManager.stop();
        for (PacketManager packetManager : componentsManager.packetManagerMap.values()) {
            packetManager.stop();
        }
    }
    public double updateWireLength(){

        double totalLength = 0;
        for(Wire wire : componentsController.wireViewMap.keySet()) {
            totalLength += wire.length;
        }
        level.wireLength =  level.initialWireLength - totalLength;
        if(level.wireLength <= 0){
            level.wireLength = 0;
        }
        return level.wireLength;
    }

}
