package manager;

import manager.computers.ComputerManager;
import manager.packets.PacketManager;
import model.computer.Computer;
import model.packet.Packet;

import java.util.HashMap;
import java.util.Map;

public class ComponentsManager {
    public static ComponentsManager TheComponentsManager;
    public  Map<Computer, ComputerManager> computerManagerMap = new HashMap<>();
    public  Map<Packet, PacketManager>  packetManagerMap= new HashMap<>();

    public ComponentsManager(){
        TheComponentsManager = this;
    }

    public ComputerManager getManager(Computer computer) {
        return computerManagerMap.get(computer);
    }


    public PacketManager getManager(Packet packet) {
        return packetManagerMap.get(packet);
    }


    public void putManager(Computer computer, ComputerManager computerManager) {
        computerManagerMap.put(computer, computerManager);
    }


    public void putManager(Packet packet, PacketManager packetManager) {
        packetManagerMap.put(packet, packetManager);
    }

}
