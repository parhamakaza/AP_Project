package model.packet;

import controller.PacketContoller;
import manager.GameManager;
import model.Type;
import model.wire.Wire;

import java.io.Serializable;


public abstract class Packet implements Serializable {
    public static final double SIZE = 20;
    public static int theID = 0;
    public int id;
    public Wire wire;
    public double x;
    public double y;
    public int value;
    public boolean insideSystem = false;
    protected boolean trozhan = false;
    public double deflectedX = 0;
    public double deflectedY = 0;
    protected int size;
    protected int noise = 0;

    public void increaseNoize(){
        noise += 1;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setNoise(int noise) {
        this.noise = noise;
    }

    public void  checkToKill(){
        if(noise >= size){
            PacketContoller.killPacket(this);
        }
    }

    public int getSize() {
        return size;
    }

    public int getNoise() {
        return noise;
    }

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
        if (noise == 0){

            return false;
        }
        return true;

    }


}
