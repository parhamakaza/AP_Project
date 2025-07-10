package manager;

import controller.LevelController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import manager.computers.ComputerManager;

import java.util.ArrayList;
import java.util.List;

public class GameLoopManager {
    public static GameLoopManager gameLoopManager = new GameLoopManager();
    public boolean paused;
    protected Timeline mainTimeLine = new Timeline();
    public static void addKeyFrame(KeyFrame keyFrame){
        gameLoopManager.mainTimeLine.getKeyFrames().add(keyFrame);

    }


    public static void removeKeyFrame(KeyFrame keyFrame){
        gameLoopManager.mainTimeLine.getKeyFrames().remove(keyFrame);


    }

    public  void pauseAndResume(boolean p){
        paused = p;
        if(mainTimeLine == null){
            return;
        }
        if ( p && mainTimeLine.getStatus() == Animation.Status.RUNNING) {
            mainTimeLine.pause();

            LevelController.levelViewMap.get(LevelManager.lvl).timeline.pause();

        }
        if(!p && mainTimeLine.getStatus() == Animation.Status.PAUSED){
            mainTimeLine.play();

            LevelController.levelViewMap.get(LevelManager.lvl).timeline.play();

        }
    }



    public void start() {
        for (ComputerManager computer : ComputerManager.computerManagerMap.values()) {
            computer.timeline.play();
        }
        CollisonManager.checkForCollison();
        mainTimeLine.setCycleCount(Animation.INDEFINITE);
        mainTimeLine.play();

    }



}
