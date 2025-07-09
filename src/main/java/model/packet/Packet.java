package model.packet;

import manager.LevelManager;
import model.wire.Wire;


public abstract class Packet {
    public static final double SIZE = 20;
    public static int theID = 0;
    public int id;
    public Wire wire;
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

    public Packet() {
        theID++;
        this.id = theID;
        LevelManager.lvl.packets.add(this);

    }


}
