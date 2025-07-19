package model.packet;

import model.Type;

public class BitPacket extends Packet {
    public MassivePacket fatherPacket;
    public BitPacket (MassivePacket packet){
        super();
        this.health = 1;
        value = 0;
        this.type = Type.BIT;
        this.insideSystem = true;
        this.fatherPacket = packet;
        this.vpn = fatherPacket.isVpn();

    }
}
