package manager;

import controller.PacketContoller;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.computer.Transferer;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;
import model.port.Port;
import model.port.PortType;
import model.port.SquarePort;
import model.port.TrianglePort;
import view.PacketView;

import java.util.HashMap;

public class TransfererManager {
    public static HashMap<Transferer, TransfererManager> transfererMap = new HashMap<>();
    private Transferer transferer;
    private KeyFrame keyFrame;

    public TransfererManager(Transferer transferer) {
        this.transferer = transferer;
        keyFrame = startPacketTransfer();
        transfererMap.put(transferer, this);
    }

    public KeyFrame startPacketTransfer() {
        keyFrame = new KeyFrame(Duration.millis(100), e -> {
            transferPacket();
        });
        GameLoopManager.addKeyFrame(keyFrame);
        return keyFrame;
    }

    public void transferPacket() {
        if (!transferer.packets.isEmpty()) {
            Packet packet = transferer.packets.getLast();


            if (transferer.packets.size() > 5) {
                transferer.packets.remove(packet);
                PacketContoller.killPacket(packet);
                return;
            }

            if (packet instanceof SquarePacket) {
                for (Port i : transferer.ports) {
                    if ((i instanceof SquarePort) && (i.portType.equals(PortType.OUTPUT)) && (i.wire.avaible)) {
                        PacketView.sendPacket(i, packet);
                        transferer.packets.remove(packet);
                        return;
                    }
                }
                for (Port i : transferer.ports) {
                    if (i.portType.equals(PortType.OUTPUT) && (i.wire.avaible)) {
                        PacketView.sendPacket(i, packet);
                        transferer.packets.remove(packet);

                        return;
                    }
                }
            }

            if (packet instanceof TrianglePacket) {
                for (Port i : transferer.ports) {
                    if ((i instanceof TrianglePort) && i.portType.equals(PortType.OUTPUT) && (i.wire.avaible)) {
                        transferer.packets.remove(packet);


                        PacketView.sendPacket(i, packet);
                        return;
                    }
                }

                for (Port i : transferer.ports) {
                    if (i.portType.equals(PortType.OUTPUT) && (i.wire.avaible)) {
                        transferer.packets.remove(packet);
                        PacketView.sendPacket(i, packet);
                        return;
                    }
                }

            }

        }

    }

}
