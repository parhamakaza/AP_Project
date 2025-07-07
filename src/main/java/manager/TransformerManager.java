package manager;

import controller.PacketContoller;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.computer.Transformer;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;
import model.port.Port;
import model.port.PortType;
import model.port.SquarePort;
import model.port.TrianglePort;
import view.PacketView;

public class TransformerManager {

    public static KeyFrame transferPacket(Transformer transformer) {
       return new KeyFrame(Duration.millis(500), event -> {
            // Make sure the transformer object and its packets list are accessible here
            if (transformer != null && !transformer.packets.isEmpty()) {
                Packet packet = transformer.packets.getLast();

                // 1. If the transformer is overloaded, destroy the packet.
                if (transformer.packets.size() > 5) {
                    transformer.packets.remove(packet);
                    PacketContoller.killPacket(packet);
                    return; // Exit early
                }

                // 2. Handle SquarePackets
                if (packet instanceof SquarePacket) {
                    // First, try to send to a matching port type (SquarePort)
                    for (Port port : transformer.ports) {
                        if (port instanceof SquarePort && port.portType.equals(PortType.OUTPUT) && port.wire.avaible) {
                            PacketView.sendPacket(port, packet);
                            transformer.packets.remove(packet);
                            return; // Packet sent, exit early
                        }
                    }
                    // If no matching port was found, send to any available output port
                    for (Port port : transformer.ports) {
                        if (port.portType.equals(PortType.OUTPUT) && port.wire.avaible) {
                            PacketView.sendPacket(port, packet);
                            transformer.packets.remove(packet);
                            return; // Packet sent, exit early
                        }
                    }
                }

                // 3. Handle TrianglePackets
                if (packet instanceof TrianglePacket) {
                    // First, try to send to a matching port type (TrianglePort)
                    for (Port port : transformer.ports) {
                        if (port instanceof TrianglePort && port.portType.equals(PortType.OUTPUT) && port.wire.avaible) {
                            PacketView.sendPacket(port, packet);
                            transformer.packets.remove(packet);
                            return; // Packet sent, exit early
                        }
                    }
                    // If no matching port was found, send to any available output port
                    for (Port port : transformer.ports) {
                        if (port.portType.equals(PortType.OUTPUT) && port.wire.avaible) {
                            PacketView.sendPacket(port, packet);
                            transformer.packets.remove(packet);
                            return; // Packet sent, exit early
                        }
                    }
                }

                // 4. If the packet could not be sent (e.g., no available ports), remove it.
                // This line is only reached if none of the 'return' statements above were triggered.
                transformer.packets.remove(packet);
            }
        });

    }

}
