package controller;


import javafx.scene.layout.Pane;
import model.*;
import service.SceneManager;
import view.PacketView;
import java.util.HashMap;


public class PacketContoller {
    public static HashMap<Packet, PacketView> packetMap = new HashMap<>();


    public static PacketView makePacket(Packet packet) {
        PacketView packetView = new PacketView(packet);
        packetMap.put(packet, packetView);
        return packetView;
    }

    public static void killPacket(Packet packet){
        Pane root =((Pane) SceneManager.getPrimaryStage().getScene().getRoot());
        LevelsController.lvl.lostPackets++;
        packet.wire.avaible = true;
        LevelsController.lvl.packets.remove(packet);
        PacketView packetView = packetMap.get(packet);
        root.getChildren().remove(packetView.getShape());
    }



}
