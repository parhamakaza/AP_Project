package manager.packets;

import javafx.scene.shape.QuadCurve;
import model.packet.MaticPacket;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;

public class PacketManagerFactory {
    private static final Random RANDOM = new Random();
    protected static final Map<Class<? extends Packet>, BiFunction<Packet, List<QuadCurve> ,PacketManager>> managerRegistry = new HashMap<>();
    static {
        managerRegistry.put(SquarePacket.class, (packet, path) -> new SquarePacketManager(packet , path));
        managerRegistry.put(TrianglePacket.class,  (packet, path) -> new TrianglePacketManager(packet ,path));
        managerRegistry.put(MaticPacket.class,  (packet, path) -> new MaticPacketManager(packet ,path));

    }
    private static final List<Class<? extends Packet>> ELIGIBLE_TYPES = List.of(
            SquarePacket.class,
            TrianglePacket.class,
            MaticPacket.class
    );

    public static Class<? extends Packet> selectRandomType() {
        // Generate a random index for the list.
        int randomIndex = RANDOM.nextInt(ELIGIBLE_TYPES.size());

        // Return the Class object at that random index.
        return ELIGIBLE_TYPES.get(randomIndex);
    }


    public static PacketManager createManager(Packet packet , List<QuadCurve> path ){
        BiFunction<Packet, List<QuadCurve>, PacketManager> managerCreator;
        if(packet.isVpn()){
           managerCreator = managerRegistry.get(selectRandomType());
        } else {
            managerCreator = managerRegistry.get(packet.getClass());
        }
        PacketManager packetManager = managerCreator.apply(packet , path);

        PacketManager.packetManagerMap.put(packet , packetManager);
        return packetManager;


    }



}
