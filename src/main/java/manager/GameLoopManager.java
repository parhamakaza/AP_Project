package manager;

import controller.LevelController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import model.computer.Computer;
import model.computer.Transferer;
import model.computer.Server;
import view.PacketView;

import static manager.TransfererManager.transfererMap;

public class GameLoopManager {
    public static GameLoopManager gameLoopManager = new GameLoopManager();
    public  boolean paused;
    private  Timeline mainTimeLine = new Timeline();;
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

    public  void start(){
        for(Computer computer : LevelManager.lvl.comps){
            if (computer instanceof Transferer){

            }else if(computer instanceof Server){
                addKeyFrame(ServerManager.makePacket2((Server) computer));
            }
        }
        CollisonManager.checkForCollison();
        mainTimeLine.setCycleCount(Animation.INDEFINITE);
        mainTimeLine.play();

    }



}
