package manager.computers;

import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.computer.Computer;
import model.computer.Spy;
import model.packet.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpyManager extends ComputerManager {
    private static Random random = new Random();
    public  List<Spy> spyList = new ArrayList<>();
    public SpyManager(Computer computer){
        super(computer);
        spyList.add((Spy) this.getComputer());

    }


    @Override
    public void takePacket(Packet packet) {
        if (packet.isVpn()) {
            packet.setVpn(false);
            VPNManager.resetPacket(packet);
            super.takePacket(packet);

        } else {
            Spy spy = getRandomSpy();
            packet.insideSystem = true;
            spy.packets.add(packet);
        }

    }



    public  Spy getRandomSpy() {

        // 1. Handle edge cases: If the list is null or empty, there's nothing to choose.
        if (spyList == null || spyList.isEmpty()) {
            return null; // Or you could throw an IllegalArgumentException
        }

        // 2. Create a single Random instance to use.

        // 3. Generate a random index from 0 (inclusive) to the list size (exclusive).
        int randomIndex = random.nextInt(spyList.size());

        // 4. Return the item at that random index.
        return spyList.get(randomIndex);
    }



}