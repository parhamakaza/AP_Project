package manager;

import controller.*;

public class ShopManager {
    public static double gameSpeed = 1;
    public static  boolean airyaman = false;
    public static  boolean atar = false;
    public static  boolean anahita = false;


    public static void reset(){
        ComputerController.computerViewMap.clear();
        LevelController.levelViewMap.clear();
        PacketContoller.packetViewMap.clear();
        PortController.portViewMap.clear();
        WireController.wireViewMap.clear();


    }

}
