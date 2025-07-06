package manager;

import controller.*;
import model.*;
import view.PacketView;

import static manager.GameLoopManager.gameLoopManager;

public class LevelManager {
    public static double gameSpeed = 1;
    public static  boolean airyaman = false;
    public static  boolean atar = false;
    public static  boolean anahita = false;
    public static Level lvl;


    /*public static void startTimer() {
        Timeline timeline = new Timeline();
        double t = 1 /gameSpeed;
        KeyFrame kf = new KeyFrame(Duration.seconds(t), e -> {
            if(!paused){
                lvl.currentTime++;
                lvl.currentTimeLabel.setText("Time: " + lvl.currentTime + "s");
                lvl.timelineSlider.setValue(lvl.timelineSlider.getValue() + 1);
                if (lvl.currentTime >= 20) {
                    timeline.stop();
                    lvlIsOver(true);
                }
            }
        });
        timertl = timeline;
        timeline.getKeyFrames().add(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void checkPacketLost(){
       double packetloss =(double) lvl.lostPackets/ lvl.generatedPackets;
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
       percentFormat.setMaximumFractionDigits(1);
        lvl.packetLossLabel.setText("Packet loss :" + percentFormat.format(packetloss));
        if(packetloss > 0.5){
            pauseLvl(lvl);
            lvlIsOver(false);
        }

    }

    public static void travelInTime(int time){
        gameSpeed = 10;
        resumelvl(lvl);

        ServerControler.updatePacketGenerator((Server) lvl.comps.getFirst());
        GameLoopController.start();
        for(Packet p : lvl.packets){
            try{
                ((SquarePacket)p).startTimeline2();
            }catch (Exception e){
                ((TrianglePacket)p).startTimeline2();
            }
        }

        double t =  time  * 100;
        PauseTransition delay = new PauseTransition(Duration.millis(t));
        delay.setOnFinished(event -> {
            gameSpeed = 1;
            System.out.println(time);
            lvl.currentTime = time;
            ServerControler.updatePacketGenerator((Server) lvl.comps.getFirst());
            start();
            for(Packet p : lvl.packets){
                try{
                    ((SquarePacket)p).startTimeline2();
                }catch (Exception e){
                    ((TrianglePacket)p).startTimeline2();
                }
            }
        });
        delay.play();

    }*/
    public static void reset(){
        ComputerController.computerMap.clear();
        LevelController.levelMap.clear();
        PacketContoller.packetMap.clear();
        PortController.portMap.clear();
        WireController.wireMap.clear();
        PacketView.packetTimelines.clear();



    }

    public static void resetMyLevel(){
        gameLoopManager.pauseAndResume(true);
        lvl.currentTime = 0;
        lvl.coins = 10;
        int n = lvl.packets.size();
        for(int i = 0; i < n; i++){
           PacketContoller.killPacket(lvl.packets.getFirst());
        }
        lvl.lostPackets = 0;
        lvl.generatedPackets = 0;
        lvl.packets.clear();

    }

}
