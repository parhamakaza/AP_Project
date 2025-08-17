package manager.computers;

import controller.PacketContoller;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.Type;
import model.computer.Computer;
import model.packet.*;
import model.port.*;

import java.util.Map;
import java.util.Random;

import static manager.LevelManager.lvl;

public  class ServerManager extends ComputerManager {
    private static final Random random = new Random();

    private static boolean ss = true;

    public ServerManager(Computer computer){
        super(computer);

    }

    @Override
    public  void takePacket(Packet packet){
        if(packet.getType() == Type.MASSIVE){
            lvl.reachedMassivePackets.add((MassivePacket) packet);
        }
        if (packet.getType() == Type.BIT){
            BitPacket pacekt1 = (BitPacket) packet;
            MassivePacket msp = pacekt1.fatherPacket;
            msp.aliveChildren--;
        }
        lvl.coins = lvl.coins + packet.value;
        PacketContoller.removePacketView(packet);
        PacketContoller.removePacketManager(packet);
        lvl.packets.remove(packet);
    }

    @Override
    public void startTransfer(){
        Computer computer = getComputer();
        KeyFrame packetCreationKeyFrame = new KeyFrame(Duration.seconds(1),
                e -> {
                    for (Port port : computer.ports) {
                        if(port .wire == null){
                            continue;
                        }

                        if (port.wire.avaible && port.portType == PortType.OUTPUT ) {

                            Packet packet = null;
                            if(probability() ){
                                    packet = new ConfidentialPacket();
                                if(probability2()){

                               // packet =new MassivePacket();
                                }else {
                                }

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
                            lvl.increaseGemeratedPacket(packet.getSize());
                            sendPacket(port , packet);
                        }
                    }
                }
        );
        timeline.getKeyFrames().add(packetCreationKeyFrame);
    }
    //5% chance
    private boolean probability() {
        return random.nextDouble() < 0.10;
    }

    private boolean probability2(){

        return random.nextDouble() < 0.5;

    }






}
