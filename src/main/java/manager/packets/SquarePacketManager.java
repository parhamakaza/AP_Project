package manager.packets;

import javafx.scene.shape.QuadCurve;
import model.packet.Packet;
import model.wire.Wire;

import java.util.List;

public class SquarePacketManager extends PacketManager{
    public SquarePacketManager(Packet packet, Wire path) {
        super(packet, path);
        if(packet.getType() != packet.wire.type){
            speed = speed * 2;
        }

    }


}
