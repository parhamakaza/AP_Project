package model;



import java.util.ArrayList;


public class Level {
    public double currentTime = 0;
    public int generatedPackets = 0;
    public int lostPackets = 0;
    public int coins = 10;
    public double wireLength ;
    public ArrayList<Computer> comps = new ArrayList<>();
    public ArrayList<Packet> packets = new ArrayList<>();
    public Level(){

    }
    public Level(double x){
            wireLength = x;
    }




}
