package manager.Computers;

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
import static view.PacketView.movePacket;

public  class ServerManager extends ComputerManager {
    public ServerManager(Computer computer){
        super(computer);
        makePacket();
    }

    public static void takePacket(Packet packet){
        lvl.coins = lvl.coins + packet.value;
        SceneManager.getCurrentPane().getChildren().remove(PacketContoller.packetMap.get(packet).getShape());
        lvl.packets.remove(packet);
    }

    public void makePacket(){
        Computer computer = getComputer();
        KeyFrame packetCreationKeyFrame = new KeyFrame(
                // This is the TIMING. Change this duration to what you need (e.g., Duration.millis(500)).
                Duration.seconds(1),

                // This is the ACTION. The code to be executed at the specified time.
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
