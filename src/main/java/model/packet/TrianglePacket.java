package model.packet;


import model.port.Port;

public class TrianglePacket extends Packet {


    public TrianglePacket (Port sPort){
        super(sPort);
        this.health = 3;
    }





}
