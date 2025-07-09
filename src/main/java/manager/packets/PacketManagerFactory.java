package manager.packets;

import model.packet.Packet;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PacketManagerFactory {
    private static final Map<Class<? extends Packet>, Function<Packet, PacketManager>> managerRegistry = new HashMap<>();
}
