package model;



import manager.LevelManager;
import model.computer.Computer;
import model.packet.Packet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Level implements Serializable {
    ;
    public int generatedPackets = 0;
    public int lostPackets = 0;
    public int coins = 10;
    public final double initialWireLength;

    public double getTime() {
        return time;
    }

    public double wireLength ;
    private double time = 0;
    public List<Computer> comps = new ArrayList<>();
    public List<Packet> packets = new ArrayList<>();

    public Level(double x){
        initialWireLength = x;
        wireLength = x;
        LevelManager.lvl = this;
    }
    public void increaseTime(){
        time += 0.1;

    }

}
