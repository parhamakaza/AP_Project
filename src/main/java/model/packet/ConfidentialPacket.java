package model.packet;

import model.Type;

public class ConfidentialPacket extends Packet{

    public ConfidentialPacket (){
        super();
        this.size = 4;
        value = 3;
        this.type = Type.CONFIDENTIAL;
    }


}
