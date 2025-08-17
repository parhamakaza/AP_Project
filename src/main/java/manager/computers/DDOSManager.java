package manager.computers;

import controller.PacketContoller;
import javafx.scene.paint.Color;
import model.Type;
import model.computer.Computer;
import model.packet.Packet;
import model.port.Port;

import static controller.ComponentsController.TheComponentsController;

public class DDOSManager extends ComputerManager{

    public DDOSManager(Computer computer){
        super(computer);

    }



    @Override
    public void takePacket(Packet packet) {
        if (packet.getType() == Type.BIT){
            PacketContoller.killPacket(packet);
            return;
        }
        super.takePacket(packet);
        if(packet.isVpn()){
            VPNManager.resetPacket(packet);
            return;
        }

        if (!packet.isDamged()) {
            packet.increaseNoise();
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
        TheComponentsController.getView(packet).getShape().setFill(Color.RED);

    }

}
