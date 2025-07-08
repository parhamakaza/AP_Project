package manager.Computers;

import controller.PacketContoller;
import javafx.animation.KeyFrame;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.computer.Computer;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;
import model.port.Port;
import model.port.PortType;
import model.port.SquarePort;
import model.port.TrianglePort;
import view.PacketView;

import java.util.Optional;

public class DDOSManager extends ComputerManager{

    public DDOSManager(Computer computer){
        super(computer);
        transfer();
    }

    @Override
    public void transfer(){
        KeyFrame sabotageKeyFrame = new KeyFrame(
                // 1. The Timing: When this code should run.
                // Change this duration to what you need, e.g., Duration.millis(100).
                Duration.seconds(1),

                // 2. The Action: The code to execute at the specified time.
                event -> {
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

                        // This logic finds a mismatched port for sabotage
                        boolean isPerfectMatch = (packet instanceof SquarePacket && port instanceof TrianglePort) || (packet instanceof TrianglePacket && port instanceof SquarePort);

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
        );
        timeline.getKeyFrames().add(sabotageKeyFrame);
    }

    @Override
    public void takePacket(Packet packet) {
        packet.insideSystem = true;
        this.computer.packets.add(packet);
        if (packet.value == packet.health) {
            packet.health--;
        }
        if (probability()) {
            makeTrojan(packet);
        }

    }
    // 30% chance
    private boolean probability() {
        return Math.random() < 0.3;
    }
    private void makeTrojan(Packet packet){
        packet.setTrozhan(true);
        PacketContoller.packetMap.get(packet).getShape().setFill(Color.RED);
    }
}
