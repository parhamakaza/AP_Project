package controller;


import javafx.scene.layout.Pane;
import manager.LevelManager;
import model.packet.Packet;
import service.SceneManager;
import view.packets.PacketView;
import view.packets.PacketViewFactory;

import java.util.HashMap;


public class PacketContoller {
    public static HashMap<Packet, PacketView> packetMap = new HashMap<>();


    public static PacketView makePacket(Packet packet) {
        PacketView packetView = PacketViewFactory.creatView(packet);
        packetMap.put(packet, packetView);
        return packetView;

    }

    public static void killPacket(Packet packet){
        System.out.println("packet dead :" + packet);
        packet.wire.avaible = true;
        LevelManager.lvl.lostPackets++;
        LevelManager.lvl.packets.remove(packet);
        PacketView packetView = packetMap.get(packet);
        SceneManager.removeComponent(packetView.getShape());
    }



}
