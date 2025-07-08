package manager;

import controller.PacketContoller;
import javafx.animation.Timeline;
import model.computer.Computer;
import model.packet.Packet;

import java.util.HashMap;
import java.util.Map;

public class ComputerManager  {

    public static Computer takePacket(Packet packet , Computer computer){
            packet.insideSystem= true;
        computer.packets.add(packet);
        return computer;
    }
}
