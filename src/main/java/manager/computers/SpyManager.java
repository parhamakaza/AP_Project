package manager.computers;


import controller.PacketContoller;
import model.Type;
import model.computer.Computer;
import model.computer.Spy;
import model.packet.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpyManager extends ComputerManager {
    private static Random random = new Random();
    public static List<Spy> spyList = new ArrayList<>();

    public SpyManager(Computer computer) {
        super(computer);
        spyList.add((Spy) this.getComputer());
    }


    @Override
    public void takePacket(Packet packet) {

        if(packet.getType()== Type.CONFIDENTIAL){
            PacketContoller.killPacket(packet);
            return;
        }

        if (packet.isVpn()) {
            VPNManager.resetPacket(packet);
            super.takePacket(packet);
        } else {
            Spy spy = getRandomSpy();
            spy.packets.add(packet);
        }

    }


    public Spy getRandomSpy() {

        int randomIndex = random.nextInt(spyList.size());
        return spyList.get(randomIndex);
    }


}