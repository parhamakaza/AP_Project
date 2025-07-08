package model.packet;

import manager.LevelManager;
import model.port.Port;
import model.wire.Wire;


public abstract class Packet {
    public static final int SIZE = 20;
    public static int theID = 0;
    public int id;
    public Wire wire;
    public Port sPort;
    public Port ePort;
    public double x;
    public double y;
    public int health;
    public int value;
    public boolean insideSystem = false;
    private boolean trozhan = false;

    public void setTrozhan(boolean trozhan) {
        this.trozhan = trozhan;
    }

    public boolean isTrozhan() {
        return trozhan;
    }

    public Packet(Port sPort){
        this.sPort = sPort;
        theID++;
        this.id= theID;
        LevelManager.lvl.packets.add(this);
        this.sPort=sPort;
        this.wire = sPort.wire;
        this.ePort = sPort.wire.ePort;
        this.x = sPort.x;
        this.y = sPort.y;
    }


}
