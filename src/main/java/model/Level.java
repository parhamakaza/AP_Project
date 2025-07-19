package model;



import model.computer.Computer;
import model.packet.Packet;
import java.util.ArrayList;
import java.util.List;


public class Level {
    public double currentTime = 0;
    public int generatedPackets = 0;
    public int lostPackets = 0;
    public int coins = 10;
    public double wireLength ;
    public List<Computer> comps = new ArrayList<>();
    public List<Packet> packets = new ArrayList<>();
    public Level(){

    }
    public Level(double x){
            wireLength = x;
    }




}
