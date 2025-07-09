package model.packet;


import model.port.Port;

public class SquarePacket extends Packet {

    public SquarePacket (){
        super();
        this.health = 2;
        value = health;
    }


}
