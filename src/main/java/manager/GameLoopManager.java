package manager;

import controller.LevelController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import model.computer.Computer;
import model.computer.Transferer;
import model.computer.Server;
import view.PacketView;

public class GameLoopManager {
    public static boolean paused;
    private static Timeline mainTimeLine ;
    public static void addKeyFrame(KeyFrame keyFrame){
        mainTimeLine.getKeyFrames().add(keyFrame);

    }


    public static void removeKeyFrame(KeyFrame keyFrame){
        mainTimeLine.getKeyFrames().remove(keyFrame);


    }

    public static void pauseAndResume(boolean p){
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

    public static void start(){
        mainTimeLine = new Timeline();
        for(Computer computer : LevelManager.lvl.comps){
            if (computer instanceof Transferer){
                addKeyFrame(SystemManager.startPacketTransfer((Transferer) computer));
            }else if(computer instanceof Server){
                addKeyFrame(ServerManager.makePacket2((Server) computer));
            }
        }
        CollisonManager.checkForCollison();
        mainTimeLine.setCycleCount(Animation.INDEFINITE);
        mainTimeLine.play();

    }



}
