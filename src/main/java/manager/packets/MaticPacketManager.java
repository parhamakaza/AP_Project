package manager.packets;

import javafx.scene.shape.QuadCurve;
import model.packet.Packet;
import model.wire.Wire;

import java.util.List;

public class MaticPacketManager extends PacketManager implements AccelerationDamper{
    private static final double THE_ACCELERATION = 10;
    private static final double MIN_SPEED = 50;
    private boolean slowing = false;
    private boolean accelerating = true;

    public MaticPacketManager(Packet packet, Wire path) {
        super(packet, path);
        if (packet.getType() != packet.wire.type) {
            slowing = true;
        }

    }



    @Override
    public void handle(long now) {
        if(accelerating) {
            if (!slowing && lastUpdate != 0) {
                speed += THE_ACCELERATION * ((now - lastUpdate) / 1_000_000_000.0);
            } else if (slowing && lastUpdate != 0 && speed >= MIN_SPEED) {
                speed -= THE_ACCELERATION * ((now - lastUpdate) / 1_000_000_000.0);
            }
        }
        super.handle(now);


    }


    @Override
    public void turnAccelerationOff() {
        accelerating =false;


    }
}
