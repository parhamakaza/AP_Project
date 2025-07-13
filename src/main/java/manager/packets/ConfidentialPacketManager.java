package manager.packets;

import javafx.scene.shape.QuadCurve;
import model.computer.Computer;
import model.packet.Packet;

import java.util.List;

public class ConfidentialPacketManager extends PacketManager{
    public ConfidentialPacketManager(Packet packet, List<QuadCurve> path) {
        super(packet, path);
    }
    @Override
    public void handle(long now){
        setSpeed();
        super.handle(now);

    }

    private boolean destinationComputerIsEmpty(){
        Computer computer = packet.wire.ePort.computer;

        if(computer.packets.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
    private void setSpeed(){
        if(destinationComputerIsEmpty()){
            speed = STANDARDSPEED;
        }else {
            speed = STANDARDSPEED / 2;
        }
    }


}
