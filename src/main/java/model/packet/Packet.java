package model.packet;

import manager.GameManager;
import model.Type;
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
    protected boolean trozhan = false;
    public double deflectedX = 0;
    public double deflectedY = 0;


    public boolean isVpn() {
        return vpn;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setVpn(boolean vpn) {
        this.vpn = vpn;
    }

    protected boolean vpn = false;

    public Type getType() {
        return type;
    }

    protected Type type;

    public void setTrozhan(boolean trozhan) {
        this.trozhan = trozhan;
    }

    public boolean isTrozhan() {
        return trozhan;
    }

    public Packet() {
        theID++;
        this.id = theID;
        GameManager.lvl.packets.add(this);
    }


    public boolean isDamged(){
        if (this.health == this.value){
            return false;
        }else {
            return true;
        }
    }


}
