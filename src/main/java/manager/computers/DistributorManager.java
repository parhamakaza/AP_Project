package manager.computers;

import controller.PacketContoller;
import manager.packets.PacketManager;
import manager.packets.PacketManagerFactory;
import model.Type;
import model.computer.Computer;
import model.packet.BitPacket;
import model.packet.MassivePacket;
import model.packet.Packet;
import model.port.Port;
import model.port.PortType;

import java.util.ArrayList;
import java.util.List;

import static controller.WireController.wireViewMap;

public class DistributorManager extends ComputerManager {
    private List<Packet> bitPackets = new ArrayList<>();

    public DistributorManager(Computer computer) {
        super(computer);
    }

    @Override
    public void takePacket(Packet packet) {
        if (packet.getType().equals(Type.MASSIVE)) {
            takeMassivePacket((MassivePacket) packet);
        } else {
            super.takePacket(packet);
        }
    }

    private void takeMassivePacket(MassivePacket packet) {
        packet.insideSystem = true;
        for (int i = 0; i < packet.getSize(); i++) {
            Packet packet1 = new BitPacket(packet);
            PacketContoller.makePacket(packet1);
            bitPackets.add(packet1);

        }
    }

    @Override
    protected Packet choosePacketToSend() {
        if (bitPackets.isEmpty()) {
            return super.choosePacketToSend();
        } else {
            return bitPackets.getFirst();
        }
    }

    @Override
    public void sendPacket(Port sPort, Packet packet) {
        super.sendPacket(sPort, packet);
        if (packet.getType() == Type.BIT) {
            bitPackets.remove(packet);
        }
    }

}
