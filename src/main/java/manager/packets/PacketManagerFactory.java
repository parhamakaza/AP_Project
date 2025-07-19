package manager.packets;

import manager.ComponentsManager;
import model.Type;
import model.packet.*;
import model.wire.Wire;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;

public class PacketManagerFactory {
    private static final Random RANDOM = new Random();
    protected static final Map<Class<? extends Packet>, BiFunction<Packet, Wire ,PacketManager>> managerRegistry = new HashMap<>();
    static {
        managerRegistry.put(SquarePacket.class, (packet, wire) -> new SquarePacketManager(packet , wire));
        managerRegistry.put(TrianglePacket.class,  (packet, path) -> new TrianglePacketManager(packet ,path));
        managerRegistry.put(MaticPacket.class,  (packet, path) -> new MaticPacketManager(packet ,path));
        managerRegistry.put(ConfidentialPacket.class , ((packet, path) -> new ConfidentialPacketManager(packet ,path ) ));
        managerRegistry.put(MassivePacket.class , (packet, path) -> new MassivePacketManager(packet , path));
        managerRegistry.put(BitPacket.class , (packet, path) -> new BitPacketManager(packet, path));
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


    public static PacketManager createManager(Packet packet , Wire wire){
        BiFunction<Packet, Wire, PacketManager> managerCreator;
        if(packet.isVpn() && packet.getType() != Type.CONFIDENTIAL){
           managerCreator = managerRegistry.get(selectRandomType());
        } else {
            managerCreator = managerRegistry.get(packet.getClass());
        }
        PacketManager packetManager = managerCreator.apply(packet , wire);

        ComponentsManager.packetManagerMap.put(packet , packetManager);
        return packetManager;


    }



}
