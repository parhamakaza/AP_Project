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
import service.SceneManager;

import static manager.LevelManager.lvl;
import static view.packets.PacketView.movePacket;

public  class ServerManager extends ComputerManager {
    public ServerManager(Computer computer){
        super(computer);
        transfer();
    }

    public  void takePacket(Packet packet){
        lvl.coins = lvl.coins + packet.value;
        SceneManager.getCurrentPane().getChildren().remove(PacketContoller.packetMap.get(packet).getShape());
        lvl.packets.remove(packet);
    }
    @Override
    public void transfer(){
        Computer computer = getComputer();
        KeyFrame packetCreationKeyFrame = new KeyFrame(Duration.seconds(1),
                event -> {
                    for (Port port : computer.ports) {
                        if (port.wire.avaible && port.portType == PortType.OUTPUT) {

                            if (port instanceof SquarePort) {
                                SquarePacket sq = new SquarePacket(port);
                                PacketContoller.makePacket(sq);
                                movePacket(sq);
                                lvl.generatedPackets++;
                            }
                            if (port instanceof TrianglePort) {
                                TrianglePacket tri = new TrianglePacket(port);
                                PacketContoller.makePacket(tri);
                                movePacket(tri);
                                lvl.generatedPackets++;
                            }
                        }
                    }
                }
        );
        timeline.getKeyFrames().add(packetCreationKeyFrame);

    }






}
