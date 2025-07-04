package model.packet;


import model.port.Port;

public class SquarePacket extends Packet {

    public SquarePacket (Port sPort){
        super(sPort);
        this.health = 2;
    }


}
