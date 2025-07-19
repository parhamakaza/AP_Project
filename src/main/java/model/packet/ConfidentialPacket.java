package model.packet;

import model.Type;

public class ConfidentialPacket extends Packet{
    private static final int INITIAL_HEALTH = 4;

    public ConfidentialPacket (){
        super();
        this.health = 4;
        value = 3;
        this.type = Type.CONFIDENTIAL;
    }

    @Override
    public boolean isDamged(){
        return health < INITIAL_HEALTH;
    }
}
