package manager.computers;

import controller.PacketContoller;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.computer.Computer;
import model.packet.MaticPacket;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;
import model.port.*;
import service.SceneManager;

import static manager.LevelManager.lvl;
import static manager.packets.PacketManager.sendPacket;

public  class ServerManager extends ComputerManager {
    public ServerManager(Computer computer){
        super(computer);
        transfer();
    }
    @Override
    public  void takePacket(Packet packet){
        lvl.coins = lvl.coins + packet.value;
        SceneManager.removeComponent(PacketContoller.packetViewMap.get(packet).getShape());
        lvl.packets.remove(packet);
    }
    @Override
    public void transfer(){
        Computer computer = getComputer();
        KeyFrame packetCreationKeyFrame = new KeyFrame(Duration.seconds(1),
                event -> {
                    for (Port port : computer.ports) {
                        if (port.wire.avaible && port.portType == PortType.OUTPUT) {
                            Packet packet = null;
                            if (port instanceof SquarePort) {
                                packet = new SquarePacket();
                            }
                            if (port instanceof TrianglePort) {
                                packet = new TrianglePacket();
                            }
                            if(port instanceof MaticPort){
                                packet = new MaticPacket();
                            }
                            PacketContoller.makePacket(packet);
                            lvl.generatedPackets++;
                            sendPacket(port , packet);
                        }
                    }
                }
        );
        timeline.getKeyFrames().add(packetCreationKeyFrame);

    }






}
