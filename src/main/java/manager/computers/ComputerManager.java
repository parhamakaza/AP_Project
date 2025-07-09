package manager.computers;

import controller.PacketContoller;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import manager.packets.PacketManager;
import model.computer.Computer;
import model.packet.Packet;
import model.port.Port;
import model.port.PortType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class ComputerManager {
    public static Map<Computer, ComputerManager> computerManagerMap = new HashMap<>();
    public Computer computer;
    public Timeline timeline = new Timeline();

    public Computer getComputer() {
        return computer;
    }

    public ComputerManager(Computer computer) {
        this.computer = computer;
        computerManagerMap.put(computer, this);
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void takePacket(Packet packet) {
        packet.insideSystem = true;
        this.computer.packets.add(packet);
    }

    abstract void transfer();

    public boolean isPerfect(Packet packet, Port port) {
        return packet.getType() == port.getType();
    }

    public void standardtransfer() {
        Computer computer = this.computer;
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


            boolean isPerfectMatch = this.isPerfect(packet, port);

            if (isPerfectMatch) {
                bestFitPort = port;
                break;
            }
        }

        Port portToSendFrom = Optional.ofNullable(bestFitPort).orElse(firstAvailablePort);
        if (portToSendFrom != null) {
            PacketManager.sendPacket(portToSendFrom, packet);
            computer.packets.remove(packet);
        }

    }

}
