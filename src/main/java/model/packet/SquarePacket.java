package model.packet;


import model.Type;


public class SquarePacket extends Packet {

    public SquarePacket (){
        super();
        this.health = 2;
        value = health;
        this.type = Type.SQUARE;
    }

}
