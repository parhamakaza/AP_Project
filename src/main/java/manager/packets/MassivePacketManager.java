package manager.packets;

import javafx.scene.shape.QuadCurve;
import model.computer.Computer;
import model.packet.Packet;
import model.wire.Wire;

import java.util.List;

import static manager.WireManager.disConnectWire;

public class MassivePacketManager extends PacketManager{
    private static final double MASSIVE_ACCELERATION = 20;
    public MassivePacketManager(Packet packet, Wire path) {
        super(packet, path);
        path.increaseMassivePassed();
    }

    @Override
    public void handle(long now){
        if(!packet.isVpn() && wire.isCurved()){
            speed += MASSIVE_ACCELERATION * ((now - lastUpdate) / 1_000_000_000.0);
        }

        super.handle(now);

        packet.deflectedY += 0.1;

    }



    @Override
    protected void packetMovementEnds(Computer computer){
        if(wire.getMassivePassed() >= 3){
            disConnectWire(wire);
        }
        super.packetMovementEnds(computer);
    }

}
