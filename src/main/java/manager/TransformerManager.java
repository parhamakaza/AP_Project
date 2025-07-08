package manager;

import controller.PacketContoller;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import manager.Computers.ComputerManager;
import model.computer.Computer;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;
import model.port.Port;
import model.port.PortType;
import model.port.SquarePort;
import model.port.TrianglePort;
import view.PacketView;

public class TransformerManager extends ComputerManager {
    public TransformerManager(Computer computer) {
        super(computer);
        transfer();
    }


    public void transfer(){
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event ->  transferPacket(this.getComputer()));
    }

    public static void transferPacket(Computer computer) {

        if (!computer.packets.isEmpty()) {
            Packet packet = computer.packets.getLast();

            // 1. If the computer is overloaded, destroy the packet.
            if (computer.packets.size() > 5) {
                computer.packets.remove(packet);
                PacketContoller.killPacket(packet);
                return; // Exit early
            }

            // 2. Handle SquarePackets
            if (packet instanceof SquarePacket) {
                // First, try to send to a matching port type (SquarePort)
                for (Port port : computer.ports) {
                    if (port instanceof SquarePort && port.portType.equals(PortType.OUTPUT) && port.wire.avaible) {
                        PacketView.sendPacket(port, packet);
                        computer.packets.remove(packet);
                        return; // Packet sent, exit early
                    }
                }
                // If no matching port was found, send to any available output port
                for (Port port : computer.ports) {
                    if (port.portType.equals(PortType.OUTPUT) && port.wire.avaible) {
                        PacketView.sendPacket(port, packet);
                        computer.packets.remove(packet);
                        return; // Packet sent, exit early
                    }
                }
            }

            // 3. Handle TrianglePackets
            if (packet instanceof TrianglePacket) {
                // First, try to send to a matching port type (TrianglePort)
                for (Port port : computer.ports) {
                    if (port instanceof TrianglePort && port.portType.equals(PortType.OUTPUT) && port.wire.avaible) {
                        PacketView.sendPacket(port, packet);
                        computer.packets.remove(packet);
                        return; // Packet sent, exit early
                    }
                }
                // If no matching port was found, send to any available output port
                for (Port port : computer.ports) {
                    if (port.portType.equals(PortType.OUTPUT) && port.wire.avaible) {
                        PacketView.sendPacket(port, packet);
                        computer.packets.remove(packet);
                        return; // Packet sent, exit early
                    }
                }
            }

        }

    }
}
