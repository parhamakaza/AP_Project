package manager.computers;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import model.computer.Computer;
import model.packet.Packet;

import java.util.HashMap;
import java.util.Map;

public abstract class ComputerManager {
    public static Map<Computer , ComputerManager> computerManagerMap = new HashMap<>();
    public Computer computer;
    public Timeline timeline = new Timeline();

    public Computer getComputer() {
        return computer;
    }

    public ComputerManager(Computer computer){
        this.computer = computer;
        computerManagerMap.put(computer , this);
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void takePacket (Packet packet ){
        packet.insideSystem= true;
        this.computer.packets.add(packet);
    }

    abstract void transfer();


}
