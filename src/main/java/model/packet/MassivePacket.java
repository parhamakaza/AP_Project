package model.packet;

import model.Type;

public class MassivePacket extends Packet {

    public void reduceSize() {
        size = size - 1;

    }




    public MassivePacket() {
        super();
        this.size = 8;
        value = size;
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
