package manager.computers;

import controller.PacketContoller;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.computer.Computer;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;
import model.port.Port;
import model.port.PortType;
import model.port.SquarePort;
import model.port.TrianglePort;
import view.packets.PacketView;

import java.util.Optional;

public class TransformerManager extends ComputerManager {
    public TransformerManager(Computer computer) {
        super(computer);
        transfer();
    }

    @Override
    public void transfer(){
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event ->  transferPacket(this.getComputer()));
        timeline.getKeyFrames().add(keyFrame);
    }

    public static void transferPacket(Computer computer){

        if (computer.packets.isEmpty()) {
            return;
        }

        Packet packet = computer.packets.getLast();

        if (computer.packets.size() > 5) {
            computer.packets.remove(packet);
            PacketContoller.killPacket(packet);
            return;
        }


        Port bestFitPort = null;
        Port firstAvailablePort = null;

        for (Port port : computer.ports) {

            if (port.portType != PortType.OUTPUT || !port.wire.avaible) {
                continue;
            }


            if (firstAvailablePort == null) {
                firstAvailablePort = port;
            }


            boolean isPerfectMatch = (packet instanceof SquarePacket && port instanceof SquarePort) || (packet instanceof TrianglePacket && port instanceof TrianglePort);

            if (isPerfectMatch) {
                bestFitPort = port;
                break;
            }
        }

        Port portToSendFrom = Optional.ofNullable(bestFitPort).orElse(firstAvailablePort);
        if (portToSendFrom != null) {
            PacketView.sendPacket(portToSendFrom, packet);
            computer.packets.remove(packet);
        }

    }


}
