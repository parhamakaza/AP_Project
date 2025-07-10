package manager;

import controller.*;
import model.*;

import static manager.GameLoopManager.gameLoopManager;

public class LevelManager {
    public static double gameSpeed = 1;
    public static  boolean airyaman = false;
    public static  boolean atar = false;
    public static  boolean anahita = false;
    public static Level lvl;



    public static void reset(){
        ComputerController.computerViewMap.clear();
        LevelController.levelViewMap.clear();
        PacketContoller.packetViewMap.clear();
        PortController.portViewMap.clear();
        WireController.wireViewMap.clear();




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
