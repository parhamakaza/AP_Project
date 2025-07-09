package view.port;

import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;
import model.port.Port;
import model.port.SquarePort;
import model.port.TrianglePort;
import view.packets.PacketView;
import view.packets.SquarePacketView;
import view.packets.TrianglePacketView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PortViewFactory {
    private static final Map<Class<? extends Port>, Function<Port, PortView>> managerRegistry = new HashMap<>();
    static {
        managerRegistry.put(SquarePort.class, port-> new SquarePortView(port));
        managerRegistry.put(TrianglePort.class, port -> new TrianglePortView(port));


    }
    public static PortView creatPortView(Port port) {

        Function<Port, PortView> managerCreator = managerRegistry.get(port.getClass());
        return managerCreator.apply(port);

    }
}
