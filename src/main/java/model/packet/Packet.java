package model.packet;

import controller.PacketContoller;
import manager.LevelManager;
import manager.packets.PacketState;
import model.Type;
import model.wire.Wire;

import java.io.Serializable;


public abstract class Packet implements Serializable {
    public static final double SIZE = 20;
    public static final double STANDARDSPEED = 80.0;
    public static int theID = 0;
    public int id;
    public Wire wire;
    public double x;
    public double y;
    public int value;
    public boolean insideSystem = true;
    protected boolean trozhan = false;
    public double deflectedX = 0;
    public double deflectedY = 0;
    public double distanceTravled = 0;
    protected int size;
    protected int noise = 0;
    protected double speed = STANDARDSPEED;
    protected double packetStartTime;

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setState(PacketState state) {
        this.state = state;
    }

    public PacketState getState() {
        return state;
    }

    protected PacketState state = PacketState.FORWARD;

    public void increaseNoise(){
        noise += 1;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setNoise(int noise) {
        this.noise = noise;
    }

    public void setPacketStartTime(double packetStartTime) {
        this.packetStartTime = packetStartTime;
    }

    public double getPacketStartTime() {
        return packetStartTime;
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
        LevelManager.lvl.packets.add(this);
    }


    public boolean isDamged(){
        return noise != 0;
    }

    public void resetSpeed(){
        speed = STANDARDSPEED;

    }


}
