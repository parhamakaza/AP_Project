package manager.packets;

import javafx.scene.shape.QuadCurve;
import model.packet.MaticPacket;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class PacketManagerFactory {
    protected static final Map<Class<? extends Packet>, BiFunction<Packet, List<QuadCurve> ,PacketManager>> managerRegistry = new HashMap<>();
    static {
        managerRegistry.put(SquarePacket.class, (packet, path) -> new SquarePacketManager(packet , path));
        managerRegistry.put(TrianglePacket.class,  (packet, path) -> new TrianglePacketManager(packet ,path));
        managerRegistry.put(MaticPacket.class,  (packet, path) -> new MaticPacketManager(packet ,path));

    }

    public static PacketManager createManager(Packet packet , List<QuadCurve> path ){

        BiFunction<Packet, List<QuadCurve>, PacketManager> managerCreator = managerRegistry.get(packet.getClass());
        PacketManager packetManager =managerCreator.apply(packet , path);
        PacketManager.packetManagerMap.put(packet , packetManager);
        return packetManager;


    }


}
