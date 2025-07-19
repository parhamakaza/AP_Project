package manager;

import manager.computers.ComputerManager;
import manager.packets.PacketManager;
import model.computer.Computer;
import model.packet.Packet;

import java.util.HashMap;
import java.util.Map;

public class ComponentsManager {
    public static Map<Computer, ComputerManager> computerManagerMap = new HashMap<>();
    public static Map<Packet, PacketManager>  packetManagerMap= new HashMap<>();
}
