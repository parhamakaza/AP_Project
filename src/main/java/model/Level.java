package model;



import manager.GameManager;
import model.computer.Computer;
import model.packet.Packet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Level implements Serializable {
    public double currentTime = 0;
    public int generatedPackets = 0;
    public int lostPackets = 0;
    public int coins = 10;
    public final double initialWireLength;
    public double wireLength ;
    public List<Computer> comps = new ArrayList<>();
    public List<Packet> packets = new ArrayList<>();

    public Level(double x){
        initialWireLength = x;
        wireLength = x;
        GameManager.lvl = this;
    }

}
