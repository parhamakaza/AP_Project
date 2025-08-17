package manager.packets;

import javafx.scene.shape.QuadCurve;
import model.packet.Packet;
import model.wire.Wire;

import java.util.List;

public class TrianglePacketManager extends PacketManager implements AccelerationDamper {
    private static final double TRIANGLE_ACCELERATION = 20;
    private boolean acceleration = false;

    public TrianglePacketManager(Packet packet, Wire path) {
        super(packet, path);
        if (packet.getType() != packet.wire.type) {
            acceleration = true;
        }
    }

    @Override
    public void handle(long now) {
        if (acceleration && lastUpdate != 0) {
            speed += TRIANGLE_ACCELERATION * ((now - lastUpdate) / 1_000_000_000.0);
        }
        super.handle(now);


    }

    @Override
    public void turnAccelerationOff() {
        acceleration = false;
    }

}
