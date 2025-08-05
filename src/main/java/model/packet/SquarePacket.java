package model.packet;


import model.Type;


public class SquarePacket extends Packet {

    public SquarePacket (){
        super();
        size = 2;
        value = 2;
        this.type = Type.SQUARE;
    }

}
