package model;



import manager.LevelManager;
import model.computer.Computer;
import model.packet.MassivePacket;
import model.packet.Packet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Level implements Serializable {

    private int generatedPackets = 0;
    private int lostPackets = 0;
    public int coins = 10;
    public final double initialWireLength;

    public double getTime() {
        return time;
    }

    public double wireLength ;
    private double time = 0;
    public List<Computer> comps = new ArrayList<>();
    public List<Packet> packets = new ArrayList<>();
    public List<MassivePacket> reachedMassivePackets =  new ArrayList<>();

    public Level(double x){
        initialWireLength = x;
        wireLength = x;
        LevelManager.lvl = this;
    }

    public void increaseTime(){
        time += 0.1;

    }
    public void increaseLostPacket(int lost){
        lostPackets += lost;

    }
    public void increaseGemeratedPacket(int generatedPacketSize){
        generatedPackets += generatedPacketSize;

    }

    public double calculateSimplePacketLoss(){
        if (generatedPackets == 0) {
            return 0.0;
        }
        return ((double) lostPackets / generatedPackets ) * 100;
    }


    public void calculateComplicatedPacketLoss(){



    }



}
