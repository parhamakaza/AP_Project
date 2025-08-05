package model.packet;

import model.Type;

public class MaticPacket extends Packet {

    public MaticPacket (){
        super();
        size = 2;
        value = 2;
        type = Type.MATIC;

    }

}
