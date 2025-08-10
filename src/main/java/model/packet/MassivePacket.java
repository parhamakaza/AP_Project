package model.packet;

import model.Type;

public class MassivePacket extends Packet {
    public MassivePacket madedFrom;
    public int aliveChildren = 0;
    public int insideMergerChildren = 0;



    public MassivePacket() {
        super();
        this.size = 8;
        value = size;
        this.type = Type.MASSIVE;

    }

    public void setSize(int size) {
        this.size = size;
    }

    public MassivePacket(int size , MassivePacket father) {
        this();
        this.size =size;
        value = size;
        madedFrom = father;

    }

}