package manager.computers;

import controller.ComputerController;
import controller.PacketContoller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
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

    public ComputerManager(Computer computer){
        this.computer = computer;
        computerManagerMap.put(computer, this);
        timeline.setCycleCount(Animation.INDEFINITE);
        transfer();
    }

    public void takePacket(Packet packet) {
        packet.insideSystem = true;
        this.computer.packets.add(packet);
    }

    public void transfer(){
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10) , event -> {Computer computer = this.computer;
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
            }});
        timeline.getKeyFrames().add(keyFrame);



    }

    public boolean isPerfect(Packet packet, Port port) {
        return packet.getType() == port.getType();
    }

   /* public void standardtransfer() {
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

    }*/
    public static void disableComputer(Computer computer){
        computer.disable = true;
        Shape shape =ComputerController.computerViewMap.get(computer).getShape();
        shape.setOpacity(0.5);
        Timeline timeline = ComputerManager.computerManagerMap.get(computer).timeline;
        timeline.pause();
        PauseTransition pause = new PauseTransition(Duration.seconds(4));

        pause.setOnFinished(event -> {
            computer.disable = false;
            shape.setOpacity(1);
            timeline.play();
        });

        pause.play();

    }

}
