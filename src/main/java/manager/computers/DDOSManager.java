package manager.computers;

import controller.PacketContoller;
import javafx.animation.KeyFrame;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.computer.Computer;
import model.packet.Packet;
import model.port.Port;

public class DDOSManager extends ComputerManager{

    public DDOSManager(Computer computer){
        super(computer);

    }



    @Override
    public void takePacket(Packet packet) {
        super.takePacket(packet);
        if(packet.isVpn()){
            packet.setVpn(false);
            VPNManager.resetPacket(packet);
            return;
        }
        if (!packet.isDamged()) {
            packet.health--;
        }
        if (probability()) {
            makeTrojan(packet);
        }
    }

    @Override
    public boolean isPerfect(Packet packet, Port port){
       return packet.getType() != port.getType();
    }
    // 30% chance
    private boolean probability() {
        return Math.random() < 0.3;
    }
    private void makeTrojan(Packet packet){
        packet.setTrozhan(true);
        PacketContoller.packetViewMap.get(packet).getShape().setFill(Color.RED);
    }
}
