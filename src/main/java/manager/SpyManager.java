package manager;

import javafx.animation.Timeline;
import model.computer.Computer;
import model.computer.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpyManager extends ComputerManager {
    private static Random random = new Random();
    public static List<Spy> spyList = new ArrayList<>();

    public static Timeline SpyTransferPacket(Spy spy) {
        return TransformerManager.transferPacket(spy);
    }

    public static Spy getRandomSpy() {

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