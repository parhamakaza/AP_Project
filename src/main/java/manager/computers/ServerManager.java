package manager.computers;

import controller.PacketContoller;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.computer.Computer;
import model.packet.*;
import model.port.*;
import service.SceneManager;

import java.util.Random;

import static manager.LevelManager.lvl;
import static manager.packets.PacketManager.sendPacket;

public  class ServerManager extends ComputerManager {
    private static final Random random = new Random();

    public ServerManager(Computer computer){
        super(computer);

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
                e -> {
                    for (Port port : computer.ports) {
                        if (port.wire.avaible && port.portType == PortType.OUTPUT) {

                            Packet packet = null;
                            if(probability()){
                                packet =new ConfidentialPacket();
                            }else {
                                if (port instanceof SquarePort) {
                                    packet = new SquarePacket();
                                }
                                if (port instanceof TrianglePort) {
                                    packet = new TrianglePacket();
                                }
                                if (port instanceof MaticPort) {
                                    packet = new MaticPacket();
                                }
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
    //10% chance
    private boolean probability() {
        return random.nextDouble() < 0.05;
    }






}
