package manager.packets;

import javafx.scene.shape.QuadCurve;
import model.packet.Packet;
import model.wire.Wire;

import java.util.List;

public class MassivePacketManager extends PacketManager{
    public MassivePacketManager(Packet packet, Wire path) {
        super(packet, path);
    }
}
