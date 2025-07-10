package manager.packets;

import javafx.scene.shape.QuadCurve;
import model.packet.Packet;

import java.util.List;

public class TrianglePacketManager extends PacketManager {
    private static final double THE_ACCELERATION = 10;
    private boolean acceleration = false;

    public TrianglePacketManager(Packet packet, List<QuadCurve> path) {
        super(packet, path);
        if (packet.getType() != packet.wire.type) {
            acceleration = true;
        }
    }

    @Override
    public void handle(long now) {
        if (acceleration && lastUpdate != 0) {
            speed += THE_ACCELERATION * ((now - lastUpdate) / 1_000_000_000.0);
        }
        super.handle(now);


    }

}
