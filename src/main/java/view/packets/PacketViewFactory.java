package view.packets;

import manager.computers.*;
import manager.packets.PacketManager;
import model.computer.*;
import model.packet.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PacketViewFactory {
    private static final Map<Class<? extends Packet>, Function<Packet, PacketView>> managerRegistry = new HashMap<>();
    static {
        managerRegistry.put(SquarePacket.class, packet-> new SquarePacketView(packet));
        managerRegistry.put(TrianglePacket.class, packet -> new TrianglePacketView(packet));
        managerRegistry.put(MaticPacket.class, packet -> new MaticPacketView(packet));
        managerRegistry.put(ConfidentialPacket.class, packet -> new ConfidentialPacketView(packet));
    }
    public static PacketView creatView(Packet packet) {

        Function<Packet, PacketView> managerCreator = managerRegistry.get(packet.getClass());
        return managerCreator.apply(packet);

    }

}
