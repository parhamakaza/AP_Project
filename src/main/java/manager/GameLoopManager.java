package manager;

import controller.LevelController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import manager.computers.ComputerManager;
import view.packets.PacketView;

import java.util.ArrayList;
import java.util.List;

public class GameLoopManager {
    public static GameLoopManager gameLoopManager = new GameLoopManager();
    public boolean paused;
    private Timeline mainTimeLine = new Timeline();
    private List<Timeline> computersTimeLine = new ArrayList<>();
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
            pauseAndResumePackets(p);
            LevelController.levelMap.get(LevelManager.lvl).timeline.pause();

        }
        if(!p && mainTimeLine.getStatus() == Animation.Status.PAUSED){
            mainTimeLine.play();
            pauseAndResumePackets(p);
            LevelController.levelMap.get(LevelManager.lvl).timeline.play();

        }
    }

    private static void pauseAndResumePackets(boolean p){
        for (Timeline timeLine : PacketView.packetTimelines){
            if ( p && timeLine.getStatus() == Animation.Status.RUNNING) {
                timeLine.pause();

            }
            if(!p && timeLine.getStatus() == Animation.Status.PAUSED){
                timeLine.play();

            }
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
