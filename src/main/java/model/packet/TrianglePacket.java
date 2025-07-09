package model.packet;


import model.Type;
import model.port.Port;

public class TrianglePacket extends Packet {


    public TrianglePacket (){
        super();
        this.health = 3;
        value = health;
        this.type = Type.TRIANGLE;

    }





}
