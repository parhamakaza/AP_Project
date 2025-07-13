package model.packet;

import model.Type;

public class ConfidentialPacket extends Packet{
    private static final int INITAL_HEALTH = 4;

    public ConfidentialPacket (){
        super();
        this.health = 4;
        value = 3;
        this.type = Type.Confidential;
    }
    @Override
    public boolean isDamged(){
        return health < INITAL_HEALTH;
    }
}
