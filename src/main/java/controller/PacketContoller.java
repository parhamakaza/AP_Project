package controller;


import manager.LevelManager;
import manager.packets.PacketManager;
import manager.packets.PacketManagerFactory;
import model.packet.Packet;
import service.SceneManager;
import view.packets.PacketView;
import view.packets.PacketViewFactory;

import static controller.ComponentsController.TheComponentsController;
import static manager.ComponentsManager.TheComponentsManager;


public class PacketContoller {


    public static PacketView makePacket(Packet packet) {
        PacketView packetView = PacketViewFactory.creatView(packet);
        TheComponentsController.putView(packet, packetView);

        if(!packet.insideSystem){
            PacketManagerFactory.createManager(packet, packet.wire);
        }

        return packetView;

    }

    public static void killPacket(Packet packet){
        System.out.println("packet dead :" + packet);
        packet.wire.avaible = true;
        LevelManager.lvl.lostPackets++;
        LevelManager.lvl.packets.remove(packet);
        removePacketView(packet);
        removePacketManager(packet);
    }

    public static void removePacketView(Packet packet){
        PacketView packetView = TheComponentsController.packetViewMap.remove(packet);
        SceneManager.removeComponent(packetView.getShape());
    }

    public static void removePacketManager(Packet packet){
        PacketManager packetManager = TheComponentsManager.packetManagerMap.remove(packet);
        packetManager.stop();
    }



}
