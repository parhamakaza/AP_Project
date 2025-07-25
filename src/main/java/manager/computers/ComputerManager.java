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
import manager.packets.PacketManagerFactory;
import model.computer.Computer;
import model.packet.Packet;
import model.port.Port;
import model.port.PortType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static controller.WireController.wireViewMap;

public abstract class ComputerManager {
    public static Map<Computer, ComputerManager> computerManagerMap = new HashMap<>();
    public Computer computer;
    public Timeline timeline = new Timeline();

    public void sendPacket(Port sPort , Packet packet){
        sPort.wire.avaible = false;
        packet.insideSystem =false;
        if(sPort.portType.equals(PortType.OUTPUT)) {
            packet.x = sPort.x + packet.deflectedX;
            packet.y = sPort.y + packet.deflectedY;
            packet.wire = sPort.wire;
            PacketManager packetManager = PacketManagerFactory.createManager(packet,sPort.wire);
            packetManager.start();
        }
        computer.packets.remove(packet);
    }

    public Computer getComputer() {
        return computer;
    }

    public ComputerManager(Computer computer){
        this.computer = computer;
        computerManagerMap.put(computer, this);
        timeline.setCycleCount(Animation.INDEFINITE);
        startTransfer();
    }

    public void takePacket(Packet packet) {
        packet.insideSystem = true;
        this.computer.packets.add(packet);
    }

    protected Packet choosePacketToSend(){
        if (computer.packets.isEmpty()) {
            return null;
        }

        if (computer.packets.size() > 5) {
            Packet packet = computer.packets.removeLast();
            PacketContoller.killPacket(packet);
            return null;
        }
        return computer.packets.getFirst();
    }

    protected void transfer(){


       Packet packet = choosePacketToSend();
        if(packet == null){
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
            sendPacket(portToSendFrom, packet);
        }
    }



    public void startTransfer(){
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10) , event -> transfer());
        timeline.getKeyFrames().add(keyFrame);


    }

    public boolean isPerfect(Packet packet, Port port) {
        return packet.getType() == port.getType();
    }

    protected void disableComputer(){
        computer.disable = true;
        Shape shape = ComputerController.computerViewMap.get(computer).getShape();
        shape.setOpacity(0.5);
        timeline.pause();
        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        pause.setOnFinished(event -> {
            computer.disable = false;
            shape.setOpacity(1);
            timeline.play();
        });

        pause.play();

    }

}
