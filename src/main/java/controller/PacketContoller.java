package controller;


import manager.GameManager;
import model.packet.Packet;
import service.SceneManager;
import view.packets.PacketView;
import view.packets.PacketViewFactory;

import java.util.HashMap;


public class PacketContoller {
    public static HashMap<Packet, PacketView> packetViewMap = new HashMap<>();


    public static PacketView makePacket(Packet packet) {
        PacketView packetView = PacketViewFactory.creatView(packet);
        packetViewMap.put(packet, packetView);
        return packetView;

    }

    public static void killPacket(Packet packet){
        System.out.println("packet dead :" + packet);
        packet.wire.avaible = true;
        GameManager.lvl.lostPackets++;
        GameManager.lvl.packets.remove(packet);
        PacketView packetView = packetViewMap.get(packet);
        SceneManager.removeComponent(packetView.getShape());
    }



}
