package manager;

import controller.LevelController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import manager.computers.ComputerManager;

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

            LevelController.levelViewMap.get(GameManager.lvl).timeline.pause();

        }
        if(!p && mainTimeLine.getStatus() == Animation.Status.PAUSED){
            mainTimeLine.play();

            LevelController.levelViewMap.get(GameManager.lvl).timeline.play();

        }
    }



    public void start() {
        for (ComputerManager computer : ComponentsManager.computerManagerMap.values()) {
            computer.timeline.play();
        }
        new CollisonManager();
        mainTimeLine.setCycleCount(Animation.INDEFINITE);
        mainTimeLine.play();

    }



}
