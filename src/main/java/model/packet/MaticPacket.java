package model.packet;

import model.Type;

public class MaticPacket extends Packet {

    public MaticPacket (){
        super();
        this.health = 2;
        value = health;
        this.type = Type.MATIC;

    }

}
