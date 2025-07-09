package manager.computers;

import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.computer.Computer;

public class TransformerManager extends ComputerManager {
    public TransformerManager(Computer computer) {
        super(computer);
        transfer();
    }

    @Override
    public void transfer(){
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event ->  standardtransfer());
        timeline.getKeyFrames().add(keyFrame);
    }





}
