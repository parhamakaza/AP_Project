package manager.Computers;

import javafx.animation.KeyFrame;
import javafx.util.Duration;
import manager.TransformerManager;
import model.computer.Computer;
import model.computer.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpyManager extends ComputerManager {
    private static Random random = new Random();
    public static List<Spy> spyList = new ArrayList<>();
    public SpyManager(Computer computer){
        super(computer);
        spyList.add((Spy) this.getComputer());
        transfer();
    }


    public void transfer(){
        KeyFrame keyFramee = new KeyFrame(
                // This is the TIMING. Change this duration to what you need (e.g., Duration.millis(500)).
                Duration.seconds(1), e -> {
            TransformerManager.transferPacket(this.getComputer());
        });
        timeline.getKeyFrames().add(keyFramee);
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