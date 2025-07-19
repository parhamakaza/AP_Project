package model.packet;

import model.Type;

public class MassivePacket extends Packet {
    private int size = 8;

    public void reduceSize() {
        size = size - 1;

    }


    public int getSize() {
        return size;
    }

    public MassivePacket() {
        super();
        this.health = 8;
        value = health;
        this.type = Type.MASSIVE;

    }

    public void setSize(int size) {
        this.size = size;
    }

    public MassivePacket(int size) {
        this();
        this.size =size;

    }

}
